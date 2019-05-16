package com.only.framework.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.only.framework.R;
import com.only.framework.activity.ExternalStorageDemo;
import com.only.framework.activity.InternalStorageDemo;
import com.only.framework.activity.QRCodeDemo;
import com.only.framework.activity.SQLiteDatabaseDemo;
import com.only.framework.adapter.Fragment5LayoutRecyclerView;
import com.only.framework.library.adapter.IRecyclerViewInterface;
import com.only.framework.library.adapter.RecyclerViewAdapter;
import com.only.framework.presenter.ListPresenter;
import com.only.framework.view.Interface.IListView;

import java.util.List;

/**
 * Created by asus on 2019/1/4.
 */

public class Fragment5 extends BaseFragment<IListView, ListPresenter<IListView>> implements IListView,View.OnClickListener {
    private View view;
    private RecyclerView recycler;
    private RecyclerViewAdapter<String,Fragment5LayoutRecyclerView> adapter;
    private IRecyclerViewInterface<Fragment5LayoutRecyclerView> recyclerViewInterface;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout5, container, false);
        initView();
        mFragmentPresenter.face();
        return view;
    }

    @Override
    protected ListPresenter<IListView> createPresenter() {
        return new ListPresenter<>();
    }


    private void initView() {
        recycler = view.findViewById(R.id.recycler);
    }


    @Override
    public void show(final List<String> list) {

    }

    @Override
    public void onClick(View view) {

    }
}
