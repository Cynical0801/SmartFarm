package com.example.smartfarm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingTemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_tem);

        Intent intent = getIntent();
        int targetTem = intent.getIntExtra("TargetTem", 30);    // 목표 온도
        TextView t1, tvTargetT, tvCurT, tvTem;// 온도
        Button btnReturn1, btnTmpDown, btnTmpUp;
        NumberPicker npcTarTmp;


        tvTargetT = (TextView) findViewById(R.id.tvTargetT);

        // 목표 온도값을 설정
        npcTarTmp = (NumberPicker) findViewById(R.id.npcTarTmp);
        npcTarTmp.setMinValue(0);
        npcTarTmp.setMaxValue(100);
        npcTarTmp.setValue(targetTem);

        // + 버튼을 누르면 값이 1씩 증가
        btnTmpUp = (Button) findViewById(R.id.btnTmpUp);
        btnTmpUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                npcTarTmp.setValue(npcTarTmp.getValue()+1);
            }
        });

        // - 버튼을 누르면 값이 1씩 감소
        btnTmpDown = (Button) findViewById(R.id.btnTmpDown);
        btnTmpDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                npcTarTmp.setValue(npcTarTmp.getValue()-1);
            }
        });

        // 종료 버튼
        btnReturn1 = (Button) findViewById(R.id.btnReturn1);
        btnReturn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
