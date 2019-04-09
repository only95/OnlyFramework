package com.only.framework;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by asus on 2019/1/12.
 * Dialog输入文本框
 */

public class TextDialog extends Dialog {
    private Context context;
    private EditText editText;
    private Button btn_ok,btn_no;
    private TextDialogListener dialogListener;
    public TextDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public TextDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_text,null);
        setContentView(view);
        setCancelable(false);
        Window window = getWindow();
        WindowManager.LayoutParams mParams = window.getAttributes();
        mParams.width= WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(mParams);

        editText=view.findViewById(R.id.edit_text);
        btn_ok=view.findViewById(R.id.btn_ok);
        btn_no=view.findViewById(R.id.btn_no);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = editText.getText().toString();
                dialogListener.setBtnOk(string);
                dismiss();
            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogListener.setBtnNo();
                dismiss();
            }
        });
    }

    public void setOnTextDialogListener(TextDialogListener dialogListener){
        this.dialogListener=dialogListener;
    }


    public interface TextDialogListener{
        void setBtnOk(String content);
        void setBtnNo();
    }
}
