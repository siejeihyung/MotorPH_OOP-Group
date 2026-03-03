package gui;

import model.FileHandler;
import model.Leave;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * LeavePanel — Admin view for managing employee leave requests.
 * Matches the gradient style of AttendancePanel.
 *
 * Features:
 *  • View all leave requests in a table
 *  • Search by Employee ID
 *  • File a new leave request (dialog form)
 *  • Approve or Reject a selected pending request
 *  • Data persisted to data/leave.txt via FileHandler
 */
public class LeavePanel extends JPanel {

    // ── Data ────────────────────────────────────────────────────────────────
    private final FileHandler fileHandler;
    private List<String[]> allLeaveData;

    // ── Table ────────────────────────────────────────────────────────────────
    private JTable table;
    private DefaultTableModel model;

    // ── Search ───────────────────────────────────────────────────────────────
    private JTextField searchField;

    // ── Column indices ───────────────────────────────────────────────────────
    // leave.txt row: employeeId, leaveID, date, type, days, reason, status
    private static final int COL_EMP_ID  = 0;
    private static final int COL_LEAVE_ID = 1;
    private static final int COL_DATE    = 2;
    private static final int COL_TYPE    = 3;
    private static final int COL_DAYS    = 4;
    private static final int COL_REASON  = 5;
    private static final int COL_STATUS  = 6;

    // ── Colors (matches AttendancePanel) ─────────────────────────────────────
    private final Color gradientStart = new Color(255, 204, 229);
    private final Color gradientEnd   = new Color(255, 229, 180);
    private final Color headerBlue    = new Color(33, 150, 243);

    // ════════════════════════════════════════════════════════════════════════
    //  Constructor
    // ════════════════════════════════════════════════════════════════════════
    public LeavePanel(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // Load saved leave records
        fileHandler.readLeaveFile();
        allLeaveData = fileHandler.getAllLeaveData();

        // Build table
        String[] columns = {"Employee #", "Leave ID", "Date", "Type", "Days", "Reason", "Status"};
        model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(model);
        table.setRowHeight(26);
        table.setFillsViewportHeight(true);
        table.setShowGrid(true);
        table.setDefaultRenderer(Object.class, new LeaveTableCellRenderer());
        table.getTableHeader().setDefaultRenderer(new LeaveTableHeaderRenderer());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new ModernScrollBarUI());

        add(buildTopPanel(), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buildActionPanel(), BorderLayout.SOUTH);

