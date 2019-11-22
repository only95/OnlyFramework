package com.only.framework.library.dpscreenadaptation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * ${tags}
 *
 * @Title: ${enclosing_method}
 * @author:wujun
 */
public class ViewCanMeasure extends TextView {
    public ViewCanMeasure(Context context) {
        this(context, null);
    }

    public ViewCanMeasure(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        Log.e("width","++++++++++++++++++++++++++++++++++++++++"+getMeasuredWidth());
        Log.e("height","++++++++++++++++++++++++++++++++++++++++"+getMeasuredHeight());

        setText("w:"+getMeasuredWidth()+"px\n"+"h:"+getMeasuredHeight()+"px");
    }

}
