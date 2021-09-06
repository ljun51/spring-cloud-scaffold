package io.github.ljun51.system.service.impl;

import com.github.pagehelper.PageHelper;
import io.github.ljun51.common.util.Utils;
import io.github.ljun51.system.mapper.MenuMapper;
import io.github.ljun51.system.model.Menu;
import io.github.ljun51.system.service.MenuService;
import io.github.ljun51.system.service.RoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private MenuMapper menuMapper;

    private RoleMenuService roleMenuService;

    public MenuServiceImpl(MenuMapper menuMapper, RoleMenuService roleMenuService) {
        this.menuMapper = menuMapper;
        this.roleMenuService = roleMenuService;
    }

    @Override
    public List<Menu> selectList(Menu data, int page, int size, String orderBy) {
        PageHelper.startPage(page, size, orderBy);
        return menuMapper.selectList(data);
    }

    @Override
    public Menu insert(Menu data) {
        data.setId(Utils.uuid());
        data.setSort(data.getSort() != null ? data.getSort() : 0);
        data.setStatus(data.getStatus() != null ? data.getStatus() : Boolean.TRUE);
        data.setRemark(StringUtils.isEmpty(data.getRemark()) ? data.getMenuName() : data.getRemark());
        data.setCreateDate(new Date());
        data.setUpdateDate(new Date());
        menuMapper.insert(data);
        return data;
    }

    @Override
    public Menu update(Menu data) {
        Assert.hasLength(data.getId(), "id can not be empty!");
        data.setUpdateDate(new Date());
        menuMapper.update(data);
        return selectOne(data.getId());
    }

    @Override
    public Menu selectOne(String id) {
        return menuMapper.selectOne(id);
    }

    @Override
    public boolean delete(String id) {
        Assert.hasLength(id, "id can not be empty!");
        roleMenuService.delete(null, id);
        return 1 == menuMapper.delete(id);
    }

}