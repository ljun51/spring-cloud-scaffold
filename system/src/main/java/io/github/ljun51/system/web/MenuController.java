package io.github.ljun51.system.web;

import com.github.pagehelper.PageInfo;
import io.github.ljun51.common.util.Const;
import io.github.ljun51.common.util.Utils;
import io.github.ljun51.system.model.Menu;
import io.github.ljun51.system.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(final MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public Map<Object, Object> selectList(@RequestParam(value = "menuName", required = false) String menuName,
                                          @RequestParam(value = "status", required = false) String status,
                                          @RequestParam(value = "orderBy", defaultValue = "sort asc") String orderBy,
                                          @RequestParam(value = "page", defaultValue = Const.PAGE) int page,
                                          @RequestParam(value = "size", defaultValue = Const.SIZE) int size) {
        Menu data = new Menu();
        data.setMenuName(menuName);
        if (StringUtils.isNotEmpty(status)) {
            data.setStatus(Boolean.parseBoolean(status));
        }

        List<Menu> dataList = menuService.selectList(data, page, size, orderBy);
        PageInfo<Menu> pageInfo = new PageInfo<>(dataList);
        return Utils.body("dataList", pageInfo.getList(), "total", pageInfo.getTotal());
    }

    @PostMapping
    public Map<Object, Object> insert(@Valid @RequestBody Menu data) {
        return Utils.body("data", menuService.insert(data));
    }

    @PutMapping("/{id}")
    public Map<Object, Object> update(@PathVariable String id, @Valid @RequestBody Menu data) {
        data.setId(id);
        return Utils.body("data", menuService.update(data));
    }

    @GetMapping("/{id}")
    public Map<Object, Object> selectOne(@PathVariable String id) {
        return Utils.body("data", menuService.selectOne(id));
    }

    @DeleteMapping("/{id}")
    public Map<Object, Object> delete(@PathVariable String id) {
        return Utils.body("data", menuService.delete(id));
    }
}