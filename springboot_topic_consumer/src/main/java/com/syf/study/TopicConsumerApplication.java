package com.syf.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
public class TopicConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopicConsumerApplication.class, args);
		System.out.println("¶©ÔÄ³É¹¦£¬¶Ë¿Ú£º8082");
	}

}
