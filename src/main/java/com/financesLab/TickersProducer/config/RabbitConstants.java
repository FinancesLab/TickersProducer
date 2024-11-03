package com.financesLab.TickersProducer.config;

public class RabbitConstants {

	public static final String AMQP_HOST = System.getenv("AMQP_HOST") != null ? System.getenv("AMQP_HOST")
			: System.getProperty("AMQP_HOST", "amqp://guest:guest@localhost:5672");

	// Relevant Facts
	// Queues
	public static final String RELEVANT_FACTS_QUEUE = "relevant_facts_tasks";
	public static final String RELEVANT_FACTS_DLQ = RELEVANT_FACTS_QUEUE + ".dlq";
	public static final String RELEVANT_FACTS_PARKING_LOT = RELEVANT_FACTS_QUEUE + ".parking-lot";

	// Exchanges
	public static final String RELEVANT_FACTS_EXCHANGE = RELEVANT_FACTS_QUEUE + "_exchange";
	public static final String RELEVANT_FACTS_DLX_EXCHANGE = RELEVANT_FACTS_QUEUE + ".dlx";
	public static final String RELEVANT_FACTS_PARKING_LOT_EXCHANGE = RELEVANT_FACTS_QUEUE + ".plx";

	// Tickers Details
	// Queues
	public static final String TICKERS_DETAILS_QUEUE = "tickers_details_tasks";
	public static final String TICKERS_DETAILS_DLQ = TICKERS_DETAILS_QUEUE + ".dlq";
	public static final String TICKERS_DETAILS_PARKING_LOT = TICKERS_DETAILS_QUEUE + ".parking-lot";

	// Exchanges
	public static final String TICKERS_DETAILS_EXCHANGE = TICKERS_DETAILS_QUEUE + "_exchange";
	public static final String TICKERS_DETAILS_DLX_EXCHANGE = TICKERS_DETAILS_QUEUE + ".dlx";
	public static final String TICKERS_DETAILS_PARKING_LOT_EXCHANGE = TICKERS_DETAILS_QUEUE + "_exchange.parking-lot";

	// Fanout Exchange for Main Tasks
	public static final String MAIN_TASKS_FANOUT_EXCHANGE = "main_tasks_fanout";

}
