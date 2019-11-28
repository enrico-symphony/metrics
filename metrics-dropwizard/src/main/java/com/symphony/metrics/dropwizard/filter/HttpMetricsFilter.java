package com.symphony.metrics.dropwizard.filter;

import com.codahale.metrics.MetricRegistry;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

import static com.codahale.metrics.MetricRegistry.name;

@Component
@Lazy
@Slf4j
public class HttpMetricsFilter implements Filter {

    private MetricRegistry dropwizardRegistry;

    @Autowired
    public HttpMetricsFilter(MetricRegistry dropwizardRegistry) {
        this.dropwizardRegistry = dropwizardRegistry;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Filter called by " + servletRequest.getRemoteAddr());
        com.codahale.metrics.Timer.Context dropwizardTimer = dropwizardRegistry.timer("test.timer.dropwizard").time();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            // Simulate long process
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // do nothing
        } finally {
            dropwizardTimer.stop();
        }
    }
}
