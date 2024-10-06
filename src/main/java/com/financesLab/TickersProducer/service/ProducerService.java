package com.financesLab.TickersProducer.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financesLab.TickersProducer.config.RabbitConstants;
import com.financesLab.TickersProducer.entity.TickerEntity;
import com.financesLab.TickersProducer.repository.TickersRepository;
import com.financesLab.TickersProducer.util.JsonUtils;

@Service
public class ProducerService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private TickersRepository tickersRepository;

	public void process() {
		System.out.println("[ProducerService] Processing message: ");

		for (TickerEntity ticker : tickersRepository.findAllWithLabels()) {
			String json = JsonUtils.toJson(ticker);
			if (ticker.isHasRelevantFacts()) {
				rabbitTemplate.convertAndSend(RabbitConstants.MAIN_TASKS_FANOUT_EXCHANGE, "", json);
				continue;
			}

			rabbitTemplate.convertAndSend(RabbitConstants.TICKERS_DETAILS_EXCHANGE,
					RabbitConstants.TICKERS_DETAILS_QUEUE, json);
		}

	}

}
