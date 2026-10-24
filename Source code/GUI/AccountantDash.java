package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import EXPENSE.ExpenseService;
import REVENUE.RevenueService;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;

public class AccountantDash extends JPanel
{
    private AppFrame appFrame;
    private JLabel totalRevenueLabel;
    private JLabel totalExpensesLabel;
    private JLabel netBalanceLabel;
    private JPanel transactionList;

    private final Color ORANGE =
            new Color(245, 140, 30);

    private final Color DARK =
            new Color(15, 15, 15);

    private final Color LIGHT =
            new Color(246, 246, 246);

    private final Color CARD =
            Color.WHITE;

    private final Color TEXT =
            new Color(45, 45, 45);

    private final Color MUTED =
            new Color(120, 120, 120);


    public AccountantDash(
            AppFrame appFrame
    )
    {
        this.appFrame = appFrame;

        setLayout(
                new BorderLayout()
        );

        setBackground(
                LIGHT
        );


        // ==================================================
        // SIDEBAR
        // ==================================================

        JPanel sidebar =
                new JPanel();

        sidebar.setLayout(
                new BorderLayout()
        );

        sidebar.setBackground(
                DARK
        );

        sidebar.setPreferredSize(
                new Dimension(
                        210,
                        0
                )
        );


        // ==================================================
        // SIDEBAR TOP
        // ==================================================

        JPanel sidebarTop =
                new JPanel();

        sidebarTop.setLayout(
                new BoxLayout(
                        sidebarTop,
                        BoxLayout.Y_AXIS
                )
        );

        sidebarTop.setBackground(
                DARK
        );

        sidebarTop.setBorder(
                new EmptyBorder(
                        30,
                        16,
                        10,
                        16
                )
        );


        // ==================================================
        // BRAND
        // ==================================================

        JLabel brandLabel =
                new JLabel(
                        "<html>RESTAURANT<br>BUDGET PLANNER</html>"
                );

        brandLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        brandLabel.setForeground(
                Color.WHITE
        );

        brandLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        sidebarTop.add(
                brandLabel
        );


        sidebarTop.add(
                Box.createVerticalStrut(14)
        );


        // ==================================================
        // ORANGE LINE
        // ==================================================

        JPanel orangeLine =
                new JPanel();

        orangeLine.setBackground(
                ORANGE
        );

        orangeLine.setMaximumSize(
                new Dimension(
                        42,
                        4
                )
        );

        orangeLine.setPreferredSize(
                new Dimension(
                        42,
                        4
                )
        );

        orangeLine.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        sidebarTop.add(
                orangeLine
        );


        sidebarTop.add(
                Box.createVerticalStrut(30)
        );


        // ==================================================
        // NAVIGATION BUTTONS
        // ==================================================

        JButton dashboardButton =
                createMenuButton(
                        "Dashboard",
                        true
                );


        JButton revenueButton =
                createMenuButton(
                        "Record Revenue",
                        false
                );

        revenueButton.addActionListener(e ->
        appFrame.showScreen("ACCOUNTANT_REVENUE")
);

        JButton expenseButton =
                createMenuButton(
                        "Record Expense",
                        false
                );

        expenseButton.addActionListener(e ->
        appFrame.showScreen("ACCOUNTANT_EXPENSE")
);


        JButton transactionsButton =
                createMenuButton(
                        "Transactions",
                        false
                );
                transactionsButton.addActionListener(e ->
    appFrame.showScreen("ACCOUNTANT_TRANSACTIONS")
);



        // ==================================================
        // SIDEBAR NAVIGATION
        // ==================================================

        dashboardButton.addActionListener(
                e ->
                {
                    appFrame.showScreen(
                            "ACCOUNTANT_DASHBOARD"
                    );
                }
        );


        revenueButton.addActionListener(
                e ->
                {
                    // Revenue screen will be connected later
                }
        );


        expenseButton.addActionListener(
                e ->
                {
                    // Expense screen will be connected later
                }
        );


        transactionsButton.addActionListener(
                e ->
                {
                    // Transactions screen will be connected later
                }
        );


        sidebarTop.add(
                dashboardButton
        );

        sidebarTop.add(
                Box.createVerticalStrut(8)
        );

        sidebarTop.add(
                revenueButton
        );

        sidebarTop.add(
                Box.createVerticalStrut(8)
        );

        sidebarTop.add(
                expenseButton
        );

        sidebarTop.add(
                Box.createVerticalStrut(8)
        );

        sidebarTop.add(
                transactionsButton
        );


        sidebar.add(
                sidebarTop,
                BorderLayout.NORTH
        );


        // ==================================================
        // LOGOUT
        // ==================================================

        JButton logoutButton =
                createMenuButton(
                        "Logout",
                        false
                );


        logoutButton.addActionListener(
                e ->
                {
                    appFrame.showScreen(
                            "LOGIN"
                    );
                }
        );


        JPanel logoutPanel =
                new JPanel(
                        new BorderLayout()
                );

        logoutPanel.setBackground(
                DARK
        );

        logoutPanel.setBorder(
                new EmptyBorder(
                        10,
                        16,
                        24,
                        16
                )
        );


        logoutPanel.add(
                logoutButton,
                BorderLayout.NORTH
        );


        sidebar.add(
                logoutPanel,
                BorderLayout.SOUTH
        );


        // ==================================================
        // MAIN AREA
        // ==================================================

        JPanel mainPanel =
                new JPanel();

        mainPanel.setLayout(
                new BorderLayout()
        );

        mainPanel.setBackground(
                LIGHT
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        28,
                        30,
                        28,
                        30
                )
        );


