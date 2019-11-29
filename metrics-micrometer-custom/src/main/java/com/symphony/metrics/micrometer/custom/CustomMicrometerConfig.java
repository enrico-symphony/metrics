package com.symphony.metrics.micrometer.custom;

import io.micrometer.core.instrument.step.StepRegistryConfig;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class CustomMicrometerConfig implements StepRegistryConfig {

    @Override
    public String prefix() {
        return "custom-console";
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public Duration step() {
        return Duration.of(5, ChronoUnit.SECONDS);
    }
}
