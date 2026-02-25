/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import model.FileHandler;
import java.util.Arrays;
            
/**
 *
 * @author SunnyEljohn
 */
public class EmployeeDashboard extends JFrame {
    private String[] dataArray;
        
    public EmployeeDashboard(String employeeId) {
        System.out.println("Opening Dashboard for ID: " + employeeId); // Debug line       
        setTitle("MotorPH Employee Portal - " + employeeId);
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. Fetch data for this specific person
        FileHandler fileHandler = new FileHandler();
        fileHandler.readEmployeeFile();
        this.dataArray = fileHandler.getEmployeeById(employeeId);  
        
        // 2. ONLY add panel if data exists (Change == to !=)
        if (dataArray == null) {
            // Configuring Vector 
            Vector<Object> employeeVector = new Vector<>(Arrays.asList(dataArray));
            // Pass to Panel
            ViewEmployeePanel detailPanel = new ViewEmployeePanel(employeeVector);            
            // add UI
            if (detailPanel != null){
            add(detailPanel, BorderLayout.CENTER);
        }
    } 
        // 2. Setup the UI
        setLayout(new BorderLayout());    
        
        // EmployeeDashboard Constructor
            // Header Setup
            JPanel header = new JPanel(new BorderLayout());
            header.setBackground(new Color(50, 50, 50));
            header.setPreferredSize(new Dimension(1100, 50));

            JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
            ButtonPanel.setOpaque(false);

            // Print Payslip Button
            JButton printPayslipBtn = new JButton("Print Payslip");
            printPayslipBtn.addActionListener(e -> {
                java.util.Vector<Object> employeeVector = new java.util.Vector<>(java.util.Arrays.asList(this.dataArray));

                // Use parseMoney to safely handle commas
                double gross = parseMoney(dataArray[13]); 
                double rice = parseMoney(dataArray[14]);
                double phone = parseMoney(dataArray[15]);

                // For now, setting a placeholder for deductions
                double deductions = 0.0; 
                double net = (gross + rice + phone) - deductions;

                // Launch the updated PayslipFrame with 4 arguments
                new PayslipFrame(employeeVector, gross, deductions, net);
            });

            // Logout Button
            JButton logoutBtn = new JButton("Logout");
            logoutBtn.addActionListener(e -> {
                new LoginPanel().setVisible(true);
                dispose();
            });

            ButtonPanel.add(printPayslipBtn);
            ButtonPanel.add(logoutBtn);

            JLabel welcomeLabel = new JLabel("  Welcome, " + dataArray[2] + " " + dataArray[1]);
            welcomeLabel.setForeground(Color.WHITE);
            welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

            header.add(welcomeLabel, BorderLayout.WEST);
            header.add(ButtonPanel, BorderLayout.EAST);
            add(header, BorderLayout.NORTH);

            // Employee Details
            java.util.Vector<Object> employeeVector = new java.util.Vector<>(java.util.Arrays.asList(this.dataArray));
            ViewEmployeePanel detailPanel = new ViewEmployeePanel(employeeVector);
            add(detailPanel, BorderLayout.CENTER);

            setVisible(true);
            System.out.println("Dashboard is now visible.");
        } // End of if
    
            private double parseMoney(Object value) {
                if (value == null || value.toString().isEmpty()) return 0.0;
                // Removes commas and quotes so "90,000" becomes "90000"
                String cleanValue = value.toString().replace(",", "").replace("\"", "").trim();
                try {
                    return Double.parseDouble(cleanValue);
                } catch (NumberFormatException e) {
                    return 0.0;
                }
            }
        } 
        
        
        
        
        
        
        
        
        
        
        
        
        