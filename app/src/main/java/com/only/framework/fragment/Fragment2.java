package com.only.framework.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.only.framework.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by asus on 2019/1/4.
 */

public class Fragment2 extends Fragment{
    private View view;
    private TextView textView;
    private Button button;
    private Bitmap bitmap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_layout2,container,false);
        initView();
        textView.setText("Fragment2");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                RelativeLayout linearLayout=view.findViewById(R.id.linearLayout);
                linearLayout.setDrawingCacheEnabled(true);
                linearLayout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                linearLayout.layout(0, 0, linearLayout.getMeasuredWidth(), linearLayout.getMeasuredHeight());
                File directory = Environment.getExternalStorageDirectory();
                try {
                    String urlName=System.currentTimeMillis()+".png";
                    bitmap  = linearLayout.getDrawingCache();
                    FileOutputStream os = new FileOutputStream(directory.getAbsolutePath() + "/Pictures/"+urlName);
                    boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG, 90, os);
                    if (compress){
                        Toast.makeText(getActivity().getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(), "保存失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
        return view;
    }

    //然后View和其内部的子View都具有了实际大小，也就是完成了布局，相当与添加到了界面上。接着就可以创建位图并在上面绘制了：
    private void layoutView(View v, int width, int height) {
        // 整个View的大小 参数是左上角 和右下角的坐标
        v.layout(0, 0, width, height);
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(10000, View.MeasureSpec.AT_MOST);
        /** 当然，measure完后，并不会实际改变View的尺寸，需要调用View.layout方法去进行布局。
         * 按示例调用layout函数后，View的大小将会变成你想要设置成的大小。
         */
        v.measure(measuredWidth, measuredHeight);
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
    }


    private void initView() {
        button=view.findViewById(R.id.button);
        textView=view.findViewById(R.id.tv);
    }
    /**
     * @param linearLayout 要转化为图片的布局
     */
    private void generatBitmap(LinearLayout linearLayout) {
        linearLayout.setDrawingCacheEnabled(true);
        linearLayout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        linearLayout.layout(0, 0, linearLayout.getMeasuredWidth(), linearLayout.getMeasuredHeight());
        linearLayout.buildDrawingCache();
        bitmap = Bitmap.createBitmap(linearLayout.getDrawingCache());
        linearLayout.setDrawingCacheEnabled(false);
        linearLayout.setGravity(Gravity.CENTER);  //因为刚刚重新测量布局一次，需要重新设置view居中
    }
    public static Bitmap captureView(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        bmp = Bitmap.createBitmap(bmp);
        view.destroyDrawingCache();
        return bmp;
    }



}
