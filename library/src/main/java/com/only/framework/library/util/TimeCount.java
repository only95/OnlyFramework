package com.only.framework.library.util;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;

import com.only.framework.library.R;


/**
 * Created by Administrator on 2018/7/5.
 * 时间倒计时
 */

public class TimeCount extends CountDownTimer {
    private Button textView;
    public TimeCount(Button textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.textView = textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        textView.setClickable(false);//不可点击
        textView.setText(millisUntilFinished/1000+"秒后重新发送");//设置倒计时时间
        textView.setBackgroundResource(R.drawable.bt_shape_send_code);
        /**
         * 超链接 URLSpan
         * 文字背景颜色 BackgroundColorSpan
         * 文字颜色 ForegroundColorSpan
         * 字体大小 AbsoluteSizeSpan
         * 粗体、斜体 StyleSpan
         * 删除线 StrikethroughSpan
         * 下划线 UnderlineSpan
         * 图片 ImageSpan
         * http://blog.csdn.net/ah200614435/article/details/7914459
         */
        SpannableString spannableString = new SpannableString(textView.getText().toString());  //获取按钮上的文字
        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);//设置倒计时颜色
        /**
         * public void setSpan(Object what, int start, int end, int flags) {
         * 主要是start跟end，start是起始位置,无论中英文，都算一个。
         * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
         */
        spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为红色
        textView.setText(spannableString);

    }

    @Override
    public void onFinish() {
        textView.setText("获取验证码");
        textView.setBackgroundResource(R.drawable.bt_shape_send_code_s);
        textView.setClickable(true);
    }
}
