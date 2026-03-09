/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import dao.TicketDAO;
import model.Ticket;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author SunnyEljohn
 */
public class TicketSupportPanel extends JPanel {
    
    public TicketSupportPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new SpringLayout());
        formPanel.setOpaque(false);

        String[] labels = {"Category: ", "Subject: ", "Message: "};
        String[] categories = {"Technical Issue", "Payroll Inquiry", "HR Systems", "Other"};
        
        JComboBox<String> categoryBox = new JComboBox<>(categories);
        JTextField subjectField = new JTextField(20);
        JTextArea messageArea = new JTextArea(5, 20);
        messageArea.setLineWrap(true);

        formPanel.add(new JLabel(labels[0]));
        formPanel.add(categoryBox);
        formPanel.add(new JLabel(labels[1]));
        formPanel.add(subjectField);
        formPanel.add(new JLabel(labels[2]));
        formPanel.add(new JScrollPane(messageArea));

        // This uses the SpringUtilities you uploaded to align everything
        SpringUtilities.makeCompactGrid(formPanel, 3, 2, 6, 6, 10, 10);

        JButton submitBtn = new JButton("Submit Ticket");
        
        // ─── START OF INSERTED LOGIC ───
        submitBtn.addActionListener(e -> {
            TicketDAO dao = new TicketDAO();
            
            // 1. Automatically generate the next ID (e.g., TKT-1001)
            String nextID = dao.generateNewID();
            
            // 2. Collect data from your GUI fields
            // NOTE: Replace "Sunny Eljohn" with your session variable if you have one
            String user = "Sunny Eljohn"; 
            String cat  = (String) categoryBox.getSelectedItem();
            String sub  = subjectField.getText();
            String desc = messageArea.getText();
            
            // Basic Validation: Don't submit if fields are empty
            if (sub.trim().isEmpty() || desc.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // 3. Create the Ticket object
            Ticket newTicket = new Ticket(nextID, user, cat, sub, desc);
            
            // 4. Save to CSV via the DAO
            dao.saveTicket(newTicket);
            
            JOptionPane.showMessageDialog(this, "Ticket " + nextID + " submitted successfully!");
            
            // 5. Clear fields for next use
            subjectField.setText("");
            messageArea.setText("");
        });
        // ─── END OF INSERTED LOGIC ───

        add(formPanel, BorderLayout.CENTER);
        add(submitBtn, BorderLayout.SOUTH);
    }
}