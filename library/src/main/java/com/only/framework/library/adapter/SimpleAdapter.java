package com.only.framework.library.adapter;

import android.content.Context;

import java.util.List;

public abstract class SimpleAdapter<T> extends BaseAdapter<T,BaseViewHolder> {

    public SimpleAdapter(List<T> datas, Context context, int LayoutResID) {
        super(datas, context, LayoutResID);
    }
}
