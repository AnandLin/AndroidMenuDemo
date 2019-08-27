package com.anand.menudemo.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.anand.menudemo.R;
import com.anand.menudemo.view.CircleSeekBar;

import java.util.Random;

public class DiyViewActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTest;
    private CircleSeekBar circleSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diy_view);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        btnTest = findViewById(R.id.btn_test);
        circleSeekBar = findViewById(R.id.circleSeekBar);
    }

    private void initData() {
    }

    private void initEvent() {
        btnTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_test:
                //随机设定圆环大小
                int i = new Random().nextInt(100) + 1;
                circleSeekBar.setProgress(i);
                break;
        }
    }
}
