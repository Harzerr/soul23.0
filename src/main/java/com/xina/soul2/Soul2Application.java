package com.xina.soul2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Soul2Application {

	// 后台接口启动
	public static void main(String[] args) {
		SpringApplication.run(Soul2Application.class, args);
	}

}
