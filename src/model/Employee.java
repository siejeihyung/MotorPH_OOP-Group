package model;

// This is the SINGLE Parent Class for all types of Employees

/**
 *
 * @author SunnyEljohn
 */
// Inside Employee.java

public abstract class Employee implements IPayable {

    static double calculateGrossSalary() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    // Identity Fields (Protected so subclasses can use them)
    protected String employeeID;
    protected String lastName;
    protected String firstName;
    protected String sss;
    protected String philhealth;
    protected String tin;
    protected String pagibig;

    // Salary Fields
    protected double basicSalary;
    protected double semiMonthlyRate;
    protected double hourlyRate;
    protected double totalBenefits;

    // Merged Constructor: This initializes everything at once
    public Employee(String employeeID, String lastName, String firstName, 
                    double basicSalary, double semiMonthlyRate, double hourlyRate, double totalBenefits) {
        this.employeeID = employeeID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.basicSalary = basicSalary;
        this.semiMonthlyRate = semiMonthlyRate;
        this.hourlyRate = hourlyRate;
        this.totalBenefits = totalBenefits;
    }
    
    public double getBasicSalary() {
        return basicSalary;
    }

    public double getTotalBenefits() {
        return totalBenefits;
    }

    // --- Getters and Setters ---
    public String getEmployeeID() { return employeeID; }
    public String getFullName() { return firstName + " " + lastName; }
    public double getHourlyRate() { return hourlyRate; }

    // --- Abstract Methods ---
    // Subclasses like regularEmployee MUST implement these
    public abstract double calculateGross();
}
