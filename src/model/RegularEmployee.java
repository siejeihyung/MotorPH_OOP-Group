/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * MotorPH Regular Employee Subclass
 */
package model;

/**
 * MotorPH Regular Employee Subclass
 */
public class RegularEmployee extends Employee {

    public RegularEmployee(String employeeID, String lastName, String firstName, 
                           double basicSalary, double semiMonthlyRate, 
                           double hourlyRate, Benefits benefits) {
        
        super(employeeID, lastName, firstName, basicSalary, semiMonthlyRate, hourlyRate, benefits);
    }
    
    // Regular employees get basic salary + fixed benefits
    // Adding this to satisfy the Employee abstract class if it still uses calculateGross
    @Override
    public double calculateGrossPay() {
        // Regular employees get basic salary + fixed benefits
        return this.basicSalary + this.benefits.calculateTotalAllowance();
    }
}
