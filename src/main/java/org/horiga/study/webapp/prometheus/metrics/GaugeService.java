package org.horiga.study.webapp.prometheus.metrics;

import io.prometheus.client.CollectorRegistry;

public class GaugeService extends PrometheusMetricService
		implements org.springframework.boot.actuate.metrics.GaugeService,
		org.springframework.boot.actuate.metrics.CounterService {

	public GaugeService(CollectorRegistry registry) {
		super(registry);
	}

	@Override
	public void submit(String name, double value) {
		super.gauge(name).set(value);
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
