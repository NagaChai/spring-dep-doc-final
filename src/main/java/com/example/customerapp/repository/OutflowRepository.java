package com.example.customerapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.customerapp.model.Outflow;
import com.example.customerapp.projection.CustomerProjection;
import com.example.customerapp.projection.OutflowProjection;

public interface OutflowRepository extends JpaRepository<Outflow, Long> {
	  List<Outflow> findByDateBetween(LocalDate startDate, LocalDate endDate);
	  @Query("SELECT o.name AS name, o.purpose AS purpose, o.date AS date, o.amount AS amount " +
		       "FROM Outflow o WHERE o.date BETWEEN :startDate AND :endDate")
		List<OutflowProjection> findOutflowSummaryByDateRange(
		    @Param("startDate") LocalDate startDate,
		    @Param("endDate") LocalDate endDate
		);

}
