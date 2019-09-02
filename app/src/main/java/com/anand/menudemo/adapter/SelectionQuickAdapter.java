package com.anand.menudemo.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;

import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;

import com.anand.menudemo.R;
import com.anand.menudemo.bean.LongItemDetails;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @ProjectName: MenuDemo
 * @Package: com.anand.menudemo.adapter
 * @ClassName: SelectionQuickAdapter
 * @Description: java类作用描述
 * @Author: AnandLin
 * @CreateDate: 2019/8/30 11:18
 * @UpdateUser: AnandLin
 * @UpdateDate: 2019/8/30 11:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SelectionQuickAdapter extends BaseQuickAdapter<String, SelectionQuickAdapter.SelectionQickViewHolder> {

    private SelectionTracker mSelectionTracker;

    public SelectionQuickAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        //明确指出此适配器的每个项目将具有类型的唯一稳定标识符非常重要Long。
        setHasStableIds(true);
    }

    /**
     * 为了能够使用项目的位置作为其唯一标识符,需重写getItemId
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * BaseRecyclerViewAdapterHelper分首次刷新与局部刷新
     * 首次加载调用此接口刷新
     * @param helper
     * @param item
     */
    @Override
    protected void convert(@NonNull SelectionQickViewHolder helper, String item) {
        helper.tv.setText(item);
        if(mSelectionTracker != null){
            if(mSelectionTracker.isSelected(getItemId(helper.getLayoutPosition()))){
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

    /**
     * BaseRecyclerViewAdapterHelper分首次刷新与局部刷新
     * 已存在布局调用此接口刷新
     * @param helper
     * @param item
     * @param payloads
     */
    @Override
    protected void convertPayloads(@NonNull SelectionQickViewHolder helper, String item, @NonNull List<Object> payloads) {
        //局部刷新
        if(mSelectionTracker != null){
            if(mSelectionTracker.isSelected(getItemId(helper.getLayoutPosition()))){
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

    public class SelectionQickViewHolder extends BaseViewHolder{

        TextView tv;

        public SelectionQickViewHolder(View view) {
            super(view);
            tv = view.findViewById(android.R.id.text1);
        }

       public ItemDetailsLookup.ItemDetails getItemDetails(){
            return new LongItemDetails(getAdapterPosition(),getItemId());
        }
    }
}
