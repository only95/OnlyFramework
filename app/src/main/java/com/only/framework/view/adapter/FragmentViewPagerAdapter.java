package com.only.framework.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * ${tags}
 *
 * @Title: ${enclosing_method}
 * @author:wujun
 */
public class FragmentViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public FragmentViewPagerAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }
}
