package com.ypkj.sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.AuditorAware;

import com.ypkj.sso.security.SecurityUtils;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.ypkj.sso.dao.mapper")
@ComponentScan(value = {"com.ypkj.sso"})
public class SsoApplication {

	@Bean(name = "auditorAware")
	public AuditorAware<String> auditorAware() {
		return ()-> SecurityUtils.getCurrentUserUsername();
	}

	public static void main(String[] args) {
		SpringApplication.run(SsoApplication.class, args);
	}

}
