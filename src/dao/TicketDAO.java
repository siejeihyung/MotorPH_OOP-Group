/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Ticket;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SunnyEljohn
 */
public class TicketDAO {
    
    private final String filePath = "tickets.csv";

    // ── Generate a unique ID (e.g., TKT-1001) ────────────────────────────────
    public String generateNewID() {
        List<Ticket> existing = getAllTickets();
        
        if (existing.isEmpty()) {
            return "TKT-1001";
        }
        
        try {
            Ticket lastTicket = existing.get(existing.size() - 1);
            String lastID = lastTicket.getTicketID(); 
            int lastNumber = Integer.parseInt(lastID.replace("TKT-", ""));
            return "TKT-" + (lastNumber + 1);
        } catch (Exception e) {
            return "TKT-" + (System.currentTimeMillis() % 10000); 
        }
    }

    // ── Save a new ticket to CSV ─────────────────────────────────────────────
    public void saveTicket(Ticket ticket) {
        try (FileWriter fw = new FileWriter(filePath, true);
             PrintWriter out = new PrintWriter(new BufferedWriter(fw))) {
            
            out.println(String.format("%s,%s,%s,%s,%s,%s",
                    ticket.getTicketID(),
                    ticket.getSenderName(),
                    ticket.getCategory(),
                    ticket.getSubject().replace(",", ";"),
                    ticket.getDescription().replace(",", ";"),
                    ticket.getStatus()));
        } catch (IOException e) {
            System.err.println("Error saving ticket: " + e.getMessage());
        }
    }

    // ── Read all tickets from CSV ─────────────────────────────────────────────
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        File file = new File(filePath);
        
        if (!file.exists()) return tickets;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 6) {
                    Ticket t = new Ticket(data[0], data[1], data[2], data[3], data[4]);
                    t.setStatus(data[5]);
                    tickets.add(t);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading tickets: " + e.getMessage());
        }
        return tickets;
    }
}