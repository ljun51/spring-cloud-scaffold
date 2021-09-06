package io.github.ljun51.system.mapper;

import io.github.ljun51.system.model.Menu;

import java.util.List;

public interface MenuMapper {

    List<Menu> selectList(Menu data);

    Menu selectOne(String id);

    int insert(Menu data);

    int update(Menu data);

    int delete(String id);

}
