/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 * Represents a leave request filed by an employee.
 * Stores leave type, duration, reason, and approval status.
 */
public class Leave {

    private String leaveID;
    private String employeeId;   // links leave to an employee
    private LocalDate date;
    private String type;         // "Sick", "Vacation", "Emergency"
    private int days;
    private String reason;       // FIXED: was throwing UnsupportedOperationException
    private String status;       // "Pending", "Approved", "Rejected"

    // ── Full constructor ──────────────────────────
    public Leave(String leaveID, String employeeId, LocalDate date,
                 String type, int days, String reason) {
        this.leaveID    = leaveID;
        this.employeeId = employeeId;
        this.date       = date;
        this.type       = type;
        this.days       = days;
        this.reason     = reason;
        this.status     = "Pending"; // Default status
    }

    // ── Backward-compatible constructor ───────
    public Leave(String leaveID, LocalDate date, String type, int days) {
        this(leaveID, "", date, type, days, "");
    }

    // ── Getters ─────────────────────────────────────────────────────────────
    public String    getLeaveID()    { return leaveID; }
    public String    getEmployeeId() { return employeeId; }
    public LocalDate getDate()       { return date; }
    public String    getType()       { return type; }
    public int       getDays()       { return days; }
    public String    getReason()     { return reason; }
    public String    getStatus()     { return status; }

    // ── Setter ──────────────────────────────────────────────────────────────
    public void setStatus(String status) { this.status = status; }

    // ── Convert to CSV row for FileHandler ──────────────────────────────────
    public String[] toRow() {
        return new String[]{
            employeeId,
            leaveID,
            date != null ? date.toString() : "",
            type,
            String.valueOf(days),
            reason,
            status
        };
    }

    // ── Rebuild Leave from a CSV row ─────────────────────────────────────────
    // Row format: employeeId, leaveID, date, type, days, reason, status
    public static Leave fromRow(String[] row) {
        Leave leave = new Leave(
            row[1],                      // leaveID
            row[0],                      // employeeId
            LocalDate.parse(row[2]),     // date
            row[3],                      // type
            Integer.parseInt(row[4]),    // days
            row[5]                       // reason
        );
        leave.setStatus(row[6]);         // restore saved status
        return leave;
    }
}
