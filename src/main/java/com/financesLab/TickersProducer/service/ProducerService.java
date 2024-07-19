package com.financesLab.TickersProducer.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financesLab.TickersProducer.config.RabbitConstants;
import com.financesLab.TickersProducer.repository.TickersRepository;

@Service
public class ProducerService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private TickersRepository tickersRepository;

	public void process() {
		System.out.println("[ProducerService] Processing message: ");

		tickersRepository.findAll().forEach(ticker -> System.out.println(ticker.getTickerId()));

		rabbitTemplate.convertAndSend(RabbitConstants.RELEVANT_FACTS_QUEUE, "Test message");
		rabbitTemplate.convertAndSend(RabbitConstants.TICKERS_DETAILS_QUEUE, "Test message");
	}

}
