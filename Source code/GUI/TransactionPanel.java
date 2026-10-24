package GUI;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;

import EXPENSE.ExpenseService;
import REVENUE.RevenueService;
import REVENUE.RevenueService.RevenueRecord;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class TransactionPanel extends JPanel {

    private final AppFrame appFrame;
    private JComboBox<String> monthBox;
    private JComboBox<Integer> yearBox;
    private JTable transactionTable;
    private DefaultTableModel transactionModel;

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

    public TransactionPanel(AppFrame appFrame) {

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
                new BoxLayout(
                        sidebarTop,
                        BoxLayout.Y_AXIS
                )
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

        brand.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        sidebarTop.add(brand);

        sidebarTop.add(
                Box.createVerticalStrut(14)
        );

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

        sidebarTop.add(
                Box.createVerticalStrut(30)
        );

        // ---------- Navigation ----------

        JButton dashboardButton =
                createMenuButton(
                        "Dashboard",
                        false
                );

        JButton revenueButton =
                createMenuButton(
                        "Record Revenue",
                        false
                );

        JButton expenseButton =
                createMenuButton(
                        "Record Expense",
                        false
                );

        JButton transactionsButton =
                createMenuButton(
                        "Transactions",
                        true
                );

        sidebarTop.add(dashboardButton);

        sidebarTop.add(
                Box.createVerticalStrut(8)
        );

        sidebarTop.add(revenueButton);

        sidebarTop.add(
                Box.createVerticalStrut(8)
        );

        sidebarTop.add(expenseButton);

        sidebarTop.add(
                Box.createVerticalStrut(8)
        );

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
                createMenuButton(
                        "Logout",
                        false
                );

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
                new JLabel("Transactions");

        title.setFont(
                new Font("Segoe UI", Font.BOLD, 28)
        );

        title.setForeground(TEXT);

        JLabel subtitle =
                new JLabel(
                        "View and manage the restaurant's transactions"
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
                new JPanel(new BorderLayout());

        content.setBackground(LIGHT);

        content.setBorder(
                new EmptyBorder(26, 0, 0, 0)
        );

        // =========================================================
        // TRANSACTIONS CARD
        // =========================================================

        JPanel transactionsCard =
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
                        25,
                        25,
                        28,
                        25
                )
        );

        // small orange accent bar
        recordsContent.add(createAccentBar());

        recordsContent.add(
                Box.createVerticalStrut(12)
        );

        JLabel recordsTitle =
                new JLabel("Transaction Records");

        recordsTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        recordsTitle.setForeground(TEXT);

        recordsTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel recordsSubtitle =
                new JLabel(
                        "Revenue and expense records for the selected month"
                );

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

        // =========================================================
        // MONTH SELECTION
        // =========================================================

        String[] months = {
                "January", "February", "March", "April",
                "May", "June", "July", "August",
                "September", "October", "November", "December"
        };

        monthBox = new JComboBox<>(months);


        int currentYear =
                java.time.LocalDate.now().getYear();

        Integer[] years = new Integer[21];

        for (int i = 0; i < years.length; i++) {
            years[i] = currentYear - 10 + i;
        }
          yearBox = new JComboBox<>(years);

        // Select the current month and year by default
        monthBox.setSelectedIndex(
                java.time.LocalDate.now().getMonthValue() - 1
        );

        yearBox.setSelectedItem(currentYear);

        // Period selector styling
        for (JComboBox<?> selector : new JComboBox<?>[]{
                monthBox,
                yearBox
        }) {
            styleComboBox(selector);

            selector.setPreferredSize(
                    new Dimension(150, 40)
            );
        }

        JPanel filterPanel =
                new JPanel(new FlowLayout(
                        FlowLayout.LEFT,
                        0,
                        0
                ));

        filterPanel.setOpaque(false);

        filterPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        filterPanel.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 42)
        );

        filterPanel.add(monthBox);

        filterPanel.add(
                Box.createHorizontalStrut(10)
        );

        filterPanel.add(yearBox);

        recordsContent.add(filterPanel);

        recordsContent.add(
                Box.createVerticalStrut(14)
        );

        // =========================================================
        // TRANSACTION TABLE
        // =========================================================

        String[] columns = {
                "Date",
                "Type",
                "Category",
                "Description",
                "Amount"
        };

        
      transactionModel = new DefaultTableModel(columns, 0) {

       @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
   };

