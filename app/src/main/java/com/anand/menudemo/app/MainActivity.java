package com.anand.menudemo.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.anand.menudemo.R;
import com.anand.menudemo.fragmet.BaseFragmet;
import com.anand.menudemo.fragmet.OptionMenuFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout lllContianer;
    private FrameLayout flContianer;

    private Button btnOptionMenu01;
    private Button btnOptionMenu02;
    private Button btnContextMenu;
    private Button btnListViewContextMode;
    private Button btnRecycleViewContextMode;
    private Button btnSingleView;
    private Button btnPopupMenu;


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
        lllContianer = findViewById(R.id.ll_contianer);
        flContianer = findViewById(R.id.fragment_contianer);

        btnOptionMenu01 = findViewById(R.id.btn_OptionMenu01);
        btnOptionMenu02 = findViewById(R.id.btn_OptionMenu02);
        btnContextMenu = findViewById(R.id.btn_ContextMenu);
        btnListViewContextMode = findViewById(R.id.btn_ListView_ContextMode);
        btnRecycleViewContextMode = findViewById(R.id.btn_RecycleView_ContextMode);
        btnSingleView = findViewById(R.id.btn_single_view);
        btnPopupMenu = findViewById(R.id.btn_PopupMenu);
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
        btnListViewContextMode.setOnClickListener(this);
        btnRecycleViewContextMode.setOnClickListener(this);
        btnSingleView.setOnClickListener(this);
        btnPopupMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_OptionMenu01:
                OptionMenuActivity.actionStart(MainActivity.this);
                break;
            case R.id.btn_OptionMenu02:
//                OptionMenu2Activity.actionStart(MainActivity.this);
                replaceFragment(OptionMenuFragment.newInstance());
                break;
            case R.id.btn_ContextMenu:
                ContextMenuActivity.actionStart(MainActivity.this);
                break;
            case R.id.btn_ListView_ContextMode:
                ContextMenu2Activity.actionStart(MainActivity.this);
                break;
            case R.id.btn_RecycleView_ContextMode:
                ContextMenu3Activity.actionStart(MainActivity.this);
                break;
            case R.id.btn_single_view:
                ContextMenu4Activity.actionStart(MainActivity.this);
                break;
            case R.id.btn_PopupMenu:
                PopupMenuActivity.actionStart(MainActivity.this);
                break;
        }
    }

    private void replaceFragment(BaseFragmet fragmet){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_contianer,fragmet)
                .addToBackStack(null)
                .commit();
    }
}
