package io.github.ljun51.wechat.web;

import com.github.pagehelper.PageInfo;
import io.github.ljun51.common.util.Const;
import io.github.ljun51.common.util.Utils;
import io.github.ljun51.wechat.model.Menu;
import io.github.ljun51.wechat.service.MenuService;
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

    @GetMapping()
    public Map<Object, Object> selectList(@RequestParam(value = "programId") String programId,
                                          @RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "status", required = false) Boolean status,
                                          @RequestParam(value = "orderBy", defaultValue = "sort asc") String orderBy,
                                          @RequestParam(value = "page", defaultValue = Const.PAGE) int page,
                                          @RequestParam(value = "size", defaultValue = Const.SIZE) int size) {
        Menu data = new Menu();
        data.setProgramId(programId);
        data.setName(name);
        data.setStatus(status);
        List<Menu> dataList = menuService.selectList(data, page, size, orderBy);
        PageInfo<Menu> pageInfo = new PageInfo<>(dataList);
        return Utils.body("dataList", pageInfo.getList(), "total", pageInfo.getTotal());
    }

    @GetMapping("/{id}")
    public Map<Object, Object> selectOne(@PathVariable String id) {
        return Utils.body("data", menuService.selectOne(id));
    }

    @PostMapping()
    public Map<Object, Object> insert(@RequestBody @Valid Menu data) {
        return Utils.body("data", menuService.insert(data));
    }

    @PutMapping("/{id}")
    public Map<Object, Object> update(@PathVariable String id, @RequestBody @Valid Menu data) {
        data.setId(id);
        return Utils.body("data", menuService.update(data));
    }

    @DeleteMapping("/{id}")
    public Map<Object, Object> delete(@PathVariable String id) {
        return Utils.body("data", menuService.delete(id));
    }

    @GetMapping("/wechat/{programId}")
    public Map<Object, Object> getMenu(@PathVariable String programId) {
        return Utils.body("data", menuService.getMenu(programId));
    }

    @PostMapping("/wechat/{programId}")
    public Map<Object, Object> createMenu(@PathVariable String programId) {
        return Utils.body("data", menuService.createMenu(programId));
    }

    @DeleteMapping("/wechat/{programId}")
    public Map<Object, Object> deleteMenu(@PathVariable String programId) {
        return Utils.body("data", menuService.deleteMenu(programId));
    }
}