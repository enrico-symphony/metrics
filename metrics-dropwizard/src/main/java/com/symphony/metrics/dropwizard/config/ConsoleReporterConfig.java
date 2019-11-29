package com.symphony.metrics.dropwizard.config;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.codahale.metrics.Slf4jReporter.LoggingLevel.INFO;
import static java.util.concurrent.TimeUnit.SECONDS;

@Component
public class ConsoleReporterConfig {

    @Autowired
    public ConsoleReporterConfig(MetricRegistry registry) {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);
    }

}
