package com.anand.menudemo.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;

import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;

import com.anand.menudemo.bean.StringItemDetails;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @ProjectName: MenuDemo
 * @Package: com.anand.menudemo.adapter
 * @ClassName: SelectionStringAdapter
 * @Description: java类作用描述
 * @Author: AnandLin
 * @CreateDate: 2019/9/5 10:52
 * @UpdateUser: AnandLin
 * @UpdateDate: 2019/9/5 10:52
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SelectionStringAdapter extends BaseQuickAdapter<String, SelectionStringAdapter.SelectionStringViewHolder> {

    private SelectionTracker mSelectionTracker;

    public SelectionStringAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        //明确指出此适配器的每个项目将具有类型的唯一稳定标识符非常重要Long。
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    protected void convert(@NonNull SelectionStringViewHolder helper, String item) {
        helper.tv.setText(item);
        if(mSelectionTracker != null){
            if(mSelectionTracker.isSelected(item)){
                helper.getConvertView().setBackgroundColor(Color.parseColor("#80deea"));
                if(helper.tv instanceof CheckedTextView){
                    ((CheckedTextView)helper.tv).setChecked(true);
                }
            }else {
                helper.getConvertView().setBackgroundColor(Color.WHITE);
                if(helper.tv instanceof CheckedTextView){
                    ((CheckedTextView)helper.tv).setChecked(false);
                }
            }
        }
    }

    @Override
    protected void convertPayloads(@NonNull SelectionStringViewHolder helper, String item, @NonNull List<Object> payloads) {
        //局部刷新
        if(mSelectionTracker != null){
            if(mSelectionTracker.isSelected(item)){
                helper.getConvertView().setBackgroundColor(Color.parseColor("#80deea"));
                if(helper.tv instanceof CheckedTextView){
                    ((CheckedTextView)helper.tv).setChecked(true);
                }
            }else {
                helper.getConvertView().setBackgroundColor(Color.WHITE);
                if(helper.tv instanceof CheckedTextView){
                    ((CheckedTextView)helper.tv).setChecked(false);
                }
            }
        }
    }

    public void setSelectionTracker(SelectionTracker mSelectionTracker) {
        this.mSelectionTracker = mSelectionTracker;
    }

    public class SelectionStringViewHolder extends BaseViewHolder{

        TextView tv;

        public SelectionStringViewHolder(View view) {
            super(view);
            tv = view.findViewById(android.R.id.text1);
        }

        public ItemDetailsLookup.ItemDetails getItemDetails(){
            return new StringItemDetails(getAdapterPosition(),getData().get(getAdapterPosition()));
        }
    }

}
