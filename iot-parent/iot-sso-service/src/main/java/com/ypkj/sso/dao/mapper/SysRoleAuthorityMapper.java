package com.ypkj.sso.dao.mapper;

import com.ypkj.sso.dao.entity.SysRoleAuthorityKey;

public interface SysRoleAuthorityMapper {
    int deleteByPrimaryKey(SysRoleAuthorityKey key);

    int insert(SysRoleAuthorityKey record);

    int insertSelective(SysRoleAuthorityKey record);
}