transactionTable = new JTable(transactionModel);
        transactionTable.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        transactionTable.setRowHeight(40);

        transactionTable.setGridColor(
                new Color(240, 240, 240)
        );

        transactionTable.setShowVerticalLines(false);
        transactionTable.setShowHorizontalLines(true);
        transactionTable.setIntercellSpacing(new Dimension(0, 1));
        transactionTable.setBackground(Color.WHITE);

        transactionTable.setSelectionBackground(
                new Color(255, 240, 224)
        );

        transactionTable.setSelectionForeground(Color.BLACK);
        transactionTable.setFillsViewportHeight(true);

        // body cells (type and amount are bold green / red, amount right-aligned)
        transactionTable.setDefaultRenderer(
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

                        boolean revenue =
                                "Revenue".equals(
                                        table.getValueAt(row, 1)
                                );

                        if (column == 1 || column == 4) {
                            setFont(new Font("Segoe UI", Font.BOLD, 13));
                            setForeground(
                                    revenue
                                            ? new Color(30, 150, 30)
                                            : new Color(200, 60, 60)
                            );
                        } else {
                            setFont(new Font("Segoe UI", Font.PLAIN, 13));
                            setForeground(
                                    column == 0
                                            ? new Color(110, 110, 110)
                                            : TEXT
                            );
                        }

                        setHorizontalAlignment(
                                column == 4
                                        ? SwingConstants.RIGHT
                                        : SwingConstants.LEFT
                        );

                        return this;
                    }
                }
        );

        // header cells (dark with an orange underline)
        JTableHeader tableHeader = transactionTable.getTableHeader();

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
                                column == 4
                                        ? SwingConstants.RIGHT
                                        : SwingConstants.LEFT
                        );

                        return this;
                    }
                }
        );

        JScrollPane tableScroll =
                new JScrollPane(
                        transactionTable
                );

        tableScroll.setBorder(
                BorderFactory.createLineBorder(
                        new Color(235, 235, 235)
                )
        );

        tableScroll.getViewport().setBackground(Color.WHITE);

        recordsContent.add(tableScroll);

        transactionsCard.add(
                recordsContent,
                BorderLayout.CENTER
        );

        content.add(
                transactionsCard,
                BorderLayout.CENTER
        );

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

        expenseButton.addActionListener(e ->
                appFrame.showScreen(
                        "ACCOUNTANT_EXPENSE"
                )
        );

        transactionsButton.addActionListener(e ->
                appFrame.showScreen(
                        "ACCOUNTANT_TRANSACTIONS"
                )
        );

        logoutButton.addActionListener(e ->
                appFrame.showScreen(
                        "LOGIN"
                )
        );
    }

     // =============================================================
// LOAD TRANSACTIONS FROM ORACLE
// =============================================================

public void refreshTransactions() {

    int restaurantId = appFrame.getRestaurantId();

    if (restaurantId <= 0) {
        transactionModel.setRowCount(0);
        return;
    }

    int month = monthBox.getSelectedIndex() + 1;
    int year = (Integer) yearBox.getSelectedItem();

    transactionModel.setRowCount(0);

    try {
        List<Object[]> rows = new ArrayList<>();

        // Load revenue records for this restaurant and period.
        List<RevenueRecord> revenues =
                RevenueService.getAccountantRevenue(
                        month,
                        year,
                        restaurantId
                );

        DateTimeFormatter inputFormat =
                DateTimeFormatter.ofPattern("yyyy-MM-dd");

        DateTimeFormatter displayFormat =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (RevenueRecord revenue : revenues) {

            LocalDate date =
                    LocalDate.parse(
                            revenue.getDate(),
                            inputFormat
                    );

            rows.add(new Object[]{
                    date,
                    "Revenue",
                    revenue.getCategory(),
                    revenue.getDescription(),
                    "+$" + String.format(
                            Locale.US,
                            "%,.2f",
                            revenue.getAmount()
                    )
            });
        }

        // Load expense records for this restaurant and period.
        ExpenseService expenseService = new ExpenseService();

        List<Object[]> expenses =
                expenseService.getExpenses(
                        month,
                        year,
                        restaurantId
                );

        for (Object[] expense : expenses) {

            java.sql.Date sqlDate =
                    (java.sql.Date) expense[0];

            LocalDate date = sqlDate.toLocalDate();

            rows.add(new Object[]{
                    date,
                    "Expense",
                    expense[1],
                    expense[2],
                    "-$" + String.format(
                            Locale.US,
                            "%,.2f",
                            ((java.math.BigDecimal) expense[3]).doubleValue()
                    )
            });
        }

        // Show newest transactions first.
        rows.sort(
                Comparator.comparing(
                        (Object[] row) -> (LocalDate) row[0]
                ).reversed()
        );

        for (Object[] row : rows) {

            LocalDate date = (LocalDate) row[0];

            transactionModel.addRow(new Object[]{
                    date.format(displayFormat),
                    row[1],
                    row[2],
                    row[3],
                    row[4]
            });
        }

    } catch (SQLException | RuntimeException exception) {

        JOptionPane.showMessageDialog(
                this,
                "Unable to load transactions:\n"
                        + exception.getMessage(),
                "Transaction Error",
                JOptionPane.ERROR_MESSAGE
        );
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
                        11,
                        16,
                        11,
                        16
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
    // ROUNDED FIELD BORDER (turns orange when hovered / open)
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

            // dropdowns that are hovered / open
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
}