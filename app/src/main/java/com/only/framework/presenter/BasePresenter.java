package com.only.framework.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by asus on 2019/1/4.
 */

public class BasePresenter<T> {

   public WeakReference<T> weakReference;
    //绑定
    public void bindingPresenter(T View){
        weakReference=new WeakReference<T>(View);
    }

    //解绑
    public void untyingPresenter(){
        weakReference.clear();
    }
}
