package com.only.framework.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
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
import com.only.framework.adapter.ListFilesAdapter;
import com.only.framework.adapter.StorageView;
import com.only.framework.presenter.storage.StoragePresenter;
import com.only.framework.view.BaseActivity;
import com.only.framework.view.storage.IStorageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by asus on 2019/1/10.
 */

public class ExternalStorageDemo extends BaseActivity<IStorageView, StoragePresenter<IStorageView>> {


    private static final String TAG = "ExternalStorageDemo";
    private ListView listView;
    private Toolbar toolbar;
    private File[] data;
    private ListFilesAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.external_storage);
        initView();
        setSupportActionBar(toolbar);
        inListener();
        //获取根目录
        final File file = Environment.getExternalStorageDirectory();
        Log.e(TAG, file.getAbsolutePath());
        data = file.listFiles();
        Log.e(TAG, data.length + "--");
        adapter = new ListFilesAdapter(data, ExternalStorageDemo.this);
        listView.setAdapter(adapter);
        adapter.getLayoutView(new IFilesStorage() {
            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                StorageView storageView = null;
                if (view == null) {
                    view = LayoutInflater.from(ExternalStorageDemo.this).inflate(R.layout.item_external_srorage, null);
                    storageView = new StorageView();
                    storageView.imageView = view.findViewById(R.id.image_external);
                    storageView.textView = view.findViewById(R.id.tv_external);
                    storageView.time = view.findViewById(R.id.time);
                    view.setTag(storageView);
                } else {
                    storageView = (StorageView) view.getTag();
                }
                if (data[i].isDirectory()) {
                    storageView.getImageView().setImageResource(R.mipmap.folder);
                } else {
                    storageView.getImageView().setImageResource(R.mipmap.txt);
                }
                storageView.getTextView().setText(data[i].getName());
                long modified = data[i].lastModified();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+9"));
                String format = simpleDateFormat.format(new Date(modified));
                storageView.getTime().setText(format);
                return view;
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final File parentFile = data[i].getParentFile();
                StorageOperationDialog storageOperationDialog = new StorageOperationDialog(ExternalStorageDemo.this, R.style.DialogStyle);
                storageOperationDialog.setOperationListener(new StorageOperationDialog.IOnStorageListener() {
                    @Override
                    public void setWritFile(String name, String content) {
                        basePresenter.writeToFile(content, data[i].getAbsolutePath(), name);
                        data = data[i].listFiles();
                        adapter.Refresh(data);
                    }

                    @Override
                    public void deleteFile() {
                        basePresenter.deleteFile(data[i].getAbsolutePath());
                        if (data[i].getParentFile().listFiles().length == 0) {
                            if (parentFile.getAbsolutePath().equals(Environment.getExternalStorageDirectory().getAbsolutePath())) {
                                data = Environment.getDataDirectory().listFiles();
                            } else {
                                data = parentFile.getParentFile().listFiles();
                            }
                        } else {
                            data = data[i].getParentFile().listFiles();
                        }
                        adapter.Refresh(data);
                    }

                    @Override
                    public void renameTo(String content) {
                        basePresenter.renameFile(data[i].getAbsolutePath(), parentFile.getAbsolutePath() + "/" + content);
                        data = data[i].getParentFile().listFiles();
                        adapter.Refresh(data);
                    }

                    @Override
                    public void addFile(String nameFile) {
                        basePresenter.createFile(data[i].getAbsolutePath(), nameFile);
                        data = data[i].listFiles();
                        adapter.Refresh(data);
                    }
                });

                storageOperationDialog.show();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (data[i].isDirectory()) {
                    if (data[i].listFiles().length == 0) {
                        Toast.makeText(ExternalStorageDemo.this, "这是一个空文件夹", Toast.LENGTH_SHORT).show();
                    } else {
                        data = data[i].listFiles();
                        adapter.Refresh(data);
                    }
                } else {
                    openFile(data[i]);
//                    try {
//                        FileInputStream is = new FileInputStream(data[i].getAbsolutePath());
//                        int c = -1;
//                        StringBuffer stringBuffer = new StringBuffer();
//                        while ((c = is.read()) != -1) {
//                            stringBuffer.append((char) c);
//                        }
//                        Toast.makeText(ExternalStorageDemo.this, stringBuffer.toString(), Toast.LENGTH_SHORT).show();
//                        is.close();
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
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
                String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                if (data.length > 0) {
                    Log.e(TAG, data[0].getParentFile().getAbsolutePath() + "-----------" + path);
                    if (data[0].getParentFile().getAbsolutePath().equals(path)) {
                        finish();
                    } else {
                        File file = data[0].getParentFile().getParentFile();
                        data = file.listFiles();
                        adapter.Refresh(data);
                    }
                } else {
                    finish();
                }

            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add:
                        TextDialog textDialog = new TextDialog(ExternalStorageDemo.this, R.style.DialogStyle);
                        textDialog.setOnTextDialogListener(new TextDialog.TextDialogListener() {
                            @Override
                            public void setBtnOk(String content) {
                                File path;
                                if (data.length > 0) {
                                    path = data[0].getParentFile();
                                } else {
                                    path = Environment.getDownloadCacheDirectory();
                                }
                                basePresenter.createFile(path.getAbsolutePath(), content);
                                data = path.listFiles();
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
        toolbar = findViewById(R.id.toolbar);
        listView = findViewById(R.id.listView);

    }



    private void openFile(File f)
    {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        String type = getMIMEType(f);
        intent.setDataAndType(Uri.fromFile(f), type);
        startActivity(intent);
    }

    private String getMIMEType(File f){
        String end = f
                .getName()
                .substring(f.getName().lastIndexOf(".") + 1,
                        f.getName().length()).toLowerCase();
        String type = "";
        if (end.equals("mp3") || end.equals("aac") || end.equals("aac")
                || end.equals("amr") || end.equals("mpeg")
                || end.equals("mp4"))
        {
            type = "audio";
        } else if (end.equals("jpg") || end.equals("gif")
                || end.equals("png") || end.equals("jpeg"))
        {
            type = "image";
        } else
        {
            type = "*";
        }
        type += "/*";
        return type;
    }
}
