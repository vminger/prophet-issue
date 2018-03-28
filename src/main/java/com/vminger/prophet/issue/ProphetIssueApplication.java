package com.vminger.prophet.issue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProphetIssueApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProphetIssueApplication.class, args);
	}
}
