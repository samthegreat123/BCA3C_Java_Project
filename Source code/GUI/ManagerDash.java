package GUI;

import FINANCIAL_ANALYSIS.FinancialAnalysisService;
import FINANCIAL_ANALYSIS.FinancialAnalysisService.BudgetPerformance;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class ManagerDash extends JPanel
{
    private AppFrame appFrame;

    private final Color ORANGE =
            new Color(245, 140, 30);

    private final Color DARK =
            new Color(15, 15, 15);

    private final Color LIGHT =
            new Color(246, 246, 246);

    private JLabel plannedBudgetValue;
    private JLabel totalRevenueValue;
    private JLabel totalExpensesValue;

    private JPanel performanceRowsPanel;


    public ManagerDash(
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

        sidebarTop.add(
                brandLabel
        );


        sidebarTop.add(
                Box.createVerticalStrut(14)
        );


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

        orangeLine.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        sidebarTop.add(
                orangeLine
        );


        sidebarTop.add(
                Box.createVerticalStrut(30)
        );


        JButton dashboardButton =
                createMenuButton(
                        "Dashboard"
                );

        dashboardButton.setBackground(
                ORANGE
        );

        dashboardButton.setForeground(
                Color.WHITE
        );


        JButton budgetButton =
                createMenuButton(
                        "Budget"
                );


        JButton revenueButton =
                createMenuButton(
                        "Revenue"
                );

        revenueButton.addActionListener(
                e ->
                {
                    appFrame.showScreen(
                            "REVENUE"
                    );
                }
        );


        JButton performanceButton =
                createMenuButton(
                        "Performance"
                );


        performanceButton.addActionListener(
                e ->
                {
                    appFrame.showScreen(
                            "FINANCIAL_ANALYSIS"
                    );
                }
        );


        JButton reportsButton =
                createMenuButton(
                        "Reports"
                );

        reportsButton.addActionListener(
                e ->
                {
                    appFrame.showScreen(
                            "REPORT"
                    );
                }
        );


        dashboardButton.addActionListener(
                e ->
                {
                    appFrame.showScreen(
                            "MANAGER_DASHBOARD"
                    );
                }
        );


        budgetButton.addActionListener(
                e ->
                {
                    appFrame.showScreen(
                            "BUDGET"
                    );
                }
        );


        sidebarTop.add(
                dashboardButton
        );

        sidebarTop.add(
                Box.createVerticalStrut(8)
        );

        sidebarTop.add(
                budgetButton
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
                performanceButton
        );

        sidebarTop.add(
                Box.createVerticalStrut(8)
        );

        sidebarTop.add(
                reportsButton
        );


        sidebar.add(
                sidebarTop,
                BorderLayout.NORTH
        );


        JButton logoutButton =
                createMenuButton(
                        "Logout"
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
                BorderLayout.CENTER
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
                        "Manager Dashboard"
                );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        titleLabel.setForeground(
                Color.BLACK
        );


        JLabel subtitleLabel =
                new JLabel(
                        "Overview of your restaurant's financial activity"
                );

        subtitleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        subtitleLabel.setForeground(
                new Color(
                        110,
                        110,
                        110
                )
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
                        "M"
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


        JLabel managerLabel =
                new JLabel(
                        "Manager"
                );

        managerLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        managerLabel.setForeground(
                new Color(
                        70,
                        70,
                        70
                )
        );


        RoundedPanel profileChip =
                new RoundedPanel(
                        new BorderLayout(
                                10,
                                0
                        ),
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
                managerLabel,
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


        JPanel statsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                18,
                                0
                        )
                );

        statsPanel.setBackground(
                LIGHT
        );

        statsPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        115
                )
        );


        JPanel budgetCard =
                createStatCard(
                        "PLANNED BUDGET",
                        "0",
                        false
                );


        JPanel revenueCard =
                createStatCard(
                        "TOTAL REVENUE",
                        "0",
                        true
                );


        JPanel expenseCard =
                createStatCard(
                        "TOTAL EXPENSES",
                        "0",
                        false
                );


        statsPanel.add(
                budgetCard
        );

        statsPanel.add(
                revenueCard
        );

        statsPanel.add(
                expenseCard
        );


        centerPanel.add(
                statsPanel
        );


        centerPanel.add(
                Box.createVerticalStrut(20)
        );


        JPanel lowerPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                18,
                                0
                        )
                );

        lowerPanel.setBackground(
                LIGHT
        );


        RoundedPanel performancePanel =
                new RoundedPanel(
                        new BorderLayout(),
                        Color.WHITE,
                        18,
                        true
                );

        performancePanel.setLayout(
                new BoxLayout(
                        performancePanel,
                        BoxLayout.Y_AXIS
                )
        );

        performancePanel.setBorder(
                new EmptyBorder(
                        20,
                        24,
                        23,
                        24
                )
        );


        JLabel performanceTitle =
                new JLabel(
                        "Budget Performance"
                );

        performanceTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        performanceTitle.setForeground(
                Color.BLACK
        );


        performancePanel.add(
                createSectionTitle(
                        performanceTitle
                )
        );


        performancePanel.add(
                Box.createVerticalStrut(18)
        );


        performanceRowsPanel =
                new JPanel();

        performanceRowsPanel.setLayout(
                new BoxLayout(
                        performanceRowsPanel,
                        BoxLayout.Y_AXIS
                )
        );

        performanceRowsPanel.setOpaque(
                false
        );

        performanceRowsPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        performancePanel.add(
                performanceRowsPanel
        );


        lowerPanel.add(
                performancePanel
        );


        RoundedPanel quickPanel =
                new RoundedPanel(
                        new BorderLayout(),
                        Color.WHITE,
                        18,
                        true
                );

        quickPanel.setLayout(
                new BoxLayout(
                        quickPanel,
                        BoxLayout.Y_AXIS
                )
        );

        quickPanel.setBorder(
                new EmptyBorder(
                        20,
                        24,
                        23,
                        24
                )
        );


        JLabel quickTitle =
                new JLabel(
                        "Quick Actions"
                );

        quickTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        quickTitle.setForeground(
                Color.BLACK
        );


        quickPanel.add(
                createSectionTitle(
                        quickTitle
                )
        );


        quickPanel.add(
                Box.createVerticalStrut(18)
        );


        JPanel quickRow1 =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                12,
                                0
                        )
                );

        quickRow1.setOpaque(
                false
        );

        quickRow1.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        quickRow1.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        74
                )
        );


        JButton manageBudget =
                createQuickButton(
                        "Budget",
                        "Manage budget"
                );


        JButton viewRevenue =
                createQuickButton(
                        "Revenue",
                        "View revenue"
                );


        viewRevenue.addActionListener(
                e ->
                {
                    appFrame.showScreen(
                            "REVENUE"
                    );
                }
        );


        manageBudget.addActionListener(
                e ->
                {
                    appFrame.showScreen(
                            "BUDGET"
                    );
                }
        );


        quickRow1.add(
                manageBudget
        );

        quickRow1.add(
                viewRevenue
        );


        quickPanel.add(
                quickRow1
        );


        quickPanel.add(
                Box.createVerticalStrut(12)
        );


        JPanel quickRow2 =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                12,
                                0
                        )
                );

        quickRow2.setOpaque(
                false
        );

        quickRow2.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        quickRow2.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        74
                )
        );


        JButton performance =
                createQuickButton(
                        "Performance",
                        "Check results"
                );


        performance.addActionListener(
                e ->
                {
                    appFrame.showScreen(
                            "FINANCIAL_ANALYSIS"
                    );
                }
        );


        JButton reports =
                createQuickButton(
                        "Reports",
                        "View reports"
                );


        reports.addActionListener(
                e ->
                {
                    appFrame.showScreen(
                            "REPORT"
                    );
                }
        );


        quickRow2.add(
                performance
        );

        quickRow2.add(
                reports
        );


        quickPanel.add(
                quickRow2
        );


        lowerPanel.add(
                quickPanel
        );


        centerPanel.add(
                lowerPanel
        );


        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );


        add(
                sidebar,
                BorderLayout.WEST
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );


        loadDashboardData();
    }


    // ==================================================
    // LOAD DASHBOARD DATA
    // ==================================================

    private void loadDashboardData()
    {
        try
        {
            String selectedPeriod =
                    appFrame.getSelectedFinancialPeriod();

            if (selectedPeriod == null)
            {
                return;
            }

            String[] parts =
                    selectedPeriod.split(" ");

            String monthName =
                    parts[0];

            int year =
                    Integer.parseInt(parts[1]);

            int month =
                    getMonthNumber(monthName);

            String budgetPeriod =
                    selectedPeriod;

            int managerId =
                    appFrame.getManagerId();
        
            int restaurantId = appFrame.getRestaurantId();


            double plannedBudget =
                    FinancialAnalysisService.getPlannedBudget(
                            budgetPeriod,
                            managerId,
                            restaurantId
                            
                    );


            double totalRevenue =
                    FinancialAnalysisService.getTotalRevenue(
                            month,
                            year,
                            restaurantId
                    );


            double totalExpenses =
                    FinancialAnalysisService.getTotalExpenses(
                            month,
                            year,
                            restaurantId
                    );


            plannedBudgetValue.setText(
                    formatAmount(
                            plannedBudget
                    )
            );


            totalRevenueValue.setText(
                    formatAmount(
                            totalRevenue
                    )
            );


            totalExpensesValue.setText(
                    formatAmount(
                            totalExpenses
                    )
            );


            List<BudgetPerformance> performanceList =
                    FinancialAnalysisService.getBudgetPerformance(
                            budgetPeriod,
                            managerId,
                            restaurantId,
                            month,
                            year
                    );


            performanceRowsPanel.removeAll();


            if (performanceList.isEmpty())
            {
                JLabel noDataLabel =
                        new JLabel(
                                "No budget data available for this month."
                        );

                noDataLabel.setFont(
                        new Font(
                                "Segoe UI",
                                Font.PLAIN,
                                12
                        )
                );

                noDataLabel.setForeground(
                        new Color(
                                110,
                                110,
                                110
                        )
                );

                noDataLabel.setAlignmentX(
                        Component.LEFT_ALIGNMENT
                );


                performanceRowsPanel.add(
                        noDataLabel
                );
            }
            else
            {
                for (
                        BudgetPerformance record :
                        performanceList
                )
                {
                    double planned =
                            record.getPlannedAmount();

                    double actual =
                            record.getActualAmount();

                    int percentage = 0;

                    if (planned > 0)
                    {
                        percentage =
                                (int) Math.round(
                                        (actual / planned)
                                                * 100
                                );
                    }

                    percentage =
                            Math.min(
                                    percentage,
                                    100
                            );

                    String amount =
                            formatAmount(
                                    actual
                            )
                            + " / "
                            + formatAmount(
                                    planned
                            );

                    addBudgetRow(
                            performanceRowsPanel,
                            record.getCategory(),
                            amount,
                            percentage
                    );
                }
            }


            performanceRowsPanel.revalidate();

            performanceRowsPanel.repaint();
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Could not load dashboard data:\n"
                            + ex.getMessage(),
                    "Dashboard Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // ==================================================
    // REFRESH DASHBOARD
    // ==================================================

    public void refreshDashboard()
    {
        loadDashboardData();
    }


    // ==================================================
    // GET MONTH NUMBER
    // ==================================================

    private int getMonthNumber(
            String monthName
    )
    {
        switch (monthName)
        {
            case "January":
                return 1;

            case "February":
                return 2;

            case "March":
                return 3;

            case "April":
                return 4;

            case "May":
                return 5;

            case "June":
                return 6;

            case "July":
                return 7;

            case "August":
                return 8;

            case "September":
                return 9;

            case "October":
                return 10;

            case "November":
                return 11;

            case "December":
                return 12;

            default:
                return 1;
        }
    }


    // ==================================================
    // FORMAT AMOUNT
    // ==================================================

    private String formatAmount(
            double amount
    )
    {
        if (amount == Math.rint(amount))
        {
            return String.format(
                    "%,.0f",
                    amount
            );
        }

        return String.format(
                "%,.2f",
                amount
        );
    }


    // ==================================================
    // SECTION TITLE
    // ==================================================

    private JPanel createSectionTitle(
            JLabel title
    )
    {
        JPanel marker =
                new JPanel();

        marker.setBackground(
                ORANGE
        );

        marker.setPreferredSize(
                new Dimension(
                        4,
                        16
                )
        );

        title.setBorder(
                new EmptyBorder(
                        0,
                        9,
                        0,
                        0
                )
        );


        JPanel row =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                0,
                                0
                        )
                );

        row.setOpaque(
                false
        );

        row.add(
                marker
        );

        row.add(
                title
        );

        row.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        row.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        24
                )
        );


        return row;
    }


    // ==================================================
    // MENU BUTTON
    // ==================================================

    private JButton createMenuButton(
            String text
    )
    {
        JButton button =
                new JButton(text)
                {
                    private boolean hover = false;

                    {
                        addMouseListener(
                                new MouseAdapter()
                                {
                                    @Override
                                    public void mouseEntered(
                                            MouseEvent e
                                    )
                                    {
                                        hover = true;

                                        if (!getBackground().equals(
                                                ORANGE
                                        ))
                                        {
                                            setForeground(
                                                    Color.WHITE
                                            );
                                        }

                                        repaint();
                                    }


                                    @Override
                                    public void mouseExited(
                                            MouseEvent e
                                    )
                                    {
                                        hover = false;

                                        if (!getBackground().equals(
                                                ORANGE
                                        ))
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
                    protected void paintComponent(
                            Graphics g
                    )
                    {
                        Graphics2D g2 =
                                (Graphics2D) g.create();


                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );


                        Color fill =
                                getBackground();


                        if (
                                hover
                                && !fill.equals(ORANGE)
                        )
                        {
                            fill =
                                    new Color(
                                            38,
                                            38,
                                            38
                                    );
                        }


                        g2.setColor(
                                fill
                        );


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
                new Color(
                        190,
                        190,
                        190
                )
        );


        button.setBackground(
                DARK
        );


        button.setFocusPainted(
                false
        );


        button.setContentAreaFilled(
                false
        );


        button.setOpaque(
                false
        );


        button.setBorderPainted(
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


        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );


        return button;
    }


    // ==================================================
    // STAT CARD
    // ==================================================

    private JPanel createStatCard(
            String title,
            String value,
            boolean orangeValue
    )
    {
        RoundedPanel card =
                new RoundedPanel(
                        new BorderLayout(),
                        Color.WHITE,
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
                        18,
                        22,
                        21,
                        22
                )
        );


        RoundedPanel accentBar =
                new RoundedPanel(
                        new BorderLayout(),
                        ORANGE,
                        4,
                        false
                );


        accentBar.setPreferredSize(
                new Dimension(
                        28,
                        4
                )
        );


        accentBar.setMaximumSize(
                new Dimension(
                        28,
                        4
                )
        );


        accentBar.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel titleLabel =
                new JLabel(
                        title
                );


        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );


        titleLabel.setForeground(
                new Color(
                        110,
                        110,
                        110
                )
        );


        titleLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel valueLabel =
                new JLabel(
                        value
                );


        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );


        valueLabel.setForeground(
                orangeValue
                        ? ORANGE
                        : Color.BLACK
        );


        valueLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        if (
                title.equals(
                        "PLANNED BUDGET"
                )
        )
        {
            plannedBudgetValue =
                    valueLabel;
        }
        else if (
                title.equals(
                        "TOTAL REVENUE"
                )
        )
        {
            totalRevenueValue =
                    valueLabel;
        }
        else if (
                title.equals(
                        "TOTAL EXPENSES"
                )
        )
        {
            totalExpensesValue =
                    valueLabel;
        }


        card.add(
                accentBar
        );


        card.add(
                Box.createVerticalStrut(12)
        );


        card.add(
                titleLabel
        );


        card.add(
                Box.createVerticalStrut(5)
        );


        card.add(
                valueLabel
        );


        return card;
    }


    // ==================================================
    // BUDGET ROW
    // ==================================================

    private void addBudgetRow(
            JPanel parent,
            String category,
            String amount,
            int percentage
    )
    {
        JPanel row =
                new JPanel();


        row.setLayout(
                new BoxLayout(
                        row,
                        BoxLayout.Y_AXIS
                )
        );


        row.setOpaque(
                false
        );


        row.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JPanel header =
                new JPanel(
                        new BorderLayout()
                );


        header.setOpaque(
                false
        );


        header.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel categoryLabel =
                new JLabel(
                        category
                );


        categoryLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );


        categoryLabel.setForeground(
                new Color(
                        50,
                        50,
                        50
                )
        );


        JLabel amountLabel =
                new JLabel(
                        amount
                );


        amountLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );


        amountLabel.setForeground(
                new Color(
                        110,
                        110,
                        110
                ));


        header.add(
                categoryLabel,
                BorderLayout.WEST
        );


        header.add(
                amountLabel,
                BorderLayout.EAST
        );


        row.add(
                header
        );


        row.add(
                Box.createVerticalStrut(8)
        );


        JProgressBar progressBar =
                new JProgressBar(
                        0,
                        100
                );


        progressBar.setUI(
                new RoundedProgressUI()
        );


        progressBar.setValue(
                percentage
        );


        progressBar.setForeground(
                ORANGE
        );


        progressBar.setBackground(
                new Color(
                        235,
                        235,
                        235
                )
        );


        progressBar.setBorderPainted(
                false
        );


        progressBar.setOpaque(
                false
        );


        progressBar.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        progressBar.setPreferredSize(
                new Dimension(
                        100,
                        10
                )
        );


        progressBar.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        10
                )
        );


        row.add(
                progressBar
        );


        parent.add(
                row
        );


        parent.add(
                Box.createVerticalStrut(15)
        );
    }


    // ==================================================
    // QUICK ACTION BUTTON
    // ==================================================

    private JButton createQuickButton(
            String title,
            String description
    )
    {
        JButton button =
                new JButton(
                        "<html><b>"
                        + title
                        + "</b><br>"
                        + description
                        + "</html>"
                )
                {
                    private boolean hover = false;

                    {
                        addMouseListener(
                                new MouseAdapter()
                                {
                                    @Override
                                    public void mouseEntered(
                                            MouseEvent e
                                    )
                                    {
                                        hover = true;

                                        repaint();
                                    }


                                    @Override
                                    public void mouseExited(
                                            MouseEvent e
                                    )
                                    {
                                        hover = false;

                                        repaint();
                                    }
                                }
                        );
                    }


                    @Override
                    protected void paintComponent(
                            Graphics g
                    )
                    {
                        Graphics2D g2 =
                                (Graphics2D) g.create();


                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );


                        int w =
                                getWidth();

                        int h =
                                getHeight();


                        g2.setColor(
                                hover
                                        ? new Color(
                                                255,
                                                248,
                                                238
                                        )
                                        : new Color(
                                                250,
                                                250,
                                                250
                                        )
                        );


                        g2.fillRoundRect(
                                0,
                                0,
                                w - 1,
                                h - 1,
                                16,
                                16
                        );


                        g2.setColor(
                                hover
                                        ? ORANGE
                                        : new Color(
                                                225,
                                                225,
                                                225
                                        )
                        );


                        g2.setStroke(
                                new BasicStroke(
                                        hover
                                                ? 2f
                                                : 1f
                                )
                        );


                        g2.drawRoundRect(
                                hover
                                        ? 1
                                        : 0,
                                hover
                                        ? 1
                                        : 0,
                                hover
                                        ? w - 3
                                        : w - 1,
                                hover
                                        ? h - 3
                                        : h - 1,
                                16,
                                16
                        );


                        g2.setColor(
                                ORANGE
                        );


                        g2.setStroke(
                                new BasicStroke(
                                        2f,
                                        BasicStroke.CAP_ROUND,
                                        BasicStroke.JOIN_ROUND
                                )
                        );


                        int ax =
                                w - 22;

                        int ay =
                                h / 2;


                        g2.drawLine(
                                ax - 4,
                                ay - 4,
                                ax,
                                ay
                        );


                        g2.drawLine(
                                ax,
                                ay,
                                ax - 4,
                                ay + 4
                        );


                        g2.dispose();


                        super.paintComponent(g);
                    }
                };


        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );


        button.setForeground(
                new Color(
                        60,
                        60,
                        60
                )
        );


        button.setBackground(
                Color.WHITE
        );


        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );


        button.setFocusPainted(
                false
        );


        button.setContentAreaFilled(
                false
        );


        button.setOpaque(
                false
        );


        button.setBorderPainted(
                false
        );


        button.setBorder(
                new EmptyBorder(
                        12,
                        16,
                        12,
                        34
                )
        );


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        return button;
    }


    // ==================================================
    // ROUNDED PANEL
    // ==================================================

    private static class RoundedPanel
            extends JPanel
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

            this.radius =
                    radius;

            this.shadow =
                    shadow;

            setBackground(
                    background
            );

            setOpaque(
                    false
            );
        }


        @Override
        protected void paintComponent(
                Graphics g
        )
        {
            Graphics2D g2 =
                    (Graphics2D) g.create();


            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );


            int w =
                    getWidth();

            int h =
                    getHeight();


            if (shadow)
            {
                for (
                        int i = 1;
                        i <= 3;
                        i++
                )
                {
                    g2.setColor(
                            new Color(
                                    0,
                                    0,
                                    0,
                                    7
                            )
                    );


                    g2.fillRoundRect(
                            0,
                            i,
                            w,
                            h - 3,
                            radius,
                            radius
                    );
                }


                g2.setColor(
                        getBackground()
                );


                g2.fillRoundRect(
                        0,
                        0,
                        w,
                        h - 3,
                        radius,
                        radius
                );


                g2.setColor(
                        new Color(
                                0,
                                0,
                                0,
                                14
                        )
                );


                g2.drawRoundRect(
                        0,
                        0,
                        w - 1,
                        h - 4,
                        radius,
                        radius
                );
            }
            else
            {
                g2.setColor(
                        getBackground()
                );


                g2.fillRoundRect(
                        0,
                        0,
                        w,
                        h,
                        radius,
                        radius
                );
            }


            g2.dispose();
        }
    }


    // ==================================================
    // ROUNDED PROGRESS BAR UI
    // ==================================================

    private static class RoundedProgressUI
            extends BasicProgressBarUI
    {
        @Override
        protected void paintDeterminate(
                Graphics g,
                JComponent c
        )
        {
            Graphics2D g2 =
                    (Graphics2D) g.create();


            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );


            int w =
                    progressBar.getWidth();

            int h =
                    progressBar.getHeight();


            g2.setColor(
                    progressBar.getBackground()
            );


            g2.fillRoundRect(
                    0,
                    0,
                    w,
                    h,
                    h,
                    h
            );


            int fillWidth =
                    (int) (
                            w *
                            progressBar.getPercentComplete()
                    );


            if (fillWidth > 0)
            {
                g2.setColor(
                        progressBar.getForeground()
                );


                g2.fillRoundRect(
                        0,
                        0,
                        Math.max(
                                fillWidth,
                                h
                        ),
                        h,
                        h,
                        h
                );
            }


            g2.dispose();
        }
    }
}