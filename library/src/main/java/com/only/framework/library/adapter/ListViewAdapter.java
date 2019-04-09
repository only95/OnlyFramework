package com.only.framework.library.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.List;

/**
 * Created by asus on 2019/1/4.。
 * ListView适配器封装自用
 */

public class ListViewAdapter<T, V> extends BaseAdapter {
    List<T> list;
    Context context;
    IListViewInterface iListViewAdapter;
    V viewHandler;

    /**
     * 提供的构造方法
     *
     * @param list             数据
     * @param context          上下文
     * @param iListViewAdapter 外部接口
     */
    public ListViewAdapter(List<T> list, Context context, IListViewInterface iListViewAdapter) {
        this.list = list;
        this.context = context;
        this.iListViewAdapter = iListViewAdapter;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View layoutView = iListViewAdapter.getLayoutView(i, view, viewGroup, context, viewHandler);
        return layoutView;
    }
}
