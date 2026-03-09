/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import dao.*;
import service.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Vector;

/**
 * AdminDashboard — Full-access dashboard for HR Admin.
 * RBAC: Admin sees Employee, Leave Management, Attendance, Payslip.
 */
public class AdminDashboard extends JFrame {

    private final CardLayout cardLayout = new CardLayout();
    private JPanel contentPanel;
    private final EmployeePanel employeePanel = new EmployeePanel();

    // ── Buttons stored as fields so they can be wired after sidebar is built ──
    private JButton employeeBtn;
    private JButton leaveBtn;
    private JButton attendanceBtn;
    private JButton printPayslipBtn;
    private JButton logoutBtn;
    private JButton ticketBtn;

    public AdminDashboard(String username) {
        setTitle("MotorPH — Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(800, 500));

        // ── Services ──────────────────────────────────────────────────────────
        LeaveService      leaveService      = new LeaveService(new LeaveDAO());
        AttendancePanel   attendancePanel   = new AttendancePanel(new model.FileHandler());
        LeavePanel        leavePanel        = new LeavePanel(leaveService);

        // ── Content area ──────────────────────────────────────────────────────
        contentPanel = new JPanel(cardLayout) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(29, 69, 143));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        employeePanel.setOpaque(false);
        leavePanel.setOpaque(false);
        attendancePanel.setOpaque(false);

        contentPanel.add(employeePanel,            "Employee");
        contentPanel.add(leavePanel,               "Leave");
        contentPanel.add(attendancePanel,          "Attendance");
        contentPanel.add(new TicketSupportPanel(), "Tickets");

        // ── Build sidebar — buttons are assigned to fields inside ─────────────
        JPanel sidebar = buildSidebar();

        // ── Wire buttons directly ─────────────────────────────────────────────
        employeeBtn.addActionListener(e ->
                cardLayout.show(contentPanel, "Employee"));

        leaveBtn.addActionListener(e ->
                cardLayout.show(contentPanel, "Leave"));

        attendanceBtn.addActionListener(e ->
                cardLayout.show(contentPanel, "Attendance"));
        
        ticketBtn.addActionListener(e -> 
                cardLayout.show(contentPanel, "Tickets"));

        printPayslipBtn.addActionListener(e -> {
            EmployeeTable table = (EmployeeTable) employeePanel.getDashboardTable();
            Vector<Object> selected = table.getSelectedEmployeeFullDetails();
            if (selected != null) {
                double gross = parseMoney(selected.get(13));
                new PayslipFrame(selected, gross, 0.0, gross);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please select an employee from the table first!");
            }
        });

        logoutBtn.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> new LoginPanel().setVisible(true));
        });

        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    // ════════════════════════════════════════════════════════════════════════
    //  Sidebar — buttons assigned to fields here
    // ════════════════════════════════════════════════════════════════════════
    private JPanel buildSidebar() {
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setBackground(Color.WHITE);
        sidebar.setPreferredSize(new Dimension(250, getHeight()));
        sidebar.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Profile
        JPanel profilePanel = new JPanel(new BorderLayout(10, 0));
        profilePanel.setBackground(Color.WHITE);
        profilePanel.add(new JLabel(loadIcon("/assets/userprofile.png", 40, 40)),
                BorderLayout.WEST);

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.setBackground(Color.WHITE);
        JLabel nameLabel = new JLabel("Admin");
        JLabel roleLabel = new JLabel("HR Manager");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        roleLabel.setForeground(Color.GRAY);
        namePanel.add(nameLabel);
        namePanel.add(roleLabel);
        profilePanel.add(namePanel, BorderLayout.CENTER);

        // Nav
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(Color.WHITE);
        navPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        JLabel generalLabel = new JLabel("General");
        generalLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        generalLabel.setBorder(new EmptyBorder(0, 10, 10, 0));
        generalLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ── Assign to fields ──────────────────────────────────────────────────
        employeeBtn     = makeNavBtn("Employee",         "employee.png");
        leaveBtn        = makeNavBtn("Leave Management", "leave.png");
        attendanceBtn   = makeNavBtn("Attendance",       "attendance.png");
        printPayslipBtn = makeNavBtn("Print Payslip",    "Payslip Button.png");
        logoutBtn       = makeNavBtn("Log-out",          "logout.png");
        ticketBtn       = makeNavBtn("Ticket Support",   "IT Support.png");
        logoutBtn.setForeground(Color.GRAY);

        navPanel.add(Box.createVerticalStrut(20));
        navPanel.add(generalLabel);
        navPanel.add(employeeBtn);
        navPanel.add(Box.createVerticalStrut(5));
        navPanel.add(leaveBtn);
        navPanel.add(Box.createVerticalStrut(5));
        navPanel.add(attendanceBtn);
        navPanel.add(Box.createVerticalStrut(5));
        navPanel.add(printPayslipBtn);
        navPanel.add(ticketBtn);
        navPanel.add(Box.createVerticalStrut(5));

        sidebar.add(profilePanel, BorderLayout.NORTH);
        sidebar.add(navPanel,     BorderLayout.CENTER);
        sidebar.add(logoutBtn,    BorderLayout.SOUTH);
        return sidebar;
    }

    // ── Nav button factory ────────────────────────────────────────────────────
    private JButton makeNavBtn(String text, String iconFile) {
        JButton btn = new JButton(text);
        btn.setIcon(loadIcon("/assets/" + iconFile, 20, 20));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setIconTextGap(15);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(new Color(240, 240, 240)); }
            @Override public void mouseExited(MouseEvent e)  { btn.setBackground(Color.WHITE); }
        });
        return btn;
    }

    private ImageIcon loadIcon(String path, int w, int h) {
        URL url = getClass().getResource(path);
        if (url == null) return null;
        return new ImageIcon(
                new ImageIcon(url).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
    }

    private double parseMoney(Object value) {
        if (value == null) return 0.0;
        try {
            return Double.parseDouble(
                    value.toString().replace(",", "").replace("\"", "").trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
