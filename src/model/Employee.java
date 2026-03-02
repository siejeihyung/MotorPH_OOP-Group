package model;

import java.util.ArrayList;
import java.util.List;

// This class represents an Employee with relevant salary details
public abstract class Employee {

    // Employee ID (e.g., unique identifier for each employee)
    private String employeeID;

    // Full name of the employee
    private String name;
    
    // Leave
    private List<Leave> leaveHistory;
    private int sickLeaveBalance = 5;      // Default 5 days
    private int vacationLeaveBalance = 10; // Default 10 days
    private int emergencyLeaveBalance = 3; // Default 3 days

    // Basic monthly salary of the employee
    private double basicSalary;

    // Semi-monthly salary rate (used if paid twice a month)
    private double semiMonthlyRate;

    // Hourly rate used for computing pay based on hours worked
    private double hourlyRate;
    
    // Leave Request
    private int leavesTaken;

    // Constructor to initialize all attributes of the Employee object
    public Employee(String employeeID, String name) {
        this.employeeID = employeeID;
        this.name = name;
        this.leaveHistory = new ArrayList<>(); 
    }
    
    public abstract double calculateGrossWeeklySalary();
    
    // Add the leave object to the list
    public void fileLeave(Leave leave) {
        this.leaveHistory.add(leave);
    }

    // to count total days taken
    public int getTotalLeavesTaken() {
        int total = 0;
        for (Leave l : leaveHistory) {
            total += l.getDays();
        }
        return total;
    }

    
    public int getSickLeaveBalance() { return sickLeaveBalance; }
    public void setSickLeaveBalance(int balance) { this.sickLeaveBalance = balance; }
    public int getVacationLeaveBalance() { return vacationLeaveBalance; }
    public void setVacationLeaveBalance(int balance) { this.vacationLeaveBalance = balance; }
    public int getEmergencyLeaveBalance() { return emergencyLeaveBalance; }
    public void setEmergencyLeaveBalance(int balance) { this.emergencyLeaveBalance = balance; }
    
    // Getters
    public String getEmployeeID() { return employeeID; }
    public String getName() { return name; }
    public List<Leave> getLeaveHistory() { return leaveHistory; }
}
