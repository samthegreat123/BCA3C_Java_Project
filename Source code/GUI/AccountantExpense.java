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

import EXPENSE.ExpenseService;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;

import javax.swing.table.DefaultTableModel;

public class AccountantExpense extends JPanel {

    private final AppFrame appFrame;
    private DefaultTableModel expenseTableModel;

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

    public AccountantExpense(AppFrame appFrame) {

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
        sidebarTop.setLayout(
                new BoxLayout(sidebarTop, BoxLayout.Y_AXIS)
        );
        sidebarTop.setBackground(DARK);
        sidebarTop.setBorder(
                new EmptyBorder(30, 16, 10, 16)
        );

        JLabel brand = new JLabel(
                "<html>RESTAURANT<br>BUDGET PLANNER</html>"
        );

        brand.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );
        brand.setForeground(Color.WHITE);
        brand.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebarTop.add(brand);
        sidebarTop.add(Box.createVerticalStrut(14));

        JPanel orangeLine = new JPanel();
        orangeLine.setBackground(ORANGE);
        orangeLine.setMaximumSize(
                new Dimension(42, 4)
        );
        orangeLine.setPreferredSize(
                new Dimension(42, 4)
        );
        orangeLine.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        sidebarTop.add(orangeLine);
        sidebarTop.add(Box.createVerticalStrut(30));

        // ---------- Navigation ----------

        JButton dashboardButton =
                createMenuButton("Dashboard", false);

        JButton revenueButton =
                createMenuButton("Record Revenue", false);

        JButton expenseButton =
                createMenuButton("Record Expense", true);

        JButton transactionsButton =
                createMenuButton("Transactions", false);

        sidebarTop.add(dashboardButton);
        sidebarTop.add(Box.createVerticalStrut(8));

        sidebarTop.add(revenueButton);
        sidebarTop.add(Box.createVerticalStrut(8));

        sidebarTop.add(expenseButton);
        sidebarTop.add(Box.createVerticalStrut(8));

        sidebarTop.add(transactionsButton);

        sidebar.add(
                sidebarTop,
                BorderLayout.NORTH
        );

        // ---------- Logout ----------

        JPanel logoutPanel =
                new JPanel(new BorderLayout());

        logoutPanel.setBackground(DARK);
        logoutPanel.setBorder(
                new EmptyBorder(10, 16, 24, 16)
        );

        JButton logoutButton =
                createMenuButton("Logout", false);

        logoutPanel.add(
                logoutButton,
                BorderLayout.NORTH
        );

        sidebar.add(
                logoutPanel,
                BorderLayout.SOUTH
        );

        // =========================================================
        // MAIN CONTENT
        // =========================================================

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(LIGHT);
        mainPanel.setBorder(
                new EmptyBorder(28, 30, 28, 30)
        );

        // ---------- Header ----------

        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setBackground(LIGHT);

        JPanel titlePanel =
                new JPanel();

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        titlePanel.setBackground(LIGHT);

        JLabel title =
                new JLabel("Record Expense");

        title.setFont(
                new Font("Segoe UI", Font.BOLD, 28)
        );

        title.setForeground(TEXT);

        JLabel subtitle =
                new JLabel(
                        "Record and manage the restaurant's daily expenses"
                );

        subtitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        subtitle.setForeground(MUTED);

        titlePanel.add(title);
        titlePanel.add(
                Box.createVerticalStrut(6)
        );
        titlePanel.add(subtitle);

        // profile chip: orange avatar + "Accountant"
        RoundedPanel avatar =
                new RoundedPanel(new GridBagLayout(), ORANGE, 30, false);

        avatar.setPreferredSize(new Dimension(30, 30));

        JLabel avatarLetter =
                new JLabel("A");

        avatarLetter.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        avatarLetter.setForeground(Color.WHITE);

        avatar.add(avatarLetter);

        JLabel roleLabel =
                new JLabel("Accountant");

        roleLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        roleLabel.setForeground(
                new Color(80, 80, 80)
        );

        RoundedPanel profileChip =
                new RoundedPanel(new BorderLayout(10, 0), Color.WHITE, 40, true);

        profileChip.setBorder(
                new EmptyBorder(6, 8, 9, 16)
        );

