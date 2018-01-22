package com.ypkj.sys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ypkj.sys.dao.entity.SysUser;

@RestController
public class DemoController {
	@GetMapping("/demo")
	@PreAuthorize("hasAuthority('root')")
	public String getDemo() {
		return "good";
	}

	@GetMapping("/demo2")
	public String getDemo2() {
		return "good2";
	}

	@GetMapping("/list-user")
	public Object listUser() {
		List<SysUser> users = new ArrayList<>();
		SysUser user = new SysUser();
		user.setAccount("dm");
		users.add(user);
		return users;
	}
}
