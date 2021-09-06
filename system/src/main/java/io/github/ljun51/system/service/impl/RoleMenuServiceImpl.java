package io.github.ljun51.system.service.impl;

import io.github.ljun51.common.util.Utils;
import io.github.ljun51.system.mapper.RoleMenuMapper;
import io.github.ljun51.system.model.RoleMenu;
import io.github.ljun51.system.service.RoleMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    private RoleMenuMapper roleMenuMapper;

    public RoleMenuServiceImpl(final RoleMenuMapper roleMenuMapper) {
        this.roleMenuMapper = roleMenuMapper;
    }

    @Override
    public List<RoleMenu> selectList(String roleId) {
        return roleMenuMapper.selectList(roleId);
    }

    @Override
    public void insert(String roleId, List<RoleMenu> menuList) {
        Assert.hasLength(roleId, "roleId can not be empty!");
        if (menuList != null && menuList.size() > 0) {
            for (RoleMenu data : menuList) {
                if (StringUtils.isNotEmpty(data.getMenuId())) {
                    data.setId(Utils.uuid());
                    data.setRoleId(roleId);
                    roleMenuMapper.insert(data);
                }
            }
        }
    }

    @Override
    public void update(String roleId, List<RoleMenu> menuList) {
        Assert.hasLength(roleId, "roleId can not be empty!");
        delete(roleId, null);
        insert(roleId, menuList);
    }

    @Override
    public int delete(String roleId, String menuId) {
        Assert.isTrue(StringUtils.isEmpty(roleId) && StringUtils.isEmpty(menuId), "roleId, menuId can not be empty!");
        return roleMenuMapper.delete(roleId, menuId);
    }
}