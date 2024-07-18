package com.financesLab.TickersProducer.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.financesLab.TickersProducer.service.ProducerService;

@Service
public class Producer {

	@Autowired
	private ProducerService producerService;

	// every day - 01:00:00
	@Scheduled(cron = "0 0 1 * * *")
	public void start() {
		System.out.println("[INFO] Iniciando processamento...");
		producerService.process();
		System.out.println("[INFO] Processamento finalizado.");
	}

}
