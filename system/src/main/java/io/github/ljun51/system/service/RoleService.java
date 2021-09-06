package io.github.ljun51.system.service;

import io.github.ljun51.system.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> selectList(Role data, int page, int size, String orderBy);

    Role insert(Role data);

    Role update(Role data);

    Role selectOne(String id);

    boolean delete(String id);

}
