/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author antongalfo
 */
public class LeaveManager {

    // Can this employee take this leave?
    public static boolean requestLeave(Employee emp, Leave leave) {
        
        String type = leave.getReason(); // Assuming 'reason' holds "Sick", "Vacation", etc.
        int days = leave.getDays();

        // 1.leave type and CHECK balance
        if (type.equalsIgnoreCase("Sick")) {
            
            if (emp.getSickLeaveBalance() >= days) {
                // Approved: Deduct balance
                int newBalance = emp.getSickLeaveBalance() - days;
                emp.setSickLeaveBalance(newBalance);
                
                // File the leave
                emp.fileLeave(leave);
                System.out.println("✅ Sick Leave Approved! Remaining: " + newBalance);
                return true;
            } else {
                System.out.println("❌ Denied: Insufficient Sick Leave Balance.");
                return false;
            }

        } else if (type.equalsIgnoreCase("Vacation")) {
            
            if (emp.getVacationLeaveBalance() >= days) {
                // Approved: Deduct balance
                int newBalance = emp.getVacationLeaveBalance() - days;
                emp.setVacationLeaveBalance(newBalance);
                
                emp.fileLeave(leave);
                System.out.println("✅ Vacation Leave Approved! Remaining: " + newBalance);
                return true;
            } else {
                System.out.println("❌ Denied: Insufficient Vacation Leave Balance.");
                return false;
            }

        } else if (type.equalsIgnoreCase("Emergency")) {
             
            if (emp.getEmergencyLeaveBalance() >= days) {
                int newBalance = emp.getEmergencyLeaveBalance() - days;
                emp.setEmergencyLeaveBalance(newBalance);
                
                emp.fileLeave(leave);
                System.out.println("✅ Emergency Leave Approved! Remaining: " + newBalance);
                return true;
            } else {
                System.out.println("❌ Denied: Insufficient Emergency Leave Balance.");
                return false;
            }
        }

        // If type is unknown
        System.out.println("⚠️ Unknown leave type: " + type);
        return false;
    }
}
