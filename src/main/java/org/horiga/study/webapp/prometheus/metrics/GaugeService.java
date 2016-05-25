package org.horiga.study.webapp.prometheus.metrics;

import io.prometheus.client.CollectorRegistry;

public class GaugeService extends PrometheusMetricService
		implements org.springframework.boot.actuate.metrics.GaugeService {

	protected GaugeService(CollectorRegistry registry) {
		super(registry);
	}

	@Override
	public void submit(String name, double value) {
		super.gauge(name).set(value);
	}
}
