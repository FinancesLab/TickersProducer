package com.financesLab.TickersProducer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.financesLab.TickersProducer.entity.TickerEntity;

public interface TickersRepository extends JpaRepository<TickerEntity, String> {

	@Query("SELECT new TickerEntity(t.tickerId, t.ticker, ty.label, s.label, m.label, t.companyName, t.hasRelevantFacts) "
			+ "FROM TickerEntity t " //
			+ "JOIN TypeEntity ty ON t.typeId = ty.id " //
			+ "JOIN SectorEntity s ON t.sectorId = s.id " //
			+ "JOIN MarketEntity m ON t.marketId = m.id")
	List<TickerEntity> findAllWithLabels();

}
