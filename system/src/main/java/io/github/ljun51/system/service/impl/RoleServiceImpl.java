package io.github.ljun51.system.service.impl;

import com.github.pagehelper.PageHelper;
import io.github.ljun51.common.util.Utils;
import io.github.ljun51.system.mapper.RoleMapper;
import io.github.ljun51.system.model.Role;
import io.github.ljun51.system.service.RoleMenuService;
import io.github.ljun51.system.service.RoleService;
import io.github.ljun51.system.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleMapper roleMapper;

    private RoleMenuService roleMenuService;

    private UserRoleService userRoleService;

    public RoleServiceImpl(RoleMapper roleMapper, RoleMenuService roleMenuService, UserRoleService userRoleService) {
        this.roleMapper = roleMapper;
        this.roleMenuService = roleMenuService;
        this.userRoleService = userRoleService;
    }

    @Override
    public List<Role> selectList(Role data, int page, int size, String orderBy) {
        PageHelper.startPage(page, size, orderBy);
        return roleMapper.selectList(data);
    }

    @Override
    public Role insert(Role data) {
        data.setId(Utils.uuid());
        data.setRemark(StringUtils.isEmpty(data.getRemark()) ? data.getRoleName() : data.getRemark());
        data.setStatus(data.getStatus() != null ? data.getStatus() : Boolean.TRUE);
        data.setCreateDate(new Date());
        data.setUpdateDate(new Date());
        roleMapper.insert(data);

        roleMenuService.insert(data.getId(), data.getMenuList());
        return data;
    }

    @Override
    public Role update(Role data) {
        Assert.hasLength(data.getId(), "id can not be empty!");
        data.setUpdateDate(new Date());
        roleMapper.update(data);

        roleMenuService.update(data.getId(), data.getMenuList());
        return selectOne(data.getId());
    }

    @Override
    public Role selectOne(String id) {
        return roleMapper.selectOne(id);
    }

    @Override
    public boolean delete(String id) {
        Assert.hasLength(id, "id can not be empty!");
        roleMenuService.delete(id, null);
        userRoleService.delete(null, id);
        return 1 == roleMapper.delete(id);
    }
}