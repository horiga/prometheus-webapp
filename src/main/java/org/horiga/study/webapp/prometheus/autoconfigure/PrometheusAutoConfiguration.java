package org.horiga.study.webapp.prometheus.autoconfigure;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.hotspot.GarbageCollectorExports;
import io.prometheus.client.hotspot.MemoryPoolsExports;
import io.prometheus.client.hotspot.StandardExports;
import org.springframework.boot.actuate.autoconfigure.MetricRepositoryAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(CollectorRegistry.class)
@AutoConfigureBefore(MetricRepositoryAutoConfiguration.class)
public class PrometheusAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	CollectorRegistry prometheusMetricRegistry() {
		CollectorRegistry registry = new CollectorRegistry();
		new MemoryPoolsExports().register(registry);
		new StandardExports().register(registry);
		new GarbageCollectorExports().register(registry);
		return registry;
	}


}
