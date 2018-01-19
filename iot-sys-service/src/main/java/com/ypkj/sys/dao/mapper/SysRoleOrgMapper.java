package com.ypkj.sys.dao.mapper;

import com.ypkj.sys.dao.entity.SysRoleOrgKey;

public interface SysRoleOrgMapper {
    int deleteByPrimaryKey(SysRoleOrgKey key);

    int insert(SysRoleOrgKey record);

    int insertSelective(SysRoleOrgKey record);
}