package com.only.framework.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.only.framework.R;

/**
 * Created by asus on 2019/1/4.
 */

public class Fragment3 extends Fragment {
    private static final String TAG = "Fragment3";
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout, container, false);
        initView();
        return view;
    }

    private void initView() {
    }
}
