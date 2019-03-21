package com.syf.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class TopicProducerApplication {
	public static void main(String[] args) {
		SpringApplication.run(TopicProducerApplication.class, args);
		System.out.println("�����������ģ��˿ڣ�8085");
		}
}
