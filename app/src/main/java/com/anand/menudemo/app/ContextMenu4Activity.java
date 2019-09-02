package com.anand.menudemo.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.anand.menudemo.R;

public class ContextMenu4Activity extends AppCompatActivity {

    private static final String TAG = "ContextMenu4Activity";

    private Button btnContextMode;

    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu4);
        
        initView();             //初始化视图
        initData();             //初始化数据
        initEvent();            //初始化事件
    }

    public static void actionStart(Context context){
        Intent intent = new Intent(context,ContextMenu4Activity.class);
        context.startActivity(intent);
    }

    private void initView() {
        btnContextMode = findViewById(R.id.btn_contextMode);
    }

    private void initData() {
    }

    private void initEvent() {
        btnContextMode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(actionMode == null){
                    actionMode = startSupportActionMode(callback);
                    v.setSelected(true);        //设置View为选中状态
                    return true;
                }
                return false;
            }
        });
    }

    private ActionMode.Callback callback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.context_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.context_menu_add:
                    Toast.makeText(ContextMenu4Activity.this, "点击了添加按钮", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.context_menu_del:
                    Toast.makeText(ContextMenu4Activity.this, "点击了删除按钮", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.context_menu_save:
                    Toast.makeText(ContextMenu4Activity.this, "点击了保存按钮", Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };
}
