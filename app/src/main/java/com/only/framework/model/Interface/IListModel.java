package com.only.framework.model.Interface;

import java.util.List;

/**
 * Created by asus on 2019/1/4.
 */

public interface IListModel {

    void ListDate(ListModelListener listModelListener);

    interface ListModelListener{
        void listener(List data);
    }
}
