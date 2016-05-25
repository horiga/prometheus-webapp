package org.horiga.study.webapp.prometheus.metrics;

import io.prometheus.client.CollectorRegistry;

public class CounterService extends PrometheusMetricService
		implements org.springframework.boot.actuate.metrics.CounterService {

	protected CounterService(CollectorRegistry registry) {
		super(registry);
	}

	@Override
	public void increment(String name) {
		super.gauge(name).inc();
	}

	@Override
	public void decrement(String name) {
		super.gauge(name).dec();
	}
}
