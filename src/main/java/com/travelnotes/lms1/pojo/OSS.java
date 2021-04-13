package com.travelnotes.lms1.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oss")
public class OSS {
    private String endpoint;
    private String keyid;
    private String keysecret;
    private String bucketname;
    private String filehost;
    private String headPicbucket;
}
