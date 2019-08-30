package com.anand.menudemo.bean;

import android.support.annotation.Nullable;

import androidx.recyclerview.selection.ItemDetailsLookup;

/**
 * @ProjectName: MenuDemo
 * @Package: com.anand.menudemo.bean
 * @ClassName: LongItemDetails
 * @Description: java类作用描述
 * @Author: AnandLin
 * @CreateDate: 2019/8/30 11:33
 * @UpdateUser: AnandLin
 * @UpdateDate: 2019/8/30 11:33
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LongItemDetails extends ItemDetailsLookup.ItemDetails<Long> {
    private int position;
    private Long selectionKey;

    public LongItemDetails(int position, Long selectionKey) {
        this.position = position;
        this.selectionKey = selectionKey;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Nullable
    @Override
    public Long getSelectionKey() {
        return selectionKey;
    }
}
