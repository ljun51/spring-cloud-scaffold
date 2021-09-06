package io.github.ljun51.wechat.web;

import com.github.pagehelper.PageInfo;
import io.github.ljun51.common.util.Const;
import io.github.ljun51.common.util.Utils;
import io.github.ljun51.wechat.model.Program;
import io.github.ljun51.wechat.service.ProgramService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/program")
public class ProgramController {

    private final ProgramService programService;

    public ProgramController(final ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    public Map<Object, Object> selectList(@RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "status", required = false) String status,
                                          @RequestParam(value = "orderBy", required = false) String orderBy,
                                          @RequestParam(value = "page", defaultValue = Const.PAGE) int page,
                                          @RequestParam(value = "size", defaultValue = Const.SIZE) int size) {
        Program data = new Program();
        data.setName(name);
        if (StringUtils.isNotEmpty(status)) {
            data.setStatus(Boolean.parseBoolean(status));
        }

        List<Program> dataList = programService.selectList(data, page, size, orderBy);
        PageInfo<Program> pageInfo = new PageInfo<>(dataList);
        return Utils.body("dataList", pageInfo.getList(), "total", pageInfo.getTotal());
    }

    @PostMapping
    public Map<Object, Object> insert(@Valid @RequestBody Program data) {
        return Utils.body("data", programService.insert(data));
    }

    @PutMapping("/{id}")
    public Map<Object, Object> update(@PathVariable String id, @Valid @RequestBody Program data) {
        data.setId(id);
        return Utils.body("data", programService.update(data));
    }

    @GetMapping("/{id}")
    public Map<Object, Object> selectOne(@PathVariable String id) {
        return Utils.body("data", programService.selectById(id));
    }

    @DeleteMapping("/{id}")
    public Map<Object, Object> delete(@PathVariable String id) {
        return Utils.body("data", programService.delete(id));
    }
}