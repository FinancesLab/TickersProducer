package com.financesLab.TickersProducer.config;

import static com.financesLab.TickersProducer.config.RabbitConstants.*;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

	@Bean
	public static CachingConnectionFactory rabbitConnectionFactory(RabbitProperties config) {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setUri(AMQP_HOST);
		return connectionFactory;
	}

	@Bean
	public static RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	@Bean
	public ApplicationListener<ApplicationReadyEvent> applicationReadyEventListener(RabbitAdmin rabbitAdmin) {
		return event -> {
			rabbitAdmin.initialize();
		};
	}

	@Bean
	Queue messagesQueue() {
		return QueueBuilder.durable(RELEVANT_FACTS_QUEUE)
				.withArgument("x-dead-letter-exchange", RELEVANT_FACTS_DLX_EXCHANGE).build();
	}

	@Bean
	Queue deadLetterQueue() {
		return QueueBuilder.durable(RELEVANT_FACTS_DLQ).build();
	}

	@Bean
	Queue parkingLotQueue() {
		return QueueBuilder.durable(RELEVANT_FACTS_PARKING_LOT).build();
	}

	@Bean
	DirectExchange messagesExchange() {
		return new DirectExchange(RELEVANT_FACTS_EXCHANGE);
	}

	@Bean
	FanoutExchange deadLetterExchange() {
		return new FanoutExchange(RELEVANT_FACTS_DLX_EXCHANGE, true, false);
	}

	@Bean
	FanoutExchange parkingLotExchange() {
		return new FanoutExchange(RELEVANT_FACTS_PARKING_LOT_EXCHANGE);
	}

	@Bean
	Binding bindingMessages() {
		return BindingBuilder.bind(messagesQueue()).to(messagesExchange()).with(RELEVANT_FACTS_QUEUE);
	}

	@Bean
	Binding deadLetterBinding() {
		return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange());
	}

	@Bean
	Binding parkingLotBinding() {
		return BindingBuilder.bind(parkingLotQueue()).to(parkingLotExchange());
	}

}
