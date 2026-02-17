MotorPH Payroll System - OOP Polymorphism & Leave Management
This branch introduces advanced Object-Oriented Programming concepts, specifically Polymorphism, and implements the foundational logic for the Leave Management System. These updates enhance the flexibility of the codebase and allow for specialized handling of different employee categories and leave types.

🚀 New Features & Updates
1. Polymorphism Implementation
Method Overriding: Implemented polymorphic behavior in the calculation of benefits and deductions. The system can now process various employee types (e.g., Full-time vs. Part-time) through a unified interface while executing specific logic for each.

Abstract Base Classes: Defined core structures for "Leave" and "Employee" to ensure consistent implementation across all inherited subclasses.

2. Leave Management Module
Leave Calculation: Added logic to track and calculate leave balances (Sick Leave, Vacation Leave, and Emergency Leave).

Status Tracking: Introduced functionality to handle leave requests, approvals, and the subsequent impact on the payroll cycle (e.g., Paid vs. Unpaid leave).

Validation: Added checks to ensure employees cannot apply for more leave than their current accrued balance.

3. Code Refactoring
Updated the Employee class hierarchy to support dynamic binding.

Improved modularity by separating leave-related logic from the core payroll calculation class.

🛠️ How it Works
Inheritance: The system uses a base Leave class which is extended by specific leave types.

Dynamic Binding: When the payroll is processed, the system dynamically determines the correct leave deduction based on the specific object type at runtime.

📁 Files Modified
Employee.java (or relevant class): Updated to support abstract methods for polymorphism.

LeaveManager.java: New class created to handle the business logic for employee time-off.

PayrollSystem.java: Updated to integrate leave deductions into the final salary computation.
