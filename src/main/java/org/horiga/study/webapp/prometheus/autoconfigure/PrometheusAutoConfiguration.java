package org.horiga.study.webapp.prometheus.autoconfigure;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.GarbageCollectorExports;
import io.prometheus.client.hotspot.MemoryPoolsExports;
import io.prometheus.client.hotspot.StandardExports;
import org.horiga.study.webapp.prometheus.metrics.CounterService;
import org.horiga.study.webapp.prometheus.metrics.GaugeService;
import org.horiga.study.webapp.prometheus.metrics.reader.PrometheusMetricReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.MetricRepositoryAutoConfiguration;
import org.springframework.boot.actuate.endpoint.MetricReaderPublicMetrics;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(CollectorRegistry.class)
@AutoConfigureBefore(MetricRepositoryAutoConfiguration.class)
public class PrometheusAutoConfiguration {

	@Value("${prometheus.servlet.uri:/prometheus}")
	private String prometheusEndpointURI;

	@Bean
	@ConditionalOnMissingBean
	public CollectorRegistry prometheusMetricRegistry() {
		CollectorRegistry registry = new CollectorRegistry();
		new MemoryPoolsExports().register(registry);
		new StandardExports().register(registry);
		new GarbageCollectorExports().register(registry);
		return registry;
	}

	@Bean
	@ConditionalOnMissingBean({CounterService.class,
		org.springframework.boot.actuate.metrics.CounterService.class})
	public CounterService counterService(CollectorRegistry registry) {
		return new CounterService(registry);
	}

	@Bean
	@ConditionalOnMissingBean({GaugeService.class,
		org.springframework.boot.actuate.metrics.CounterService.class})
	public GaugeService gaugeService(CollectorRegistry registry) {
		return new GaugeService(registry);
	}

	@Bean
	public MetricReaderPublicMetrics prometheusMetricReaderPublicMetrics(CollectorRegistry registry) {
		return new MetricReaderPublicMetrics(new PrometheusMetricReader(registry));
	}

	@Bean
	public ServletRegistrationBean prometheusServletRegistrationBean(CollectorRegistry registry) {
		return new ServletRegistrationBean(new MetricsServlet(registry), prometheusEndpointURI);
	}
}
