package org.horiga.study.webapp.prometheus.metrics;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
public abstract class PrometheusMetricService {

	private final CollectorRegistry registry;

	private final ConcurrentMap<String, Gauge> gauges = new ConcurrentHashMap<>();

	protected PrometheusMetricService(CollectorRegistry registry) {
		this.registry = registry;
	}

	protected Gauge gauge(String name) {
		return gauges.computeIfAbsent(name, k -> {
			Gauge gauge = Gauge.build()
						.name(name.replaceAll("[^a-zA-Z0-9_]", "_")) // sanitize
						.help(name)
						.create();
			registry.register(gauge);
			return gauge;
		});
	}

	public void reset(String name) {
		gauges.computeIfPresent(name, (key, gauge) -> {
			gauge.set(0);
			return gauge;
		});
	}
}
