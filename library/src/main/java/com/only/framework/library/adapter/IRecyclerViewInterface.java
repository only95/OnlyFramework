package com.only.framework.library.adapter;

import android.view.ViewGroup;

/**
 * Created by asus on 2019/1/7.
 */

public interface IRecyclerViewInterface<P> {



    P getLayoutView(ViewGroup parent, int viewType);


    /**
     * 提供外部调用方法
     * @param viewHandler
     * @param position
     */
    void onBindViewHolder(P viewHandler,int position);
}
