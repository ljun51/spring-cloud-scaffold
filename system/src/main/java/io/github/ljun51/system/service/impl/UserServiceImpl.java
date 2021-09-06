package io.github.ljun51.system.service.impl;

import com.github.pagehelper.PageHelper;
import io.github.ljun51.common.util.AvatarHelper;
import io.github.ljun51.common.util.Utils;
import io.github.ljun51.system.mapper.UserMapper;
import io.github.ljun51.system.model.User;
import io.github.ljun51.system.service.UserRoleService;
import io.github.ljun51.system.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    private UserRoleService userRoleService;

    public UserServiceImpl(UserMapper userMapper, UserRoleService userRoleService) {
        this.userMapper = userMapper;
        this.userRoleService = userRoleService;
    }

    private static String nickname() {
        return RandomStringUtils.randomAlphanumeric(6).toLowerCase();
    }

    @Override
    public List<User> selectList(User data, int page, int size, String orderBy) {
        PageHelper.startPage(page, size, orderBy);
        return userMapper.selectList(data);
    }

    @Override
    public User insert(User data) {
        data.setId(Utils.uuid());
        data.setPassword(StringUtils.isEmpty(data.getPassword()) ? Utils.md5(data.getUsername()) : data.getPassword());
        data.setNickname(StringUtils.isEmpty(data.getNickname()) ? nickname() : data.getNickname());
        data.setAvatar(AvatarHelper.avatar(data.getId()));
        data.setStatus(data.getStatus() != null ? data.getStatus() : Boolean.TRUE);
        data.setCreateDate(new Date());
        data.setUpdateDate(new Date());
        userMapper.insert(data);

        userRoleService.insert(data.getId(), data.getRoleList());
        return data;
    }

    @Override
    public User update(User data) {
        Assert.hasLength(data.getId(), "id can not be empty!");
        data.setUpdateDate(new Date());
        userMapper.update(data);

        userRoleService.update(data.getId(), data.getRoleList());
        return selectOne(data.getId());
    }

    @Override
    public User selectOne(String id) {
        User data = userMapper.selectOne(id);
        data.setRoleList(userRoleService.selectList(id));
        return data;
    }

    @Override
    public boolean delete(String id) {
        Assert.hasLength(id, "id can not be empty!");
        userRoleService.delete(id, null);
        return 1 == userMapper.delete(id);
    }

}