package com.anand.menudemo.fragmet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.ViewConfiguration;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ProjectName: AndroidMenuDemo
 * @Package: com.anand.menudemo.app
 * @ClassName: BaseFragmet
 * @Description: TODO
 * @Author: Anand
 * @CreateDate: 2019/9/2 21:50
 * @UpdateUser: Anand
 * @UpdateDate: 2019/9/2 21:50
 * @UpdateRemark: TODO:更新说明
 * @Version: 1.0
 */
public class BaseFragmet extends Fragment {

    private static final String TAG = "BaseFragmet";

    /**
     * 要让Fragment中的菜单项显示出来，还需要在Fragment中调用setHasOptionsMenu(true)方法。
     * 传入true作为参数表明Fragment需要加载菜单项。
     * 建议在Fragment的onCreate方法中调用这个方法
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        Log.i(TAG, "onPrepareOptionsMenu: ");
        if(menu != null) {
            setIconEnable(menu, true);
        }
        super.onPrepareOptionsMenu(menu);
    }

    /**
     * Icon显示（Android 4.0以上版本需要反射方式方可显示图标，4.0之前不需要）
     * @param menu
     * @param enable
     */
    protected void setIconEnable(Menu menu, boolean enable) {
        if (menu != null) {
            //实现菜单title与Icon同时显示效果
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, enable);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 部分实体机因为实体菜单键Menu导致actionbar上的三个点不显示
     * 方案：通过反射，设置Menu实体菜单键是否可用
     * 该方法在OnCreate()调用
     * @param enable false，实体键不可用，会在actionbar上显示小点
     *               true，实体键可用，点击实体键才会显示menuItem
     */
    private void setHasPermanentMenuKey(boolean enable){
        try {
            ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if(menuKeyField != null){
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(viewConfiguration,enable);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
