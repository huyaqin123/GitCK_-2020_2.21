package com.bdqn.mall.mall.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.mall.common.utils.FileUploadUtils;
import com.bdqn.mall.common.utils.SystemConstants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileController {


    /**
     * 文件上传
     * @param attach
     * @return
     */
    @RequestMapping("/uploadFile")
    public String uploadFile(MultipartFile attach){
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        //判断是否有选中文件
        if(!attach.isEmpty()){
            //获取源文件的名称
            String oldName = attach.getOriginalFilename();
            //重命名文件名称
            String newFileName = FileUploadUtils.createNewFileName(oldName);
            //解决文件夹下图片文件过多的问题（以当前系统日期作为文件夹名称）
            String datePath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            //组装新名称
            String finalName = datePath + "/" + newFileName + "_temp";
            //构造文件对象(IMAGE_UPLOAD_PATH:文件上传的地址)
            File dest = new File(SystemConstants.IMAGE_UPLOAD_PATH,finalName);
            //判断文件是否存在
            if(!dest.getParentFile().exists()){
                //不存在则创建文件夹
                dest.getParentFile().mkdirs();
            }
            try {
                //将图片文件写入到磁盘
                attach.transferTo(dest);
                //将生成的图片路径保存在map集合，目的是图片回显
                map.put("imagePath",finalName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return JSON.toJSONString(map);
    }


}
