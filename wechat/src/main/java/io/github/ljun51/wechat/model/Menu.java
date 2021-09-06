package io.github.ljun51.wechat.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class Menu {

    private String id;

    /**
     * 程序ID
     */
    @NotEmpty
    private String programId;

    /**
     * 父级菜单ID
     */
    private String menuId;

    /**
     * 菜单标题，不超过16个字节，子菜单不超过60个字节
     */
    @NotEmpty
    private String name;

    /**
     * view、miniprogram类型必须 网页 链接，用户点击菜单可打开链接，不超过1024字节。
     * type为miniprogram时，不支持小程序的老版本客户端将打开本url。
     */
    private String url;

    /**
     * 菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
     */
    private String type;

    /**
     * miniprogram类型必须 小程序的appid（仅认证公众号可配置）
     */
    private String appid;

    /**
     * miniprogram类型必须 小程序的页面路径
     */
    private String pagepath;

    /**
     * click等点击类型必须 菜单KEY值，用于消息接口推送，不超过128字节
     */
    private String key;

    /**
     * media_id类型和view_limited类型必须 调用新增永久素材接口返回的合法media_id
     */
    private String media_id;

    private Integer sort;

    private Boolean status;

    /**
     * 二级菜单数组，个数应为1~5个
     */
    @Size(max = 5)
    private List<Menu> sub_button;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Menu> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<Menu> sub_button) {
        this.sub_button = sub_button;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Menu [appid=" + appid + ", createDate=" + createDate + ", id=" + id + ", key=" + key + ", media_id="
                + media_id + ", menuId=" + menuId + ", name=" + name + ", pagepath=" + pagepath + ", programId="
                + programId + ", sort=" + sort + ", status=" + status + ", sub_button=" + sub_button + ", type=" + type
                + ", updateDate=" + updateDate + ", url=" + url + "]";
    }

}
