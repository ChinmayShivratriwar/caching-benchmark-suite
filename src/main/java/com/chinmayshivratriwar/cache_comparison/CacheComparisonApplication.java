package com.chinmayshivratriwar.cache_comparison;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"com.chinmayshivratriwar"})
@EnableAsync
public class CacheComparisonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheComparisonApplication.class, args);
	}


}
