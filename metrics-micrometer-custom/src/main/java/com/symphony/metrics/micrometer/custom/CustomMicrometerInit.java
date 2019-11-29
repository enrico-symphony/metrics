package com.symphony.metrics.micrometer.custom;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomMicrometerInit {

    @Bean
    public MeterRegistry customMicrometerRegistry() {
        CustomMicrometerRegistry registry = new CustomMicrometerRegistry(new CustomMicrometerConfig(), Clock.SYSTEM);
        return registry;
    }

}
