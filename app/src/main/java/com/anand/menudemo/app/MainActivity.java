package com.anand.menudemo.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.anand.menudemo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnOptionMenu01;
    private Button btnOptionMenu02;
    private Button btnContextMenu;
    private Button btn_ContextMode;
    private Button btnSubMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();         //初始化视图
        initData();         //初始化数据
        initEvent();        //初始化事件
    }

    /**
     * 初始化视图
     */
    private void initView() {
        btnOptionMenu01 = findViewById(R.id.btn_OptionMenu01);
        btnOptionMenu02 = findViewById(R.id.btn_OptionMenu02);
        btnContextMenu = findViewById(R.id.btn_ContextMenu);
        btn_ContextMode = findViewById(R.id.btn_ContextMode);
    }

    /**
     * 初始化数据
     */
    private void initData() {
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        btnOptionMenu01.setOnClickListener(this);
        btnOptionMenu02.setOnClickListener(this);
        btnContextMenu.setOnClickListener(this);
        btn_ContextMode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_OptionMenu01:
                OptionMenuActivity.actionStart(MainActivity.this);
                break;
            case R.id.btn_OptionMenu02:
                OptionMenu2Activity.actionStart(MainActivity.this);
                break;
            case R.id.btn_ContextMenu:
                ContextMenuActivity.actionStart(MainActivity.this);
                break;
            case R.id.btn_ContextMode:
                ContextMenu2Activity.actionStart(MainActivity.this);
                break;
        }
    }
}
