package com.ypkj.sso.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ypkj.sso.dao.entity.SysUser;
import com.ypkj.sso.dao.mapper.SysUserMapper;

import java.util.Set;

//@Service("userDetailsService")
@Slf4j
public class DomainUserDetailsService implements UserDetailsService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String lowcaseUsername = username.toLowerCase();
		SysUser realUser = sysUserMapper.selectUserAuthoirty("admin");

		// 上面避免了我们类似 Java 8 之前的做法
		if (realUser != null) {
			Set<GrantedAuthority> grantedAuthorities = realUser.getAuthorities();
			return new User(realUser.getAccount(), realUser.getPassword(), grantedAuthorities);
		} else {
			throw new UsernameNotFoundException("用户" + lowcaseUsername + "不存在!");
		}
		// return realUser.map(user -> {
		// Set<GrantedAuthority> grantedAuthorities = user.getAuthorities();
		// return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
		// }).orElseThrow(() -> new UsernameNotFoundException("用户" + lowcaseUsername +
		// "不存在!"));
	}
}
