package com.anand.menudemo.bean;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MotionEvent;

import androidx.recyclerview.selection.ItemDetailsLookup;

/**
 * @ProjectName: MenuDemo
 * @Package: com.anand.menudemo.bean
 * @ClassName: StringItemDetails
 * @Description: java类作用描述
 * @Author: AnandLin
 * @CreateDate: 2019/9/5 10:44
 * @UpdateUser: AnandLin
 * @UpdateDate: 2019/9/5 10:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class StringItemDetails extends ItemDetailsLookup.ItemDetails<String> {

    private int position;
    private String selectionKey;

    public StringItemDetails(int position, String selectionKey) {
        this.position = position;
        this.selectionKey = selectionKey;
    }


    @Override
    public int getPosition() {
        return position;
    }

    @Nullable
    @Override
    public String getSelectionKey() {
        return selectionKey;
    }
}
