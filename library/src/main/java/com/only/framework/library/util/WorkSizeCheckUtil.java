package com.only.framework.library.util;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by asus on 2018/8/15.
 */

public class WorkSizeCheckUtil {

    //常用的正则表达式，存放在这样，以满足登录，注册等页面的监听
    public final static String LEAST_TWO = "^.{2,}$";
    public final static String LEAST_SIX_PASS = "(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}";//字母+数字。最少6位的正则表达式
    public final static String LEAST_SIX = "^.{6,}$";//字母+数字。最少6位的正则表达式
    public static final String REGEX_PHONE_NUMBER = "^(0(10|2\\d|[3-9]\\d\\d)[- ]{0,3}\\d{7,8}|0?1[3584]\\d{9})$";
    public static String leastNum(int number){
        return "^.{" + number + ",}$";//最少number位
    }

    private static class WorkSizeCheckUtilGetInstance{
        private static WorkSizeCheckUtil WorkSizeUtil() {
            WorkSizeCheckUtil workSizeCheckUtil = new WorkSizeCheckUtil();
            return workSizeCheckUtil;
        };
    }

    public static WorkSizeCheckUtil getInstance(){
        return WorkSizeCheckUtilGetInstance.WorkSizeUtil();
    }


    //回调监听，来改变具体的样式
    public interface IEditTextChangeListener{
        void textChange(boolean isHasContent);
    }
    public interface RegisterTextChangeListener{
        void textChange2(boolean isHasContent);
    }



    /**
     * 检测输入框是否都输入了内容
     * 从而改变按钮的是否可点击
     * 以及输入框后面的X的可见性，X点击删除输入框的内容
     */
    public static class TextChangeListener {
        /**可以改变任何view*/
        private View button;
        private TextView[] editTexts;//其中getTag来获取String 是正则表达式的匹配规则

        public TextChangeListener(View button){
            this.button=button;
        }
        public TextChangeListener addAllEditText(TextView... editTexts){
            this.editTexts=editTexts;
            initEditListener();
            return this;
        }

        private void initEditListener() {
            Log.i("TAG", "调用了遍历editext的方法");
            for (TextView editText:editTexts){
                editText.addTextChangedListener(new TextChange());//添加监听
            }
        }
        /**
         * edit输入的变化来改变按钮的是否点击
         */
        private class TextChange implements TextWatcher {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (checkAllEdit()){
                    button.setEnabled(true);
                    if (mChangeListener != null) {
                        mChangeListener.textChange(true);
                    }

                }else {
                    button.setEnabled(false);
                    if (mChangeListener != null) {
                        mChangeListener.textChange(false);
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        }
        /**
         * 检查所有的edit是否输入了数据
         * @return
         */
        private boolean checkAllEdit() {
            for (TextView editText:editTexts){
                String editableStr = editText.getText().toString();
                String patternStr = (String) editText.getTag();
                if (!TextUtils.isEmpty(editableStr)){//不为空就继续
                    if (patternStr != null && !TextUtils.isEmpty(patternStr)){//规则不为空
                        Pattern p = Pattern.compile(patternStr);
                        Matcher m = p.matcher(editableStr);
                        //规则不为空就继续
                        if (!m.matches()) return false;//验证不通过就返回false
                    }
                }else {//为空就返回false
                    return false;
                }
            }
            return true;//如果都符合条件就返回true；
        }
        //发布作品时候填写尺寸的监听器
        static IEditTextChangeListener mChangeListener;

        /**如果控件只是简单的开始和禁用的话，可以不用调这个监听*/
        public static void setChangeListener(IEditTextChangeListener changeListener) {
            mChangeListener = changeListener;
        }
    }
    public static class TextChangeListener2 {
        /**可以改变任何view*/
        private View button;
        private TextView[] editTexts;//其中getTag来获取String 是正则表达式的匹配规则

        public TextChangeListener2(View button){
            this.button=button;
        }
        public TextChangeListener2 addAllEditText(TextView... editTexts){
            this.editTexts=editTexts;
            initEditListener();
            return this;
        }

        private void initEditListener() {

            for (TextView editText:editTexts){
                editText.addTextChangedListener(new TextChange2());//添加监听
            }
        }
        /**
         * edit输入的变化来改变按钮的是否点击
         */
        private class TextChange2 implements TextWatcher {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (checkAllEdit()){
                    button.setEnabled(true);
                    if (registerChangeListener != null) {
                        registerChangeListener.textChange2(true);
                    }

                }else {
                    button.setEnabled(false);
                    if (registerChangeListener != null) {
                        registerChangeListener.textChange2(false);
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        }
        static RegisterTextChangeListener registerChangeListener;
        public static void setChangeListener(RegisterTextChangeListener changeListener) {
            registerChangeListener = changeListener;
        }

        /**
         * 检查所有的edit是否输入了数据
         * @return
         */
        private boolean checkAllEdit() {
            for (TextView editText:editTexts){
                String editableStr = editText.getText().toString();
                String patternStr = (String) editText.getTag();
                if (!TextUtils.isEmpty(editableStr)){//不为空就继续
                    if (patternStr != null && !TextUtils.isEmpty(patternStr)){//规则不为空
                        Pattern p = Pattern.compile(patternStr);
                        Matcher m = p.matcher(editableStr);
                        //规则不为空就继续
                        if (!m.matches()) return false;//验证不通过就返回false
                    }
                }else {//为空就返回false
                    return false;
                }
            }
            return true;//如果都符合条件就返回true；
        }

    }

}