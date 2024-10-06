package com.financesLab.TickersProducer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tickers")
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TickerEntity {

	@Id
	private String tickerId;
	private String typeId;
	private String sectorId;
	private String marketId;
	private String companyName;

	@JsonIgnore
	private boolean hasRelevantFacts;

	@Column(name = "ticker_name")
	private String ticker;

	@Transient
	private String type;

	@Transient
	private String sector;

	@Transient
	private String market;

	public TickerEntity(String tickerId, String ticker, String type, String sector, String market, String companyName,
			boolean hasRelevantFacts) {
		this.tickerId = tickerId;
		this.ticker = ticker;
		this.type = type;
		this.sector = sector;
		this.market = market;
		this.companyName = companyName;
		this.hasRelevantFacts = hasRelevantFacts;
	}

}
