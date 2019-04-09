package com.only.framework.presenter.storage;

/**
 * Created by asus on 2019/1/12.
 */

public interface IStoragePresenter {

    /**
     * 删除
     * @param path
     */
    boolean deleteFile(String path);

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


    /**
     * 在文件夹中添加文本
     * @param str 文本
     * @param filePath 文件路径
     * @param fileName 文件名
     */
    void writeToFile(String str,String filePath,String fileName);
}
