package com.chinmayshivratriwar.cache_comparison;

import com.chinmayshivratriwar.cache_comparison.service.CacheBenchmarkService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.chinmayshivratriwar"})
public class CacheComparisonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheComparisonApplication.class, args);
	}

	@Bean
	public CommandLineRunner runBenchmark(CacheBenchmarkService benchmarkService) {
		return args -> {
			benchmarkService.runTests();
		};
	}

}
