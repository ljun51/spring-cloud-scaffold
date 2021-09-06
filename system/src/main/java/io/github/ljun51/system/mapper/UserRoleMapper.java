package io.github.ljun51.system.mapper;

import io.github.ljun51.system.model.UserRole;

import java.util.List;

public interface UserRoleMapper {

    List<UserRole> selectList(String userId);

    int insert(UserRole data);

    int delete(String userId, String roleId);

}
