package io.github.ljun51.system.mapper;

import io.github.ljun51.system.model.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuMapper {

    List<RoleMenu> selectList(String roleId);

    int insert(RoleMenu data);

    int delete(@Param("roleId") String roleId, @Param("menuId") String menuId);

}
