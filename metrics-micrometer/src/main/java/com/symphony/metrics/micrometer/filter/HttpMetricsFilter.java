package com.symphony.metrics.micrometer.filter;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import java.io.IOException;

@Component
@Lazy
@Slf4j
public class HttpMetricsFilter implements Filter {

    private final MeterRegistry micrometerRegistry;

    @Autowired
    public HttpMetricsFilter(MeterRegistry micrometerRegistry) {
        this.micrometerRegistry = micrometerRegistry;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Filter called by " + servletRequest.getRemoteAddr());
        Timer.Sample micrometerTimer = Timer.start();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            // Simulate long process
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // do nothing
        } finally {
            micrometerTimer.stop(micrometerRegistry.timer("test.timer.micrometer"));
        }
    }
}
