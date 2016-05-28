package org.horiga.study.webapp.prometheus;

import com.codahale.metrics.MetricRegistry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class EchoService {

	@Autowired
	private MetricRegistry metricRegistry; // Dropwizard

	public String echo(String echo) throws Exception {
		final long start = System.currentTimeMillis();
		try {
			Thread.sleep(RandomUtils.nextLong(0, 100));
			return echo;
		} finally {
			metricRegistry.timer("timer.echo.execution.millis")
					.update(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);
		}
	}
}