        // ==================================================
        // TOP AREA
        // ==================================================

        JPanel topPanel =
                new JPanel(
                        new BorderLayout()
                );

        topPanel.setBackground(
                LIGHT
        );


        JPanel titlePanel =
                new JPanel();

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        titlePanel.setBackground(
                LIGHT
        );


        JLabel titleLabel =
                new JLabel(
                        "Accountant Dashboard"
                );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        titleLabel.setForeground(
                TEXT
        );


        JLabel subtitleLabel =
                new JLabel(
                        "Manage the restaurant's daily financial records"
                );

        subtitleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        subtitleLabel.setForeground(
                MUTED
        );


        titlePanel.add(
                titleLabel
        );

        titlePanel.add(
                Box.createVerticalStrut(6)
        );

        titlePanel.add(
                subtitleLabel
        );


        // profile chip: orange avatar + "Accountant"
        RoundedPanel avatar =
                new RoundedPanel(
                        new GridBagLayout(),
                        ORANGE,
                        30,
                        false
                );

        avatar.setPreferredSize(
                new Dimension(
                        30,
                        30
                )
        );

        JLabel avatarLetter =
                new JLabel(
                        "A"
                );

        avatarLetter.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        avatarLetter.setForeground(
                Color.WHITE
        );

        avatar.add(
                avatarLetter
        );


        JLabel accountantLabel =
                new JLabel(
                        "Accountant"
                );

        accountantLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        accountantLabel.setForeground(
                new Color(
                        80,
                        80,
                        80
                )
        );


        RoundedPanel profileChip =
                new RoundedPanel(
                        new BorderLayout(10, 0),
                        Color.WHITE,
                        40,
                        true
                );

        profileChip.setBorder(
                new EmptyBorder(
                        6,
                        8,
                        9,
                        16
                )
        );

        profileChip.add(
                avatar,
                BorderLayout.WEST
        );

        profileChip.add(
                accountantLabel,
                BorderLayout.CENTER
        );


        JPanel chipWrap =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                0,
                                0
                        )
                );

        chipWrap.setBackground(
                LIGHT
        );

        chipWrap.add(
                profileChip
        );


        topPanel.add(
                titlePanel,
                BorderLayout.WEST
        );

        topPanel.add(
                chipWrap,
                BorderLayout.EAST
        );


        mainPanel.add(
                topPanel,
                BorderLayout.NORTH
        );


        // ==================================================
        // CENTER CONTENT
        // ==================================================

        JPanel centerPanel =
                new JPanel();

        centerPanel.setLayout(
                new BoxLayout(
                        centerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        centerPanel.setBackground(
                LIGHT
        );


        centerPanel.add(
                Box.createVerticalStrut(26)
        );


        // ==================================================
        // SUMMARY CARDS
        // ==================================================

        JPanel summaryPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                20,
                                0
                        )
                );

        summaryPanel.setBackground(
                LIGHT
        );

        summaryPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        125
                )
        );


      JPanel revenueCard = createSummaryCard(
        "Total Revenue", "$0.00", ORANGE
);
totalRevenueLabel = (JLabel) revenueCard.getComponent(4);
summaryPanel.add(revenueCard);

JPanel expensesCard = createSummaryCard(
        "Total Expenses", "$0.00", ORANGE
);
totalExpensesLabel = (JLabel) expensesCard.getComponent(4);
summaryPanel.add(expensesCard);

