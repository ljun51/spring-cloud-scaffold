package io.github.ljun51.wechat.model;

public class MenuResp extends WechatResp {

    private ButtonResp menu;

    public ButtonResp getMenu() {
        return menu;
    }

    public void setMenu(ButtonResp menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "MenuResp [menu=" + menu + "]";
    }

}