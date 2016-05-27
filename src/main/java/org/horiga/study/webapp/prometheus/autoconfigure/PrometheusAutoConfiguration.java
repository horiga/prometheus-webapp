package org.horiga.study.webapp.prometheus.autoconfigure;

import com.codahale.metrics.MetricRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.dropwizard.DropwizardExports;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.GarbageCollectorExports;
import io.prometheus.client.hotspot.MemoryPoolsExports;
import io.prometheus.client.hotspot.StandardExports;
import io.prometheus.client.hotspot.ThreadExports;
import lombok.extern.slf4j.Slf4j;
import org.horiga.study.webapp.prometheus.metrics.CounterService;
import org.horiga.study.webapp.prometheus.metrics.GaugeService;
import org.horiga.study.webapp.prometheus.metrics.reader.PrometheusMetricReader;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class PrometheusAutoConfiguration {

	@Value("${prometheus.servlet.uri:/prometheus}")
	private String prometheusEndpointURI;

	@Value("${prometheus.metrics.export.memoryPools.enable:true}")
	private boolean enableMemoryPoolsExports;

	@Value("${prometheus.metrics.export.memoryPools.enable:false}")
	private boolean enableStandardExports;

	@Value("${prometheus.metrics.export.garbageCollector.enable:true}")
	private boolean enableGarbageCollectorExports;

	@Value("${prometheus.metrics.export.thread.enable:true}")
	private boolean enableThreadExports;

	@Bean
	@ConditionalOnMissingBean
	public CollectorRegistry prometheusMetricRegistry(final MetricRegistry metricRegistry) {
		final CollectorRegistry registry = new CollectorRegistry();
		if (enableMemoryPoolsExports)
			new MemoryPoolsExports().register(registry);
		if (enableStandardExports)
			new StandardExports().register(registry);
		if (enableGarbageCollectorExports)
			new GarbageCollectorExports().register(registry);
		if (enableThreadExports)
			new ThreadExports().register(registry);
		new DropwizardExports(metricRegistry).register(registry);
		return registry;
	}

	@Bean
	@ConditionalOnMissingBean({CounterService.class,
		org.springframework.boot.actuate.metrics.CounterService.class})
	public CounterService counterService(final CollectorRegistry registry) {
		return new CounterService(registry);
	}

	@Bean
	@ConditionalOnMissingBean({GaugeService.class,
		org.springframework.boot.actuate.metrics.CounterService.class})
	public GaugeService gaugeService(final CollectorRegistry registry) {
		return new GaugeService(registry);
	}

	@Bean
	public MetricReaderPublicMetrics prometheusMetricReaderPublicMetrics(final CollectorRegistry registry) {
		log.info("[prometheusMetricReaderPublicMetrics]");
		return new MetricReaderPublicMetrics(new PrometheusMetricReader(registry));
	}

	@Bean
	public ServletRegistrationBean prometheusServletRegistrationBean(final CollectorRegistry registry) {
		return new ServletRegistrationBean(new MetricsServlet(registry), prometheusEndpointURI);
	}
}
