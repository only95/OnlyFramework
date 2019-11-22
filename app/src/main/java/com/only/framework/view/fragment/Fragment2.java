package com.only.framework.view.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.only.framework.R;
import com.only.framework.library.util.ApacheHttpClient;
import com.only.framework.library.util.LogUtil;
import com.only.framework.library.view.SpeedRecyclerView;
import com.only.framework.library.view.gallery.CardAdapterHelper;
import com.only.framework.library.view.gallery.CardScaleHelper;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by asus on 2019/1/4.
 */

public class Fragment2 extends Fragment {
    @BindView(R.id.recyclerView)
    SpeedRecyclerView recyclerView;
    Unbinder unbinder;
    private View view;

    private String [] strArray={"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574398460960&di=ed6f15c59416df7748a1f592cef3268b&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201811%2F04%2F20181104105330_3uxZ3.jpeg",
    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574398460960&di=ed6f15c59416df7748a1f592cef3268b&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201811%2F04%2F20181104105330_3uxZ3.jpeg",
    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574398460960&di=ed6f15c59416df7748a1f592cef3268b&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201811%2F04%2F20181104105330_3uxZ3.jpeg",
    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574398460960&di=ed6f15c59416df7748a1f592cef3268b&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201811%2F04%2F20181104105330_3uxZ3.jpeg"};

    private MyAdapter adapter;
    private CardScaleHelper mCardScaleHelper;
    private Bitmap bg;
    private int mLastPos = -1;
    private MyHandler myHandler;
    private static class MyHandler extends Handler {
        private Bitmap bm;
        private WeakReference<Fragment2> personalActivityWeakReference;

        public MyHandler(Fragment2 activity) {
            personalActivityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            bm = bundle.getParcelable("bm");
            personalActivityWeakReference.get().bg=bm;
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout2, container, false);
        unbinder = ButterKnife.bind(this, view);
        initDate();
        return view;
    }

    private void initDate() {
        myHandler=new MyHandler(Fragment2.this);
        adapter=new MyAdapter(getActivity(),strArray);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapter);
        // mRecyclerView绑定scale效果
        mCardScaleHelper = new CardScaleHelper();
        mCardScaleHelper.setCurrentItemPos(0);
        mCardScaleHelper.attachToRecyclerView(recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    notifyBackgroundChange();
                }
            }
        });
        notifyBackgroundChange();
    }

    private void notifyBackgroundChange() {
        if (mLastPos == mCardScaleHelper.getCurrentItemPos()) return;
        mLastPos = mCardScaleHelper.getCurrentItemPos();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bm;
                bm = new ApacheHttpClient().getHttpBmp(strArray[mLastPos]);
                Bundle bundle = new Bundle();
                bundle.putParcelable("bm", bm);
                Message message = new Message();
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        }).start();

//        bg = ((BitmapDrawable) getResources().getDrawable(
//                mList.get(mLastPos).getPic())).getBitmap();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private String[] mList;
        private Context mContext;
        private CardAdapterHelper mCardAdapterHelper = new CardAdapterHelper();
        private int position;

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public MyAdapter(Context ctx, String [] mList) {
            this.mList = mList;
            this.mContext = ctx;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_share_image, parent, false);
            mCardAdapterHelper.onCreateViewHolder(parent, itemView);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            mCardAdapterHelper.onBindViewHolder(holder.itemView, position, getItemCount());

            Glide.with(mContext).load(mList[position]).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    holder.share_iv.setImageDrawable(resource);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList.length;
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView share_iv;

            public ViewHolder(final View view) {
                super(view);
                share_iv = (ImageView) view.findViewById(R.id.share_iv);
            }
        }
    }

}
