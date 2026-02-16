/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * MotorPH Regular Employee Subclass
 */
public class RegularEmployee extends Employee {

    public RegularEmployee(String employeeID, String lastName, String firstName, 
                           double basicSalary, double semiMonthlyRate, 
                           double hourlyRate, double totalBenefits) {
        
        super(employeeID, lastName, firstName, basicSalary, semiMonthlyRate, hourlyRate, totalBenefits);
    }

    // Renamed to match the exact name in your grossPayable interface
    @Override
    public double getcalculateGrossWeeklySalary() {
        return getBasicSalary() + getTotalBenefits();
    }

    // Adding this to satisfy the Employee abstract class if it still uses calculateGross
    @Override
    public double calculateGross() {
        return getcalculateGrossWeeklySalary();
    }
}