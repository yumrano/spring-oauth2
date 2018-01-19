package com.ypkj.sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private RedisConnectionFactory connectionFactory;

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public RedisTokenStore tokenStore() {
		return new RedisTokenStore(connectionFactory);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)//
				// 若无，refresh_token会有UserDetailsService is required错误
				.userDetailsService(userDetailsService)//
				.tokenStore(tokenStore());
		endpoints.tokenServices(defaultTokenServices());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
		// enable client to get the authenticated when using the /oauth/token to get a
		// access token
		// there is a 401 authentication is required if it doesn't allow form
		// authentication for clients when access /oauth/token
		security.allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()//
				.withClient("ypkj_test").scopes("select").secret("ypkj_test123").authorizedGrantTypes("authorization_code", "refresh_token").autoApprove(true)//
//				.and().withClient("webapp").scopes("select").authorizedGrantTypes("implicit")//
				.and().withClient("sys_ypkj").scopes("select").secret("ypkj888...").authorizedGrantTypes("password", "refresh_token");
	}

	/**
	 * <p>
	 * 注意，自定义TokenServices的时候，需要设置@Primary，否则报错，
	 * </p>
	 * 
	 * @return
	 */
	@Primary
	@Bean
	public DefaultTokenServices defaultTokenServices() {
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(tokenStore());
		tokenServices.setSupportRefreshToken(true);
		// tokenServices.setClientDetailsService(clientDetails());
		tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12); // token有效期自定义设置，默认12小时
		tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);// 默认30天，这里修改
		return tokenServices;
	}

}
