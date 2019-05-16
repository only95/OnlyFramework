package com.only.framework.library.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseAdapter<T,H extends BaseViewHolder> extends RecyclerView.Adapter<BaseViewHolder>{
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected int mLayoutResID;
    protected  OnItemClickListener listener;
    public BaseAdapter(List<T> datas, Context context, int LayoutResID){
        this.mDatas = datas;
        this.mContext = context;
        this.mLayoutResID = LayoutResID;
        mInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(mLayoutResID,parent,false);
        return new BaseViewHolder(view,listener);
    }

    public interface  OnItemClickListener{
        void OnClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        T t =getItem(position);
        bindData(holder,t);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    public T getItem(int position){
        return mDatas.get(position);
    }
    public abstract void bindData(BaseViewHolder baseViewHolder,T t);
}
