package io.github.ljun51.system.service;

import io.github.ljun51.system.model.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> selectList(Menu data, int page, int size, String orderBy);

    Menu insert(Menu data);

    Menu update(Menu data);

    Menu selectOne(String id);

    boolean delete(String id);

}
