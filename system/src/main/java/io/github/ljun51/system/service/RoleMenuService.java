package io.github.ljun51.system.service;

import io.github.ljun51.system.model.RoleMenu;

import java.util.List;

public interface RoleMenuService {

    List<RoleMenu> selectList(String roleId);

    void insert(String roleId, List<RoleMenu> menuList);

    void update(String roleId, List<RoleMenu> menuList);

    int delete(String roleId, String menuId);
}