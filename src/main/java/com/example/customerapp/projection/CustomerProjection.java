package com.example.customerapp.projection;

import java.time.LocalDate;

public interface CustomerProjection {
	Integer getRoomNumber();
    String getName();
    double getRentMonth();
    LocalDate getDate();
    Double getRentPaid();
    Double getMaintenance();
    Double getParkingBill();
    Double getElectricityBill();
}
