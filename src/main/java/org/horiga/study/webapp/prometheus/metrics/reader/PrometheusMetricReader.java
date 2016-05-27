package org.horiga.study.webapp.prometheus.metrics.reader;

import io.prometheus.client.CollectorRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.actuate.metrics.reader.MetricReader;

@Slf4j
public class PrometheusMetricReader implements MetricReader {

	private final CollectorRegistry registry;

	public PrometheusMetricReader(CollectorRegistry registry) {
		this.registry = registry;
	}

	@Override
	public Metric<?> findOne(String s) {
		log.info("[findOne]");

		return null;
	}

	@Override
	public Iterable<Metric<?>> findAll() {
		log.info("[findAll]");
		return null;
	}

	@Override
	public long count() {
		log.info("[count]");
		return 0;
	}
}
