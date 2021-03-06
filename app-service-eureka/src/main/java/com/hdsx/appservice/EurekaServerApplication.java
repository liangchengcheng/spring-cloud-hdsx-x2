package com.hdsx.appservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 梁铖城
 * 2020年03月03日10:55:24
 * 服务注册中心
 */
@EnableEurekaServer
@SpringBootApplication
@RestController
public class EurekaServerApplication {

	public static final Logger logger = LoggerFactory.getLogger(EurekaServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
		logger.error("lccccccccccccccc");
		logger.info("lccccccccccccccc");
		logger.debug("lccccccccccccccc");
		logger.error("lccccccccccccccc");
		logger.error("lccccccccccccccc");
		logger.debug("lccccccccccccccc");
		logger.error("lccccccccccccccc");
		logger.debug("lccccccccccccccc");
		logger.debug("lccccccccccccccc");
		logger.debug("lccccccccccccccc");
		logger.error("lccccccccccccccc");
	}

	@GetMapping("/ok")
	public String ok() {
		return "lcc 521";
	}

}
