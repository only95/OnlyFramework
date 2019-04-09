package com.only.framework.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.only.framework.R;

import framework.only.com.qrcodezxing.QRCodeUtil;
import framework.only.com.qrcodezxing.CaptureActivity;

/**
 * Created by asus on 2019/1/15.
 * 二维码
 */

public class QRCodeDemo extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private EditText qr_edit;
    private Button btn_generate, btn_scanning;
    private ImageView image_code;
    private Bitmap bitmap;
    private LinearLayout linearLayout;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle=msg.getData();
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_code);
        initView();
        inListener();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_scanning:
                startActivity(new Intent(QRCodeDemo.this,CaptureActivity.class));
                break;
            case R.id.btn_generate:
                if (qr_edit.getText().toString().equals("")){
                    Toast.makeText(QRCodeDemo.this,"请输入要生成的二维码url数据",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Bitmap bitmap = QRCodeUtil.createQRCodeBitmap(qr_edit.getText().toString(), 400);
                    image_code.setImageBitmap(bitmap);
                }

                break;
            default:
                break;
        }
    }
    private void inListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_generate.setOnClickListener(this);
        btn_scanning.setOnClickListener(this);
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        qr_edit = findViewById(R.id.qr_edit);
        btn_scanning = findViewById(R.id.btn_scanning);
        btn_generate = findViewById(R.id.btn_generate);
        image_code = findViewById(R.id.image_code);
        linearLayout = QRCodeDemo.this.findViewById(R.id.linearLayout);
    }

}