        refreshTable(allLeaveData);
    }

    // ════════════════════════════════════════════════════════════════════════
    //  Top panel: search bar + File Leave button
    // ════════════════════════════════════════════════════════════════════════
    private JPanel buildTopPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        // ── Summary cards ────────────────────────────────────────────────────
        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        summaryPanel.setOpaque(false);
        summaryPanel.add(makeSummaryCard("📋 Total Requests", countByStatus(null)));
        summaryPanel.add(makeSummaryCard("⏳ Pending",         countByStatus("Pending")));
        summaryPanel.add(makeSummaryCard("✅ Approved",        countByStatus("Approved")));
        summaryPanel.add(makeSummaryCard("❌ Rejected",        countByStatus("Rejected")));

        // ── Search bar + File Leave button ───────────────────────────────────
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setOpaque(false);

        searchField = new JTextField(15);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        searchField.setPreferredSize(new Dimension(180, 30));
        searchField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));

        JButton searchBtn = makeRoundButton("Search", new Color(30, 144, 255), 80, 36);
        searchBtn.addActionListener(e -> search());
        searchField.addActionListener(e -> search());

        JButton clearBtn = makeRoundButton("Clear", new Color(150, 150, 150), 70, 36);
        clearBtn.addActionListener(e -> {
            searchField.setText("");
            refreshTable(allLeaveData);
        });

        JButton fileLeaveBtn = makeRoundButton("+ File Leave", new Color(76, 175, 80), 110, 36);
        fileLeaveBtn.addActionListener(e -> openFileLeaveDialog());

        searchPanel.add(new JLabel("Search Employee ID:"));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        searchPanel.add(clearBtn);
        searchPanel.add(Box.createHorizontalStrut(30));
        searchPanel.add(fileLeaveBtn);

        panel.add(summaryPanel, BorderLayout.NORTH);
        panel.add(searchPanel, BorderLayout.CENTER);
        return panel;
    }

    // ════════════════════════════════════════════════════════════════════════
    //  Bottom action panel: Approve / Reject selected row
    // ════════════════════════════════════════════════════════════════════════
    private JPanel buildActionPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panel.setOpaque(false);

        JButton approveBtn = makeRoundButton("✔ Approve", new Color(56, 142, 60), 120, 38);
        approveBtn.addActionListener(e -> updateSelectedStatus("Approved"));

        JButton rejectBtn = makeRoundButton("✘ Reject", new Color(211, 47, 47), 110, 38);
        rejectBtn.addActionListener(e -> updateSelectedStatus("Rejected"));

        panel.add(approveBtn);
        panel.add(rejectBtn);
        return panel;
    }

    // ════════════════════════════════════════════════════════════════════════
    //  Dialog: File a new leave request
    // ════════════════════════════════════════════════════════════════════════
    private void openFileLeaveDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "File Leave Request", true);
        dialog.setSize(420, 360);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        // ── Form ─────────────────────────────────────────────────────────────
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(new EmptyBorder(20, 25, 10, 25));
        form.setBackground(Color.WHITE);
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(6, 6, 6, 6);
        gc.anchor = GridBagConstraints.WEST;
        gc.fill   = GridBagConstraints.HORIZONTAL;

        JTextField empIdField  = new JTextField(15);
        JTextField dateField   = new JTextField("YYYY-MM-DD", 15);
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"Sick", "Vacation", "Emergency"});
        JSpinner daysSpinner   = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
        JTextField reasonField = new JTextField(15);

        Object[][] rows = {
            {"Employee ID *",  empIdField},
            {"Start Date *",   dateField},
            {"Leave Type *",   typeBox},
            {"Number of Days *", daysSpinner},
            {"Reason",         reasonField}
        };

        for (int i = 0; i < rows.length; i++) {
            gc.gridx = 0; gc.gridy = i; gc.weightx = 0;
            form.add(new JLabel((String) rows[i][0]), gc);
            gc.gridx = 1; gc.weightx = 1;
            form.add((Component) rows[i][1], gc);
        }

        // ── Buttons ──────────────────────────────────────────────────────────
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setBackground(Color.WHITE);
        JButton submitBtn = makeRoundButton("Submit", new Color(33, 150, 243), 90, 34);
        JButton cancelBtn = makeRoundButton("Cancel", new Color(150, 150, 150), 80, 34);
        cancelBtn.addActionListener(e -> dialog.dispose());

        submitBtn.addActionListener(e -> {
            String empId  = empIdField.getText().trim();
            String dateStr = dateField.getText().trim();
            String type   = (String) typeBox.getSelectedItem();
            int days      = (int) daysSpinner.getValue();
            String reason = reasonField.getText().trim();

            // Validate
            if (empId.isEmpty() || dateStr.isEmpty() || dateStr.equals("YYYY-MM-DD")) {
                JOptionPane.showMessageDialog(dialog, "Employee ID and Date are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            LocalDate date;
            try {
                date = LocalDate.parse(dateStr);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(dialog, "Date must be in YYYY-MM-DD format.", "Invalid Date", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Build leave & persist
            String leaveID = "LV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            Leave leave = new Leave(leaveID, empId, date, type, days, reason);
            String[] row = leave.toRow();

            boolean saved = fileHandler.appendLeave(row);
            if (saved) {
                allLeaveData.add(row);
                refreshTable(allLeaveData);
                refreshSummaryCards();
                JOptionPane.showMessageDialog(dialog, "Leave request filed successfully!\nLeave ID: " + leaveID, "Success", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to save leave request. Check data/leave.txt.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnPanel.add(submitBtn);
        btnPanel.add(cancelBtn);

        dialog.add(form, BorderLayout.CENTER);
        dialog.add(btnPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // ════════════════════════════════════════════════════════════════════════
    //  Approve / Reject selected row
    // ════════════════════════════════════════════════════════════════════════
    private void updateSelectedStatus(String newStatus) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a leave request first.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String currentStatus = (String) model.getValueAt(selectedRow, COL_STATUS);
        if (!"Pending".equals(currentStatus)) {
            JOptionPane.showMessageDialog(this, "Only 'Pending' requests can be updated.", "Already Processed", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String leaveId = (String) model.getValueAt(selectedRow, COL_LEAVE_ID);
        boolean updated = fileHandler.updateLeaveStatus(leaveId, newStatus);

        if (updated) {
            model.setValueAt(newStatus, selectedRow, COL_STATUS);
            // Update local data list too
            for (String[] row : allLeaveData) {
                if (row[COL_LEAVE_ID].equals(leaveId)) {
                    row[COL_STATUS] = newStatus;
                    break;
                }
            }
            refreshSummaryCards();
            JOptionPane.showMessageDialog(this, "Leave request " + newStatus.toLowerCase() + ".", "Updated", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update status.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ════════════════════════════════════════════════════════════════════════
    //  Search
    // ════════════════════════════════════════════════════════════════════════
    private void search() {
        String input = searchField.getText().trim().toLowerCase();
        if (input.isEmpty()) {
            refreshTable(allLeaveData);
            return;
        }
        List<String[]> filtered = new ArrayList<>();
        for (String[] row : allLeaveData) {
            if (row[COL_EMP_ID].toLowerCase().contains(input)) {
                filtered.add(row);
            }
        }
        if (filtered.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No leave records found for: " + input, "No Results", JOptionPane.INFORMATION_MESSAGE);
        }
        refreshTable(filtered);
    }

    // ════════════════════════════════════════════════════════════════════════
    //  Helpers
    // ════════════════════════════════════════════════════════════════════════
    private void refreshTable(List<String[]> data) {
        model.setRowCount(0);
        for (String[] row : data) {
            model.addRow(row);
        }
    }

    private int countByStatus(String status) {
        if (status == null) return allLeaveData.size();
        int count = 0;
        for (String[] row : allLeaveData) {
            if (row[COL_STATUS].equalsIgnoreCase(status)) count++;
        }
        return count;
    }

    // Summary card labels are kept as fields so we can refresh them
    private JLabel totalLabel, pendingLabel, approvedLabel, rejectedLabel;

    private JPanel makeSummaryCard(String title, int count) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
            new EmptyBorder(6, 14, 6, 14)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel countLabel = new JLabel(String.valueOf(count));
        countLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        countLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Cache for refresh
        if (title.contains("Total"))    totalLabel    = countLabel;
        if (title.contains("Pending"))  pendingLabel  = countLabel;
        if (title.contains("Approved")) approvedLabel = countLabel;
        if (title.contains("Rejected")) rejectedLabel = countLabel;

        card.add(titleLabel);
        card.add(countLabel);
        return card;
    }

    private void refreshSummaryCards() {
        if (totalLabel    != null) totalLabel.setText(String.valueOf(countByStatus(null)));
        if (pendingLabel  != null) pendingLabel.setText(String.valueOf(countByStatus("Pending")));
        if (approvedLabel != null) approvedLabel.setText(String.valueOf(countByStatus("Approved")));
        if (rejectedLabel != null) rejectedLabel.setText(String.valueOf(countByStatus("Rejected")));
    }

    // Rounded colored button (matches AttendancePanel style)
    private JButton makeRoundButton(String text, Color bgColor, int w, int h) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setOpaque(false);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setPreferredSize(new Dimension(w, h));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bgColor);
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);
                super.paint(g2, c);
                g2.dispose();
            }
        });
        return btn;
    }

    // ── Gradient background ──────────────────────────────────────────────────
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(0, 0, gradientStart, 0, getHeight(), gradientEnd);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    // ════════════════════════════════════════════════════════════════════════
    //  Cell renderer — color-coded status + striped rows
    // ════════════════════════════════════════════════════════════════════════
    private class LeaveTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (!isSelected) {
                String status = (String) model.getValueAt(row, COL_STATUS);
                if ("Approved".equals(status)) {
                    c.setBackground(new Color(232, 245, 233)); // light green
                } else if ("Rejected".equals(status)) {
                    c.setBackground(new Color(255, 235, 238)); // light red
                } else {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(230, 240, 255));
                }
            }
            setHorizontalAlignment(SwingConstants.CENTER);
            c.setFont(new Font("SansSerif", Font.PLAIN, 13));
            return c;
        }
    }

    // ════════════════════════════════════════════════════════════════════════
    //  Header renderer (matches AttendancePanel)
    // ════════════════════════════════════════════════════════════════════════
    private class LeaveTableHeaderRenderer extends DefaultTableCellRenderer {
        public LeaveTableHeaderRenderer() {
            setOpaque(true);
            setBackground(headerBlue);
            setForeground(Color.WHITE);
            setFont(new Font("SansSerif", Font.BOLD, 14));
            setHorizontalAlignment(SwingConstants.CENTER);
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            return this;
        }
    }

    // ════════════════════════════════════════════════════════════════════════
    //  Minimal scrollbar UI (matches AttendancePanel)
    // ════════════════════════════════════════════════════════════════════════
    private static class ModernScrollBarUI extends BasicScrollBarUI {
        @Override protected void configureScrollBarColors() {
            thumbColor = new Color(180, 180, 180);
            trackColor = new Color(0, 0, 0, 0);
        }
        @Override protected JButton createDecreaseButton(int o) { return invisible(); }
        @Override protected JButton createIncreaseButton(int o) { return invisible(); }
        private JButton invisible() {
            JButton b = new JButton();
            b.setPreferredSize(new Dimension(0, 0));
            return b;
        }
    }
}
