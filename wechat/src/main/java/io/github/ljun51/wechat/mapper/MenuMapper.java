package io.github.ljun51.wechat.mapper;

import io.github.ljun51.wechat.model.Menu;

import java.util.List;

public interface MenuMapper {

    Menu selectOne(String id);

    List<Menu> selectList(Menu data);

    int insert(Menu data);

    int update(Menu data);

    int delete(String id);

}
