/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
/**
 *
 * @author antongalfo
 */
public class Leave {
    private String leaveID;
    private LocalDate date;
    private String type; // "Sick", "Vacation", "Emergency"
    private int days;
    private String status; // "Pending", "Approved", "Rejected"

    public Leave(String leaveID, LocalDate date, String type, int days) {
        this.leaveID = leaveID;
        this.date = date;
        this.type = type;
        this.days = days;
        this.status = "Pending"; // Default status
    }

    // Getters
    public String getType() { return type; }
    public String getStatus() { return status; }
    public int getDays() { return days; }

    String getReason() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
