package com.anand.menudemo.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.anand.menudemo.R;
import com.anand.menudemo.adapter.SelectArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class ContextMenu2Activity extends AppCompatActivity {

    private static final String TAG = "ContextMenu2Activity";


    private ListView listView;
    private  ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu2);

        initView();
        initData();
        initEvent();
    }

    public static void actionStart(Context context){
        Intent intent = new Intent(context,ContextMenu2Activity.class);
        context.startActivity(intent);
    }


    private void initView() {
        listView = findViewById(R.id.list_view);
        mAdapter = new SelectArrayAdapter<>(this,android.R.layout.simple_list_item_multiple_choice,createDataList());
//        mAdapter = new ArrayAdapter<>(this,R.layout.item_listview,R.id.tv_data,createDataList());
        listView.setAdapter(mAdapter);
    }

    private void initData() {
        //为Listview配置上下文操作模式
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
    }

    private void initEvent() {
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                //当列表中的项目选中或取消勾选时，这个方法会被触发
                //可以在这个方法中做一些更新操作，比如更改上下文操作栏的标题
                //这里显示已选中的项目数
                mode.setTitle("已选中："+listView.getCheckedItemCount()+"项");
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.context_menu,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                //可以对上下文操作栏做一些更新操作（会被ActionMode的invalidate方法触发）
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()){
                    case R.id.context_menu_add:
                        StringBuilder sb = new StringBuilder();
                        for (long id:listView.getCheckedItemIds()) {
                            sb.append(id);
                        }
                        Toast.makeText(ContextMenu2Activity.this, "点击了添加按钮"+sb.toString(), Toast.LENGTH_SHORT).show();
                        //关闭上下文操作栏
                        mode.finish();
                        return true;
                    case R.id.context_menu_del:
                        Toast.makeText(ContextMenu2Activity.this, "点击了删除按钮", Toast.LENGTH_SHORT).show();
                        //关闭上下文操作栏
                        mode.finish();
                        return true;
                    case R.id.context_menu_save:
                        Toast.makeText(ContextMenu2Activity.this, "点击了保存按钮", Toast.LENGTH_SHORT).show();
                        //关闭上下文操作栏
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                //在上下文操作栏被移除时会触发，可以对Activity做一些必要的更新
                //默认情况下，此时所有的选中项将会被取消选中

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ContextMenu2Activity.this, "点击了菜单", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //生成测试数据List
    private List<String> createDataList(){
        List<String> list=new ArrayList<>();
        for(int i=0;i<20;i++){
            list.add("测试条目"+i);
        }
        return list;
    }
}
