package com.only.framework.presenter.storage;

import com.only.framework.model.storage.IStorageModel;
import com.only.framework.model.storage.StorageModel;
import com.only.framework.presenter.BasePresenter;
import com.only.framework.view.storage.IStorageView;

/**
 * Created by asus on 2019/1/12.
 */

public class StoragePresenter<T extends IStorageView> extends BasePresenter<T> implements IStoragePresenter{


    private IStorageModel storageModel=new StorageModel();


    @Override
    public boolean deleteFile(String path) {
        boolean b = storageModel.deleteFile(path);
        return b;
    }

    @Override
    public void renameFile(String fromFile, String toFile) {
        storageModel.renameFile(fromFile,toFile);
    }

    @Override
    public void createFile(String filePath, String fileName) {
        storageModel.createFile(filePath,fileName);
    }

    @Override
    public void writeToFile(String str, String filePath, String fileName) {
        storageModel.writeToFile(str,filePath,fileName);
    }
}
