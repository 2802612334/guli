package com.atguigu.eduservice.util;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wx.open")
public class VxOpenProperties implements InitializingBean {

    private String appid;
    private String appsecret;
    private String redirecturl;

    public static String APP_ID;
    public static String APP_SECRET;
    public static String REDIRECT_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        VxOpenProperties.APP_ID = this.appid;
        VxOpenProperties.APP_SECRET = this.appsecret;
        VxOpenProperties.REDIRECT_URL = this.redirecturl;
    }
}
