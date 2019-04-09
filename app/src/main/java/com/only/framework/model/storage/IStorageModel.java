package com.only.framework.model.storage;

import java.io.File;

/**
 * Created by asus on 2019/1/12.
 */

public interface IStorageModel {
    /**
     * 删除
     * @param path 路径
     */
    boolean deleteFile(String path);

    /**
     * 重命名
     * @param fromFile 当前路径
     * @param toFile    覆盖的路径名
     */
    void renameFile(String  fromFile,String  toFile);

    /**
     * 创建文件
     * @param filePath 文件路径
     * @param fileName 文件名称
     */
    boolean createFile(String filePath,String fileName);

    /**
     * 在文件夹中添加文本
     * @param str 文本
     * @param filePath 文件路径
     * @param fileName 文件名
     */
    void writeToFile(String str,String filePath,String fileName);

    /**
     * 修改文本中的内日哦那个
     * @param str 覆盖类容
     * @param filePath 文件地址
     * @param append 指定写入方式 如（append=true 则追加  and  append=false  则覆盖）
     */
    void modifyFile(String str,String filePath,boolean append);
}
