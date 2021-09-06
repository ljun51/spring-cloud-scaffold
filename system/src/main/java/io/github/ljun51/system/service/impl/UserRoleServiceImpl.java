package io.github.ljun51.system.service.impl;

import io.github.ljun51.common.util.Utils;
import io.github.ljun51.system.mapper.UserRoleMapper;
import io.github.ljun51.system.model.UserRole;
import io.github.ljun51.system.service.UserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleMapper userRoleMapper;

    public UserRoleServiceImpl(final UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public List<UserRole> selectList(String userId) {
        return userRoleMapper.selectList(userId);
    }

    @Override
    public void insert(String userId, List<UserRole> roleList) {
        Assert.hasLength(userId, "userId can not be empty!");
        if (roleList != null && roleList.size() > 0) {
            for (UserRole data : roleList) {
                if (StringUtils.isNotEmpty(data.getRoleId())) {
                    data.setId(Utils.uuid());
                    data.setUserId(userId);
                    userRoleMapper.insert(data);
                }
            }
        }
    }

    @Override
    public void update(String userId, List<UserRole> roleList) {
        Assert.hasLength(userId, "userId can not be empty!");
        delete(userId, null);
        insert(userId, roleList);
    }

    @Override
    public int delete(String userId, String roleId) {
        Assert.isTrue(StringUtils.isEmpty(userId) && StringUtils.isEmpty(roleId), "userId, roleId can not be empty!");
        return userRoleMapper.delete(userId, roleId);
    }
}