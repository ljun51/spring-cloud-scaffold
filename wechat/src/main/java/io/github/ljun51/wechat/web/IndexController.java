package io.github.ljun51.wechat.web;

import io.github.ljun51.common.util.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class IndexController {

    @RequestMapping("/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/test")
    public Map<Object, Object> test() {
        return Utils.body("data", "ok");
    }

}