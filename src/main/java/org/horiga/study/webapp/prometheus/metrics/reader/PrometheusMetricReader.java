package org.horiga.study.webapp.prometheus.metrics.reader;

import io.prometheus.client.CollectorRegistry;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.actuate.metrics.reader.MetricReader;


public class PrometheusMetricReader implements MetricReader {

	private final CollectorRegistry registry;

	public PrometheusMetricReader(CollectorRegistry registry) {
		this.registry = registry;
	}

	@Override
	public Metric<?> findOne(String s) {
		return null;
	}

	@Override
	public Iterable<Metric<?>> findAll() {
		return null;
	}

	@Override
	public long count() {
		return 0;
	}
}
