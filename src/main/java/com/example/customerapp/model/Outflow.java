package com.example.customerapp.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
public class Outflow {

    @Id
    private Long id;
    private String name;
    private String purpose;
    @DateTimeFormat(pattern = "dd-MM-yyyy") // For binding from web form
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    
    public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	private double amount;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
