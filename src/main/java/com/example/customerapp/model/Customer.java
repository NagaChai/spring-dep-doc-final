package com.example.customerapp.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "room_number", unique = false)
    private Integer roomNumber;
    private String name;
    private String mobileNumber;
    private Double rentAdvance;
    private Double rentPaid;
    private Double maintenance;
    private Double parkingBill;
    private Double electricityBill;
    private Double rentMonth;
    public Double getRentPaid() {
		return rentPaid;
	}

	public void setRentPaid(Double rentPaid) {
		this.rentPaid = rentPaid;
	}

	
    public Double getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(Double maintenance) {
		this.maintenance = maintenance;
	}

	public Double getParkingBill() {
		return parkingBill;
	}

	public void setParkingBill(Double parkingBill) {
		this.parkingBill = parkingBill;
	}

	public Double getElectricityBill() {
		return electricityBill;
	}

	public void setElectricityBill(Double electricityBill) {
		this.electricityBill = electricityBill;
	}

	private String paymentStatus;
    private String paymentMode;
    private String chequeNumber;

    @Lob
    private String uploadedImage;
    @DateTimeFormat(pattern = "dd-MM-yyyy") // For binding from web form
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Double getRentAdvance() {
		return rentAdvance;
	}

	public void setRentAdvance(Double rentAdvance) {
		this.rentAdvance = rentAdvance;
	}

	public Double getRentMonth() {
		return rentMonth;
	}

	public void setRentMonth(Double rentMonth) {
		this.rentMonth = rentMonth;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public String getUploadedImage() {
		return uploadedImage;
	}

	public void setUploadedImage(String uploadedImage) {
		this.uploadedImage = uploadedImage;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	
}