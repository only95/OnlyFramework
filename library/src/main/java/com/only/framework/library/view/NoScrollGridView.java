package com.only.framework.library.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2018/8/31.
 * 解决ScrollView嵌套GridView ,解决嵌套Grideview的显示不完全的问题
 */

public class NoScrollGridView extends GridView{
    public NoScrollGridView(Context context) {
        super(context);
    }

    public NoScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /* (non-Javadoc)
     * @see android.widget.GridView#onMeasure(int, int)
     * 重写onMeasure方法
     * Integer.MAX_VALUE >> 2  移两位>>2 == /4 得到Integer可以表示的边界值
     * GridView之所以会滚动，是因为空间不足以显示全控件，需要更大的空间，
     * 不让他滑动，就把他的测量高度设置为足够大，这里用Intager的边界值表示
     * MeasureSpec.AT_MOST是最大尺寸，控件大小一般随着控件的子空间或内容进行变化，此时控件尺寸只要不超过父控件允许的最大尺寸即可。
     * 因此，此时的mode是AT_MOST，size给出了父控件允许的最大尺寸。
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    }
