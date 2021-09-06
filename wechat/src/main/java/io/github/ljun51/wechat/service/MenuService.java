package io.github.ljun51.wechat.service;

import io.github.ljun51.wechat.model.Menu;
import io.github.ljun51.wechat.model.MenuResp;
import io.github.ljun51.wechat.model.WechatResp;

import java.util.List;

public interface MenuService {

    List<Menu> selectList(Menu data, int page, int size, String orderBy);

    Menu selectOne(String id);

    Menu insert(Menu data);

    Menu update(Menu data);

    boolean delete(String id);

    WechatResp createMenu(String programId);

    MenuResp getMenu(String programId);

    WechatResp deleteMenu(String programId);
}