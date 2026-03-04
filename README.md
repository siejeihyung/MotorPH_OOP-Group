# MotorPH Payroll & HR System — NewGUI Version 1.0

## 📝 Description
Welcome to the stable release of the **MotorPH NewGUI (Version 1.0)**. This branch represents a significant milestone in the project's development, moving from a legacy gradient-based design to a professional, enterprise-grade interface. This system is designed to handle employee records, attendance, and payroll calculations for MotorPH.

## 🚀 What's New in Version 1.0
In this update, we have focused on UI consistency and robust Object-Oriented (OOP) implementation:

* **Enterprise Branding:** Transitioned from the experimental pink/orange gradients to a solid **MotorPH Blue** theme (`#1D458F`).
* **Transparency Optimization:** Optimized the GUI component hierarchy. By utilizing `setOpaque(false)` and overriding `paintComponent` in the `EmployeePanel` and `EmployeeTable`, we achieved a seamless "layered" look where the main background remains consistent across all views.
* **High-Contrast Sidebar:** Redesigned the navigation sidebar for better accessibility, featuring white text on a dark blue background and custom icons for a modern feel.
* **Stabilized Data Parsing:** Improved the `parseMoney` logic to handle formatted numeric strings (e.g., "90,000") without causing runtime exceptions.



## 🛠 Technical Features (OOP Principles)
This project is built using **Java Swing** with a focus on clean code and OOP standards:
* **Inheritance:** Custom UI classes extend `JPanel` or `JFrame` to modularize the interface.
* **Encapsulation:** All file operations are handled via the `FileHandler` class, protecting the integrity of the underlying CSV data.
* **Dynamic Rendering:** Implemented custom `TableCellRenderers` to provide a responsive table experience that adjusts font sizes and row colors dynamically.

## 📋 Core Functionalities
* **Employee Management:** View, Add, Update, and Delete employee records with real-time file synchronization.
* **Payroll Processing:** Calculate Gross/Net pay and generate payslips.
* **Attendance Tracking:** (In Progress) Integrated file reading for employee time logs.
* **User Feedback:** Custom-styled modal dialogs for warnings and success messages.

## ⚙️ Development Setup
To run this project locally:
1.  Clone this specific branch: `git clone -b NewGUI-Version1.0 https://github.com/siejeihyung/MotorPH_OOP-Group-28.git`
2.  Open the project in your preferred IDE (NetBeans/IntelliJ/Eclipse).
3.  **Crucial:** Perform a **"Clean and Build"** to ensure all new color assets and transparency settings are correctly compiled.
4.  Run `LoginPanel.java` to start the application.

---
*This is still in process of updating.*
