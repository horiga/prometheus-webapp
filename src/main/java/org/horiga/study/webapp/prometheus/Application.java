package org.horiga.study.webapp.prometheus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@RequestMapping("/echo")
	public String echo(@RequestParam(value = "echo", required = false, defaultValue = "hello!!") String echo)
			throws Exception {
		if ("error".equals(echo))
			throw new IllegalArgumentException("error");
		return echo;
	}

	@RequestMapping("/hoge/fuga")
		public String hoge_fuga(@RequestParam(value = "echo", required = false, defaultValue = "hello!!") String echo) {
		return echo;
	}
}
