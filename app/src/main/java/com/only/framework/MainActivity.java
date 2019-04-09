package com.only.framework;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import java.util.List;


import com.only.framework.adapter.FragmentViewPagerAdapter;
import com.only.framework.presenter.MainPresenter;
import com.only.framework.utlis.BottomNavigationViewHelper;
import com.only.framework.view.BaseActivity;
import com.only.framework.view.Interface.IMainView;

public class MainActivity extends BaseActivity<IMainView, MainPresenter<IMainView>>
        implements IMainView, BottomNavigationView.OnNavigationItemSelectedListener {


    private BottomNavigationView navigationView;
    private FragmentViewPagerAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        BottomNavigationViewHelper.disableShiftMode(navigationView);//适配当item大于5的时候保持不乱
        navigationView.setOnNavigationItemSelectedListener(this);//监听BottomNavigationView Item事件
        basePresenter.setAddFragment();//调用Presenter层中的setAddFragment方法

        //禁止划动
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                return true;
//            }
//        });

    }

    /**
     * 实现抽象接口 返回指定Presenter层
     *
     * @return
     */
    @Override
    protected MainPresenter<IMainView> createPresenter() {
        return new MainPresenter<>();
    }

    /**
     * 添加绑定组件
     */
    private void initView() {
        viewPager = $(R.id.viewPager_layout);
        navigationView = $(R.id.navigationView);
    }

    /**
     * 对BottomNavigationView Item监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                viewPager.setCurrentItem(0);
                break;
            case R.id.home2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.home3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.home4:
                viewPager.setCurrentItem(3);
                break;
            case R.id.home5:
                viewPager.setCurrentItem(4);
                break;
        }
        return true;
    }

    /**
     * 实现IMainView中getFragment的方法回调过来的的fragmentList，然后添加到FragmentViewPagerAdapter适配器中
     *
     * @param fragmentList
     */
    @Override
    public void getFragment(List<Fragment> fragmentList) {
        //实现对象FragmentViewPagerAdapter适配器
        adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);//往ViewPager中添加FragmentViewPagerAdapter适配器对象
        /**
         * 对viewPager的监听
         * 当按住页面并往左拖动并且手指未松开时，先执行onPageScrollStateChanged(int state),此时，state=1，
         * 然后执行onPageScrolled(int position, float positionOffset, int positionOffsetPixels)，
         * 此时该方法的三个参数分别为position=0，positionOffset从0.0开始逐渐增加但始终小于1，positionOffsetPixels从0开始增加，但始终小于屏幕宽度的分辨率
         */
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             *
             * @param position
             * @param positionOffset
             * @param positionOffsetPixels
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /**
             * 当ViewPager页面可见时，执行
             * 获取当前fragment下标位置
             * @param position
             */
            @Override
            public void onPageSelected(int position) {
                //当划动时navigationView跟着移动到当前位置
                navigationView.getMenu().getItem(position).setChecked(true);
            }

            /**
             *当按住页面并往左拖动并且手指未松开时
             * @param state
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);//默认下标为0的fragment
    }
}
