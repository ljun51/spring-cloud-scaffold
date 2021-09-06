package io.github.ljun51.wechat.service.impl;

import com.github.pagehelper.PageHelper;
import io.github.ljun51.common.util.Utils;
import io.github.ljun51.wechat.config.WechatConfig;
import io.github.ljun51.wechat.mapper.ProgramMapper;
import io.github.ljun51.wechat.model.Program;
import io.github.ljun51.wechat.model.TokenResp;
import io.github.ljun51.wechat.service.ProgramService;
import io.github.ljun51.wechat.service.WechatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramMapper programMapper;

    private final WechatService wechatService;

    private final WechatConfig wechatConfig;

    public ProgramServiceImpl(final ProgramMapper programMapper, final WechatService wechatService,
                              final WechatConfig wechatConfig) {
        this.programMapper = programMapper;
        this.wechatService = wechatService;
        this.wechatConfig = wechatConfig;
    }

    @Override
    public List<Program> selectList(Program data, int page, int size, String orderBy) {
        PageHelper.startPage(page, size, orderBy);
        return programMapper.selectList(data);
    }

    @Override
    public Program insert(Program data) {
        data.setId(Utils.uuid());
        data.setStatus(data.getStatus() != null ? data.getStatus() : Boolean.TRUE);
        data.setCreateDate(new Date());
        data.setUpdateDate(new Date());
        programMapper.insert(data);
        return data;
    }

    @Override
    public Program update(Program data) {
        Assert.hasLength(data.getId(), "id can not be empty!");
        data.setUpdateDate(new Date());
        programMapper.update(data);
        return selectById(data.getId());
    }

    public Program selectOne(Program data) {
        return programMapper.selectOne(data);
    }

    @Override
    public Program selectById(String id) {
        Program data = new Program();
        data.setId(id);
        return selectOne(data);
    }

    public Program selectByAppid(String appid) {
        Program data = new Program();
        data.setAppid(appid);
        return selectOne(data);
    }

    @Override
    public boolean delete(String id) {
        Assert.hasLength(id, "id can not be empty!");
        return 1 == programMapper.delete(id);
    }

    @Override
    public String access_token() {
        Program program = selectByAppid(wechatConfig.getAppid());
        return getAccessTokenByDatabase(program);
    }

    @Override
    public String access_token(String id) {
        Program program = selectById(id);
        return getAccessTokenByDatabase(program);
    }

    private String getAccessTokenByDatabase(Program program) {
        if (program != null) {
            if (StringUtils.isNotEmpty(program.getAccess_token()) && program.getExpires_in().after(new Date())) {
                return program.getAccess_token();
            } else {
                return getAccessTokenByWechat(program);
            }
        }
        throw new RuntimeException("program can not be found!");
    }

    /**
     * 从微信获取access_token
     *
     * @param program 需要更新的App
     * @return access_token
     */
    private String getAccessTokenByWechat(Program program) {
        TokenResp tokenResp = wechatService.token(program.getAppid(), program.getAppsecret());
        if (tokenResp.isSuccess()) {
            program.setAccess_token(tokenResp.getAccess_token());
            program.setExpires_in(new Date(System.currentTimeMillis() + tokenResp.getExpires_in() * 1000));
            update(program);
            return program.getAccess_token();
        } else {
            throw new RuntimeException(tokenResp.getErrcode() + ": " + tokenResp.getErrmsg());
        }
    }

}