package com.hdsx.appservice.elk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 梁铖城
 * 2020年03月03日10:55:24
 * 服务注册中心
 */
@SpringBootApplication
@RestController
public class ElkServerApplication {

	public static final Logger logger = LoggerFactory.getLogger(ElkServerApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(ElkServerApplication.class, args);
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
