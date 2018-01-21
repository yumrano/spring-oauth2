package com.ypkj.sys.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	@GetMapping("/demo")
	@PreAuthorize("hasAuthority('root')")
	public String getDemo() {
		return "good";
	}

	@GetMapping("/demo2")
	@PreAuthorize("#oauth2.hasScope('select') AND hasAuthority('root')")
	public String getDemo2() {
		return "good2";
	}

	@GetMapping("/list-user")
	@PreAuthorize("hasRole('root')")
	public String listUser() {
//		List<SysUser> users = new ArrayList<>();
//		SysUser user = new SysUser();
//		user.setAccount("dm");
//		users.add(user);
		return "list-user";
	}
}
