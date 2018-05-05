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

<<<<<<< HEAD:springboot-SpringSecurity1/src/main/java/com/us/example/domain/Permission.java
    private String method;
    
    //父节点id
    private int pid;
=======
    // 父节点id
    private String pid;
>>>>>>> a18485c54f5c6c6437f4b658621f637ab09ccf4f:commonSecurity/src/main/java/com/phy/common/security/entity/Permission.java

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

<<<<<<< HEAD:springboot-SpringSecurity1/src/main/java/com/us/example/domain/Permission.java
    public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getPid() {
=======
    public String getPid() {
    
>>>>>>> a18485c54f5c6c6437f4b658621f637ab09ccf4f:commonSecurity/src/main/java/com/phy/common/security/entity/Permission.java
        return pid;
    }

    public void setPid(String pid) {
    
        this.pid = pid;
    }

}
