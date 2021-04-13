package com.travelnotes.lms1.controller;

import com.travelnotes.lms1.mapper.HKMapper;
import com.travelnotes.lms1.pojo.OSS;
import com.travelnotes.lms1.pojo.Pic;
import com.travelnotes.lms1.server.OSSutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@RestController
public class PicController {

    @Autowired
    private OSSutil ossUtilProperties;

    @Autowired
    private OSS ossProperties;

    @Autowired
    private HKMapper picMapper;

    @GetMapping("/getRandPic")
    public Pic getRandomPic() throws IOException {
        String bucketName = ossProperties.getBucketname();
        //获取图片列表
        List<String> picList = ossUtilProperties.getPicList(bucketName);
        //随机图片序号
        int pic = new Random().nextInt(picList.size());
        String pic_text = picMapper.getPicText(pic);
//        System.out.println(ossProperties.getFilehost() + picList.get(pic));
        return new Pic(ossProperties.getFilehost() + picList.get(pic), pic_text);
    }


}
