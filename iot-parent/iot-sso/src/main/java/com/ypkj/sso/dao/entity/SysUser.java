package com.ypkj.sso.dao.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class SysUser {
    private Integer id;

    private String account;

    private String password;

    private String name;

    private Integer orgId;

    private String telephone;

    private String mobileTel;

    private String mail;

    private Integer state;

    private String remarks;

    private Integer createId;

    private Date createDate;

    private Integer updateId;

    private Date updateDate;
    
    private Set<SysAuthority> sysAuthoritys;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getMobileTel() {
        return mobileTel;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel == null ? null : mobileTel.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    public Set<SysAuthority> getSysAuthoritys() {
		return sysAuthoritys;
	}

	public void setSysAuthoritys(Set<SysAuthority> sysAuthoritys) {
		this.sysAuthoritys = sysAuthoritys;
	}
    
    public Set<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> userAuthotities = new HashSet<>();
		for (SysAuthority authority : this.getSysAuthoritys()) {
			userAuthotities.add(new SimpleGrantedAuthority(authority.getCode()));
		}

		return userAuthotities;
	}
}