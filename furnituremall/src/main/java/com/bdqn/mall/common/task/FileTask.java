package com.bdqn.mall.common.task;

import com.bdqn.mall.common.utils.FileUploadUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FileTask {

    /**
     * 每天定时删除
     */
    @Scheduled(cron = "0 59 23 * * ?")
    public void deleteFileTask(){
        //删除文件
        //调用定时删除图片的方法
        FileUploadUtils.deleteFile();
        System.out.println("文件在"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +"删除");
    }

}
