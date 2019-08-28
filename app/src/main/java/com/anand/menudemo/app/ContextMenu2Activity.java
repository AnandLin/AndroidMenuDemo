package com.anand.menudemo.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.anand.menudemo.R;
import com.anand.menudemo.adapter.QuickAdapter;
import com.anand.menudemo.view.BaseBoostRecycleView;
import com.anand.menudemo.view.RecyclerViewWithContextMenu;

import java.util.ArrayList;
import java.util.List;

public class ContextMenu2Activity extends AppCompatActivity {


    private BaseBoostRecycleView recyclerView;
    private QuickAdapter mAdapter;

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
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mAdapter = new QuickAdapter(R.layout.item_recyclerview,createDataList());
        recyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        recyclerView.setChoiceMode(BaseBoostRecycleView.CHOICE_MODE_MULTIPLE_MODAL);
    }

    private void initEvent() {
        recyclerView.setMultiChoiceModeListener(new BaseBoostRecycleView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                mode.setTitle("已选中："+recyclerView.getCheckedItemCount()+"项目");
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.context_menu,menu);
                return false;
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
                        Toast.makeText(ContextMenu2Activity.this, "点击了添加", Toast.LENGTH_SHORT).show();
                        mode.finish();
                        return true;
                    case R.id.context_menu_del:
                        Toast.makeText(ContextMenu2Activity.this, "点击了删除", Toast.LENGTH_SHORT).show();
                        mode.finish();
                        return true;
                    case R.id.context_menu_save:
                        Toast.makeText(ContextMenu2Activity.this, "点击了保存", Toast.LENGTH_SHORT).show();
                        mode.finish();
                        return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                //在上下文操作栏被移除时会触发，可以对Activity做一些必要的更新
                //默认情况下，此时所有的选中项将会被取消选中
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
