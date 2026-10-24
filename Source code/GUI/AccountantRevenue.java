package GUI;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import REVENUE.RevenueService;
import REVENUE.RevenueService.RevenueRecord;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

public class AccountantRevenue extends JPanel {

    private final AppFrame appFrame;

    // Colors — same project style
    private static final Color ORANGE = new Color(245, 140, 30);
    private static final Color DARK = new Color(15, 15, 15);
    private static final Color LIGHT = new Color(246, 246, 246);
    private static final Color CARD = Color.WHITE;
    private static final Color TEXT = new Color(45, 45, 45);
    private static final Color MUTED = new Color(120, 120, 120);

  // Style ComboBox
    private void styleComboBox(JComboBox<?> comboBox) {
    comboBox.setUI(new ModernComboUI(ORANGE));
    comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    comboBox.setForeground(TEXT);
    comboBox.setBackground(CARD);
    comboBox.setFocusable(false);
    comboBox.setBorder(
            new RoundedFieldBorder(
                    ORANGE,
                    new Color(220, 220, 220),
                    2,
                    4
            )
    );
    comboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
    comboBox.setPreferredSize(
            new Dimension(comboBox.getPreferredSize().width, 40)
    );

    // list items: roomy rows, light orange highlight
    comboBox.setRenderer(
            new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(
                        JList<?> list,
                        Object value,
                        int index,
                        boolean isSelected,
                        boolean cellHasFocus
                ) {
                    super.getListCellRendererComponent(
                            list, value, index, isSelected, cellHasFocus
                    );

                    setBorder(new EmptyBorder(8, 12, 8, 12));
                    setFont(new Font("Segoe UI", Font.PLAIN, 13));

                    if (index == -1) {
                        setBackground(Color.WHITE);
                        setForeground(TEXT);
                    } else if (isSelected) {
                        setBackground(new Color(255, 240, 224));
                        setForeground(Color.BLACK);
                    } else {
                        setBackground(Color.WHITE);
                        setForeground(new Color(60, 60, 60));
                    }

                    return this;
                }
            }
    );

    // the box is not focusable, so the orange border shows on hover instead
    comboBox.addMouseListener(
            new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    comboBox.putClientProperty("orangeHover", Boolean.TRUE);
                    comboBox.repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    comboBox.putClientProperty("orangeHover", Boolean.FALSE);
                    comboBox.repaint();
                }
            }
    );
}

