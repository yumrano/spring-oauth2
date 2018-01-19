package com.ypkj.sso.dao.mapper;

import com.ypkj.sso.dao.entity.SysRoleOrgKey;

public interface SysRoleOrgMapper {
    int deleteByPrimaryKey(SysRoleOrgKey key);

    int insert(SysRoleOrgKey record);

    int insertSelective(SysRoleOrgKey record);
}