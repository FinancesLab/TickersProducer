package com.financesLab.TickersProducer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tickers")
public class TickerEntity {

	@Id
	private String tickerId;
	private String typeId;
	private String sectorId;
	private String marketId;
	private String companyName;

}
