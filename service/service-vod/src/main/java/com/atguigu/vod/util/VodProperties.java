package com.atguigu.vod.util;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.vod.file")
public class VodProperties implements InitializingBean {

    private String keyid;
    private String keysecret;

    public static String KEYID;
    public static String KEYSECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        VodProperties.KEYID = this.keyid;
        VodProperties.KEYSECRET = this.keysecret;
    }
}
