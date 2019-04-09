package com.only.framework.adapter;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by asus on 2019/1/11.
 */

public class StorageView {
    public ImageView imageView;
    public TextView textView,time;

    public TextView getTime() {
        return time;
    }

    public void setTime(TextView time) {
        this.time = time;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
