package com.ypkj.sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

import com.ypkj.sso.security.DomainUserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/login2.html", "/images/**", "/oauth/uncache_approvals", "/oauth/cache_approvals");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()//
				.requestMatchers().antMatchers("/oauth/**", "/login/**", "/logout/**")//
				.and()//
				.authorizeRequests().anyRequest().authenticated()//
				.and()//
				.formLogin()//
				.loginProcessingUrl("/login")// 自定义登录链接和页面
				.loginPage("/login2.html").permitAll()//
				.and()//
				.logout().permitAll();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new DomainUserDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());//
		// .passwordEncoder(passwordEncoder());
	}

	@Bean
	public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
		return new SecurityEvaluationContextExtension();
	}

	// 不定义没有password grant_type
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
