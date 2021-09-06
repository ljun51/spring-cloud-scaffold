package io.github.ljun51.wechat.model;

public class WechatResp {

    private final int ok = 0;

    private Integer errcode;

    private String errmsg;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Boolean isSuccess() {
        return errcode == null || ok == this.errcode;
    }

    @Override
    public String toString() {
        return "WechatResp [errcode=" + errcode + ", errmsg=" + errmsg + "]" + super.toString();
    }

}