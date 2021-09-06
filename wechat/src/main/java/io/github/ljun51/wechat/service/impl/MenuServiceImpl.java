package io.github.ljun51.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import io.github.ljun51.common.util.Utils;
import io.github.ljun51.wechat.mapper.MenuMapper;
import io.github.ljun51.wechat.model.Menu;
import io.github.ljun51.wechat.model.MenuResp;
import io.github.ljun51.wechat.model.WechatResp;
import io.github.ljun51.wechat.service.MenuService;
import io.github.ljun51.wechat.service.ProgramService;
import io.github.ljun51.wechat.service.WechatService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;
    private final ProgramService programService;
    private final WechatService wechatService;

    public MenuServiceImpl(final MenuMapper menuMapper, final ProgramService programService,
                           final WechatService wechatService) {
        this.menuMapper = menuMapper;
        this.programService = programService;
        this.wechatService = wechatService;
    }

    @Override
    public Menu selectOne(String id) {
        return menuMapper.selectOne(id);
    }

    @Override
    public Menu insert(Menu data) {
        data.setId(Utils.uuid());
        data.setStatus(data.getStatus() != null ? data.getStatus() : Boolean.TRUE);
        data.setSort(data.getSort() != null ? data.getSort() : 0);
        data.setCreateDate(new Date());
        data.setUpdateDate(new Date());
        menuMapper.insert(data);
        return data;
    }

    @Override
    public List<Menu> selectList(Menu data, int page, int size, String orderBy) {
        PageHelper.startPage(page, size, orderBy);
        return menuMapper.selectList(data);
    }

    @Override
    public Menu update(Menu data) {
        Assert.hasLength(data.getId(), "id can not be empty!");
        data.setUpdateDate(new Date());
        menuMapper.update(data);
        return selectOne(data.getId());
    }

    @Override
    public boolean delete(String id) {
        Assert.hasLength(id, "id can not be empty!");
        return 1 == menuMapper.delete(id);
    }

    @Override
    public WechatResp createMenu(String programId) {
        String access_token = programService.access_token(programId);
        Menu menu = new Menu();
        menu.setProgramId(programId);
        List<Menu> dataList = menuMapper.selectList(menu);
        if (dataList == null || dataList.size() == 0) {
            throw new RuntimeException("No menu need create!");
        }
        System.out.println(JSON.toJSON(dataList));
        return null;
        // ButtonResp buttonReq = new ButtonResp();
        // // 一级菜单
        // List<Menu> button = new ArrayList<>();
        // for (Menu menu: dataList) {
        //     if (StringUtils.isEmpty(menu.getMenuId())) {
        //         button.add(menu);
        //     }
        // }
        // // 二级菜单
        // List<Menu> sub_button = new ArrayList<>();
        // for (Menu menu: dataList) {
        //     if (StringUtils.isNotEmpty(menu.getMenuId()) ) {

        //     }
        // }

        // return wechatService.createMenu(access_token, buttonReq);
    }

    @Override
    public MenuResp getMenu(String programId) {
        String access_token = programService.access_token(programId);
        return wechatService.getMenu(access_token);
    }

    @Override
    public WechatResp deleteMenu(String programId) {
        String access_token = programService.access_token(programId);
        return wechatService.deleteMenu(access_token);
    }

}