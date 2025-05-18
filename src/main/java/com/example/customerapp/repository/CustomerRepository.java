package com.example.customerapp.repository;

import com.example.customerapp.model.Customer;
//import com.example.customerapp.model.Inflow;
import com.example.customerapp.projection.CustomerProjection;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
//	List<Customer> findByRoomNumber(String roomNumber);
	List<Customer> findByDateBetween(LocalDate startDate, LocalDate endDate);
	Optional<Customer> findById(Long id);
	List<Customer> findByRoomNumber(Integer roomNumber);
	void deleteByRoomNumber(Integer roomNumber);
	@Query("SELECT c.roomNumber AS roomNumber, " +
		       "c.name AS name, " +
		       "c.rentMonth AS rentMonth, " +
		       "c.date AS date, " +
		       "c.rentPaid AS rentPaid, " +
		       "c.maintenance AS maintenance, " +
		       "c.parkingBill AS parkingBill, " +
		       "c.electricityBill AS electricityBill " +
		       "FROM Customer c " +
		       "WHERE c.date BETWEEN :startDate AND :endDate")
		List<CustomerProjection> findCustomerSummaryByDateRange(@Param("startDate") LocalDate startDate, 
		                                                        @Param("endDate") LocalDate endDate);



}
