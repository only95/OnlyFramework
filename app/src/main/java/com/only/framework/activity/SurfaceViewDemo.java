package com.only.framework.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.only.framework.R;

import java.util.Random;

/**
 * Created by asus on 2019/2/15.
 */

public class SurfaceViewDemo extends AppCompatActivity {
    private SurfaceView surfaceView;
    private SurfaceHolder mSurfaceHolder;
    private Bitmap mBitmap;
    private boolean isRun = true;

    /**
     * 1.绑定SurfaceView
     * 2.获取SurfaceHolder对象
     * 3.添加SurfaceHolder回调
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surface_view);
        initView();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.txt);
        mSurfaceHolder = surfaceView.getHolder();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            //创建
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                new Thread(new MyThread()).start();
            }

            //改变（大小）
            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            //销毁
            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                isRun = false;
            }
        });
    }

    private void initView() {
        surfaceView = findViewById(R.id.surfaceView);
    }

    private class MyThread implements Runnable {

        @Override
        public void run() {
            Random random=new Random();
            Canvas canvas = mSurfaceHolder.lockCanvas();//锁定画布
            //获取画布的最大宽高,为了不让超出画布在减去图片自身宽高
            int maxHeight = canvas.getHeight() - mBitmap.getHeight();
            int maxWidth = canvas.getWidth() - mBitmap.getWidth();
            mSurfaceHolder.unlockCanvasAndPost(canvas);//解锁画布
            //定义X,Y的坐标
            int x = 0;
            int y = 0;
            while (isRun) {
                //随机获取x,y坐标位置
                x=random.nextInt(maxWidth);
                y=random.nextInt(maxHeight);
                canvas = mSurfaceHolder.lockCanvas();//锁定画布
                canvas.drawBitmap(mBitmap, x, y, null);//绘制画布
                mSurfaceHolder.unlockCanvasAndPost(canvas);//解锁画布

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
