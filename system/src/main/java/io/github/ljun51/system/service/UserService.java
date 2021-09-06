package io.github.ljun51.system.service;

import io.github.ljun51.system.model.User;

import java.util.List;

public interface UserService {

    List<User> selectList(User data, int page, int size, String orderBy);

    User selectOne(String id);

    User insert(User data);

    User update(User data);

    boolean delete(String id);
}
