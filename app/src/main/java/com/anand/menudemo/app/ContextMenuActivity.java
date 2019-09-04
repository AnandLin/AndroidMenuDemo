package com.anand.menudemo.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.anand.menudemo.R;
import com.anand.menudemo.adapter.QuickAdapter;
import com.anand.menudemo.view.RecyclerViewWithContextMenu;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class ContextMenuActivity extends BaseActivity {

    private RecyclerViewWithContextMenu recyclerView;
    private QuickAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        //尺寸大小确定，可以设置该参数，避免重新计算大小
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mAdapter = new QuickAdapter(R.layout.item_recyclerview,createDataList());
        recyclerView.setAdapter(mAdapter);
        //重写onCreateContextMenu方案一：
        registerForContextMenu(recyclerView);
    }

    private void initData() {
    }

    private void initEvent() {
        //RecycleView对象对onCreateContextMenu实现方案二：
        /*recyclerView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                if(menuInfo instanceof RecyclerViewWithContextMenu.RecyclerViewContextMenuInfo){
                    RecyclerViewWithContextMenu.RecyclerViewContextMenuInfo contextMenuInfo = (RecyclerViewWithContextMenu.RecyclerViewContextMenuInfo) menuInfo;
                    if(contextMenuInfo != null && contextMenuInfo.getPostion() >= 0){
                        menu.setHeaderTitle("点击："+mAdapter.getItem(contextMenuInfo.getPostion()));
                        getMenuInflater().inflate(R.menu.context_menu,menu);
                    }
                }
            }
        });*/
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(ContextMenuActivity.this, "点击菜单了"+adapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterForContextMenu(recyclerView);
    }

    public static void actionStart(Context context){
        Intent intent = new Intent(context,ContextMenuActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(menuInfo instanceof RecyclerViewWithContextMenu.RecyclerViewContextMenuInfo){
            RecyclerViewWithContextMenu.RecyclerViewContextMenuInfo contextMenuInfo = (RecyclerViewWithContextMenu.RecyclerViewContextMenuInfo) menuInfo;
            if(contextMenuInfo != null && contextMenuInfo.getPostion() >= 0){
                menu.setHeaderTitle("点击："+mAdapter.getItem(contextMenuInfo.getPostion()));
                getMenuInflater().inflate(R.menu.context_menu,menu);
            }
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getMenuInfo() instanceof RecyclerViewWithContextMenu.RecyclerViewContextMenuInfo){
            RecyclerViewWithContextMenu.RecyclerViewContextMenuInfo contextMenuInfo = (RecyclerViewWithContextMenu.RecyclerViewContextMenuInfo) item.getMenuInfo();
            if(contextMenuInfo != null && contextMenuInfo.getPostion() >= 0){
                switch (item.getItemId()){
                    case R.id.context_menu_add:
                        Toast.makeText(this,mAdapter.getItem(contextMenuInfo.getPostion())+":添加菜单被点击" , Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.context_menu_del:
                        Toast.makeText(this,mAdapter.getItem(contextMenuInfo.getPostion())+":删除菜单被点击" , Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.context_menu_save:
                        Toast.makeText(this,mAdapter.getItem(contextMenuInfo.getPostion())+":保存菜单被点击" , Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        }
        return super.onContextItemSelected(item);
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
