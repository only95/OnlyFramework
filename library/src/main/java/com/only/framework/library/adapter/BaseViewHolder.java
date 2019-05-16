package com.only.framework.library.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private SparseArray<View> views;
    private View view ;
    protected BaseAdapter.OnItemClickListener listener;
    public BaseViewHolder(View itemView, BaseAdapter.OnItemClickListener listener) {
        super(itemView);
        views = new SparseArray<>();
        this.listener = listener;
        itemView.setOnClickListener(this);
    }

    public <T extends View> T obtainView(int id ){return findview(id);}

    private <T extends View> T findview(int id){
        view = views.get(id);
        if(view == null){
            view = itemView.findViewById(id);
            views.put(id,view);
        }
        return (T) view;
    }


    @Override
    public void onClick(View view) {
        if(listener!=null) {
            listener.OnClick(view,getLayoutPosition());
        }
    }
}
