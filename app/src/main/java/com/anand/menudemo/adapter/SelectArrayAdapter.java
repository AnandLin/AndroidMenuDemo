package com.anand.menudemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * @ProjectName: MenuDemo
 * @Package: com.anand.menudemo.adapter
 * @ClassName: SelectArrayAdapter
 * @Description: java类作用描述
 * @Author: AnandLin
 * @CreateDate: 2019/8/29 17:57
 * @UpdateUser: AnandLin
 * @UpdateDate: 2019/8/29 17:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SelectArrayAdapter<T> extends ArrayAdapter {
    public SelectArrayAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public SelectArrayAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public SelectArrayAdapter(@NonNull Context context, int resource, @NonNull T[] objects) {
        super(context, resource, objects);
    }

    public SelectArrayAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull T[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public SelectArrayAdapter(@NonNull Context context, int resource, @NonNull List<T> objects) {
        super(context, resource, objects);
    }

    public SelectArrayAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<T> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    /**
     * 如果需要获取统计数值，需要重写{@link android.widget.Adapter hasStableIds}方法，默认返回true
     * @return
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }
}
