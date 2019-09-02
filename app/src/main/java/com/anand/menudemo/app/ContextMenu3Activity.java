package com.anand.menudemo.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StableIdKeyProvider;
import androidx.recyclerview.selection.StorageStrategy;

import com.anand.menudemo.R;
import com.anand.menudemo.adapter.SelectionQuickAdapter;
import com.anand.menudemo.bean.MyItemDetailsLookup;

import java.util.ArrayList;
import java.util.List;

public class ContextMenu3Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SelectionQuickAdapter mAdapter;

    private SelectionTracker mSelectionTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu3);

        initView();
        initData();
        initEvent();

        if (savedInstanceState != null)
            mSelectionTracker.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(outState != null && mSelectionTracker != null){
            mSelectionTracker.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onBackPressed() {
        if (mSelectionTracker.hasSelection()){
            mSelectionTracker.clearSelection();
            return;
        }
        super.onBackPressed();
    }

    public static void actionStart(Context context){
        Intent intent = new Intent(context,ContextMenu3Activity.class);
        context.startActivity(intent);
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        //尺寸大小确定，可以设置该参数，避免重新计算大小
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
//        mAdapter = new SelectionQuickAdapter(R.layout.item_recyclerview,createDataList());
        mAdapter = new SelectionQuickAdapter(android.R.layout.simple_list_item_multiple_choice,createDataList());
        recyclerView.setAdapter(mAdapter);

    }

    private void initData() {
        //创建选择跟踪器
        mSelectionTracker = new SelectionTracker.Builder(
                "mySelection",
                recyclerView,
                new StableIdKeyProvider(recyclerView),      //密钥提供者
                new MyItemDetailsLookup(recyclerView),
                StorageStrategy.createLongStorage())
                .withSelectionPredicate(SelectionPredicates.<Long>createSelectAnything())
                .build();
        mAdapter.setSelectionTracker(mSelectionTracker);

    }

    private void initEvent() {
        //创建选择观察器
        mSelectionTracker.addObserver(new SelectionTracker.SelectionObserver() {
            @Override
            public void onItemStateChanged(@NonNull Object key, boolean selected) {
                super.onItemStateChanged(key, selected);
            }

            @Override
            public void onSelectionRefresh() {
                super.onSelectionRefresh();
            }

            @Override
            public void onSelectionChanged() {
                super.onSelectionChanged();
                int nItems = mSelectionTracker.getSelection().size();
                String title;
                if(nItems > 0){
                    title = "%1$d items selected";
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ef6c00")));
                }else {
                    title = getResources().getString(R.string.app_name);
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
                }
                getSupportActionBar().setTitle(String.format(title,nItems));
            }

            @Override
            public void onSelectionRestored() {
                super.onSelectionRestored();
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
