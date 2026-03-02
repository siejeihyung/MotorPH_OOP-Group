/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author antongalfo
 */
public class RegularEmployee extends Employee {

    private double basicSalary;
    private double semiMonthlyRate;
    private double hourlyRate;

    public RegularEmployee(String employeeID, String name, double basicSalary, double semiMonthlyRate, double hourlyRate1) {
        // 1. Send ID and Name up to the parent (Employee)
        super(employeeID, name);
        // specific salary details here
        this.basicSalary = basicSalary;
        this.semiMonthlyRate = semiMonthlyRate;
        this.hourlyRate = hourlyRate;
          
    }
    // 2. OVERLOADED CONSTRUCTOR
    public RegularEmployee(String employeeID, String name) {
        super(employeeID, name);
        // Set default values
        this.basicSalary = 0.0;
        this.semiMonthlyRate = 0.0;
        this.hourlyRate = 0.0;
    }

    @Override
    public double calculateGrossWeeklySalary() {
        // logic: (Monthly Salary / 4)
        // can adjust this math
        return basicSalary / 4;
    }

    // Getters
    public double getBasicSalary() { return basicSalary; }
    public double getSemiMonthlyRate() { return semiMonthlyRate; }
    public double getHourlyRate() { return hourlyRate; }
}
