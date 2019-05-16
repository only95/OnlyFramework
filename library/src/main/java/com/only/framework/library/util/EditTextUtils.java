package com.only.framework.library.util;

import android.widget.EditText;

/**
 * Author：mengyuan
 * Date  : 2017/5/19下午4:35
 * E-Mail:mengyuanzz@126.com
 * Desc  :
 */

public class EditTextUtils {


    public static String getTextTrim(EditText text) {
        return text.getText().toString().replaceAll(" ", "");
    }

    public static String getText(EditText text) {
        return text.getText().toString();
    }

}
