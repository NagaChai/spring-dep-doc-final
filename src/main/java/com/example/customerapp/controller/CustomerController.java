package com.example.customerapp.controller;

import com.example.customerapp.model.Customer;
//import com.example.customerapp.model.Inflow;
import com.example.customerapp.model.Outflow;
import com.example.customerapp.projection.CustomerProjection;
import com.example.customerapp.projection.OutflowProjection;
import com.example.customerapp.repository.CustomerRepository;
//import com.example.customerapp.repository.InflowRepository;
import com.example.customerapp.repository.OutflowRepository;
import com.example.customerapp.response.MonthlyReportResponse;
import com.example.customerapp.service.CustomerService;
import com.example.customerapp.service.FinancialReportService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")

public class CustomerController {

    private final OutflowRepository outflowRepository;

//    private final InflowRepository inflowRepository;

    private final CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private FinancialReportService financialReportService;

    CustomerController(CustomerRepository customerRepository, OutflowRepository outflowRepository) {
        this.customerRepository = customerRepository;
//        this.inflowRepository = inflowRepository;
        this.outflowRepository = outflowRepository;
    }
    @DeleteMapping("rooms/{roomNumber}")
    public ResponseEntity<String> deleteAllByRoomNumber(@PathVariable Integer roomNumber) {
        boolean deleted = customerService.deleteByRoomNumber(roomNumber);
        if (deleted) {
            return ResponseEntity.ok("All data for room " + roomNumber + " deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found.");
        }
    }

    @DeleteMapping("/outflow/{id}")
    public ResponseEntity<String> deleteOutflow(@PathVariable Long id) {
        boolean deleted = customerService.deleteOutflowById(id);
        if (deleted) {
            return ResponseEntity.ok("Outflow deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Outflow not found with id: " + id);
        }
    }
    @DeleteMapping("/customers/clear-reports")
    public ResponseEntity<String> clearReports() {
    	customerRepository.deleteAll();
//        inflowRepository.deleteAll();
        outflowRepository.deleteAll();
        return ResponseEntity.ok("Reports cleared");
    }

//    @DeleteMapping("/customer/{roomNumber}")
//    public ResponseEntity<String> deleteCustomer(@PathVariable Integer roomNumber) {
//        Optional<Customer> customer = customerRepository.findByRoomNumber(roomNumber);
//        if (customer.isPresent()) {
//            customerRepository.delete(customer.get());
//            return ResponseEntity.ok("Customer deleted successfully.");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                     .body("Customer not found.");
//        }
//    }
    @GetMapping("/customers/monthly-reports")
    public ResponseEntity<MonthlyReportResponse> getMonthlyReports(
        @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
        @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        System.out.println(startDate);
        // Call service to get the reports for the given date range
        MonthlyReportResponse report = customerService.getMonthlyReport(startDate, endDate);
        
        return ResponseEntity.ok(report);
    }

    
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok(customers);
    }


    // Endpoint to get inflows (rent)
    @GetMapping("/customers/inflows")
    public List<CustomerProjection> getCustomerInflows(
        @RequestParam("startDate") String startDateStr, 
        @RequestParam("endDate") String endDateStr) {

        // Parse the string dates into LocalDate objects
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        // Call repository method with date range
        return customerRepository.findCustomerSummaryByDateRange(startDate, endDate);
    }


    // Endpoint to get outflows (expenses)
    @GetMapping("/customers/outflows")
    public List<Outflow> getOutflows() {
        return financialReportService.getOutflows();
    }
    @GetMapping("/outflows")
    public List<OutflowProjection> getOutflowSummary(
        @RequestParam("startDate") String startDateStr,
        @RequestParam("endDate") String endDateStr) {

        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        return outflowRepository.findOutflowSummaryByDateRange(startDate, endDate);
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.ok(savedCustomer);
    }
    
    @PostMapping("/outflow")
    public ResponseEntity<Outflow> saveOutflow(@RequestBody Outflow outflow) {
        // Save the Outflow object using your service
        Outflow savedOutflow = customerService.saveOutflow(outflow);
        return ResponseEntity.ok(savedOutflow); // Return the saved outflow
    }

//    @GetMapping("/print-summary")
//    public void printSummary() {
//        customerService.printCustomerSummary();
//    }

//    @GetMapping("/{roomNumber}")
//    public ResponseEntity<Customer> getCustomer(@PathVariable String roomNumber) {
//        Customer customer = customerService.getCustomerByRoom(roomNumber);
//        if (customer != null) {
//            return ResponseEntity.ok(customer);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    @PutMapping("/customer/{id}")
    public ResponseEntity<String> updateCustomerPartially(@PathVariable Long id,  // Changed to Long and id
                                                          @RequestBody Customer updateDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);  // Use findById instead of findByRoomNumber

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            // Check each field and update only if it's not null
            if (updateDTO.getName() != null) {
                customer.setName(updateDTO.getName());
            }
            if (updateDTO.getMobileNumber() != null) {
                customer.setMobileNumber(updateDTO.getMobileNumber());
            }
            if (updateDTO.getRentAdvance() != 0.0) {
                customer.setRentAdvance(updateDTO.getRentAdvance());
            }
            if (updateDTO.getRentMonth() != 0.0) {
                customer.setRentMonth(updateDTO.getRentMonth());
            }
            if (updateDTO.getPaymentStatus() != null) {
                customer.setPaymentStatus(updateDTO.getPaymentStatus());
            }
            if (updateDTO.getPaymentMode() != null) {
                customer.setPaymentMode(updateDTO.getPaymentMode());
            }
            if (updateDTO.getChequeNumber() != null) {
                customer.setChequeNumber(updateDTO.getChequeNumber());
            }
            if (updateDTO.getUploadedImage() != null) {
                customer.setUploadedImage(updateDTO.getUploadedImage());
            }
            if (updateDTO.getDate() != null) {
                customer.setDate(updateDTO.getDate());
            }
            
            // new update
            if (updateDTO.getRentPaid() != null && updateDTO.getRentPaid() != 0.0) {
                customer.setRentPaid(updateDTO.getRentPaid());
            }
            if (updateDTO.getMaintenance() != null && updateDTO.getMaintenance() != 0.0) {
                customer.setMaintenance(updateDTO.getMaintenance());
            }
            if (updateDTO.getParkingBill() != null && updateDTO.getParkingBill() != 0.0) {
                customer.setParkingBill(updateDTO.getParkingBill());
            }
            if (updateDTO.getElectricityBill() != null && updateDTO.getElectricityBill() != 0.0) {
                customer.setElectricityBill(updateDTO.getElectricityBill());
            }

            customerRepository.save(customer);
            return ResponseEntity.ok("Customer updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        }
    }

}