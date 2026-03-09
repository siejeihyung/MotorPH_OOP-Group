/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gui;

import dao.TicketDAO;
import model.Ticket;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author SunnyEljohn
 */

/**
 * ITSupportDashboard — Specialized view for IT Support role.
 * Focuses on System Tools and Ticketing Support.
 */

public class ITSupportDashboard extends JFrame {

    private final CardLayout cardLayout = new CardLayout();
    private JPanel contentPanel;
    private JButton ticketListBtn;
    private JButton logoutBtn;

   public ITSupportDashboard(String username) {
        
        // 1. Initialize the panel FIRST. This fixes the NullPointerException!
        this.contentPanel = new JPanel(new CardLayout());

        // 2. Now it is safe to set the background
        this.contentPanel.setBackground(new Color(29, 69, 143));

        setTitle("MotorPH — IT Support Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 2. Initialize Sidebar and Buttons
        JPanel sidebar = buildSidebar(username); // Ensure this method initializes ticketListBtn/logoutBtn

        // 3. Create and populate Ticket List View
        JPanel ticketListView = new JPanel(new BorderLayout());
        ticketListView.setBorder(new EmptyBorder(20, 20, 20, 20)); // Added padding

        // Call the table loader method
        loadTicketTable(ticketListView); 

        // Add to contentPanel
        contentPanel.add(ticketListView, "TicketList");

        // 4. Wiring
        if (ticketListBtn != null) {
            ticketListBtn.addActionListener(e -> cardLayout.show(contentPanel, "TicketList"));
        }

        if (logoutBtn != null) {
            logoutBtn.addActionListener(e -> {
                dispose();
                new LoginPanel().setVisible(true);
            });
        }

        // 5. Final layout assembly
        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel buildSidebar(String user) {
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setBackground(Color.WHITE);
        sidebar.setPreferredSize(new Dimension(250, getHeight()));
        sidebar.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Profile Header (Using your style)
        JLabel nameLabel = new JLabel(user);
        JLabel roleLabel = new JLabel("IT Support Specialist");
        roleLabel.setForeground(Color.GRAY);
        
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(Color.WHITE);

        // IT Specific Buttons
        ticketListBtn = makeNavBtn("View Tickets", "attendance.png"); 
        sidebar.add(ticketListBtn);
        logoutBtn = makeNavBtn("Log-out", "logout.png");

        navPanel.add(new JLabel("System Tools"));
        navPanel.add(Box.createVerticalStrut(10));
        navPanel.add(ticketListBtn);

        sidebar.add(navPanel, BorderLayout.CENTER);
        sidebar.add(logoutBtn, BorderLayout.SOUTH);
        
        JPanel ticketListView = new JPanel(new BorderLayout());
        loadTicketTable(ticketListView); // Populate the table
        contentPanel.add(ticketListView, "TicketList");
        return sidebar;
    }

    // Reuse your makeNavBtn factory method here...
    private JButton makeNavBtn(String text, String iconFile) {
        JButton btn = new JButton(text);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setIcon(new ImageIcon("src/assets/" + "IT Support.png"));

        return btn;
    }
    
    private void loadTicketTable(JPanel ticketListView) {
        
        TicketDAO dao = new TicketDAO();
        List<Ticket> tickets = dao.getAllTickets();

        String[] columns = {"ID", "Sender", "Category", "Subject", "Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        
        System.out.println("DEBUG: Tickets retrieved from CSV: " + tickets.size());

        for (Ticket t : tickets) {
            model.addRow(new Object[]{
                t.getTicketID(), t.getSenderName(), t.getCategory(), t.getSubject(), t.getStatus()
            });
        }

        JTable table = new JTable(model);
        ticketListView.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
