package io.github.ljun51.system.mapper;

import io.github.ljun51.system.model.Role;

import java.util.List;

public interface RoleMapper {

    List<Role> selectList(Role data);

    Role selectOne(String id);

    int insert(Role data);

    int update(Role data);

    int delete(String id);

}