JPanel balanceCard = createSummaryCard(
        "Net Balance", "$0.00", new Color(40, 40, 40)
);
netBalanceLabel = (JLabel) balanceCard.getComponent(4);
summaryPanel.add(balanceCard);


        centerPanel.add(
                summaryPanel
        );


        centerPanel.add(
                Box.createVerticalStrut(20)
        );


        // ==================================================
        // REVENUE + EXPENSE AREA
        // ==================================================

        JPanel recordPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                20,
                                0
                        )
                );

        recordPanel.setBackground(
                LIGHT
        );


        recordPanel.add(
                createRecordCard(
                        "Record Revenue",
                        "Enter the revenue earned by the restaurant.",
                        ORANGE
                )
        );


        recordPanel.add(
                createRecordCard(
                        "Record Expense",
                        "Enter the expenses paid by the restaurant.",
                        ORANGE
                )
        );


        centerPanel.add(
                recordPanel
        );


        centerPanel.add(
                Box.createVerticalStrut(20)
        );


        // ==================================================
        // RECENT TRANSACTIONS
        // ==================================================

        RoundedPanel transactionsPanel =
                new RoundedPanel(
                        new BorderLayout(),
                        CARD,
                        18,
                        true
                );

        transactionsPanel.setBorder(
                new EmptyBorder(
                        22,
                        25,
                        28,
                        25
                )
        );


        JPanel transactionsHeader =
                new JPanel();

        transactionsHeader.setLayout(
                new BoxLayout(
                        transactionsHeader,
                        BoxLayout.Y_AXIS
                )
        );

        transactionsHeader.setOpaque(
                false
        );


        JLabel transactionsTitle =
                new JLabel(
                        "Recent Transactions"
                );

        transactionsTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        transactionsTitle.setForeground(
                TEXT
        );

        transactionsTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        transactionsHeader.add(
                createAccentBar(ORANGE)
        );

        transactionsHeader.add(
                Box.createVerticalStrut(12)
        );

        transactionsHeader.add(
                transactionsTitle
        );


        transactionsPanel.add(
                transactionsHeader,
                BorderLayout.NORTH
        );


        transactionList = new JPanel(
        new GridLayout(0, 4, 15, 14)
    );

        transactionList.setOpaque(
                false
        );

        transactionList.setBorder(
                new EmptyBorder(
                        18,
                        0,
                        0,
                        0
                )
        );


      
        transactionsPanel.add(
                transactionList,
                BorderLayout.CENTER
        );


        centerPanel.add(
                transactionsPanel
        );


        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );


        // ==================================================
        // ADD SIDEBAR + MAIN AREA
        // ==================================================

        add(
                sidebar,
                BorderLayout.WEST
        );


        add(
                mainPanel,
                BorderLayout.CENTER
        );
    }


    // ==================================================
    // SUMMARY CARD
    // ==================================================

    private JPanel createSummaryCard(
            String title,
            String amount,
            Color amountColor
    )
    {
        RoundedPanel card =
                new RoundedPanel(
                        null,
                        CARD,
                        18,
                        true
                );

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBorder(
                new EmptyBorder(
                        20,
                        22,
                        24,
                        22
                )
        );


        JLabel titleLabel =
                new JLabel(
                        title
                );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        titleLabel.setForeground(
                new Color(
                        100,
                        100,
                        100
                )
        );

        titleLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel amountLabel =
                new JLabel(
                        amount
                );

        amountLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        amountLabel.setForeground(
                amountColor
        );

        amountLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        card.add(
                createAccentBar(amountColor)
        );

        card.add(
                Box.createVerticalStrut(12)
        );

        card.add(
                titleLabel
        );

        card.add(
                Box.createVerticalStrut(6)
        );

        card.add(
                amountLabel
        );


        return card;
    }


    // ==================================================
    // RECORD CARD
    // ==================================================

    private JPanel createRecordCard(
            String title,
            String description,
            Color accent
    )
    {
        RoundedPanel card =
                new RoundedPanel(
                        new BorderLayout(),
                        CARD,
                        18,
                        true
                );

        card.setBorder(
                new EmptyBorder(
                        22,
                        25,
                        28,
                        25
                )
        );


        JPanel cardHeader =
                new JPanel();

        cardHeader.setLayout(
                new BoxLayout(
                        cardHeader,
                        BoxLayout.Y_AXIS
                )
        );

        cardHeader.setOpaque(
                false
        );


        JLabel titleLabel =
                new JLabel(
                        title
                );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        titleLabel.setForeground(
                accent
        );

        titleLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        titleLabel.setBorder(
                new EmptyBorder(
                        0,
                        0,
                        10,
                        0
                )
        );


        cardHeader.add(
                createAccentBar(accent)
        );

        cardHeader.add(
                Box.createVerticalStrut(12)
        );

        cardHeader.add(
                titleLabel
        );


        JLabel descriptionLabel =
                new JLabel(
                        "<html>"
                        + description
                        + "<br><br>"
                        + "Open the recording screen"
                        + " to enter a new transaction."
                        + "</html>"
                );

        descriptionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        descriptionLabel.setForeground(
                MUTED
        );

        descriptionLabel.setVerticalAlignment(
                SwingConstants.TOP
        );


        card.add(
                cardHeader,
                BorderLayout.NORTH
        );

        card.add(
                descriptionLabel,
                BorderLayout.CENTER
        );


        return card;
    }


    // ==================================================
    // ACCENT BAR (small bar at the top of a card)
    // ==================================================

    private JPanel createAccentBar(
            Color color
    )
    {
        RoundedPanel bar =
                new RoundedPanel(
                        new BorderLayout(),
                        color,
                        4,
                        false
                );

        bar.setPreferredSize(
                new Dimension(
                        28,
                        4
                )
        );

        bar.setMaximumSize(
                new Dimension(
                        28,
                        4
                )
        );

        bar.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        return bar;
    }


    // ==================================================
    // TRANSACTION
    // ==================================================

    private void addTransaction(
            JPanel panel,
            String date,
            String description,
            String type,
            String amount
    )
    {
        JLabel dateLabel =
                createTransactionLabel(
                        date
                );

        JLabel descriptionLabel =
                createTransactionLabel(
                        description
                );

        JLabel typeLabel =
                createTransactionLabel(
                        type
                );

        JLabel amountLabel =
                createTransactionLabel(
                        amount
                );

        // amounts line up on the right edge, in bold
        amountLabel.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        amountLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        typeLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );


        if (type.equals("Revenue"))
        {
            typeLabel.setForeground(
                    new Color(
                            30,
                            150,
                            30
                    )
            );

            amountLabel.setForeground(
                    new Color(
                            30,
                            150,
                            30
                    )
            );
        }
        else
        {
            typeLabel.setForeground(
                    new Color(
                            200,
                            60,
                            60
                    )
            );

            amountLabel.setForeground(
                    new Color(
                            200,
                            60,
                            60
                    )
            );
        }


        panel.add(
                dateLabel
        );

        panel.add(
                descriptionLabel
        );

        panel.add(
                typeLabel
        );

        panel.add(
                amountLabel
        );
    }


    private JLabel createTransactionLabel(
            String text
    )
    {
        JLabel label =
                new JLabel(
                        text
                );

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        label.setForeground(
                new Color(
                        80,
                        80,
                        80
                )
        );


        return label;
    }


    // ==================================================
    // MENU BUTTON
    // ==================================================

    private JButton createMenuButton(
            String text,
            boolean active
    )
    {
        JButton button =
                new JButton(
                        text
                )
                {
                    private boolean hover = false;

                    {
                        addMouseListener(
                                new MouseAdapter()
                                {
                                    @Override
                                    public void mouseEntered(MouseEvent e)
                                    {
                                        hover = true;

                                        if (!active)
                                        {
                                            setForeground(
                                                    Color.WHITE
                                            );
                                        }

                                        repaint();
                                    }

                                    @Override
                                    public void mouseExited(MouseEvent e)
                                    {
                                        hover = false;

                                        if (!active)
                                        {
                                            setForeground(
                                                    new Color(
                                                            190,
                                                            190,
                                                            190
                                                    )
                                            );
                                        }

                                        repaint();
                                    }
                                }
                        );
                    }

                    @Override
                    protected void paintComponent(Graphics g)
                    {
                        Graphics2D g2 =
                                (Graphics2D) g.create();

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        Color fill;

                        if (active)
                        {
                            fill = ORANGE;
                        }
                        else if (hover)
                        {
                            fill = new Color(38, 38, 38);
                        }
                        else
                        {
                            fill = DARK;
                        }

                        g2.setColor(fill);

                        g2.fillRoundRect(
                                0,
                                0,
                                getWidth(),
                                getHeight(),
                                14,
                                14
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
                        : new Color(
                                190,
                                190,
                                190
                        )
        );

        button.setBackground(
                active
                        ? ORANGE
                        : DARK
        );

        button.setFocusPainted(
                false
        );

        button.setBorderPainted(
                false
        );

        button.setContentAreaFilled(
                false
        );

        button.setOpaque(
                false
        );

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

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );


        return button;
    }


    // ==================================================
    // ROUNDED PANEL (cards with soft shadow)
    // ==================================================

    private static class RoundedPanel extends JPanel
    {
        private final int radius;
        private final boolean shadow;

        RoundedPanel(
                LayoutManager layout,
                Color background,
                int radius,
                boolean shadow
        )
        {
            super(layout);

            this.radius = radius;
            this.shadow = shadow;

            setBackground(background);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            int w = getWidth();
            int h = getHeight();

            if (shadow)
            {
                for (int i = 1; i <= 3; i++)
                {
                    g2.setColor(new Color(0, 0, 0, 7));
                    g2.fillRoundRect(0, i, w, h - 3, radius, radius);
                }

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, w, h - 3, radius, radius);

                g2.setColor(new Color(0, 0, 0, 14));
                g2.drawRoundRect(0, 0, w - 1, h - 4, radius, radius);
            }
            else
            {
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, w, h, radius, radius);
            }

            g2.dispose();
        }
    }

    public void refreshDashboard() {

    int restaurantId = appFrame.getRestaurantId();

    if (restaurantId <= 0) {
        totalRevenueLabel.setText("$0.00");
        totalExpensesLabel.setText("$0.00");
        netBalanceLabel.setText("$0.00");
        return;
    }

    LocalDate today = LocalDate.now();
    int month = today.getMonthValue();
    int year = today.getYear();

    try {
        double revenue = RevenueService.getTotalRevenue(
                month, year, restaurantId
        );

        BigDecimal expenses = new ExpenseService().getTotalExpenses(
                month, year, restaurantId
        );

        BigDecimal revenueAmount = BigDecimal.valueOf(revenue);
        BigDecimal balance = revenueAmount.subtract(expenses);

        totalRevenueLabel.setText(formatAmount(revenueAmount));
        totalExpensesLabel.setText(formatAmount(expenses));
        netBalanceLabel.setText(formatAmount(balance));

    } catch (SQLException | RuntimeException exception) {
        JOptionPane.showMessageDialog(
                this,
                "Unable to load dashboard totals:\n"
                        + exception.getMessage(),
                "Dashboard Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}

private String formatAmount(BigDecimal amount) {
    return "$" + String.format(
            Locale.US,
            "%,.2f",
            amount
    );
}
  public void refreshRecentTransactions() {

    int restaurantId = appFrame.getRestaurantId();

    LocalDate today = LocalDate.now();
    int month = today.getMonthValue();
    int year = today.getYear();

    transactionList.removeAll();

    if (restaurantId <= 0) {
        transactionList.revalidate();
        transactionList.repaint();
        return;
    }

    try {
        // Load revenue records for this restaurant and month.
        var revenues = RevenueService.getAccountantRevenue(
                month, year, restaurantId
        );

        // Load expense records for this restaurant and month.
        var expenses = new ExpenseService().getExpenses(
                month, year, restaurantId
        );

        // Add revenue records.
        for (var revenue : revenues) {

            String date = revenue.getDate();

            if (date != null && !date.isEmpty()) {
                date = LocalDate.parse(date)
                        .format(
                                java.time.format.DateTimeFormatter
                                        .ofPattern("dd/MM")
                        );
            }

            String description = revenue.getDescription();
            if (description == null || description.isBlank()) {
                description = revenue.getCategory();
            }

            String amount = String.format(
                    Locale.US,
                    "+$%,.2f",
                    revenue.getAmount()
            );

            addTransaction(
                    transactionList,
                    date,
                    description,
                    "Revenue",
                    amount
            );
        }

        // Add expense records.
        for (Object[] expense : expenses) {

            java.sql.Date sqlDate =
                    (java.sql.Date) expense[0];

            String date = sqlDate == null
                    ? ""
                    : sqlDate.toLocalDate().format(
                            java.time.format.DateTimeFormatter
                                    .ofPattern("dd/MM")
                    );

            String category = (String) expense[1];
            String description = (String) expense[2];
            BigDecimal expenseAmount = (BigDecimal) expense[3];

            if (description == null || description.isBlank()) {
                description = category;
            }

            String amount = String.format(
                    Locale.US,
                    "-$%,.2f",
                    expenseAmount
            );

            addTransaction(
                    transactionList,
                    date,
                    description,
                    "Expense",
                    amount
            );
        }

        if (transactionList.getComponentCount() == 0) {
            transactionList.add(
                    createTransactionLabel(
                            "No transactions recorded this month."
                    )
            );
        }

    } catch (SQLException | RuntimeException exception) {

        JOptionPane.showMessageDialog(
                this,
                "Unable to load recent transactions:\n"
                        + exception.getMessage(),
                "Transaction Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    transactionList.revalidate();
    transactionList.repaint();
}
}