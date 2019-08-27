package com.anand.menudemo.app;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.anand.menudemo.R;

public class OptionMenuActivity extends BaseActivity {

    private static final String TAG = "OptionMenuActivity";

    private MenuItem searchMenuItem;            //搜索按钮
    private MenuItem shareMenuItem;             //分享按钮
    private MenuItem collectMenuItem;           //收藏按钮
    private MenuItem previousMenuItem;          //上一步按钮
    private MenuItem nextMenuItem;              //下一步按钮
    private MenuItem allMenuItem;               //复选按钮组



    private boolean isShowNext = true;                 //是否显示下一步


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu);

        initView();         //初始化视图
        initData();         //初始化数据
        initEvent();        //初始化事件
    }

    public static void actionStart(Context context){
        Intent intent = new Intent(context,OptionMenuActivity.class);
        context.startActivity(intent);
    }

    /**
     * 初始化视图
     */
    private void initView() {
    }

    /**
     * 初始化数据
     */
    private void initData() {
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
    }

    /**
     * 创建选项菜单事件
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(TAG, "onCreateOptionsMenu: ");
        //创建选项菜单
        getMenuInflater().inflate(R.menu.option_menu,menu);
        //搜索功能
        searchMenuItem = menu.findItem(R.id.menu_search);
        searchMenuItem.setOnActionExpandListener(onActionExpandListener);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OptionMenuActivity.this, "点击了搜索按钮", Toast.LENGTH_SHORT).show();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(OptionMenuActivity.this, "提交内容为："+query, Toast.LENGTH_SHORT).show();
                //TODO 处理搜索的结果逻辑
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i(TAG,"输入内容:"+newText);
                return false;
            }
        });
        //分享功能
        shareMenuItem = menu.findItem(R.id.menu_share);
        ShareActionProvider shareProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareMenuItem);
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
        share_intent.setType("text/plain");//设置分享内容的类型
        share_intent.putExtra(Intent.EXTRA_SUBJECT, "标题");//添加分享内容标题
        share_intent.putExtra(Intent.EXTRA_TEXT, "内容");//添加分享内容
        shareProvider.setShareIntent(share_intent);
        //收藏功能
        collectMenuItem = menu.findItem(R.id.menu_collect);
        collectMenuItem.setOnActionExpandListener(onActionExpandListener);
        //上一步功能
        previousMenuItem = menu.findItem(R.id.menu_previous);
        //下一步功能
        nextMenuItem = menu.findItem(R.id.menu_next);
        //复选功能
        allMenuItem = menu.findItem(R.id.menu_all_check);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 选项菜单被选中事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG, "onOptionsItemSelected: ");
        switch (item.getItemId()){
            case R.id.menu_search:
                if(collectMenuItem != null && collectMenuItem.isActionViewExpanded()){
                   collectMenuItem.collapseActionView();
                }
                return true;
            case R.id.single_menu_01:
            case R.id.single_menu_02:
            case R.id.single_menu_03:
                item.setChecked(true);
                Toast.makeText(this, "单选按钮选中："+item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.all_menu_01:
            case R.id.all_menu_02:
            case R.id.all_menu_03:
                item.setChecked(!item.isChecked());
                if(allMenuItem != null){
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < allMenuItem.getSubMenu().size(); i++) {
                        if(allMenuItem.getSubMenu().getItem(i).isChecked()){
                            sb.append(allMenuItem.getSubMenu().getItem(i).getTitle());
                        }
                    }
                    Toast.makeText(this, "多选按钮选中："+sb.toString(), Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * 选项菜单显示前事件
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(menu != null){
            if(previousMenuItem != null && nextMenuItem != null){
                //上一步下一步可点击逻辑效果
                if(isShowNext){
                    previousMenuItem.setEnabled(false);
                    nextMenuItem.setEnabled(true);
                }else {
                    previousMenuItem.setEnabled(true);
                    nextMenuItem.setEnabled(false);
                }
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * 选项菜单打开后事件
     * @param featureId
     * @param menu
     * @return
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        Log.i(TAG, "onMenuOpened: ");
        resetMenuItem();
        return super.onMenuOpened(featureId, menu);
    }

    /**
     * 监听折叠按钮的展开关闭事件
     */
    MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
        @Override
        public boolean onMenuItemActionExpand(MenuItem item) {
            Log.i(TAG, "打开了折叠视图");
            if(previousMenuItem != null && nextMenuItem != null){
                previousMenuItem.setVisible(false);
                nextMenuItem.setVisible(false);
            }
            return true;
        }

        @Override
        public boolean onMenuItemActionCollapse(MenuItem item) {
            Log.i(TAG, "关闭了折叠视图");
            if(previousMenuItem != null && nextMenuItem != null){
                previousMenuItem.setVisible(true);
                nextMenuItem.setVisible(true);
            }
            return true;
        }
    };


    /**
     * 重置菜单
     */
    private void resetMenuItem(){
        //重置搜索菜单
        if(searchMenuItem != null && searchMenuItem.isActionViewExpanded()){
            searchMenuItem.collapseActionView();
        }
        //重置分享菜单
        if(shareMenuItem != null && shareMenuItem.isActionViewExpanded()){
            shareMenuItem.collapseActionView();
        }
        //重置收藏菜单
        if(collectMenuItem != null && collectMenuItem.isActionViewExpanded()){
            collectMenuItem.collapseActionView();
        }
    }

    /**
     * 上一步菜单点击事件
     * @param item
     */
    public void onPreviousMenu(MenuItem item){
        isShowNext = true;
        //通知系统刷新Menu
        invalidateOptionsMenu();
        Toast.makeText(this, "点击上一页菜单", Toast.LENGTH_SHORT).show();
    }

    /**
     * 下一步菜单点击事件
     * @param item
     */
    public void onNextMenu(MenuItem item){
        isShowNext = false;
        //通知系统刷新Menu
        invalidateOptionsMenu();
        Toast.makeText(this, "点击下一页菜单", Toast.LENGTH_SHORT).show();
    }
}
