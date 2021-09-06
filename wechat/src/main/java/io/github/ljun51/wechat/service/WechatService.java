package io.github.ljun51.wechat.service;

import io.github.ljun51.wechat.model.ButtonResp;
import io.github.ljun51.wechat.model.MenuResp;
import io.github.ljun51.wechat.model.TokenResp;
import io.github.ljun51.wechat.model.WechatResp;

public interface WechatService {

    MenuResp getMenu(String access_token);

    WechatResp createMenu(String access_token, ButtonResp data);

    WechatResp deleteMenu(String access_token);

    TokenResp token(String appid, String appsecret);

}