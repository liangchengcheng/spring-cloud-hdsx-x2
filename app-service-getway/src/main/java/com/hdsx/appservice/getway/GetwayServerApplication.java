package com.hdsx.appservice.getway;

import com.hdsx.appservice.getway.filter.TokenFilter;
import com.hdsx.appservice.getway.filter.UriKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@EnableFeignClients(basePackages = "com.hdsx.appservice")
@SpringBootApplication
public class GetwayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetwayServerApplication.class, args);
	}

	/*
	 * @ClassName SpringcloudGatewayMasterServiceApplication
	 * @Desc TODO   配置限流 Bean
	 * @Date 2019/6/29 17:12
	 * @Version 1.0
	 */
	@Bean(name = "uriKeyResolver")
	public UriKeyResolver uriKeyResolver() {
		return new UriKeyResolver();
	}

	/*
	 * @ClassName SpringcloudGatewayMasterServiceApplication
	 * @Desc TODO   配置认证过滤器 Bean
	 * @Date 2019/6/29 17:58
	 * @Version 1.0
	 */
	@Bean(name = "tokenFilter")
	public TokenFilter tokenFilter() {
		return new TokenFilter();
	}

}
