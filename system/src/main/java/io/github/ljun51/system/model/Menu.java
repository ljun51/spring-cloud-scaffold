package io.github.ljun51.system.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class Menu {

    private String id;

    @NotEmpty
    private String menuName;

    /**
     * 功能地址
     */
    private String menuUrl;

    /**
     * 父菜单ID
     */
    private String parentId;

    /**
     * 授权（如：user:list,user:create)
     */
    private String perms;

    /**
     * 类型：1目录，2菜单，3按钮
     */
    @NotEmpty
    private String type;

    /**
     * 图标样式
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态：0无效，1有效
     */
    private Boolean status;

    /**
     * 备注
     */
    private String remark;

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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return "Menu [createDate=" + createDate + ", icon=" + icon + ", id=" + id + ", menuName=" + menuName
                + ", menuUrl=" + menuUrl + ", parentId=" + parentId + ", perms=" + perms + ", remark=" + remark
                + ", sort=" + sort + ", status=" + status + ", type=" + type + ", updateDate=" + updateDate + "]";
    }

}