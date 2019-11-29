package com.symphony.metrics.micrometer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicrometerLegacyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrometerLegacyApplication.class, args);
	}
}
