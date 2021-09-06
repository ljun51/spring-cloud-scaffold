package io.github.ljun51.common.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Utils {

    /**
     * 返回Map形式的对象，参数必须为偶数个
     *
     * @param kvs 键值对
     * @return Map
     */
    public static Map<Object, Object> body(Object... kvs) {
        if (kvs.length % 2 != 0) {
            throw new RuntimeException("Params must be key, value pairs.");
        }
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < kvs.length; i += 2) {
            map.put(kvs[i], kvs[i + 1]);
        }
        return map;
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

}