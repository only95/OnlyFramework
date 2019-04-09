package com.only.framework.library.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by asus on 2019/1/5.
 */

public interface IListViewInterface<T> {
    /**
     * 回调getLayoutView 返回指定的view出来给适配器
     * @param i
     * @param view
     * @param viewGroup
     * @param context
     * @param v
     * @return
     */
    View getLayoutView(int i, View view, ViewGroup viewGroup, Context context, T v);
}
