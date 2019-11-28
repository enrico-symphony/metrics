package com.symphony.metrics.dropwizard;

import com.codahale.metrics.MetricRegistry;
import org.springframework.stereotype.Component;

// To create one instance of Dropwizard metric registry
@Component
public class DropwizardRegistry extends MetricRegistry {
}
