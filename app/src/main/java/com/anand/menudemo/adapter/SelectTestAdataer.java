package com.anand.menudemo.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;

import com.anand.menudemo.bean.LongItemDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: MenuDemo
 * @Package: com.anand.menudemo.adapter
 * @ClassName: SelectTestAdataer
 * @Description: java类作用描述
 * @Author: AnandLin
 * @CreateDate: 2019/8/30 17:35
 * @UpdateUser: AnandLin
 * @UpdateDate: 2019/8/30 17:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SelectTestAdataer extends RecyclerView.Adapter<SelectTestAdataer.SelectTestViewHolder> {

    private SelectionTracker mSelectionTracker;

    /**
     * List of strings to be shown
     */
    private List<String> mDatas;

    private int mLayoutResId;

    public SelectTestAdataer(int layoutResId, @Nullable List<String> data){
        this.mDatas = data == null ? new ArrayList<String>() : data;
        if (layoutResId != 0) {
            this.mLayoutResId = layoutResId;
        }
        //明确指出此适配器的每个项目将具有类型的唯一稳定标识符非常重要Long。
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public SelectTestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutResId, parent, false);
        return new SelectTestViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(@NonNull SelectTestViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
        if(mSelectionTracker != null){
            if(mSelectionTracker.isSelected(position)){
                holder.itemView.setBackgroundColor(Color.parseColor("#80deea"));
            }else {
                holder.itemView.setBackgroundColor(Color.WHITE);
            }
        }
    }

    public void setSelectionTracker(SelectionTracker mSelectionTracker) {
        this.mSelectionTracker = mSelectionTracker;
    }

    public class SelectTestViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public SelectTestViewHolder(View view) {
            super(view);
            tv = view.findViewById(android.R.id.text1);
        }

        public ItemDetailsLookup.ItemDetails getItemDetails(){
            return new LongItemDetails(getAdapterPosition(),getItemId());
        }
    }
}
