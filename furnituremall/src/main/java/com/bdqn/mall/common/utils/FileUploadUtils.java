package com.bdqn.mall.common.utils;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件上传的工具栏
 */
public class FileUploadUtils {

    /**
     * 重命名新名称
     * @param oldName
     * @return
     */
    public static String createNewFileName(String oldName) {
        //获取源文件的后缀名
        String suffix = FilenameUtils.getExtension(oldName);
        return UUIDUtil.randomUUID()+"."+suffix;
    }

    /**
     * 修改文件名
     * @param goodsimg  商品名称
     * @return
     */
    public static String renameFile(String goodsimg) {
        //构建文件对象
        File file = new File(SystemConstants.IMAGE_UPLOAD_PATH,goodsimg);
        //修改文件名
        String newFileName = goodsimg.replace("_temp","");
        //判断文件是否存在
        if(file.exists()){
            file.renameTo(new File(SystemConstants.IMAGE_UPLOAD_PATH,newFileName));
        }
        return newFileName;
    }

    /**
     * 删除旧文件
     * @param oldFile
     */
    public static void deleteFile(String oldFile) {
        //判断旧文件名称是否是默认图片名称（不能删除默认图片）
        if(!oldFile.equals(SystemConstants.DEFAULT_GOODS_IMAGE)){
            //创建文件对象
            File file = new File(SystemConstants.IMAGE_UPLOAD_PATH,oldFile);
            //判断是否存在
            if(file.exists()){
                //删除
                file.delete();
            }
        }
    }

    /**
     * 定时删除文件
     */
    public static void deleteFile(){
        //获取当前日期
        String datePath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //构建文件对象
        File file = new File(SystemConstants.IMAGE_UPLOAD_PATH,datePath);
        //读取当前日期文件夹的文件
        File [] files = file.listFiles();
        //如果当前日期文件夹中有文件
        if(files!=null &&files.length>0){
            //循环遍历该文件夹中的文件
            for (File f : files) {
                //判断该文件夹中是否有后缀名为_temp的文件，有则删除
                if(f.getName().endsWith("_temp")){
                    f.delete();//删除
                }
            }
        }

    }
}
