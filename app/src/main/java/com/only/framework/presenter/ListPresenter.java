package com.only.framework.presenter;
import android.util.Log;

import java.util.List;

import com.only.framework.model.Interface.IListModel;
import com.only.framework.model.ListModelImpl;
import com.only.framework.presenter.Interface.IListPresenter;
import com.only.framework.view.Interface.IListView;

/**
 * Created by asus on 2019/1/4.
 */

public class ListPresenter<T extends IListView> extends BasePresenter<T>  implements IListPresenter {

    IListModel listModel=new ListModelImpl();



    /**
     * 实现对数据的回调访问
     */
    @Override
    public void face() {
        if (weakReference.get()!=null){
            if (listModel!=null){
                listModel.ListDate(new IListModel.ListModelListener() {
                    @Override
                    public void listener(List data) {
                        weakReference.get().show(data);
                    }
                });
            }else{
                Log.e("text","==========222====");
            }
        }else{
            Log.e("text","==============");
        }

    }
}
