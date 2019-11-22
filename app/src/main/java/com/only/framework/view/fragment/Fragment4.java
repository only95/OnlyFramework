package com.only.framework.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.only.framework.R;

/**
 * Created by asus on 2019/1/4.
 */

public class Fragment4 extends Fragment{
    private View view;
    private TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_layout,container,false);
        initView();
        textView.setText("Fragment4");
        return view;
    }

    private void initView() {
        textView=view.findViewById(R.id.tv);
    }


}
