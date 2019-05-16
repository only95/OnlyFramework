package com.only.framework.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.only.framework.library.permission.Action;
import com.only.framework.library.permission.AndPermission;
import com.only.framework.library.permission.Permission;
import com.only.framework.presenter.BasePresenter;

//import com.yanzhenjie.permission.Action;
//import com.yanzhenjie.permission.AndPermission;
//import com.yanzhenjie.permission.Permission;

import java.util.List;


/**
 * 创建一个基类，实现一些公共实现的方法
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    public T basePresenter;
    private static final int REQUEST_CODE_SETTING = 300;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basePresenter = createPresenter();
        //绑定View
        basePresenter.bindingPresenter((V) this);

        //手动获取权限
        PermissionListener(Permission.CAMERA);
        //手动获取读写权限
        PermissionListener(Permission.Group.STORAGE);
    }

    /**
     * 定义一个抽象方法让Activity必须实现并返回指定P层Presenter
     *
     * @return
     */
    protected abstract T createPresenter();

    /**
     * 解除绑定
     */

    @Override
    protected void onDestroy() {
        super.onDestroy();
        basePresenter.untyingPresenter();

    }

    /**
     * 权限申请
     */
    private void PermissionListener(String... permissions) {
        AndPermission.with(this)
                .runtime()
                .permission(permissions)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Log.i("text", "申请成功");
                    }
                }).onDenied(new Action<List<String>>() {
            @Override
            public void onAction(List<String> data) {
                Log.i("text", "申请失败");
            }
        }).start();
    }


    /**
     * 简写findViewById
     *
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T $(int id) {
        return (T) findViewById(id);
    }
}
