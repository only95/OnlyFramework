package com.only.framework.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.only.framework.presenter.BasePresenter;

/**
 * Created by asus on 2019/1/5.
 */

public abstract class BaseFragment<V,T extends BasePresenter<V>> extends Fragment{

   public T mFragmentPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentPresenter=createPresenter();
        mFragmentPresenter.bindingPresenter((V) this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 定义一个抽象方法让Activity必须实现并返回指定P层Presenter
     * @return
     */
    protected abstract T createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFragmentPresenter.untyingPresenter();

    }
}
