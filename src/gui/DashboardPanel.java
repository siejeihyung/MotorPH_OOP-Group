package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import model.FileHandler;

public class DashboardPanel extends JFrame {

    private final CardLayout cardLayout = new CardLayout();
    JPanel contentPanel = new JPanel();
    private final EmployeePanel employeePanel = new EmployeePanel();

    public DashboardPanel(String user) {
        setTitle("MotorPH Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(800, 500));

        Color sidebarColor = Color.WHITE;
        Color gradientStart = new Color(255, 204, 229);
        Color gradientEnd   = new Color(255, 229, 180);
        Font boldFont    = new Font("Segoe UI", Font.BOLD, 16);
        Font regularFont = new Font("Segoe UI", Font.PLAIN, 14);

        // Sidebar
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setBackground(sidebarColor);
        sidebar.setPreferredSize(new Dimension(250, getHeight()));
        sidebar.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Profile area
        JPanel profilePanel = new JPanel(new BorderLayout(10, 0));
        profilePanel.setBackground(sidebarColor);
        profilePanel.add(new JLabel(loadImageIcon("/assets/userprofile.png", 40, 40)), BorderLayout.WEST);

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.setBackground(sidebarColor);
        JLabel userName = new JLabel("Admin");
        JLabel userRole = new JLabel("HR Manager");
        userName.setFont(boldFont);
        userRole.setFont(regularFont);
        userRole.setForeground(Color.GRAY);
        namePanel.add(userName);
        namePanel.add(userRole);
        profilePanel.add(namePanel, BorderLayout.CENTER);

        // Nav buttons
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(sidebarColor);
        navPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        navPanel.add(Box.createVerticalStrut(30));

        JLabel generalLabel = new JLabel("General");
        generalLabel.setFont(boldFont);
        generalLabel.setBorder(new EmptyBorder(0, 10, 10, 0));
        generalLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        navPanel.add(generalLabel);

        JButton employeeBtn = createNavButton("Employee",         "employee.png");
        JButton leaveBtn    = createNavButton("Leave Management", "leave.png"); // NEW

        navPanel.add(employeeBtn);
        navPanel.add(Box.createVerticalStrut(5));
        navPanel.add(leaveBtn);                                                 // NEW
        navPanel.add(Box.createVerticalStrut(5));

        JButton logoutButton = createNavButton("Log-out", "logout.png");
        logoutButton.setForeground(Color.GRAY);
        logoutButton.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> new LoginPanel().setVisible(true));
        });

        sidebar.add(profilePanel,  BorderLayout.NORTH);
        sidebar.add(navPanel,      BorderLayout.CENTER);
        sidebar.add(logoutButton,  BorderLayout.SOUTH);

        // Content area
        contentPanel = new JPanel(cardLayout) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, gradientStart, 0, getHeight(), gradientEnd));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        FileHandler fileHandler   = new FileHandler();
        AttendancePanel attendancePanel = new AttendancePanel(fileHandler);
        LeavePanel leavePanel     = new LeavePanel(fileHandler);  // NEW

        contentPanel.add(employeePanel, "Employee");
        contentPanel.add(leavePanel,    "Leave");                 // NEW

        employeeBtn.addActionListener(e -> cardLayout.show(contentPanel, "Employee"));
        leaveBtn.addActionListener(e    -> cardLayout.show(contentPanel, "Leave")); // NEW

        add(sidebar,       BorderLayout.WEST);
        add(contentPanel,  BorderLayout.CENTER);
        setVisible(true);
    }

    private JButton createNavButton(String text, String iconFileName) {
        JButton button = new JButton(text);
        button.setIcon(loadImageIcon("/assets/" + iconFileName, 20, 20));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIconTextGap(15);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { button.setBackground(new Color(240, 240, 240)); }
            @Override public void mouseExited(MouseEvent e)  { button.setBackground(Color.WHITE); }
        });
        return button;
    }

    private ImageIcon loadImageIcon(String path, int width, int height) {
        URL imageUrl = getClass().getResource(path);
        if (imageUrl != null) {
            Image scaled = new ImageIcon(imageUrl).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        }
        System.err.println("Image not found: " + path);
        return null;
    }
}