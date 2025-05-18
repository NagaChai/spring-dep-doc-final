package com.example.customerapp.response;

import java.util.List;

import com.example.customerapp.model.Customer;
import com.example.customerapp.model.Outflow;

public class MonthlyReportResponse {
    private double totalInflow;
    private double totalOutflow;
    private double balance;
    private List<Customer> inflowDetails;
    private List<Outflow> outflowDetails;
    public List<Customer> getInflowDetails() {
		return inflowDetails;
	}

	public void setInflowDetails(List<Customer> inflowDetails) {
		this.inflowDetails = inflowDetails;
	}

	public List<Outflow> getOutflowDetails() {
		return outflowDetails;
	}

	public void setOutflowDetails(List<Outflow> outflowDetails) {
		this.outflowDetails = outflowDetails;
	}

	// Constructor
    public MonthlyReportResponse(double totalInflow, double totalOutflow, double balance) {
        this.totalInflow = totalInflow;
        this.totalOutflow = totalOutflow;
        this.balance = balance;
    }

    // Getters and Setters
    public double getTotalInflow() {
        return totalInflow;
    }

    public void setTotalInflow(double totalInflow) {
        this.totalInflow = totalInflow;
    }

    public double getTotalOutflow() {
        return totalOutflow;
    }

    public void setTotalOutflow(double totalOutflow) {
        this.totalOutflow = totalOutflow;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
