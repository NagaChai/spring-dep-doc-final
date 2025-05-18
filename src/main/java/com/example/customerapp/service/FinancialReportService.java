package com.example.customerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.example.customerapp.model.Inflow;
import com.example.customerapp.model.Outflow;
//import com.example.customerapp.repository.InflowRepository;
import com.example.customerapp.repository.OutflowRepository;

import java.util.List;

@Service
public class FinancialReportService {

//    @Autowired
//    private InflowRepository inflowRepository;

    @Autowired
    private OutflowRepository outflowRepository;

//    public List<Inflow> getInflows() {
//        return inflowRepository.findAll();
//    }

    public List<Outflow> getOutflows() {
        return outflowRepository.findAll();
    }
}
