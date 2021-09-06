package io.github.ljun51.system.service;

import io.github.ljun51.system.model.UserRole;

import java.util.List;

public interface UserRoleService {

    List<UserRole> selectList(String userId);

    void insert(String userId, List<UserRole> roleList);

    void update(String userId, List<UserRole> roleList);

    int delete(String userId, String roleId);
}