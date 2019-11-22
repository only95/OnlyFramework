package com.only.framework.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.only.framework.R;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by asus on 2019/1/4.
 */

public class Fragment1 extends Fragment implements PlatformActionListener {
    private View view;
    private TextView textView;
    private Context context;

    private MyHandler myHandler;
    private class  MyHandler extends Handler{
        private WeakReference<Fragment1> weakReference;

        public MyHandler(Fragment1 fragment1) {
            weakReference=new WeakReference<Fragment1>(fragment1);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.arg1){
                case 1:
                    HashMap<String, Object> hashMap= (HashMap<String, Object>) msg.obj;
                    hashMap.get("city");
                    for (String str:hashMap.keySet()){
                        hashMap.get(str);
                    }
                    break;
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context=getActivity();
        view = inflater.inflate(R.layout.fragment_layout, container, false);
        initView();
        textView.setText("Fragment1");
        myHandler=new MyHandler(Fragment1.this);
        return view;
    }

    private void initView() {
        textView = view.findViewById(R.id.tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Platform qq2 = ShareSDK.getPlatform(Wechat.NAME);
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(Fragment1.this);
                qq.SSOSetting(false);
//                authorize(qq, 2);
                //如果授权就删除授权资料
                qq.showUser(null);//授权并获取用户信息
            }
        });
    }


    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);

    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Log.i("hashMap",hashMap.toString());
        Message message=new Message();
        message.obj=hashMap;
        message.arg1=1;
        myHandler.sendMessage(message);
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Log.i("失败",throwable.toString());

    }

    @Override
    public void onCancel(Platform platform, int i) {
        Log.i("取消",""+i);
    }
}
