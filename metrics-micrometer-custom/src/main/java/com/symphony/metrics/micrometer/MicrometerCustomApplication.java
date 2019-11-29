package com.symphony.metrics.micrometer;

import com.symphony.metrics.micrometer.custom.CustomMicrometerRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicrometerCustomApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrometerCustomApplication.class, args);
	}
}
