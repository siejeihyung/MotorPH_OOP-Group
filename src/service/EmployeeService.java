/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.EmployeeDAO;
import java.io.*;
import java.util.*;

/**
 * EmployeeService — Business logic for employee management.
 *
 * Handles:
 *  - credentials.csv format: username,password,role
 *  - Roles: ADMIN, HR, FINANCE
 *  - Regular employees: ID + Last Name (role = EMPLOYEE)
 */
public class EmployeeService {

    private final EmployeeDAO employeeDAO;
    private final String credentialsFilePath = "credentials.csv";

    // ── Roles for RBAC ────────────────────────────────────────────────────────
    public static final String ROLE_ADMIN    = "ADMIN";
    public static final String ROLE_HR       = "HR";
    public static final String ROLE_FINANCE  = "FINANCE";
    public static final String ROLE_EMPLOYEE = "EMPLOYEE";

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
        this.employeeDAO.load();
    }

    // ════════════════════════════════════════════════════════════════════════
    //  Authentication — returns role or null if failed
    // ════════════════════════════════════════════════════════════════════════

    /**
     * Returns the role of the authenticated user:
     *  "ADMIN", "HR", "FINANCE" → from credentials.csv
     *  "EMPLOYEE"               → from employee.csv (ID + Last Name)
     *  null                     → login failed
     */
    public String authenticate(String username, String password) {
        // Check credentials.csv first (Admin, HR, Finance)
        String roleFromCredentials = checkCredentials(username, password);
        if (roleFromCredentials != null) return roleFromCredentials;

        // Check employee.csv (Regular Employee login: ID + Last Name)
        for (String[] emp : employeeDAO.findAll()) {
            if (emp.length > 1
                    && emp[0].trim().equals(username)
                    && emp[1].trim().equalsIgnoreCase(password)) {
                return ROLE_EMPLOYEE;
            }
        }
        return null; // Login failed
    }

    /**
     * Checks credentials.csv for a matching username/password.
     * Returns the role if found, null otherwise.
     * credentials.csv format: username,password,role
     */
    private String checkCredentials(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(credentialsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(",");
                if (parts.length >= 3
                        && parts[0].trim().equals(username)
                        && parts[1].trim().equals(password)) {
                    return parts[2].trim().toUpperCase(); // Return role
                }
                // Backward compatible: if no role column, assume ADMIN
                if (parts.length == 2
                        && parts[0].trim().equals(username)
                        && parts[1].trim().equals(password)) {
                    return ROLE_ADMIN;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading credentials: " + e.getMessage());
        }
        return null;
    }

    // ════════════════════════════════════════════════════════════════════════
    //  CRUD
    // ════════════════════════════════════════════════════════════════════════
    public List<String[]> getAllEmployees()                      { return employeeDAO.findAll(); }
    public String[]       getEmployeeById(String id)            { return employeeDAO.findById(id); }
    public boolean        addEmployee(String[] row)             { return employeeDAO.append(row); }
    public boolean        updateField(String id, String col, String val) { return employeeDAO.updateField(id, col, val); }
    public boolean        deleteEmployee(String id)             { return employeeDAO.deleteById(id); }

    public String getEmployeeName(String employeeId) {
        String[] emp = employeeDAO.findById(employeeId);
        if (emp != null && emp.length > 2) return emp[2] + " " + emp[1];
        return employeeId;
    }
}