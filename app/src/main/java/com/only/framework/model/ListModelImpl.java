package com.only.framework.model;

import com.only.framework.model.Interface.IListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2019/1/4.
 */

public class ListModelImpl implements IListModel {

    /**
     * 数据处理
     *
     * @param listModelListener
     */
    @Override
    public void ListDate(ListModelListener listModelListener) {
        List<String> list = new ArrayList<>();
        list.add("内部储存(Internal Storage)");
        list.add("外部储存(External Storage)");
        list.add("二维码(QR Code)");
        list.add("数据库操作(SQLite Database)");
        list.add("第三方登录(Third party login)");
        list.add("第三方支付(Third party payment)");
        list.add("自定义绘画(Custom Painting)");
        listModelListener.listener(list);
    }
}
