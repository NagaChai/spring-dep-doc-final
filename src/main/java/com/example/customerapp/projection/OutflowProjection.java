package com.example.customerapp.projection;

import java.time.LocalDate;

public interface OutflowProjection {
    String getName();
    String getPurpose();
    LocalDate getDate();  // or LocalDate if your entity has it
    Double getAmount();
}