private void loadAccountantRevenue(
        DefaultTableModel tableModel,
        JComboBox<String> monthSelector,
        JComboBox<Integer> yearSelector
) {
    int restaurantId = appFrame.getRestaurantId();

    if (restaurantId <= 0) {
        return;
    }

    int month = monthSelector.getSelectedIndex() + 1;
    int year = (Integer) yearSelector.getSelectedItem();

    try {
        List<RevenueRecord> records =
                RevenueService.getAccountantRevenue(
                        month,
                        year,
                        restaurantId
                );

        tableModel.setRowCount(0);

        for (RevenueRecord record : records) {
            tableModel.addRow(new Object[]{
                    record.getDate(),
                    record.getCategory(),
                    String.format("$%,.2f", record.getAmount())
            });
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
                this,
                "Unable to load revenue records:\n" + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
    public AccountantRevenue(AppFrame appFrame) {

        this.appFrame = appFrame;

        setLayout(new BorderLayout());
        setBackground(LIGHT);

        // =========================================================
        // SIDEBAR
        // =========================================================

        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setBackground(DARK);
        sidebar.setPreferredSize(new Dimension(210, 0));

        // ---------- Sidebar Top ----------

        JPanel sidebarTop = new JPanel();
        sidebarTop.setLayout(new BoxLayout(sidebarTop, BoxLayout.Y_AXIS));
        sidebarTop.setBackground(DARK);
        sidebarTop.setBorder(new EmptyBorder(30, 16, 10, 16));

        JLabel brand = new JLabel(
                "<html>RESTAURANT<br>BUDGET PLANNER</html>"
        );

        brand.setFont(new Font("Segoe UI", Font.BOLD, 14));
        brand.setForeground(Color.WHITE);
        brand.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebarTop.add(brand);
        sidebarTop.add(Box.createVerticalStrut(14));

        JPanel orangeLine = new JPanel();
        orangeLine.setBackground(ORANGE);
        orangeLine.setMaximumSize(new Dimension(42, 4));
        orangeLine.setPreferredSize(new Dimension(42, 4));
        orangeLine.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebarTop.add(orangeLine);
        sidebarTop.add(Box.createVerticalStrut(30));

        // ---------- Navigation ----------

        JButton dashboardButton =
                createMenuButton("Dashboard", false);

        JButton revenueButton =
                createMenuButton("Record Revenue", true);

        JButton expenseButton =
                createMenuButton("Record Expense", false);

        expenseButton.addActionListener(e ->
        appFrame.showScreen("ACCOUNTANT_EXPENSE")
);

        JButton transactionsButton =
                createMenuButton("Transactions", false);

        sidebarTop.add(dashboardButton);
        sidebarTop.add(Box.createVerticalStrut(8));

        sidebarTop.add(revenueButton);
        sidebarTop.add(Box.createVerticalStrut(8));

        sidebarTop.add(expenseButton);
        sidebarTop.add(Box.createVerticalStrut(8));

        sidebarTop.add(transactionsButton);

        sidebar.add(sidebarTop, BorderLayout.NORTH);

        // ---------- Logout ----------

        JPanel logoutPanel = new JPanel(new BorderLayout());
        logoutPanel.setBackground(DARK);
        logoutPanel.setBorder(new EmptyBorder(10, 16, 24, 16));

        JButton logoutButton =
                createMenuButton("Logout", false);

        logoutPanel.add(logoutButton, BorderLayout.NORTH);

        sidebar.add(logoutPanel, BorderLayout.SOUTH);

        // =========================================================
        // MAIN CONTENT
        // =========================================================

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(LIGHT);
        mainPanel.setBorder(new EmptyBorder(28, 30, 28, 30));

        // ---------- Header ----------

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(LIGHT);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(LIGHT);

        JLabel title = new JLabel("Record Revenue");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(TEXT);

        JLabel subtitle = new JLabel(
                "Record and manage the restaurant's daily revenue"
        );

        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(MUTED);

        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(6));
        titlePanel.add(subtitle);

        // profile chip: orange avatar + "Accountant"
        RoundedPanel avatar = new RoundedPanel(new GridBagLayout(), ORANGE, 30, false);
        avatar.setPreferredSize(new Dimension(30, 30));

        JLabel avatarLetter = new JLabel("A");
        avatarLetter.setFont(new Font("Segoe UI", Font.BOLD, 13));
        avatarLetter.setForeground(Color.WHITE);
        avatar.add(avatarLetter);

        JLabel roleLabel = new JLabel("Accountant");
        roleLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        roleLabel.setForeground(new Color(80, 80, 80));

        RoundedPanel profileChip = new RoundedPanel(new BorderLayout(10, 0), Color.WHITE, 40, true);
        profileChip.setBorder(new EmptyBorder(6, 8, 9, 16));
        profileChip.add(avatar, BorderLayout.WEST);
        profileChip.add(roleLabel, BorderLayout.CENTER);

        JPanel chipWrap = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        chipWrap.setBackground(LIGHT);
        chipWrap.add(profileChip);

        headerPanel.add(titlePanel, BorderLayout.WEST);
        headerPanel.add(chipWrap, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // =========================================================
        // CONTENT
        // =========================================================

        JPanel content = new JPanel(new GridLayout(1, 2, 20, 0));
        content.setBackground(LIGHT);
        content.setBorder(new EmptyBorder(26, 0, 0, 0));

// =========================================================
// RECORD REVENUE CARD
// =========================================================

JPanel formCard = createCard();

JPanel formContent = new JPanel();
formContent.setLayout(
        new BoxLayout(formContent, BoxLayout.Y_AXIS)
);
formContent.setOpaque(false);
formContent.setBorder(
        new EmptyBorder(25, 25, 28, 25)
);

// small orange accent bar
formContent.add(createAccentBar());
formContent.add(Box.createVerticalStrut(12));

JLabel formTitle = new JLabel("Record Revenue");
formTitle.setFont(
        new Font("Segoe UI", Font.BOLD, 18)
);
formTitle.setForeground(TEXT);
formTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

JLabel formSubtitle = new JLabel(
        "Enter the details of the revenue received."
);
formSubtitle.setFont(
        new Font("Segoe UI", Font.PLAIN, 12)
);
formSubtitle.setForeground(MUTED);
formSubtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

formContent.add(formTitle);
formContent.add(Box.createVerticalStrut(5));
formContent.add(formSubtitle);
formContent.add(Box.createVerticalStrut(25));

// Revenue Period Selector

JLabel dateLabel = createFieldLabel("Revenue Date");
dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

String[] months = {
        "January", "February", "March", "April",
        "May", "June", "July", "August",
        "September", "October", "November", "December"
};

JComboBox<String> monthSelector =
        new JComboBox<>(months);

Integer[] days = new Integer[31];
for (int i = 0; i < days.length; i++) {
    days[i] = i + 1;
}

JComboBox<Integer> daySelector =
        new JComboBox<>(days);

int currentYear = java.time.LocalDate.now().getYear();

Integer[] years = new Integer[21];
for (int i = 0; i < years.length; i++) {
    years[i] = currentYear - 10 + i;
}

JComboBox<Integer> yearSelector =
        new JComboBox<>(years);

styleComboBox(monthSelector);
styleComboBox(daySelector);
styleComboBox(yearSelector);

JPanel dateSelectors = new JPanel(
        new GridLayout(1, 3, 10, 0)
);
dateSelectors.setOpaque(false);
dateSelectors.setAlignmentX(Component.LEFT_ALIGNMENT);
dateSelectors.setMaximumSize(
        new Dimension(Integer.MAX_VALUE, 42)
);

dateSelectors.add(monthSelector);
dateSelectors.add(daySelector);
dateSelectors.add(yearSelector);

monthSelector.setSelectedIndex(
        java.time.LocalDate.now().getMonthValue() - 1
);
daySelector.setSelectedItem(
        java.time.LocalDate.now().getDayOfMonth()
);
yearSelector.setSelectedItem(currentYear);

formContent.add(dateLabel);
formContent.add(Box.createVerticalStrut(7));
formContent.add(dateSelectors);

formContent.add(Box.createVerticalStrut(17));

// Category

JLabel categoryLabel = createFieldLabel("Category");
categoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

JTextField categoryField = new JTextField();
styleTextField(categoryField);
categoryField.setMaximumSize(
        new Dimension(Integer.MAX_VALUE, 42)
);
categoryField.setAlignmentX(Component.LEFT_ALIGNMENT);

formContent.add(categoryLabel);
formContent.add(Box.createVerticalStrut(7));
formContent.add(categoryField);

formContent.add(Box.createVerticalStrut(17));

// Amount

JLabel amountLabel = createFieldLabel("Amount");
amountLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

JTextField amountField = new JTextField();
styleTextField(amountField);
amountField.setMaximumSize(
        new Dimension(Integer.MAX_VALUE, 42)
);
amountField.setAlignmentX(Component.LEFT_ALIGNMENT);

formContent.add(amountLabel);
formContent.add(Box.createVerticalStrut(7));
formContent.add(amountField);

formContent.add(Box.createVerticalStrut(17));

// Description

JLabel descriptionLabel =
        createFieldLabel("Description");

descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

JTextArea descriptionArea = new JTextArea(4, 20);
descriptionArea.setFont(
        new Font("Segoe UI", Font.PLAIN, 13)
);
descriptionArea.setForeground(TEXT);
descriptionArea.setCaretColor(ORANGE);
descriptionArea.setLineWrap(true);
descriptionArea.setWrapStyleWord(true);
descriptionArea.setBorder(
        new RoundedFieldBorder(
                ORANGE,
                new Color(220, 220, 220),
                9,
                12
        )
);

// repaint the rounded border when the box gains / loses focus
descriptionArea.addFocusListener(
        new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                descriptionArea.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                descriptionArea.repaint();
            }
        }
);

JScrollPane descriptionScroll =
        new JScrollPane(descriptionArea);

descriptionScroll.setBorder(null);
descriptionScroll.setOpaque(false);
descriptionScroll.getViewport().setBackground(CARD);
descriptionScroll.setMaximumSize(
        new Dimension(Integer.MAX_VALUE, 100)
);
descriptionScroll.setAlignmentX(Component.LEFT_ALIGNMENT);

formContent.add(descriptionLabel);
formContent.add(Box.createVerticalStrut(7));
formContent.add(descriptionScroll);

formContent.add(Box.createVerticalStrut(25));

// Save button

JButton saveButton =
        new JButton("SAVE REVENUE");

saveButton.setUI(
        new RoundedButtonUI(
                ORANGE,
                ORANGE.darker(),
                12
        )
);
saveButton.setFont(
        new Font("Segoe UI", Font.BOLD, 12)
);
saveButton.setForeground(Color.WHITE);
saveButton.setBackground(ORANGE);
saveButton.setFocusPainted(false);
saveButton.setBorderPainted(false);
saveButton.setContentAreaFilled(false);
saveButton.setOpaque(false);
saveButton.setRolloverEnabled(true);
saveButton.setCursor(
        new Cursor(Cursor.HAND_CURSOR)
);
saveButton.setAlignmentX(Component.LEFT_ALIGNMENT);
saveButton.setPreferredSize(
        new Dimension(150, 42)
);
saveButton.setMaximumSize(
        new Dimension(170, 42)
);

formContent.add(saveButton);

formCard.add(formContent, BorderLayout.CENTER);



// =========================================================
// REVENUE RECORDS CARD
// =========================================================

JPanel recordsCard = createCard();

JPanel recordsContent = new JPanel();
recordsContent.setLayout(
        new BoxLayout(recordsContent, BoxLayout.Y_AXIS)
);
recordsContent.setOpaque(false);
recordsContent.setBorder(
        new EmptyBorder(25, 25, 28, 25)
);

// Small orange accent bar
recordsContent.add(createAccentBar());
recordsContent.add(Box.createVerticalStrut(12));

// Revenue Records Title and Period Selectors

JLabel recordsTitle = new JLabel("Revenue Records");
recordsTitle.setFont(
        new Font("Segoe UI", Font.BOLD, 18)
);
recordsTitle.setForeground(TEXT);

String[] recordMonths = {
        "January", "February", "March", "April",
        "May", "June", "July", "August",
        "September", "October", "November", "December"
};

JComboBox<String> recordMonthSelector =
        new JComboBox<>(recordMonths);

int recordsCurrentYear =
        java.time.LocalDate.now().getYear();

Integer[] recordYears = new Integer[21];

for (int i = 0; i < recordYears.length; i++) {
    recordYears[i] = recordsCurrentYear - 10 + i;
}

JComboBox<Integer> recordYearSelector =
        new JComboBox<>(recordYears);

styleComboBox(recordMonthSelector);
styleComboBox(recordYearSelector);

recordMonthSelector.setSelectedIndex(
        java.time.LocalDate.now().getMonthValue() - 1
);
recordYearSelector.setSelectedItem(recordsCurrentYear);

// Keep title and selectors on the same row
// (title on the left, month + year selectors on the right)
JPanel recordsSelectors = new JPanel(
        new FlowLayout(FlowLayout.RIGHT, 8, 0)
);
recordsSelectors.setOpaque(false);

recordsSelectors.add(recordMonthSelector);
recordsSelectors.add(recordYearSelector);

JPanel recordsHeader = new JPanel(
        new BorderLayout()
);
recordsHeader.setOpaque(false);
recordsHeader.setAlignmentX(Component.LEFT_ALIGNMENT);
recordsHeader.setMaximumSize(
        new Dimension(Integer.MAX_VALUE, 44)
);

recordsHeader.add(recordsTitle, BorderLayout.WEST);
recordsHeader.add(recordsSelectors, BorderLayout.EAST);

JLabel recordsSubtitle =
        new JLabel("Recent revenue entries");

recordsSubtitle.setFont(
        new Font("Segoe UI", Font.PLAIN, 12)
);
recordsSubtitle.setForeground(MUTED);
recordsSubtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

recordsContent.add(recordsHeader);
recordsContent.add(Box.createVerticalStrut(5));
recordsContent.add(recordsSubtitle);
recordsContent.add(Box.createVerticalStrut(20));

// Table
String[] columns = {"Date", "Category", "Amount"};

DefaultTableModel tableModel = new DefaultTableModel(
        columns, 0
) {
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
};

JTable revenueTable = new JTable(tableModel);

revenueTable.setFont(
        new Font("Segoe UI", Font.PLAIN, 13)
);
revenueTable.setRowHeight(40);
revenueTable.setGridColor(
        new Color(240, 240, 240)
);
revenueTable.setShowVerticalLines(false);
revenueTable.setShowHorizontalLines(true);
revenueTable.setIntercellSpacing(new Dimension(0, 1));
revenueTable.setBackground(Color.WHITE);
revenueTable.setSelectionBackground(
        new Color(255, 240, 224)
);
revenueTable.setSelectionForeground(Color.BLACK);
revenueTable.setFillsViewportHeight(true);

// Body cells (amount column is right-aligned, bold green)
revenueTable.setDefaultRenderer(
        Object.class,
        new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column
            ) {
                super.getTableCellRendererComponent(
                        table, value, isSelected, false, row, column
                );

                setBorder(new EmptyBorder(0, 14, 0, 14));

                if (isSelected) {
                    setBackground(new Color(255, 240, 224));
                } else {
                    setBackground(
                            row % 2 == 0
                                    ? Color.WHITE
                                    : new Color(251, 251, 251)
                    );
                }

                if (column == 2) {
                    setHorizontalAlignment(SwingConstants.RIGHT);
                    setFont(new Font("Segoe UI", Font.BOLD, 13));
                    setForeground(new Color(30, 150, 30));
                } else {
                    setHorizontalAlignment(SwingConstants.LEFT);
                    setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    setForeground(
                            column == 0
                                    ? new Color(110, 110, 110)
                                    : TEXT
                    );
                }

                return this;
            }
        }
);

        // header cells (dark with an orange underline)
        JTableHeader tableHeader = revenueTable.getTableHeader();

        tableHeader.setReorderingAllowed(false);
        tableHeader.setPreferredSize(new Dimension(0, 40));

        tableHeader.setDefaultRenderer(
                new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column
                    ) {
                        super.getTableCellRendererComponent(
                                table, value, false, false, row, column
                        );

                        setOpaque(true);
                        setBackground(DARK);
                        setForeground(Color.WHITE);
                        setFont(new Font("Segoe UI", Font.BOLD, 12));

                        setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createMatteBorder(
                                                0, 0, 3, 0, ORANGE
                                        ),
                                        new EmptyBorder(0, 14, 0, 14)
                                )
                        );

                        setHorizontalAlignment(
                                column == 2
                                        ? SwingConstants.RIGHT
                                        : SwingConstants.LEFT
                        );

                        return this;
                    }
                }
        );

        JScrollPane tableScroll =
                new JScrollPane(revenueTable);

        tableScroll.setBorder(
                BorderFactory.createLineBorder(
                        new Color(235, 235, 235)
                )
        );
        tableScroll.getViewport().setBackground(Color.WHITE);

        recordsContent.add(tableScroll);

        recordsCard.add(recordsContent, BorderLayout.CENTER);

        // Add both cards

        content.add(formCard);
        content.add(recordsCard);

        mainPanel.add(content, BorderLayout.CENTER);

        // =========================================================
        // ADD EVERYTHING
        // =========================================================

        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        // =========================================================
        // BUTTON ACTIONS
        // =========================================================

        dashboardButton.addActionListener(e ->
                appFrame.showScreen("ACCOUNTANT_DASHBOARD")
        );

        expenseButton.addActionListener(e ->
                appFrame.showScreen("ACCOUNTANT_EXPENSE")
        );

        
