package com.phy.common.security.entity;

/**
 * 
 * @desc: springboot-security
 * @author: tivy
 * @createTime: 2018-05-03 10:32:25
 * @history:
 * @version: v1.0
 */
public class Permission {

    private String id;

    // 权限名称
    private String name;

    // 权限描述
    private String descritpion;

    // 授权链接
    private String url;
    
    // 父节点id
    private String pid;

    public String getId() {
    
        return id;
    }

    public void setId(String id) {
    
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
    
        this.pid = pid;
    }

}
