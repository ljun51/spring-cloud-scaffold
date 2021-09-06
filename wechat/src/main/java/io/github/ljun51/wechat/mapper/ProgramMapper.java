package io.github.ljun51.wechat.mapper;

import io.github.ljun51.wechat.model.Program;

import java.util.List;

public interface ProgramMapper {

    List<Program> selectList(Program data);

    int insert(Program data);

    int update(Program data);

    Program selectOne(Program data);

    int delete(String id);

}
