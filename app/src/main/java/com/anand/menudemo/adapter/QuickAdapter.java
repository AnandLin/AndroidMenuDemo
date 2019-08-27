package com.anand.menudemo.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.anand.menudemo.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @ProjectName: MenuDemo
 * @Package: com.anand.menudemo.adapter
 * @ClassName: QuickAdapter
 * @Description: java类作用描述
 * @Author: AnandLin
 * @CreateDate: 2019/8/26 18:15
 * @UpdateUser: AnandLin
 * @UpdateDate: 2019/8/26 18:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class QuickAdapter extends BaseQuickAdapter<String,QuickAdapter.QuickAdapterViewHolder> {


    public QuickAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull QuickAdapterViewHolder helper, String item) {
        helper.tv.setText(item);
    }

    class QuickAdapterViewHolder extends BaseViewHolder{

        TextView tv;

        public QuickAdapterViewHolder(View view) {
            super(view);
            tv = view.findViewById(R.id.tv_data);
        }
    }
}
