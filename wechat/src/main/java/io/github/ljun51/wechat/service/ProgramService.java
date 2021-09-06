package io.github.ljun51.wechat.service;

import io.github.ljun51.wechat.model.Program;

import java.util.List;

public interface ProgramService {

    List<Program> selectList(Program data, int page, int size, String orderBy);

    Program insert(Program data);

    Program update(Program data);

    Program selectById(String id);

    /**
     * 获取默认appid的access_token
     *
     * @return access_token
     */
    String access_token();

    /**
     * 获取指定id的access_token
     *
     * @return access_token
     */
    String access_token(String id);

    boolean delete(String id);

}
