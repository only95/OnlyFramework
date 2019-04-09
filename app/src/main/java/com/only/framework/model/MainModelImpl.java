package com.only.framework.model;

import android.support.v4.app.Fragment;

import com.only.framework.fragment.Fragment1;
import com.only.framework.fragment.Fragment2;
import com.only.framework.fragment.Fragment3;
import com.only.framework.fragment.Fragment4;
import com.only.framework.fragment.Fragment5;
import com.only.framework.model.Interface.IMainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2019/1/4.
 */

public class MainModelImpl implements IMainModel {
    @Override
    public List<Fragment> addFragment() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());
        fragmentList.add(new Fragment4());
        fragmentList.add(new Fragment5());
        return fragmentList;
    }
}
