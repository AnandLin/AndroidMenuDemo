package com.anand.menudemo.bean;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.selection.ItemDetailsLookup;

import com.anand.menudemo.adapter.SelectionQuickAdapter;
import com.anand.menudemo.adapter.SelectionStringAdapter;

/**
 * @ProjectName: MenuDemo
 * @Package: com.anand.menudemo.bean
 * @ClassName: MyStringItemDetailsLookup
 * @Description: java类作用描述
 * @Author: AnandLin
 * @CreateDate: 2019/9/5 10:49
 * @UpdateUser: AnandLin
 * @UpdateDate: 2019/9/5 10:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MyStringItemDetailsLookup extends ItemDetailsLookup<String> {

    private RecyclerView recyclerView;

    public MyStringItemDetailsLookup(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }


    @Nullable
    @Override
    public ItemDetails<String> getItemDetails(@NonNull MotionEvent motionEvent) {
        View childViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if(childViewUnder != null){
            RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(childViewUnder);
            if(childViewHolder instanceof SelectionStringAdapter.SelectionStringViewHolder){
                return ((SelectionStringAdapter.SelectionStringViewHolder)childViewHolder).getItemDetails();
            }
        }
        return null;
    }
}
