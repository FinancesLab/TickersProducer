package com.financesLab.TickersProducer.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financesLab.TickersProducer.config.RabbitConstants;
import com.financesLab.TickersProducer.entity.TickerEntity;
import com.financesLab.TickersProducer.repository.TickersRepository;
import com.google.gson.Gson;

@Service
public class ProducerService {

	final Gson gson = new Gson();

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private TickersRepository tickersRepository;

	public void process() {
		System.out.println("[ProducerService] Processing message: ");

		for (TickerEntity ticker : tickersRepository.findAllWithLabels()) {
			String json = gson.toJson(ticker);
			rabbitTemplate.convertAndSend(RabbitConstants.MAIN_TASKS_FANOUT_EXCHANGE, "", json);
		}

	}

}
