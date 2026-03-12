package model;

/**
 * REFACTORED:Now an "Object" that knows how to calculate its own totals.
 */

// This class represents the non-monetary benefits or allowances provided to an employee
public class Benefits {


    private double riceSubsidy; // Rice subsidy allowance for the employee
    private double phoneAllowance;  // Phone allowance provided to the employee
    private double clothingAllowance; // Clothing allowance given to the employee

    // Constructor to initialize all benefit values
    public Benefits(double riceSubsidy, double phoneAllowance, double clothingAllowance) {
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
    }
    
    public double calculateTotalAllowance() {
        return this.riceSubsidy + this.phoneAllowance + this.clothingAllowance;
    }

    // Getter method to retrieve the rice subsidy amount
    public double getRiceSubsidy() { return riceSubsidy; }

    // Getter method to retrieve the phone allowance amount
    public double getPhoneAllowance() { return phoneAllowance; }

    // Getter method to retrieve the clothing allowance amount
    public double getClothingAllowance() { return clothingAllowance; }

    // Setter method to update the rice subsidy amount
    public void setRiceSubsidy(double riceSubsidy) { this.riceSubsidy = riceSubsidy; }

    // Setter method to update the phone allowance amount
    public void setPhoneAllowance(double phoneAllowance) { this.phoneAllowance = phoneAllowance; }

    // Setter method to update the clothing allowance amount
    public void setClothingAllowance(double clothingAllowance) { this.clothingAllowance = clothingAllowance; }
}
