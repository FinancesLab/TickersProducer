package com.financesLab.TickersProducer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financesLab.TickersProducer.entity.TickerEntity;

public interface TickersRepository extends JpaRepository<TickerEntity, String> {
}
