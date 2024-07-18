package com.financesLab.TickersProducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TickersProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TickersProducerApplication.class, args);
	}

}
