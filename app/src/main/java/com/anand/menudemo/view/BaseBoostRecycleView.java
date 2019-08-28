package com.anand.menudemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.LongSparseArray;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * @ProjectName: MenuDemo
 * @Package: com.anand.menudemo.view
 * @ClassName: BaseBoostRecycleView
 * @Description: java类作用描述
 * @Author: AnandLin
 * @CreateDate: 2019/8/28 14:47
 * @UpdateUser: AnandLin
 * @UpdateDate: 2019/8/28 14:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseBoostRecycleView extends RecyclerView {

    /**
     * 正常效果，无选择效果
     */
    public static final int CHOICE_MODE_NONE = 0;
    /**
     * 该列表最多允许一个选项
     */
    public static final int CHOICE_MODE_SINGLE = 1;
    /**
     * 该列表允许多个选项
     */
    public static final int CHOICE_MODE_MULTIPLE = 2;
    /**
     * 该列表运行在模态选择墨色中选择
     */
    public static final int CHOICE_MODE_MULTIPLE_MODAL = 3;

    private int mChoiceMode = CHOICE_MODE_NONE;

    /**
     * 当前位置的勾选状态
     */
    private SparseBooleanArray mCheckStates;

    /**
     * 当前位置勾选Id的状态
     */
    private LongSparseArray<Integer> mCheckedIdStates;

    private int mCheckedItemCount;

    private  ActionMode mChoiceActionMode;

    private MultiChoiceModeWrapper mMultiChoiceModeCallback;

    private RecyclerViewContextMenuInfo mContextMenuInfo = new RecyclerViewContextMenuInfo();

    public BaseBoostRecycleView(Context context) {
        this(context,null);
    }

    public BaseBoostRecycleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseBoostRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setChoiceMode(CHOICE_MODE_NONE);
    }

    @Override
    public boolean showContextMenuForChild(View originalView, float x, float y) {
        getPositionByChild(originalView);
        return super.showContextMenuForChild(originalView, x, y);
    }

    @Override
    public boolean showContextMenuForChild(View originalView) {
        getPositionByChild(originalView);
        return super.showContextMenuForChild(originalView);
    }

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

    public void setChoiceMode(int choiceMode) {
        mChoiceMode = choiceMode;
        if (mChoiceActionMode != null) {
            mChoiceActionMode.finish();
            mChoiceActionMode = null;
        }
        if (mChoiceMode != CHOICE_MODE_NONE) {
            if (mCheckStates == null) {
                mCheckStates = new SparseBooleanArray(0);
            }
            if (mCheckedIdStates == null && getAdapter() != null && getAdapter().hasStableIds()) {
                mCheckedIdStates = new LongSparseArray<Integer>(0);
            }
            // 如果为选择模式清除数据
            if (mChoiceMode == CHOICE_MODE_MULTIPLE_MODAL) {
                clearChoices();
                setLongClickable(true);
            }
        }
    }

    /**
     *
     */
    public interface MultiChoiceModeListener extends ActionMode.Callback {
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked);
    }

    class MultiChoiceModeWrapper implements MultiChoiceModeListener {

        private MultiChoiceModeListener mWrapped;

        public void setWrapped(MultiChoiceModeListener wrapped) {
            mWrapped = wrapped;
        }

        public boolean hasWrappedCallback() {
            return mWrapped != null;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            if(mWrapped.onCreateActionMode(mode, menu)){
                setLongClickable(false);
                return true;
            }
            return false;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return mWrapped.onPrepareActionMode(mode, menu);
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return mWrapped.onActionItemClicked(mode, item);
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mWrapped.onDestroyActionMode(mode);
            mChoiceActionMode = null;

            //结束选择模式意味着取消所有的选择
            clearChoices();

        }

        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

            mWrapped.onItemCheckedStateChanged(mode, position, id, checked);
            //如果未选择任何选项即不需要选择模式
            if (getCheckedItemCount() == 0) {
                mode.finish();
            }
        }
    }

    /**
     * 清除之前选择选项
     */
    public void clearChoices() {
        if (mCheckStates != null) {
            mCheckStates.clear();
        }
        if (mCheckedIdStates != null) {
            mCheckedIdStates.clear();
        }
        mCheckedItemCount = 0;
    }

    /**
     * Set a {@link MultiChoiceModeListener} that will manage the lifecycle of the
     * selection {@link ActionMode}. Only used when the choice mode is set to
     * {@link #CHOICE_MODE_MULTIPLE_MODAL}.
     *
     * @param listener Listener that will manage the selection mode
     *
     * @see #setChoiceMode(int)
     */
    public void setMultiChoiceModeListener(MultiChoiceModeListener listener) {
        if (mMultiChoiceModeCallback == null) {
            mMultiChoiceModeCallback = new MultiChoiceModeWrapper();
        }
        mMultiChoiceModeCallback.setWrapped(listener);
    }

    /**
     * 获取选中数量
     * @return
     */
    public int getCheckedItemCount() {
        return mCheckedItemCount;
    }

}
