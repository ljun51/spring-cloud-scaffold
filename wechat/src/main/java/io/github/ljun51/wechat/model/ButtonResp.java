package io.github.ljun51.wechat.model;

import java.util.List;

public class ButtonResp {

    /**
     * 一级菜单数组，个数应为1~3个
     */
    private List<Menu> button;

    public List<Menu> getButton() {
        return button;
    }

    public void setButton(List<Menu> button) {
        this.button = button;
    }

}