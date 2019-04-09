package com.only.framework.library.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.List;

/**
 * Created by asus on 2019/1/5.
 * RecyclerView适配器封装自用
 */

public class RecyclerViewAdapter<T,P extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<P> {


    private List<T> list;
    private Context context;
    private IRecyclerViewInterface recyclerViewInterface;
    /**
     * 提供构造方法
     *
     * @param list    数据
     * @param context 上下文
     */
    public RecyclerViewAdapter(List<T> list, Context context,IRecyclerViewInterface recyclerViewInterface) {
        this.list = list;
        this.context = context;
        this.recyclerViewInterface=recyclerViewInterface;
    }


    @NonNull
    @Override
    public P onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return (P)recyclerViewInterface.getLayoutView(parent,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull P holder, int position) {
        recyclerViewInterface.onBindViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    /**
     * 添加数据
     *
     * @param addList
     */
    public void addList(List<T> addList) {
        int position = list.size();
        list.add(position, (T) addList);
        notifyDataSetChanged();
    }

    /**
     * 刷新数据
     *
     * @param refreshList
     */
    public void refresh(List refreshList) {
        list.removeAll(list);
        list.addAll(refreshList);
        notifyDataSetChanged();
    }
}
