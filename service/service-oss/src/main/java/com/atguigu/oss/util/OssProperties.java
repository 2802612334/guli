package com.atguigu.oss.util;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
// InitializingBean参考：https://www.cnblogs.com/weiqihome/p/8922937.html
@Component
@ConfigurationProperties(prefix = "aliyun.oss.file")
public class OssProperties implements InitializingBean {

    private String endopint;
    private String keyid;
    private String keysecret;
    private String bucketname;

    public static String ENDOPINT;
    public static String KEYID;
    public static String KEYSECRET;
    public static String BUCKETNAME;


    @Override
    public void afterPropertiesSet() throws Exception {
        ENDOPINT = this.endopint;
        KEYID = this.keyid;
        KEYSECRET = this.keysecret;
        BUCKETNAME = this.bucketname;
    }
}
