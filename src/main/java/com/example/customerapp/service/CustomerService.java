package com.example.customerapp.service;

import com.example.customerapp.model.Customer;
//import com.example.customerapp.model.Inflow;
import com.example.customerapp.model.Outflow;
import com.example.customerapp.projection.CustomerProjection;
import com.example.customerapp.repository.CustomerRepository;
//import com.example.customerapp.repository.InflowRepository;
import com.example.customerapp.repository.OutflowRepository;
import com.example.customerapp.response.MonthlyReportResponse;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OutflowRepository outflowRepository;
//    @Autowired
//    private InflowRepository inflowRepository;
    public MonthlyReportResponse getMonthlyReport(LocalDate startDate, LocalDate endDate) {
        List<Customer> inflows = customerRepository.findByDateBetween(startDate, endDate);
        List<Outflow> outflows = outflowRepository.findByDateBetween(startDate, endDate);
        
        double totalInflow = inflows.stream().mapToDouble(Customer::getRentMonth).sum();
        double totalOutflow = outflows.stream().mapToDouble(Outflow::getAmount).sum();
        double balance = totalInflow - totalOutflow;

        return new MonthlyReportResponse(totalInflow, totalOutflow, balance);
    }
    public Outflow saveOutflow(Outflow outflow) {
        // Save the Outflow entity and return it
        return outflowRepository.save(outflow);
    }
    public boolean deleteOutflowById(Long id) {
        if (outflowRepository.existsById(id)) {
            outflowRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<CustomerProjection> getCustomerReportData(LocalDate startDate, LocalDate endDate) {
        return customerRepository.findCustomerSummaryByDateRange(startDate, endDate);
    }
    
    @Transactional
    public boolean deleteByRoomNumber(Integer roomNumber) {
        List<Customer> customers = customerRepository.findByRoomNumber(roomNumber);
        
        if (!customers.isEmpty()) {
            customerRepository.deleteAll(customers);
            return true;
        }
        
        return false;
    }


    
//    public void printCustomerSummary() {
//        List<CustomerProjection> summaryList = customerRepository.findCustomerSummary();
//        summaryList.forEach(c -> System.out.println(
//            "Room: " + c.getRoomNumber() + ", Name: " + c.getName() + ", Rent: " + c.getRentMonth()));
//    }
}