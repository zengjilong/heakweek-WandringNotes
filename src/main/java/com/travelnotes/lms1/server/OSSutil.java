package com.travelnotes.lms1.server;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.travelnotes.lms1.pojo.OSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class OSSutil {
    com.aliyun.oss.OSS ossClient;
    @Autowired
    private OSS ossProperties;

        public List<String> getPicList(String bucketName){
        List<String> listPic = new ArrayList<>();
        //连接阿里云OSS对象存储
        String endpoint = ossProperties.getEndpoint();
        String accessKeyId = ossProperties.getKeyid();
        String accessKeySecret = ossProperties.getKeysecret();

        try{
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            ObjectListing lists = ossClient.listObjects(bucketName);
            for (OSSObjectSummary ossObjectSummary: lists.getObjectSummaries()){
                //获取图片url
                listPic.add(ossObjectSummary.getKey());
            }
            return listPic;
        }catch(Exception e){
            return new ArrayList<>();
        }finally {
            ossClient.shutdown();
        }
    }

}
