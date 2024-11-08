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
	Queue relevantFactsQueue() {
		return QueueBuilder.durable(RELEVANT_FACTS_QUEUE)
				.withArgument("x-dead-letter-exchange", RELEVANT_FACTS_DLX_EXCHANGE)
				.withArgument("x-dead-letter-routing-key", RELEVANT_FACTS_DLQ).build();
	}

	@Bean
	Queue relevantFactsDeadLetterQueue() {
		return QueueBuilder.durable(RELEVANT_FACTS_DLQ).build();
	}

	@Bean
	Queue relevantFactsParkingLotQueue() {
		return QueueBuilder.durable(RELEVANT_FACTS_PARKING_LOT).build();
	}

	@Bean
	DirectExchange relevantFactsExchange() {
		return new DirectExchange(RELEVANT_FACTS_EXCHANGE);
	}

	@Bean
	DirectExchange relevantFactsDeadLetterExchange() {
		return new DirectExchange(RELEVANT_FACTS_DLX_EXCHANGE, true, false);
	}

	@Bean
	DirectExchange relevantFactsParkingLotExchange() {
		return new DirectExchange(RELEVANT_FACTS_PARKING_LOT_EXCHANGE);
	}

	@Bean
	Binding relevantFactsBinding() {
		return BindingBuilder.bind(relevantFactsQueue()).to(relevantFactsExchange()).with(RELEVANT_FACTS_QUEUE);
	}

	@Bean
	Binding relevantFactsDeadLetterBinding() {
		return BindingBuilder.bind(relevantFactsDeadLetterQueue()).to(relevantFactsDeadLetterExchange())
				.with(RELEVANT_FACTS_DLQ);
	}

	@Bean
	Binding relevantFactsParkingLotBinding() {
		return BindingBuilder.bind(relevantFactsParkingLotQueue()).to(relevantFactsParkingLotExchange())
				.with(RELEVANT_FACTS_PARKING_LOT);
	}

	@Bean
	Queue tickersDetailsQueue() {
		return QueueBuilder.durable(TICKERS_DETAILS_QUEUE)
				.withArgument("x-dead-letter-exchange", TICKERS_DETAILS_DLX_EXCHANGE).build();
	}

	@Bean
	Queue tickersDetailsDeadLetterQueue() {
		return QueueBuilder.durable(TICKERS_DETAILS_DLQ).build();
	}

	@Bean
	Queue tickersDetailsParkingLotQueue() {
		return QueueBuilder.durable(TICKERS_DETAILS_PARKING_LOT).build();
	}

	@Bean
	DirectExchange tickersDetailsExchange() {
		return new DirectExchange(TICKERS_DETAILS_EXCHANGE);
	}

	@Bean
	DirectExchange tickersDetailsDeadLetterExchange() {
		return new DirectExchange(TICKERS_DETAILS_DLX_EXCHANGE, true, false);
	}

	@Bean
	DirectExchange tickersDetailsParkingLotExchange() {
		return new DirectExchange(TICKERS_DETAILS_PARKING_LOT_EXCHANGE);
	}

	@Bean
	Binding tickersDetailsBinding() {
		return BindingBuilder.bind(tickersDetailsQueue()).to(tickersDetailsExchange()).with(TICKERS_DETAILS_QUEUE);
	}

	@Bean
	Binding tickersDetailsDeadLetterBinding() {
		return BindingBuilder.bind(tickersDetailsDeadLetterQueue()).to(tickersDetailsDeadLetterExchange())
				.with(TICKERS_DETAILS_DLQ);
	}

	@Bean
	Binding tickersDetailsParkingLotBinding() {
		return BindingBuilder.bind(tickersDetailsParkingLotQueue()).to(tickersDetailsParkingLotExchange())
				.with(TICKERS_DETAILS_PARKING_LOT);
	}

	@Bean
	FanoutExchange mainTasksFanoutExchange() {
		return new FanoutExchange(MAIN_TASKS_FANOUT_EXCHANGE);
	}

	@Bean
	Binding tickersDetailsMainTasksFanoutBinding() {
		return BindingBuilder.bind(tickersDetailsQueue()).to(mainTasksFanoutExchange());
	}

	@Bean
	Binding relevatFactMainTasksFanoutBinding() {
		return BindingBuilder.bind(relevantFactsQueue()).to(mainTasksFanoutExchange());
	}

}