        profileChip.add(avatar, BorderLayout.WEST);
        profileChip.add(roleLabel, BorderLayout.CENTER);

        JPanel chipWrap =
                new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        chipWrap.setBackground(LIGHT);
        chipWrap.add(profileChip);

        headerPanel.add(
                titlePanel,
                BorderLayout.WEST
        );

        headerPanel.add(
                chipWrap,
                BorderLayout.EAST
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =========================================================
        // CONTENT
        // =========================================================

        JPanel content =
                new JPanel(
                        new GridLayout(1, 2, 20, 0)
                );

        content.setBackground(LIGHT);

        content.setBorder(
                new EmptyBorder(26, 0, 0, 0)
        );

        // =========================================================
        // RECORD EXPENSE CARD
        // =========================================================

        JPanel formCard = createCard();

        JPanel formContent =
                new JPanel();

        formContent.setLayout(
                new BoxLayout(
                        formContent,
                        BoxLayout.Y_AXIS
                )
        );

        formContent.setOpaque(false);

        formContent.setBorder(
                new EmptyBorder(25, 25, 28, 25)
        );

        // small orange accent bar
        formContent.add(createAccentBar());
        formContent.add(
                Box.createVerticalStrut(12)
        );

        JLabel formTitle =
                new JLabel("Record Expense");

        formTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        formTitle.setForeground(TEXT);

        formTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel formSubtitle =
                new JLabel(
                        "Enter the details of the expense."
                );

        formSubtitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );

        formSubtitle.setForeground(MUTED);

        formSubtitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        formContent.add(formTitle);
        formContent.add(
                Box.createVerticalStrut(5)
        );
        formContent.add(formSubtitle);
        formContent.add(
                Box.createVerticalStrut(25)
        );

       // ---------- Expense Date ----------

JLabel dateLabel = createFieldLabel("Expense Date");
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


// Select today's date by default
monthSelector.setSelectedIndex(
        java.time.LocalDate.now().getMonthValue() - 1
);

daySelector.setSelectedItem(
        java.time.LocalDate.now().getDayOfMonth()
);

yearSelector.setSelectedItem(currentYear);

JPanel dateSelectors = new JPanel(
        new GridLayout(1, 3, 10, 0)
);

dateSelectors.setBackground(CARD);
dateSelectors.setAlignmentX(Component.LEFT_ALIGNMENT);
dateSelectors.setMaximumSize(
        new Dimension(Integer.MAX_VALUE, 42)
);

dateSelectors.add(monthSelector);
dateSelectors.add(daySelector);
dateSelectors.add(yearSelector);

formContent.add(dateLabel);
formContent.add(Box.createVerticalStrut(7));
formContent.add(dateSelectors);
formContent.add(Box.createVerticalStrut(17));

        // ---------- Category ----------

        JLabel categoryLabel =
                createFieldLabel("Category");

        categoryLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JTextField categoryField =
                new JTextField();

        styleTextField(categoryField);

        categoryField.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );

        categoryField.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        formContent.add(categoryLabel);
        formContent.add(
                Box.createVerticalStrut(7)
        );
        formContent.add(categoryField);

        formContent.add(
                Box.createVerticalStrut(17)
        );

        // ---------- Amount ----------

        JLabel amountLabel =
                createFieldLabel("Amount");

        amountLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JTextField amountField =
                new JTextField();

        styleTextField(amountField);

        amountField.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );

        amountField.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        formContent.add(amountLabel);
        formContent.add(
                Box.createVerticalStrut(7)
        );
        formContent.add(amountField);

        formContent.add(
                Box.createVerticalStrut(17)
        );

        // ---------- Description ----------

        JLabel descriptionLabel =
                createFieldLabel("Description");

        descriptionLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JTextArea descriptionArea =
                new JTextArea(4, 20);

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
                new JScrollPane(
                        descriptionArea
                );

        descriptionScroll.setBorder(null);

        descriptionScroll.setOpaque(false);

        descriptionScroll.getViewport().setBackground(CARD);

        descriptionScroll.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        100
                )
        );

        descriptionScroll.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        formContent.add(descriptionLabel);
        formContent.add(
                Box.createVerticalStrut(7)
        );
        formContent.add(descriptionScroll);

        formContent.add(
                Box.createVerticalStrut(25)
        );

        // ---------- Save Button ----------

        JButton saveButton =
                new JButton("SAVE EXPENSE");

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
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        saveButton.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        saveButton.setPreferredSize(
                new Dimension(150, 42)
        );

        saveButton.setMaximumSize(
                new Dimension(170, 42)
        );

        formContent.add(saveButton);
        
