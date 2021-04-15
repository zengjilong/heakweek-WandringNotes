package com.travelsnotes.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class FileUploadUtil {

    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${file.fileUrl}")
    private String fileUrl;
    //返回文件地址
    public  String uploadPage(HttpServletRequest res,MultipartFile file) {
        if (file==null) return null;
        System.out.println(222);
        String fileName = System.currentTimeMillis()+file.getOriginalFilename();  //获取文件名+加个时间戳
        System.out.println(fileName);
        //图片访问URI(即除了协议、地址和端口号的URL)
        String savePath = uploadFolder+fileName;  //图片保存路径
        System.out.println(savePath);
        File saveFile = new File(savePath);
        System.out.println(savePath);
        if (!saveFile.exists()){
            saveFile.mkdirs();
        }
        try {
            file.transferTo(saveFile);  //将临时存储的文件移动到真实存储路径下
        } catch (IOException e) {
            return null;
        }
        //返回图片访问地址
        return fileUrl+fileName;
    }
}
