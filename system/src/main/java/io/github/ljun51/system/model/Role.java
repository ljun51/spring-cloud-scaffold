package io.github.ljun51.system.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

public class Role {

    private String id;

    @NotEmpty
    private String roleName;

    private Boolean status;

    private String remark;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    private List<RoleMenu> menuList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public List<RoleMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<RoleMenu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return "Role [createDate=" + createDate + ", id=" + id + ", menuList=" + menuList + ", remark=" + remark
                + ", roleName=" + roleName + ", status=" + status + ", updateDate=" + updateDate + "]";
    }

}