package io.github.ljun51.wechat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WechatConfig {

    @Value("${com.qq.weixin.api:https://api.weixin.qq.com}")
    private String wechatApi;

    @Value("${com.qq.weixin.appid:wx440c052777a8bbf2}")
    private String appid;

    public String getWechatApi() {
        return wechatApi;
    }

    public String getAppid() {
        return appid;
    }

}