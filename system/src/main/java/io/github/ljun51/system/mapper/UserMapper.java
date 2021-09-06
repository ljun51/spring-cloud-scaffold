package io.github.ljun51.system.mapper;

import io.github.ljun51.system.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<User> selectList(User data);

    User selectOne(@Param("id") String id);

    int insert(User data);

    int update(User data);

    int delete(@Param("id") String id);
}
