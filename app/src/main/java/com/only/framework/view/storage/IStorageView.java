package com.only.framework.view.storage;

/**
 * Created by asus on 2019/1/12.
 */

public interface IStorageView {
    /**
     * 删除
     * @param path
     */
    String deleteFile(String path);

    /**
     * 重命名
     * @param fromFile
     * @param toFile
     */
    void renameFile(String  fromFile,String  toFile);

    /**
     * 创建文件
     * @param filePath
     * @param fileName
     */
    void createFile(String filePath,String fileName);
}
