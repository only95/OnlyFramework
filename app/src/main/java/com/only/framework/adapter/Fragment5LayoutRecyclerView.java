package com.only.framework.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.only.framework.R;

/**
 * Created by asus on 2019/1/9.
 * fragment5 recyclerView
 */

public class Fragment5LayoutRecyclerView extends RecyclerView.ViewHolder {

    private TextView tv;

    public Fragment5LayoutRecyclerView(View itemView) {
        super(itemView);
        tv = itemView.findViewById(R.id.tv);
    }

    public TextView getTv() {
        return tv;
    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }
}
