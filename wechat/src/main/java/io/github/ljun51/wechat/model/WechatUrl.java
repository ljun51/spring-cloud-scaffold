package io.github.ljun51.wechat.model;

public enum WechatUrl {

    MENU_CREATE("创建自定义菜单", "/cgi-bin/menu/create?access_token={ACCESS_TOKEN}"),
    MENU_DELETE("删除自定义菜单", "/cgi-bin/menu/delete?access_token={ACCESS_TOKEN}"),
    MENU_GET("获取自定义菜单", "/cgi-bin/menu/get?access_token={ACCESS_TOKEN}"),
    TOKEN("获取Access token", "/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}");

    private String url;

    private String name;

    private WechatUrl(String name, String url) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

}