package com.only.framework.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.only.framework.R;
import com.only.framework.db.MyHelp;

/**
 * Created by asus on 2019/2/12.
 * SQLiteDatabase数据操作
 */

public class SQLiteDatabaseDemo extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG ="SQLiteDatabaseDemo" ;
    private Button btn_create, btn_inert, btn_query, btn_update, btn_delete;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sql_lite_data_demo);
        initView();
        Listener();
        String path=getFilesDir().getAbsolutePath()+"/students.db";
        //openOrCreate可以打开任意位置的文件
//        db=SQLiteDatabase.openOrCreateDatabase(path,null);

        MyHelp myHelp=new MyHelp(SQLiteDatabaseDemo.this,"student",null,1);
        //获取数据库对象，位置只能在data-data-包名-Database文件夹下
        db=myHelp.getReadableDatabase();
    }

    private void Listener() {
        btn_create.setOnClickListener(this);
        btn_inert.setOnClickListener(this);
        btn_query.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }

    private void initView() {
        btn_create = findViewById(R.id.btn_create);
        btn_inert = findViewById(R.id.btn_inert);
        btn_query = findViewById(R.id.btn_query);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);
    }



    @Override
    public void onClick(View view) {
        ContentValues values;
        switch (view.getId()) {
            case R.id.btn_create:
//                db.execSQL("create table students (_id Integer primary key,name string ,sex string)");
//                Log.e(TAG,"创建成功");
                break;
            case R.id.btn_inert:
//                db.execSQL("insert into students (name,sex) values(?,?)",new String[]{"123","1"});
                values=new ContentValues();
                values.put("name","only");
                values.put("sex","男");
                /**
                 * 第一个参数：表名
                 * 第二个参数：默认为null
                 * 第三个参数：ContentVaues对象
                 */
                db.insert("students",null,values);
                break;
            case R.id.btn_query:
                Cursor cursor;
//                 cursor = db.rawQuery("select * from students", null);
                /**
                 * 第一个参数：表名
                 * 第二个参数：你要查询的列的集合，一个String[]
                 * 第三个参数：你要查询的条件，类型String
                 * 第四个参数：查询条件中通配符的内容，String[]
                 * 第五个参数：分组条件，null
                 * 第六个参数：包含条件，null
                 * 第七个参数：排序条件，null
                 * 如果除了表名之外全部为null，表示查询全部内容
                 */
                cursor= db.query("students",null,null,null,null,null,null);
                //将光标移动到第一个行
                //cursor.moveToNext() boolean类型,false为空找不到数据否则移动到下一行继续读取数据
                while (cursor.moveToNext()){
                    String id = cursor.getString(cursor.getColumnIndex("_id"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String sex = cursor.getString(cursor.getColumnIndex("sex"));
                    Log.e(TAG,id+"-"+name+"-"+sex);
                }
                break;
            case R.id.btn_update:
//                db.execSQL("update students set name='onll' where _id=1");
                values=new ContentValues();
                values.put("name","张三");
                /**
                 * 第一个参数：表名
                 * 第二个参数：要更新的ContentValues对象内容
                 * 第三个参数：要修改的条件
                 * 第四个参数：条件中通配符的内容，如果没有通配符那么默认为null
                 */
                db.update("students",values,"_id=1",null);
                break;
            case R.id.btn_delete:
//                db.execSQL("delete from students where name='123'");
                /**
                 * 第一个参数：表名
                 * 第二个参数：删除的条件
                 * 第三个参数：条件中通配符的内容，如果没有通配符那么默认为null
                 */
                db.delete("students","name='only'",null);
                break;
            default:
                break;
        }
    }
}
