package com.financesLab.TickersProducer.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financesLab.TickersProducer.config.RabbitConstants;

@Service
public class ProducerService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void process() {
		System.out.println("[ProducerService] Processing message: ");

		rabbitTemplate.convertAndSend(RabbitConstants.RELEVANT_FACTS_QUEUE, "Test message");
	}

}
