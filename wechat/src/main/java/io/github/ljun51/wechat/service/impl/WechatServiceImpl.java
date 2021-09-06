package io.github.ljun51.wechat.service.impl;

import io.github.ljun51.wechat.config.WechatConfig;
import io.github.ljun51.wechat.model.*;
import io.github.ljun51.wechat.service.WechatService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WechatServiceImpl implements WechatService {

    private final RestTemplate restTemplate;

    private final WechatConfig wechatConfig;

    public WechatServiceImpl(RestTemplate restTemplate, WechatConfig wechatConfig) {
        this.restTemplate = restTemplate;
        this.wechatConfig = wechatConfig;
    }

    @Override
    public TokenResp token(String appid, String appsecret) {
        return restTemplate.getForObject(wechatConfig.getWechatApi() + WechatUrl.TOKEN.getUrl(), TokenResp.class, appid, appsecret);
    }

    @Override
    public MenuResp getMenu(String access_token) {
        return restTemplate.getForObject(wechatConfig.getWechatApi() + WechatUrl.MENU_GET.getUrl(), MenuResp.class, access_token);
    }

    @Override
    public WechatResp createMenu(String access_token, ButtonResp data) {
        return restTemplate.postForObject(wechatConfig.getWechatApi() + WechatUrl.MENU_CREATE.getUrl(), data, WechatResp.class, access_token);
    }

    @Override
    public WechatResp deleteMenu(String access_token) {
        return restTemplate.getForObject(wechatConfig.getWechatApi() + WechatUrl.MENU_DELETE.getUrl(), WechatResp.class, access_token);
    }
}