saveButton.addActionListener(e -> {
    try {
        // Read the selected date
        int month = monthSelector.getSelectedIndex() + 1;
        int day = (Integer) daySelector.getSelectedItem();
        int year = (Integer) yearSelector.getSelectedItem();

        LocalDate expenseDate =
                LocalDate.of(year, month, day);

        // Read the form fields
        String category = categoryField.getText().trim();
        String amountText = amountField.getText()
                .trim()
                .replace(",", "");

        String description =
                descriptionArea.getText().trim();

        if (category.isEmpty()
                || amountText.isEmpty()
                || description.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please complete all expense fields.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        BigDecimal amount = new BigDecimal(amountText);

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Amount must be greater than zero.",
                    "Invalid Amount",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Use the authenticated accountant and restaurant
        int accountantId = appFrame.getAccountantId();
        int restaurantId = appFrame.getRestaurantId();

        // Save the expense to Oracle
        ExpenseService expenseService = new ExpenseService();

        expenseService.saveExpense(
                expenseDate,
                category,
                amount,
                description,
                accountantId,
                restaurantId
        );

        // Refresh the expense table
       loadExpenses(expenseTableModel);

        JOptionPane.showMessageDialog(
                this,
                "Expense saved successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

       

        // Clear the fields after successful saving
        categoryField.setText("");
        amountField.setText("");
        descriptionArea.setText("");

    } catch (DateTimeException ex) {
        JOptionPane.showMessageDialog(
                this,
                "Please select a valid date.",
                "Invalid Date",
                JOptionPane.WARNING_MESSAGE
        );

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(
                this,
                "Please enter a valid amount.",
                "Invalid Amount",
                JOptionPane.WARNING_MESSAGE
        );

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
                this,
                "Could not save expense: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
});


        formCard.add(
                formContent,
                BorderLayout.CENTER
        );

        // =========================================================
        // EXPENSE RECORDS CARD
        // =========================================================

        JPanel recordsCard =
                createCard();

        JPanel recordsContent =
                new JPanel();

        recordsContent.setLayout(
                new BoxLayout(
                        recordsContent,
                        BoxLayout.Y_AXIS
                )
        );

        recordsContent.setOpaque(false);

        recordsContent.setBorder(
                new EmptyBorder(
                        25, 25, 28, 25
                )
        );

        // small orange accent bar
        recordsContent.add(createAccentBar());
        recordsContent.add(
                Box.createVerticalStrut(12)
        );

        JLabel recordsTitle =
                new JLabel("Expense Records");

        recordsTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        recordsTitle.setForeground(TEXT);

        recordsTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel recordsSubtitle =
                new JLabel("Recent expense entries");

        recordsSubtitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );

        recordsSubtitle.setForeground(MUTED);

        recordsSubtitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        recordsContent.add(recordsTitle);

        recordsContent.add(
                Box.createVerticalStrut(5)
        );

        recordsContent.add(recordsSubtitle);

        recordsContent.add(
                Box.createVerticalStrut(20)
        );


// ---------- Expense Table Period Selector ----------

String[] recordMonths = {
        "January", "February", "March", "April",
        "May", "June", "July", "August",
        "September", "October", "November", "December"
};

JComboBox<String> recordMonthSelector =
        new JComboBox<>(recordMonths);

int recordCurrentYear =
        java.time.LocalDate.now().getYear();

Integer[] recordYears = new Integer[21];

for (int i = 0; i < recordYears.length; i++) {
    recordYears[i] = recordCurrentYear - 10 + i;
}

JComboBox<Integer> recordYearSelector =
        new JComboBox<>(recordYears);

recordMonthSelector.setSelectedIndex(
        java.time.LocalDate.now().getMonthValue() - 1
);

recordYearSelector.setSelectedItem(recordCurrentYear);

// Period selector styling
for (JComboBox<?> selector : new JComboBox<?>[]{
        recordMonthSelector,
        recordYearSelector
}) {
    styleComboBox(selector);

    selector.setPreferredSize(
            new Dimension(150, 40)
    );
}

JPanel periodSelectorPanel =
        new JPanel(
                new FlowLayout(FlowLayout.LEFT, 0, 0)
        );

periodSelectorPanel.setOpaque(false);
periodSelectorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
periodSelectorPanel.setMaximumSize(
        new Dimension(Integer.MAX_VALUE, 42)
);

periodSelectorPanel.add(recordMonthSelector);
periodSelectorPanel.add(Box.createHorizontalStrut(10));
periodSelectorPanel.add(recordYearSelector);

recordsContent.add(
        periodSelectorPanel,
        BorderLayout.NORTH
);

recordsContent.add(
        Box.createVerticalStrut(14)
);


// ---------- Expense Table ----------

String[] columns = {
        "Date",
        "Category",
        "Amount"
};

// Table model for loading expenses from Oracle
expenseTableModel = new DefaultTableModel(columns, 0) {
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
};

JTable expenseTable = new JTable(expenseTableModel);

expenseTable.setFont(
        new Font("Segoe UI", Font.PLAIN, 13)
);

expenseTable.setRowHeight(40);

expenseTable.setGridColor(
        new Color(240, 240, 240)
);

expenseTable.setShowVerticalLines(false);
expenseTable.setShowHorizontalLines(true);
expenseTable.setIntercellSpacing(new Dimension(0, 1));
expenseTable.setBackground(Color.WHITE);

expenseTable.setSelectionBackground(
        new Color(255, 240, 224)
);

expenseTable.setSelectionForeground(Color.BLACK);
expenseTable.setFillsViewportHeight(true);

// body cells (amount column is right-aligned, bold red)
expenseTable.setDefaultRenderer(
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
                    setForeground(new Color(200, 60, 60));
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
JTableHeader tableHeader = expenseTable.getTableHeader();

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
        new JScrollPane(expenseTable);

tableScroll.setBorder(
        BorderFactory.createLineBorder(
                new Color(235, 235, 235)
        )
);

tableScroll.getViewport().setBackground(Color.WHITE);

recordsContent.add(tableScroll);


recordsCard.add(
        recordsContent,
        BorderLayout.CENTER
);

        // ---------- Add Cards ----------

        content.add(formCard);
        content.add(recordsCard);

        mainPanel.add(
                content,
                BorderLayout.CENTER
        );

        // =========================================================
        // ADD SIDEBAR + MAIN CONTENT
        // =========================================================

        add(
                sidebar,
                BorderLayout.WEST
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );

        // =========================================================
        // BUTTON ACTIONS
        // =========================================================

        dashboardButton.addActionListener(e ->
                appFrame.showScreen(
                        "ACCOUNTANT_DASHBOARD"
                )
        );

        revenueButton.addActionListener(e ->
                appFrame.showScreen(
                        "ACCOUNTANT_REVENUE"
                )
        );

        transactionsButton.addActionListener(e ->
                appFrame.showScreen(
                        "ACCOUNTANT_TRANSACTIONS"
                )
        );

        logoutButton.addActionListener(e ->
                appFrame.showScreen("LOGIN")
        );
    }

    // Load Expenses
private void loadExpenses(DefaultTableModel tableModel) {
    tableModel.setRowCount(0);

    try {
        ExpenseService expenseService = new ExpenseService();
        int restaurantId = appFrame.getRestaurantId();

        for (Object[] expense : expenseService.getExpenses(restaurantId)) {
            tableModel.addRow(expense);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
            this,
            "Could not load expenses: " + ex.getMessage(),
            "Database Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}

public void refreshExpenses() {
    if (expenseTableModel != null) {
        loadExpenses(expenseTableModel);
    }
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

    private JLabel createFieldLabel(
            String text
    ) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        label.setForeground(TEXT);

        return label;
    }

    // =============================================================
    // TEXT FIELD
    // =============================================================

    private void styleTextField(
            JTextField field
    ) {

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
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

        JButton button =
                new JButton(text) {

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
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        button.setForeground(
                active
                        ? Color.WHITE
                        : new Color(190, 190, 190)
        );

        button.setBackground(
                active
                        ? ORANGE
                        : DARK
        );

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setBorder(
                new EmptyBorder(
                        11, 16, 11, 16
                )
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );

        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
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