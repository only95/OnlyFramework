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
import com.only.framework.activity.SurfaceViewDemo;
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
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerViewInterface = new IRecyclerViewInterface<Fragment5LayoutRecyclerView>() {
            @Override
            public Fragment5LayoutRecyclerView getLayoutView(ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.recycler_item,parent,false);
                return new Fragment5LayoutRecyclerView(view);
            }
            @Override
            public void onBindViewHolder(Fragment5LayoutRecyclerView viewHandler, final int position) {
                viewHandler.getTv().setText(list.get(position));
                viewHandler.getTv().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       switch (position){
                           case 0:
                                startActivity(new Intent(getActivity().getApplicationContext(), InternalStorageDemo.class));
                               break;
                           case 1:
                               startActivity(new Intent(getActivity().getApplicationContext(), ExternalStorageDemo.class));
                               break;
                           case 2:
                               startActivity(new Intent(getActivity().getApplicationContext(), QRCodeDemo.class));
                               break;
                           case 3:
                               startActivity(new Intent(getActivity().getApplicationContext(), SQLiteDatabaseDemo.class));
                               break;
                           case 4:
                               Toast.makeText(getActivity().getApplicationContext(),"position="+position,Toast.LENGTH_LONG).show();
                               break;
                           case 5:
                               Toast.makeText(getActivity().getApplicationContext(),"position="+position,Toast.LENGTH_LONG).show();
                               break;
                           case 6:
                               startActivity(new Intent(getActivity().getApplicationContext(), SurfaceViewDemo.class));
                               break;
                           default:
                               break;
                       }
                    }
                });
            }
        };
        adapter = new RecyclerViewAdapter<>(list, getActivity().getApplicationContext(), recyclerViewInterface);
        //添加分割线
        recycler.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(),DividerItemDecoration.VERTICAL));
        //设计布局样式
        recycler.setLayoutManager(layoutManager);
        //添加适配器
        recycler.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

    }
}
