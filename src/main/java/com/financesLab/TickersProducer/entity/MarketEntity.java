package com.financesLab.TickersProducer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "markets")
public class MarketEntity {

	@Id
	private String id;
	private String label;

}
