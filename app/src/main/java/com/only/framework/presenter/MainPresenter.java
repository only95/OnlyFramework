package com.only.framework.presenter;

import android.support.v4.app.Fragment;

import com.only.framework.model.Interface.IMainModel;
import com.only.framework.model.MainModelImpl;
import com.only.framework.view.Interface.IMainView;

import java.util.List;

/**
 * Created by asus on 2019/1/4.
 */

public class MainPresenter<T extends IMainView> extends BasePresenter<T>{
    IMainModel iMainModel=new MainModelImpl();

    public void setAddFragment(){
        if (weakReference.get()!=null){
            if (iMainModel!=null){
                List<Fragment> fragmentList = iMainModel.addFragment();
                weakReference.get().getFragment(fragmentList);
            }
        }
    }
}