/*Connects to the backend and Oracle*/
    saveButton.addActionListener(e -> {
    try {
        // Get the selected revenue date
        int month = monthSelector.getSelectedIndex() + 1;
        int day = (Integer) daySelector.getSelectedItem();
        int year = (Integer) yearSelector.getSelectedItem();

        LocalDate revenueDate = LocalDate.of(year, month, day);

        // Get and validate the category
        String category = categoryField.getText().trim();

        if (category.isEmpty()) {
            throw new IllegalArgumentException(
                    "Please enter a revenue category."
            );
        }

        // Get and validate the amount
        String amountText = amountField.getText()
                .trim()
                .replace(",", "");

        if (amountText.isEmpty()) {
            throw new IllegalArgumentException(
                    "Please enter the revenue amount."
            );
        }

        double amount;

        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(
                    "Please enter a valid amount."
            );
        }

        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero."
            );
        }

        // Get the description
        String description = descriptionArea.getText().trim();

        // Get the authenticated accountant and restaurant IDs
        int accountantId = appFrame.getAccountantId();
        int restaurantId = appFrame.getRestaurantId();

        if (accountantId <= 0 || restaurantId <= 0) {
            throw new IllegalStateException(
                    "Accountant or restaurant session is missing. Please log in again."
            );
        }

        // Save revenue to Oracle
        RevenueService.saveRevenue(
                revenueDate,
                category,
                amount,
                description,
                accountantId,
                restaurantId
        );

        JOptionPane.showMessageDialog(
                this,
                "Revenue saved successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Clear the form
        categoryField.setText("");
        amountField.setText("");
        descriptionArea.setText("");

        // Refresh the records table
        loadAccountantRevenue(
                tableModel,
                recordMonthSelector,
                recordYearSelector
        );

    } catch (DateTimeException ex) {
        JOptionPane.showMessageDialog(
                this,
                "Please select a valid revenue date.",
                "Invalid Date",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (IllegalArgumentException | IllegalStateException ex) {
        JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Validation Error",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
                this,
                "Unable to save revenue:\n" + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
});


recordMonthSelector.addActionListener(e ->
        loadAccountantRevenue(
                tableModel,
                recordMonthSelector,
                recordYearSelector
        )
);

recordYearSelector.addActionListener(e ->
        loadAccountantRevenue(
                tableModel,
                recordMonthSelector,
                recordYearSelector
        )
);

// Load existing records when the screen opens
loadAccountantRevenue(
        tableModel,
        recordMonthSelector,
        recordYearSelector
);

        transactionsButton.addActionListener(e ->
                appFrame.showScreen("ACCOUNTANT_TRANSACTIONS")
        );

        logoutButton.addActionListener(e ->
                appFrame.showScreen("LOGIN")
        );
    }


    // =============================================================
    // CARD
    // =============================================================

    private JPanel createCard() {

        RoundedPanel card =
                new RoundedPanel(new BorderLayout(), CARD, 18, true);

        return card;
    }

    // =============================================================
    // ACCENT BAR (small orange bar at the top of a card)
    // =============================================================

    private JPanel createAccentBar() {

        RoundedPanel bar =
                new RoundedPanel(new BorderLayout(), ORANGE, 4, false);

        bar.setPreferredSize(new Dimension(28, 4));
        bar.setMaximumSize(new Dimension(28, 4));
        bar.setAlignmentX(Component.LEFT_ALIGNMENT);

        return bar;
    }

    // =============================================================
    // FIELD LABEL
    // =============================================================

    private JLabel createFieldLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(
                new Font("Segoe UI", Font.BOLD, 12)
        );

        label.setForeground(TEXT);

        return label;
    }

    // =============================================================
    // TEXT FIELD
    // =============================================================

    private void styleTextField(JTextField field) {

        field.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        field.setForeground(TEXT);

        field.setBackground(Color.WHITE);

        field.setCaretColor(ORANGE);

        field.setBorder(
                new RoundedFieldBorder(
                        ORANGE,
                        new Color(220, 220, 220),
                        9,
                        12
                )
        );

        // repaint the rounded border when the field gains / loses focus
        field.addFocusListener(
                new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        field.repaint();
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        field.repaint();
                    }
                }
        );
    }

    // =============================================================
    // MENU BUTTON
    // =============================================================

    private JButton createMenuButton(
            String text,
            boolean active
    ) {

        JButton button = new JButton(text) {

            private boolean hover = false;

            {
                addMouseListener(
                        new MouseAdapter() {
                            @Override
                            public void mouseEntered(MouseEvent e) {
                                hover = true;

                                if (!active) {
                                    setForeground(Color.WHITE);
                                }

                                repaint();
                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
                                hover = false;

                                if (!active) {
                                    setForeground(
                                            new Color(190, 190, 190)
                                    );
                                }

                                repaint();
                            }
                        }
                );
            }

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                Color fill;

                if (active) {
                    fill = ORANGE;
                } else if (hover) {
                    fill = new Color(38, 38, 38);
                } else {
                    fill = DARK;
                }

                g2.setColor(fill);

                g2.fillRoundRect(
                        0, 0, getWidth(), getHeight(), 14, 14
                );

                g2.dispose();

                super.paintComponent(g);
            }
        };

        button.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        button.setForeground(
                active ? Color.WHITE : new Color(190, 190, 190)
        );

        button.setBackground(
                active ? ORANGE : DARK
        );

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setBorder(
                new EmptyBorder(11, 16, 11, 16)
        );

        button.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 42)
        );

        button.setAlignmentX(Component.LEFT_ALIGNMENT);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        return button;
    }

    // =============================================================
    // ROUNDED PANEL (cards with soft shadow)
    // =============================================================

    private static class RoundedPanel extends JPanel {

        private final int radius;
        private final boolean shadow;

        RoundedPanel(
                LayoutManager layout,
                Color background,
                int radius,
                boolean shadow
        ) {
            super(layout);

            this.radius = radius;
            this.shadow = shadow;

            setBackground(background);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            int w = getWidth();
            int h = getHeight();

            if (shadow) {
                for (int i = 1; i <= 3; i++) {
                    g2.setColor(new Color(0, 0, 0, 7));
                    g2.fillRoundRect(0, i, w, h - 3, radius, radius);
                }

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, w, h - 3, radius, radius);

                g2.setColor(new Color(0, 0, 0, 14));
                g2.drawRoundRect(0, 0, w - 1, h - 4, radius, radius);
            } else {
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, w, h, radius, radius);
            }

            g2.dispose();
        }
    }

    // =============================================================
    // ROUNDED FIELD BORDER (turns orange when focused)
    // =============================================================

    private static class RoundedFieldBorder extends AbstractBorder {

        private final Color focusColor;
        private final Color normalColor;
        private final int vertical;
        private final int horizontal;

        RoundedFieldBorder(
                Color focusColor,
                Color normalColor,
                int vertical,
                int horizontal
        ) {
            this.focusColor = focusColor;
            this.normalColor = normalColor;
            this.vertical = vertical;
            this.horizontal = horizontal;
        }

        @Override
        public void paintBorder(
                Component c,
                Graphics g,
                int x,
                int y,
                int width,
                int height
        ) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            // focused text fields, or dropdowns that are hovered / open
            boolean focused = c.hasFocus();

            if (c instanceof JComponent) {
                focused = focused || Boolean.TRUE.equals(
                        ((JComponent) c).getClientProperty("orangeHover")
                );
            }

            if (c instanceof JComboBox) {
                focused = focused || ((JComboBox<?>) c).isPopupVisible();
            }

            g2.setColor(focused ? focusColor : normalColor);

            g2.setStroke(new BasicStroke(focused ? 2f : 1f));

            g2.drawRoundRect(
                    x + 1, y + 1, width - 3, height - 3, 14, 14
            );

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(vertical, horizontal, vertical, horizontal);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(vertical, horizontal, vertical, horizontal);

            return insets;
        }
    }

    // =============================================================
    // MODERN COMBO BOX UI (flat, orange chevron)
    // =============================================================

    private static class ModernComboUI extends BasicComboBoxUI {

        private final Color accent;

        ModernComboUI(Color accent) {
            this.accent = accent;
        }

        @Override
        protected JButton createArrowButton() {

            JButton button = new JButton() {
                @Override
                protected void paintComponent(Graphics g) {

                    Graphics2D g2 = (Graphics2D) g.create();

                    g2.setRenderingHint(
                            RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON
                    );

                    g2.setColor(accent);

                    g2.setStroke(
                            new BasicStroke(
                                    2f,
                                    BasicStroke.CAP_ROUND,
                                    BasicStroke.JOIN_ROUND
                            )
                    );

                    int cx = getWidth() / 2;
                    int cy = getHeight() / 2;

                    g2.drawLine(cx - 4, cy - 2, cx, cy + 2);
                    g2.drawLine(cx, cy + 2, cx + 4, cy - 2);

                    g2.dispose();
                }
            };

            button.setBorder(BorderFactory.createEmptyBorder());
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setOpaque(false);

            return button;
        }

        @Override
        public void paintCurrentValueBackground(
                Graphics g,
                Rectangle bounds,
                boolean hasFocus
        ) {
            g.setColor(Color.WHITE);

            g.fillRect(
                    bounds.x,
                    bounds.y,
                    bounds.width,
                    bounds.height
            );
        }
    }

    // =============================================================
    // ROUNDED BUTTON UI
    // =============================================================

    private static class RoundedButtonUI extends BasicButtonUI {

        private final Color base;
        private final Color hover;
        private final int radius;

        RoundedButtonUI(
                Color base,
                Color hover,
                int radius
        ) {
            this.base = base;
            this.hover = hover;
            this.radius = radius;
        }

        @Override
        public void paint(Graphics g, JComponent c) {

            AbstractButton button = (AbstractButton) c;

            ButtonModel model = button.getModel();

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            Color fill = base;

            if (model.isPressed()) {
                fill = hover.darker();
            } else if (model.isRollover()) {
                fill = hover;
            }

            g2.setColor(fill);

            g2.fillRoundRect(
                    0, 0, c.getWidth(), c.getHeight(), radius, radius
            );

            g2.dispose();

            super.paint(g, c);
        }
    }
}