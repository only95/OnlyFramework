package com.only.framework;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by asus on 2019/1/12.
 */

public class StorageOperationDialog extends Dialog {
    private Context context;
    private TextView tv_text, tv_delete, tv_rename,tv_file;
    private IOnStorageListener onStorageListener;

    public StorageOperationDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public StorageOperationDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intiView();
    }

    private void intiView() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_storage_item, null);
        setContentView(view);
//        setCancelable(false);
        Window window = getWindow();
        WindowManager.LayoutParams mParams = window.getAttributes();
//        mParams.width= WindowManager.LayoutParams.WRAP_CONTENT;
//        mParams.height=WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(mParams);


        tv_text = view.findViewById(R.id.tv_text);
        tv_delete = view.findViewById(R.id.tv_delete);
        tv_rename = view.findViewById(R.id.tv_rename);
        tv_file=view.findViewById(R.id.tv_file);
        tv_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextDialog textDialog = new TextDialog(context, R.style.DialogStyle);
                textDialog.setOnTextDialogListener(new TextDialog.TextDialogListener() {
                    @Override
                    public void setBtnOk(String content) {
                        onStorageListener.addFile(content);
                    }

                    @Override
                    public void setBtnNo() {

                    }
                });
                textDialog.show();
                dismiss();
            }
        });
        tv_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextWritDialog writDialog = new TextWritDialog(context, R.style.DialogStyle);
                writDialog.setOnTextDialogListener(new TextWritDialog.TextDialogListener() {
                    @Override
                    public void setWritFile(String name, String content) {
                        onStorageListener.setWritFile(name,content);
                    }
                });
                writDialog.show();
                dismiss();
            }
        });
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStorageListener.deleteFile();
                dismiss();
            }
        });
        tv_rename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextDialog textDialog = new TextDialog(context, R.style.DialogStyle);
                textDialog.setOnTextDialogListener(new TextDialog.TextDialogListener() {
                    @Override
                    public void setBtnOk(String content) {
                        onStorageListener.renameTo(content);
                    }

                    @Override
                    public void setBtnNo() {

                    }
                });
                textDialog.show();
                dismiss();
            }
        });
    }


    public void setOperationListener(IOnStorageListener onStorageListener) {
        this.onStorageListener = onStorageListener;
    }


    public interface IOnStorageListener {
        void setWritFile(String name,String content);

        void deleteFile();

        void renameTo(String content);

        void addFile(String nameFile);
    }


}
