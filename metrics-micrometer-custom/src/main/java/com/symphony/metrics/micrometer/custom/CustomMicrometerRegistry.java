package com.symphony.metrics.micrometer.custom;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.step.StepMeterRegistry;
import io.micrometer.core.instrument.step.StepRegistryConfig;
import io.micrometer.core.instrument.util.MeterPartition;
import io.micrometer.core.instrument.util.NamedThreadFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * This class is inspired from {@link https://github.com/micrometer-metrics/micrometer/blob/master/implementations/micrometer-registry-appoptics/src/main/java/io/micrometer/appoptics/AppOpticsMeterRegistry.java }
 */
public class CustomMicrometerRegistry extends StepMeterRegistry {

    private static final ThreadFactory DEFAULT_THREAD_FACTORY = new NamedThreadFactory("custom-metrics-publisher");
    private final CustomMicrometerConfig config;

    public CustomMicrometerRegistry(CustomMicrometerConfig config, Clock clock) {
        super(config, clock);
        this.config = config;
        start(DEFAULT_THREAD_FACTORY);
    }

    /**
     * This is only a POC to try to write something in the console, emulating the dropwizard ConsoleReporter
     * but it is not fully working!
     */
    @Override
    protected void publish() {
        for (List<Meter> batch : MeterPartition.partition(this, config.batchSize())) {
            final List<String> meters = batch.stream()
                    .filter(meter -> meter instanceof Timer).map(meter -> (Timer)meter).map(timer -> String.format("%s: %f", timer.getId().getName(), timer.totalTime(TimeUnit.SECONDS)))
                    .collect(Collectors.toList());
            meters.forEach(meter -> System.out.println(meter));
        }
    }

    @Override
    protected TimeUnit getBaseTimeUnit() {
        return TimeUnit.HOURS;
    }
}
