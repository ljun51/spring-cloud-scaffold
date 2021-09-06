package io.github.ljun51.system.web;

import com.github.pagehelper.PageInfo;
import io.github.ljun51.common.util.Const;
import io.github.ljun51.common.util.Utils;
import io.github.ljun51.system.model.Role;
import io.github.ljun51.system.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(final RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public Map<Object, Object> selectList(@RequestParam(value = "roleName", required = false) String roleName,
                                          @RequestParam(value = "status", required = false) String status,
                                          @RequestParam(value = "orderBy", required = false) String orderBy,
                                          @RequestParam(value = "page", defaultValue = Const.PAGE) int page,
                                          @RequestParam(value = "size", defaultValue = Const.SIZE) int size) {
        Role data = new Role();
        data.setRoleName(roleName);
        if (StringUtils.isNotEmpty(status)) {
            data.setStatus(Boolean.parseBoolean(status));
        }

        List<Role> dataList = roleService.selectList(data, page, size, orderBy);
        PageInfo<Role> pageInfo = new PageInfo<>(dataList);
        return Utils.body("dataList", pageInfo.getList(), "total", pageInfo.getTotal());
    }

    @PostMapping
    public Map<Object, Object> insert(@Valid @RequestBody Role data) {
        return Utils.body("data", roleService.insert(data));
    }

    @PutMapping("/{id}")
    public Map<Object, Object> update(@PathVariable String id, @Valid @RequestBody Role data) {
        data.setId(id);
        return Utils.body("data", roleService.update(data));
    }

    @GetMapping("/{id}")
    public Map<Object, Object> selectOne(@PathVariable String id) {
        return Utils.body("data", roleService.selectOne(id));
    }

    @DeleteMapping("/{id}")
    public Map<Object, Object> delete(@PathVariable String id) {
        return Utils.body("data", roleService.delete(id));
    }
}