package com.only.framework.adapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.io.File;


/**
 * Created by asus on 2019/1/10.
 */

public class ListFilesAdapter extends BaseAdapter {

    private File[] data;
    private Context context;
    private IFilesStorage filesStorage;
    public ListFilesAdapter(File[] data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = filesStorage.getView(i, view, viewGroup);
        return view;
    }

    public void Refresh(File[] fileDate){
       this.data=fileDate;
       notifyDataSetChanged();
    }

    public void getLayoutView(IFilesStorage filesStorage){
        this.filesStorage=filesStorage;
    }
}
