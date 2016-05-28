package org.horiga.study.webapp.prometheus.metrics.reader;

import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.actuate.metrics.reader.MetricReader;

import io.prometheus.client.CollectorRegistry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrometheusMetricReader implements MetricReader {

	private final CollectorRegistry registry;

	public PrometheusMetricReader(CollectorRegistry registry) {
		this.registry = registry;
	}

	@Override
	public Metric<?> findOne(String name) {
		log.info("[findOne]");

		// TODO

		return null;
	}

	@Override
	public Iterable<Metric<?>> findAll() {
		log.info("[findAll]");

		// TODO

		return null;
	}

	@Override
	public long count() {
		log.info("[count]");

		// TODO

		return 0;
	}
}
