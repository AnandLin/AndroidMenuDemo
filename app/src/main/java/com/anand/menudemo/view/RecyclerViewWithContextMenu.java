package com.anand.menudemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.View;

/**
 * @ProjectName: MenuDemo
 * @Package: com.anand.menudemo.view
 * @ClassName: RecyclerViewWithContextMenu
 * @Description: java类作用描述
 * @Author: AnandLin
 * @CreateDate: 2019/8/27 13:51
 * @UpdateUser: AnandLin
 * @UpdateDate: 2019/8/27 13:51
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RecyclerViewWithContextMenu extends RecyclerView {

    private RecyclerViewContextMenuInfo mContextMenuInfo = new RecyclerViewContextMenuInfo();

    public RecyclerViewWithContextMenu(Context context) {
        super(context);
    }

    public RecyclerViewWithContextMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewWithContextMenu(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean showContextMenuForChild(View originalView) {
        getPositionByChild(originalView);
        return super.showContextMenuForChild(originalView);
    }

    @Override
    public boolean showContextMenuForChild(View originalView, float x, float y) {
        getPositionByChild(originalView);
        return super.showContextMenuForChild(originalView, x, y);
    }

    /**
     * 重写实现ContextMenuInfo返回，不然在onCreateContextMenu无法获取到menuInfo信息
     * @return
     */
    @Override
    protected ContextMenu.ContextMenuInfo getContextMenuInfo() {
        return mContextMenuInfo;
    }

    /**
     * 记录当前RecycleView中Item上下文菜单的Postion
     * @param originalView
     */
    private void getPositionByChild(View originalView){
        LayoutManager layoutManager = getLayoutManager();
        if(layoutManager != null){
            int position = layoutManager.getPosition(originalView);
            mContextMenuInfo.setPostion(position);
        }
    }

    public class RecyclerViewContextMenuInfo implements ContextMenu.ContextMenuInfo{
        private int mPostion = -1;

        public int getPostion() {
            return mPostion;
        }

        public void setPostion(int mPostion) {
            this.mPostion = mPostion;
        }
    }
}
