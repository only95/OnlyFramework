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
 * 输入文本框
 */

public class TextWritDialog extends Dialog{
    private Context context;
    private EditText edit_name,edit_content;
    private Button btn_ok,btn_no;
    private TextDialogListener dialogListener;
    public TextWritDialog(@NonNull Context context) {
        super(context);
        this.context=context;
    }

    public TextWritDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        View view= LayoutInflater.from(context).inflate(R.layout.dialog_text_writ,null);
        setCancelable(false);
        setContentView(view);
        Window window=getWindow();
        WindowManager.LayoutParams params=new WindowManager.LayoutParams();
        window.setGravity(Gravity.CENTER);
        params.height=WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
        edit_name=view.findViewById(R.id.edit_name);
        edit_content=view.findViewById(R.id.edit_content);
        btn_ok=view.findViewById(R.id.btn_ok);
        btn_no=view.findViewById(R.id.btn_no);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edit_name.getText().toString();
                String content = edit_content.getText().toString();
                dialogListener.setWritFile(name,content);
                dismiss();
            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void setOnTextDialogListener(TextDialogListener dialogListener){
        this.dialogListener=dialogListener;
    }


    interface TextDialogListener{
        void setWritFile(String name,String content);
    }
}
