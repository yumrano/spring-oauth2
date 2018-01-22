package com.ypkj.sys.dao.mapper;

import com.ypkj.sys.dao.entity.SysRoleAuthorityKey;

public interface SysRoleAuthorityMapper {
    int deleteByPrimaryKey(SysRoleAuthorityKey key);

    int insert(SysRoleAuthorityKey record);

    int insertSelective(SysRoleAuthorityKey record);
}