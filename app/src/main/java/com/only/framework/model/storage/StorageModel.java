package com.only.framework.model.storage;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2019/1/10.
 */

public class StorageModel implements IStorageModel {

    /**
     * 删除
     *
     * @param path
     */
    @Override
    public boolean deleteFile(String path) {
        boolean delete = false;
        File file = new File(path);
        if (!file.isDirectory() || file.listFiles().length == 0) {
            delete = file.delete();
        } else {
            List<File> fIle = getFIle(new File(path));
            Log.e("text", fIle.size() + "");

            for (int i = 0; i < fIle.size(); i++) {
                File f = fIle.get(i);

                Log.e("text", file.getAbsolutePath());
                delete = f.delete();
            }
        }
        return delete;
    }

    /**
     * 重命名
     *
     * @param fromFile
     * @param toFile
     */
    @Override
    public void renameFile(String fromFile, String toFile) {

        File oleFile = new File(fromFile);
        File newFile = new File(toFile);
        oleFile.renameTo(newFile);
    }

    /**
     * 创建文件夹
     *
     * @param filePath
     * @param fileName
     */
    @Override
    public boolean createFile(String filePath, String fileName) {
        File file = new File(filePath + "/" + fileName);
        if (!file.exists()) {//判断如果文件不存在则创建文件
            return file.mkdir();
        }
        return false;
    }

    /**
     * 在文件夹中添加文本
     *
     * @param str      文本
     * @param filePath 文件路径
     * @param fileName 文件名
     */
    @Override
    public void writeToFile(String str, String filePath, String fileName) {
        String path = filePath + "/" + fileName;
        File file = new File(path);
        RandomAccessFile randomAccessFile = null;
        try {
            //实例RandomAccessFile，第二次参数为读写方式
            randomAccessFile = new RandomAccessFile(file, "rw");
            //将记录指针移动到该文件最后
            randomAccessFile.seek(file.length());
            //向文件末尾添加数据
            randomAccessFile.write(str.getBytes());
            //关闭randomAccessFile
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param str      覆盖类容
     * @param filePath 文件地址
     * @param append   指定写入方式 如（append=true 则追加  and  append=false  则覆盖）
     */
    @Override
    public void modifyFile(String str, String filePath, boolean append) {
        try {
            FileWriter fileWriter = new FileWriter(new File(filePath), append);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.append(str);
            writer.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<File> getFIle(File file) {
        List<File> list = new ArrayList<>();
        File[] fileArray = file.listFiles();
        list.add(file);
        Log.e("text", "true" + fileArray.length);
        if (fileArray.length > 0) {
            for (File f : fileArray) {
                list.add(0, f);
            }
        } else {
            list.add(0, file);
        }
        return list;
    }
}
