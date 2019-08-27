package com.anand.menudemo.app;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.ViewConfiguration;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ProjectName: MenuDemo
 * @Package: com.anand.menudemo
 * @ClassName: BaseActivity
 * @Description: java类作用描述
 * @Author: AnandLin
 * @CreateDate: 2019/8/26 10:53
 * @UpdateUser: AnandLin
 * @UpdateDate: 2019/8/26 10:53
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";


    /**
     * 选项菜单显示前事件
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.i(TAG, "onPrepareOptionsMenu: ");
        if(menu != null) {
            setIconEnable(menu, true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * 选项菜单关闭后事件
     * @param menu
     */
    @Override
    public void onOptionsMenuClosed(Menu menu) {
        Log.i(TAG, "onOptionsMenuClosed: ");
        super.onOptionsMenuClosed(menu);
    }

    /**
     * Icon显示（Android 4.0以上版本需要反射方式方可显示图标，4.0之前不需要）
     * @param menu
     * @param enable
     */
    protected void setIconEnable(Menu menu, boolean enable){
        if(menu != null){
            //实现菜单title与Icon同时显示效果
            if(menu.getClass().getSimpleName().equals("MenuBuilder")){
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible",Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu,enable);
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
            ViewConfiguration viewConfiguration = ViewConfiguration.get(this);
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
