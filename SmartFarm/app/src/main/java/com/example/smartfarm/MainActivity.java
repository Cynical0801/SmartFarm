package com.example.smartfarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView, btnTmp, btnHum, btnGrd, btnHeat, btnLeftDoor, btnRightDoor;
    ImageView imageView;
    Button button;
    Switch control;

    Handler handler = new Handler();
    int targetTem, targetHum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("SmartFarm");

        /*
        final String urlStr = "http://192.168.0.26/getCurrentData"; //현재 상태 받아오기 url

        new Thread(new Runnable() {
            @Override
            public void run() {
                request(urlStr);
            }
        }).start();
        */

        // 온도
        TextView btnTmp = findViewById(R.id.btnTmp);
        btnTmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*final String urlStr = "http://192.168.1.1/tmp/36.5"; //editText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(urlStr);
                    }
                }).start(); */
                Intent intent = new Intent(getApplicationContext(), SettingTemActivity.class);
                intent.putExtra("TargetTem", targetTem);
                startActivity(intent);
            }

        });

        // 습도
        TextView btnHum = findViewById(R.id.btnHum);
        btnHum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* final String urlStr = "http://192.168.1.1/hum/60"; //editText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(urlStr);
                    }
                }).start(); */

                Intent intent = new Intent(getApplicationContext(), SettingHumActivity.class);
                intent.putExtra("TargeHum", targetHum);
                startActivity(intent);
            }
        });

        // 팬
        TextView btnGrd = findViewById(R.id.btnGrd);
        btnGrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* final String urlStr = "http://192.168.1.1/grd/30"; //editText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(urlStr);
                    }
                }).start(); `*/
                Intent intent = new Intent(getApplicationContext(), SettingFanActivity.class);
                startActivity(intent);
            }
        });

        // 열풍기
        TextView btnHeat = findViewById(R.id.btnHeat);
        btnHeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String urlStr = "http://192.168.1.1/gettmp"; //editText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(urlStr);
                    }
                }).start();
            }
        });

        // 좌측 개폐기
        TextView btnLeftDoor = findViewById(R.id.btnLeftDoor);
        btnLeftDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String urlStr = "http://192.168.1.1/btnLeftDoor"; //editText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(urlStr);
                    }
                }).start();
            }
        });

        // 우측 개폐기
        TextView btnRightDoor = findViewById(R.id.btnRightDoor);
        btnRightDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String urlStr = "http://192.168.1.1/btnRightDoor"; //editText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(urlStr);
                    }
                }).start();
            }
        });

        btnTmp.setClickable(false);
        btnHum.setClickable(false);
        btnGrd.setClickable(false);
        btnHeat.setClickable(false);
        btnLeftDoor.setClickable(false);
        btnRightDoor.setClickable(false);

        //스위치
        Switch control = findViewById(R.id.control);
        control.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (control.isChecked()==true) {
                    btnTmp.setClickable(true);
                    btnHum.setClickable(true);
                    btnGrd.setClickable(true);
                    btnHeat.setClickable(true);
                    btnLeftDoor.setClickable(true);
                    btnRightDoor.setClickable(true);
                } else {
                    btnTmp.setClickable(false);
                    btnHum.setClickable(false);
                    btnGrd.setClickable(false);
                    btnHeat.setClickable(false);
                    btnLeftDoor.setClickable(false);
                    btnRightDoor.setClickable(false);
                }

            }
        });
    }

    // 서버 요청하기
    public void request(String urlStr) {
        StringBuilder output = new StringBuilder();
        try {
            URL url = new URL(urlStr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (conn != null) {
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                int resCode = conn.getResponseCode();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while (true) {
                    line = reader.readLine();
                    if (line == null) {
                        break;
                    }

                    output.append(line + "\n");
                }
                reader.close();
                conn.disconnect();
            }
        } catch (Exception ex) {
            println("예외 발생함 : " + ex.toString());
        }

        println("응답 -> " + output.toString());
    }

    public void println(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.append(data + "\n");
            }
        });

    }


}