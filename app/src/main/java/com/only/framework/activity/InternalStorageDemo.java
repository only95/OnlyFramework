package com.only.framework.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.only.framework.R;
import com.only.framework.StorageOperationDialog;
import com.only.framework.TextDialog;
import com.only.framework.adapter.IFilesStorage;
import com.only.framework.adapter.StorageView;
import com.only.framework.adapter.ListFilesAdapter;
import com.only.framework.presenter.storage.StoragePresenter;
import com.only.framework.view.BaseActivity;
import com.only.framework.view.storage.IStorageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by asus on 2019/1/10.
 * 对系统内部内存进行数据操作
 */

public class InternalStorageDemo extends BaseActivity<IStorageView,StoragePresenter<IStorageView>> {
    private static final String TAG = "InternalStorageDemo";
    private ListView listView;
    private File[] data;
    private ListFilesAdapter adapter;
    private Toolbar toolbar;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internal_storage);
        initView();
        setSupportActionBar(toolbar);
        inListener();
        data = getFilesDir().listFiles();
        Log.e(TAG,getFilesDir().getAbsolutePath());
        adapter = new ListFilesAdapter(data, InternalStorageDemo.this);
        listView.setAdapter(adapter);


        adapter.getLayoutView(new IFilesStorage() {
            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                StorageView viewHandler=null;
                if (view == null) {
                    viewHandler = new StorageView();
                    view = LayoutInflater.from(InternalStorageDemo.this).inflate(R.layout.item_internal_srorage, null);
                    viewHandler.imageView = view.findViewById(R.id.image_internal);
                    viewHandler.textView = view.findViewById(R.id.tv_internal);
                    viewHandler.time = view.findViewById(R.id.time);
                    view.setTag(viewHandler);
                } else {
                    viewHandler = (StorageView) view.getTag();
                }
                if (data[i].isDirectory()) {//判断是否为文件夹
                    viewHandler.getImageView().setImageResource(R.mipmap.folder);//添加问价夹的图片
                } else {
                    viewHandler.getImageView().setImageResource(R.mipmap.txt);//添加文本图片
                }
                viewHandler.getTextView().setText(data[i].getName());//设置名称
                long lastModified = data[i].lastModified();//创建修改时间
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+9"));
                String format = simpleDateFormat.format(new Date(lastModified));
                viewHandler.getTime().setText(format);
                return view;
            }
        });




        //监听listView长按事件
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final File file = data[i].getParentFile();
                StorageOperationDialog operationDialog = new StorageOperationDialog(InternalStorageDemo.this, R.style.DialogStyle);
                operationDialog.setOperationListener(new StorageOperationDialog.IOnStorageListener() {
                    @Override
                    public void setWritFile(String name,String content) {
                        basePresenter.writeToFile(content,data[i].getAbsolutePath(),name);
                        data=data[i].listFiles();
                        adapter.Refresh(data);
                    }

                    @Override
                    public void deleteFile() {
                        basePresenter.deleteFile(data[i].getAbsolutePath());
                        data=data[i].getParentFile().listFiles();
                        if (data.length==0){
                            if (file.getAbsolutePath().equals(getFilesDir().getAbsolutePath())){
                                data=getFilesDir().listFiles();
                                adapter.Refresh(data);
                            }else{
                                data=file.getParentFile().listFiles();
                                adapter.Refresh(data);
                            }
                        }else{
                            adapter.Refresh(data);
                        }

                    }

                    @Override
                    public void renameTo(String content) {
                        basePresenter.renameFile(data[i].getAbsolutePath(),file.getAbsolutePath()+"/"+content);
                        data=data[i].getParentFile().listFiles();
                        adapter.Refresh(data);
                    }

                    @Override
                    public void addFile(String nameFile) {
                        basePresenter.createFile(data[i].getAbsolutePath(),nameFile);
                        data=data[i].listFiles();
                        adapter.Refresh(data);
                    }
                });
                operationDialog.show();
                return true;
            }
        });
        //监听ListView点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                String path = data[i].getAbsolutePath();//获取当前路径

                if (data[i].isDirectory()) {//判断是否为文件夹
                    if (data[i].listFiles().length == 0) {
                        Toast.makeText(InternalStorageDemo.this, "这是一个空文件夹", Toast.LENGTH_LONG).show();
                        return;
                    }else{
                        data=data[i].listFiles();
                        adapter.Refresh(data);
                    }
                } else {
                    Log.i(TAG, path);
                    try {
                        FileInputStream is=new FileInputStream(data[i].getAbsolutePath());
                        int c = -1;
                        StringBuffer buffer = new StringBuffer();
                        while ((c = is.read()) != -1) {
                            buffer.append((char) c);
                        }
                        Toast.makeText(InternalStorageDemo.this, buffer.toString(), Toast.LENGTH_LONG).show();
                        is.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
        });


    }


    @Override
    protected StoragePresenter<IStorageView> createPresenter() {
        return new StoragePresenter<>();
    }


    private void inListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFilesDir().getAbsolutePath();
                if (data.length>0){
                    if (data[0].getParentFile().getAbsolutePath().equals(getFilesDir().getAbsolutePath())){
                        finish();
                    }else{
                        File parentFile = data[0].getParentFile();
                        File mParentFile=parentFile.getParentFile();
                        data=mParentFile.listFiles();
                        adapter.Refresh(data);
                    }
                }else{
                    finish();
                }


            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add:
                        TextDialog textDialog = new TextDialog(InternalStorageDemo.this, R.style.DialogStyle);
                        textDialog.setOnTextDialogListener(new TextDialog.TextDialogListener() {
                            @Override
                            public void setBtnOk(String content) {
                                File parentFile;
                                if (data.length==0){
                                    parentFile=getFilesDir();
                                }else{
                                    parentFile = data[0].getParentFile();
                                }
                                basePresenter.createFile(parentFile.getAbsolutePath(),content);
                                data=parentFile.listFiles();
                                adapter.Refresh(data);

                            }

                            @Override
                            public void setBtnNo() {

                            }
                        });
                        textDialog.show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.storage_view, menu);
        return true;
    }


    private void initView() {
        listView = findViewById(R.id.listView);
        toolbar = findViewById(R.id.toolbar);
    }

}
