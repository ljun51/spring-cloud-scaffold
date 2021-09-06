package io.github.ljun51.system.web;

import com.github.pagehelper.PageInfo;
import io.github.ljun51.common.util.Const;
import io.github.ljun51.common.util.Utils;
import io.github.ljun51.system.model.User;
import io.github.ljun51.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Map<Object, Object> selectList(@RequestParam(value = "username", required = false) String username,
                                          @RequestParam(value = "status", required = false) String status,
                                          @RequestParam(value = "orderBy", required = false) String orderBy,
                                          @RequestParam(value = "page", defaultValue = Const.PAGE) int page,
                                          @RequestParam(value = "size", defaultValue = Const.SIZE) int size) {
        User data = new User();
        data.setUsername(username);
        if (StringUtils.isNotEmpty(status)) {
            data.setStatus(Boolean.parseBoolean(status));
        }

        List<User> dataList = userService.selectList(data, page, size, orderBy);
        PageInfo<User> pageInfo = new PageInfo<>(dataList);
        return Utils.body("dataList", pageInfo.getList(), "total", pageInfo.getTotal());
    }

    @PostMapping
    public Map<Object, Object> insert(@Valid @RequestBody User data) {
        return Utils.body("data", userService.insert(data));
    }

    @PutMapping("/{id}")
    public Map<Object, Object> update(@PathVariable String id, @Valid @RequestBody User data) {
        data.setId(id);
        return Utils.body("data", userService.update(data));
    }

    @GetMapping("/{id}")
    public Map<Object, Object> selectOne(@PathVariable String id) {
        return Utils.body("data", userService.selectOne(id));
    }

    @DeleteMapping("/{id}")
    public Map<Object, Object> delete(@PathVariable String id) {
        return Utils.body("data", userService.delete(id));
    }
}