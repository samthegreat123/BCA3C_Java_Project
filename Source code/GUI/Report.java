package GUI;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import REPORT.ReportService;
import FINANCIAL_ANALYSIS.FinancialAnalysisService;
import FINANCIAL_ANALYSIS.FinancialAnalysisService.BudgetPerformance;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Report extends JPanel
{
    private AppFrame appFrame;

    private final Color ORANGE =
            new Color(245, 140, 30);

    private final Color DARK =
            new Color(15, 15, 15);

    private final Color LIGHT =
            new Color(246, 246, 246);

    // ==================================================
    // DYNAMIC REPORT LABELS
    // ==================================================

    private JLabel plannedBudgetValue;
    private JLabel totalRevenueValue;
    private JLabel totalExpensesValue;
    private JLabel finalProfitValue;

    private JPanel summaryDetailsPanel;
    private JPanel budgetPerformancePanel;


    public Report(
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
                new JPanel(
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
                Box.createVerticalStrut(
                        14
                )
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
                Box.createVerticalStrut(
                        30
                )
        );


        // ==================================================
        // NAVIGATION
        // ==================================================

        JButton dashboardButton =
                createMenuButton(
                        "Dashboard"
                );


        JButton budgetButton =
                createMenuButton(
                        "Budget"
                );


        JButton revenueButton =
                createMenuButton(
                        "Revenue"
                );


        JButton performanceButton =
                createMenuButton(
                        "Performance"
                );


        JButton reportsButton =
                createMenuButton(
                        "Reports"
                );

        reportsButton.setBackground(
                ORANGE
        );

        reportsButton.setForeground(
                Color.WHITE
        );


        // ==================================================
        // NAVIGATION ACTIONS
        // ==================================================

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


        revenueButton.addActionListener(
                e ->
                {
                    appFrame.showScreen(
                            "REVENUE"
                    );
                }
        );


        performanceButton.addActionListener(
                e ->
                {
                    appFrame.showScreen(
                            "FINANCIAL_ANALYSIS"
                    );
                }
        );


        reportsButton.addActionListener(
                e ->
                {
                    appFrame.showScreen(
                            "REPORT"
                    );
                }
        );


        sidebarTop.add(
                dashboardButton
        );

        sidebarTop.add(
                Box.createVerticalStrut(
                        8
                )
        );

        sidebarTop.add(
                budgetButton
        );

        sidebarTop.add(
                Box.createVerticalStrut(
                        8
                )
        );

        sidebarTop.add(
                revenueButton
        );

        sidebarTop.add(
                Box.createVerticalStrut(
                        8
                )
        );

        sidebarTop.add(
                performanceButton
        );

        sidebarTop.add(
                Box.createVerticalStrut(
                        8
                )
        );

        sidebarTop.add(
                reportsButton
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
                new JPanel(
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
        // HEADER
        // ==================================================

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setBackground(
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
                        "Monthly Financial Report"
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
                        "End-of-month summary of your restaurant's financial performance"
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
                Box.createVerticalStrut(
                        6
                )
        );

        titlePanel.add(
                subtitleLabel
        );


        // ==================================================
        // PERIOD
        // ==================================================

       
RoundedPanel periodPanel =
        new RoundedPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        10,
                        0
                ),
                Color.WHITE,
                40,
                true
        );

periodPanel.setBorder(
        new EmptyBorder(
                6,
                16,
                9,
                8
        )
);


// PERIOD LABEL

JLabel periodLabel =
        new JLabel("Period:");

periodLabel.setFont(
        new Font(
                "Segoe UI",
                Font.BOLD,
                13
        )
);

periodLabel.setForeground(
        new Color(60, 60, 60)
);


// MONTH DROPDOWN

String[] months =
{
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
};

JComboBox<String> monthBox =
        new JComboBox<>(months);

styleComboBox(
        monthBox,
        150
);


// YEAR DROPDOWN

Integer[] years =
{
        2026,
        2027
};

JComboBox<Integer> yearBox =
        new JComboBox<>(years);

styleComboBox(
        yearBox,
        100
);



        // ==================================================
        // PERIOD CHANGE
        // ==================================================

        java.awt.event.ActionListener periodListener =
        e ->
        {
            String selectedPeriod =
                    monthBox.getSelectedItem()
                            + " "
                            + yearBox.getSelectedItem();

            loadReportData(selectedPeriod);
        };

monthBox.addActionListener(periodListener);
yearBox.addActionListener(periodListener);


// ==================================================
// PERIOD PANEL
// ==================================================

periodPanel.add(periodLabel);
periodPanel.add(monthBox);
periodPanel.add(yearBox);


JPanel periodWrap =
        new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT,
                        0,
                        0
                )
        );

periodWrap.setBackground(LIGHT);

periodWrap.add(periodPanel);


headerPanel.add(
        titlePanel,
        BorderLayout.WEST
);

headerPanel.add(
        periodWrap,
        BorderLayout.EAST
);

mainPanel.add(
        headerPanel,
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
                Box.createVerticalStrut(
                        24
                )
        );


        // ==================================================
        // SUMMARY
        // ==================================================

        JPanel summaryPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                16,
                                0
                        )
                );

        summaryPanel.setBackground(
                LIGHT
        );

        summaryPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        115
                )
        );


        JPanel plannedCard =
                createSummaryCard(
                        "PLANNED BUDGET",
                        "0.00",
                        Color.BLACK
                );

        plannedBudgetValue =
                getCardValueLabel(
                        plannedCard
                );


        JPanel revenueCard =
                createSummaryCard(
                        "TOTAL REVENUE",
                        "0.00",
                        ORANGE
                );

        totalRevenueValue =
                getCardValueLabel(
                        revenueCard
                );


        JPanel expensesCard =
                createSummaryCard(
                        "TOTAL EXPENSES",
                        "0.00",
                        Color.BLACK
                );

        totalExpensesValue =
                getCardValueLabel(
                        expensesCard
                );


        JPanel profitCard =
                createSummaryCard(
                        "FINAL PROFIT",
                        "0.00",
                        ORANGE
                );

        finalProfitValue =
                getCardValueLabel(
                        profitCard
                );


        summaryPanel.add(
                plannedCard
        );

        summaryPanel.add(
                revenueCard
        );

        summaryPanel.add(
                expensesCard
        );

        summaryPanel.add(
                profitCard
        );


        centerPanel.add(
                summaryPanel
        );


        centerPanel.add(
                Box.createVerticalStrut(
                        20
                )
        );


        // ==================================================
        // LOWER CONTENT
        // ==================================================

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


        // ==================================================
        // FINANCIAL SUMMARY
        // ==================================================

        summaryDetailsPanel =
                new RoundedPanel(
                        new BorderLayout(),
                        Color.WHITE,
                        18,
                        true
                );

        summaryDetailsPanel.setLayout(
                new BoxLayout(
                        summaryDetailsPanel,
                        BoxLayout.Y_AXIS
                )
        );

        summaryDetailsPanel.setBorder(
                new EmptyBorder(
                        22,
                        24,
                        25,
                        24
                )
        );


        JLabel summaryTitle =
                new JLabel(
                        "Monthly Financial Summary"
                );

        summaryTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        summaryTitle.setForeground(
                Color.BLACK
        );


        summaryDetailsPanel.add(
                createSectionTitle(
                        summaryTitle
                )
        );


        summaryDetailsPanel.add(
                Box.createVerticalStrut(
                        20
                )
        );


        lowerPanel.add(
                summaryDetailsPanel
        );


        // ==================================================
        // BUDGET PERFORMANCE
        // ==================================================

        budgetPerformancePanel =
                new RoundedPanel(
                        new BorderLayout(),
                        Color.WHITE,
                        18,
                        true
                );

        budgetPerformancePanel.setLayout(
                new BoxLayout(
                        budgetPerformancePanel,
                        BoxLayout.Y_AXIS
                )
        );

        budgetPerformancePanel.setBorder(
                new EmptyBorder(
                        22,
                        24,
                        25,
                        24
                )
        );


        JLabel budgetTitle =
                new JLabel(
                        "Budget Performance"
                );

        budgetTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        budgetTitle.setForeground(
                Color.BLACK
        );


        budgetPerformancePanel.add(
                createSectionTitle(
                        budgetTitle
                )
        );


        budgetPerformancePanel.add(
                Box.createVerticalStrut(
                        20
                )
        );


        lowerPanel.add(
                budgetPerformancePanel
        );


        centerPanel.add(
                lowerPanel
        );


        centerPanel.add(
                Box.createVerticalStrut(
                        18
                )
        );


        // ==================================================
        // GENERATE REPORT
        // ==================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        buttonPanel.setBackground(
                LIGHT
        );


        JButton generateButton =
                new JButton(
                        "Generate Report"
                );

        generateButton.setUI(
                new RoundedButtonUI(
                        ORANGE,
                        ORANGE.darker(),
                        14
                )
        );

        generateButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        generateButton.setForeground(
                Color.WHITE
        );

        generateButton.setBackground(
                ORANGE
        );

        generateButton.setFocusPainted(
                false
        );

        generateButton.setBorderPainted(
                false
        );

        generateButton.setContentAreaFilled(
                false
        );

        generateButton.setOpaque(
                false
        );

        generateButton.setRolloverEnabled(
                true
        );

        generateButton.setBorder(
                new EmptyBorder(
                        12,
                        28,
                        12,
                        28
                )
        );

        generateButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        generateButton.addActionListener(
        e ->
        {
            try
            {
               String selectedPeriod =
        monthBox.getSelectedItem()
                + " "
                + yearBox.getSelectedItem();


                int managerId =
                        appFrame.getManagerId();

                int restaurantId = appFrame.getRestaurantId();

                File reportFile =
                        ReportService.generateMonthlyReport(
                                selectedPeriod,
                                managerId,
                                restaurantId
                        );

                // Open the generated report automatically
                if (Desktop.isDesktopSupported())
                {
                    Desktop.getDesktop().open(reportFile);
                }

                JOptionPane.showMessageDialog(
                        this,
                        "Monthly report generated successfully.\n\n"
                                + reportFile.getAbsolutePath(),
                        "Report",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
            catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(
                        this,
                        "Database error:\n"
                                + ex.getMessage(),
                        "Report Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            catch (IOException ex)
            {
                JOptionPane.showMessageDialog(
                        this,
                        "Could not create or open the report:\n"
                                + ex.getMessage(),
                        "Report Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
);

        buttonPanel.add(
                generateButton
        );


        centerPanel.add(
                buttonPanel
        );


        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );


        // ==================================================
        // ADD SIDEBAR + MAIN
        // ==================================================

        add(
                sidebar,
                BorderLayout.WEST
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );


        // ==================================================
        // LOAD INITIAL PERIOD
        // ==================================================

        loadReportData(
        monthBox.getSelectedItem()
                + " "
                + yearBox.getSelectedItem()
);
    }


    // ==================================================
    // LOAD REPORT DATA
    // ==================================================

    private void loadReportData(
            String selectedPeriod
    )
    {
        try
        {
            String[] parts =
                    selectedPeriod.split(" ");

            String monthName =
                    parts[0];

            int year =
                    Integer.parseInt(
                            parts[1]
                    );

            int month =
                    getMonthNumber(
                            monthName
                    );

            int managerId =
                    appFrame.getManagerId();
                
            int restaurantId =
                     appFrame.getRestaurantId();


            // ==============================================
            // GET FINANCIAL VALUES
            // ==============================================

            double plannedBudget =
                    FinancialAnalysisService
                            .getPlannedBudget(
                                    selectedPeriod,
                                    managerId,
                                    restaurantId
                                    
                            );

            double totalRevenue =
                    FinancialAnalysisService
                            .getTotalRevenue(
                                    month,
                                    year,
                                    restaurantId
                            );

            double totalExpenses =
                    FinancialAnalysisService
                            .getTotalExpenses(
                                    month,
                                    year,
                                    restaurantId
                            );

            double finalProfit =
                    FinancialAnalysisService
                            .getNetResult(
                                    month,
                                    year,
                                    restaurantId
                            );


            // ==============================================
            // UPDATE SUMMARY CARDS
            // ==============================================

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

            finalProfitValue.setText(
                    formatAmount(
                            finalProfit
                    )
            );


            // ==============================================
            // UPDATE FINANCIAL SUMMARY
            // ==============================================

            loadFinancialSummary(
                    plannedBudget,
                    totalExpenses,
                    totalRevenue,
                    finalProfit
            );


            // ==============================================
            // UPDATE BUDGET PERFORMANCE
            // ==============================================

            loadBudgetPerformance(
                    selectedPeriod,
                    managerId,
                    restaurantId,
                    month,
                    year
            );


            revalidate();
            repaint();
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Database error:\n"
                            + ex.getMessage(),
                    "Report Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Could not load report data:\n"
                            + ex.getMessage(),
                    "Report Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // ==================================================
    // FINANCIAL SUMMARY
    // ==================================================

    private void loadFinancialSummary(
            double plannedBudget,
            double totalExpenses,
            double totalRevenue,
            double finalProfit
    )
    {
        summaryDetailsPanel.removeAll();


        JLabel summaryTitle =
                new JLabel(
                        "Monthly Financial Summary"
                );

        summaryTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        summaryTitle.setForeground(
                Color.BLACK
        );


        summaryDetailsPanel.add(
                createSectionTitle(
                        summaryTitle
                )
        );


        summaryDetailsPanel.add(
                Box.createVerticalStrut(
                        20
                )
        );


        addResultRow(
                summaryDetailsPanel,
                "Planned Budget",
                formatAmount(
                        plannedBudget
                ),
                Color.BLACK
        );


        addResultRow(
                summaryDetailsPanel,
                "Actual Expenses",
                formatAmount(
                        totalExpenses
                ),
                Color.BLACK
        );


        addResultRow(
                summaryDetailsPanel,
                "Total Revenue",
                formatAmount(
                        totalRevenue
                ),
                ORANGE
        );


        addResultRow(
                summaryDetailsPanel,
                "Final Profit",
                formatAmount(
                        finalProfit
                ),
                ORANGE
        );


        summaryDetailsPanel.revalidate();
        summaryDetailsPanel.repaint();
    }


    // ==================================================
    // BUDGET PERFORMANCE
    // ==================================================

    private void loadBudgetPerformance(
            String selectedPeriod,
            int managerId,
            int restaurantId,
            int month,
            int year
    ) throws SQLException
    {
        budgetPerformancePanel.removeAll();


        JLabel budgetTitle =
                new JLabel(
                        "Budget Performance"
                );

        budgetTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        budgetTitle.setForeground(
                Color.BLACK
        );


        budgetPerformancePanel.add(
                createSectionTitle(
                        budgetTitle
                )
        );


        budgetPerformancePanel.add(
                Box.createVerticalStrut(
                        20
                )
        );


        List<BudgetPerformance> records =
                FinancialAnalysisService
                        .getBudgetPerformance(
                                selectedPeriod,
                                managerId,
                                restaurantId,
                                month,
                                year
                        );


        for (BudgetPerformance record :
                records)
        {
            double planned =
                    record.getPlannedAmount();

            double actual =
                    record.getActualAmount();


            int percentage = 0;

            if (planned > 0)
            {
                percentage =
                        (int)
                        Math.round(
                                (actual / planned)
                                        * 100
                        );
            }


            if (percentage > 100)
            {
                percentage = 100;
            }


            if (percentage < 0)
            {
                percentage = 0;
            }


            String amount =
                    formatAmount(
                            actual
                    )
                    + " / "
                    + formatAmount(
                            planned
                    );


            addPerformanceRow(
                    budgetPerformancePanel,
                    record.getCategory(),
                    amount,
                    percentage
            );
        }


        if (records.isEmpty())
        {
            JLabel noDataLabel =
                    new JLabel(
                            "No budget performance data available."
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


            budgetPerformancePanel.add(
                    noDataLabel
            );
        }


        budgetPerformancePanel.revalidate();
        budgetPerformancePanel.repaint();
    }


    // ==================================================
    // FORMAT AMOUNT
    // ==================================================

    private String formatAmount(
            double amount
    )
    {
        return String.format(
                "$%,.2f",
                amount
        );
    }


    // ==================================================
    // MONTH NUMBER
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
                return 0;
        }
    }


    // ==================================================
    // GET VALUE LABEL FROM SUMMARY CARD
    // ==================================================

    private JLabel getCardValueLabel(
            JPanel card
    )
    {
        Component[] components =
                card.getComponents();


        for (Component component :
                components)
        {
            if (component instanceof JLabel)
            {
                JLabel label =
                        (JLabel) component;

                if (label.getFont().getSize() == 21)
                {
                    return label;
                }
            }
        }


        return null;
    }


    // ==================================================
    // SECTION TITLE (orange marker + title)
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
    // COMBO BOX STYLE
    // ==================================================

    private void styleComboBox(
            JComboBox<?> box,
            int width
    )
    {
        box.setUI(
                new ModernComboUI(
                        ORANGE
                )
        );

        box.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        box.setBackground(
                Color.WHITE
        );

        box.setForeground(
                Color.BLACK
        );

        box.setBorder(
                new RoundedFieldBorder(
                        ORANGE,
                        new Color(
                                210,
                                210,
                                210
                        )
                )
        );

        box.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        box.setRenderer(
                new DefaultListCellRenderer()
                {
                    @Override
                    public Component getListCellRendererComponent(
                            JList<?> list,
                            Object value,
                            int index,
                            boolean isSelected,
                            boolean cellHasFocus
                    )
                    {
                        super.getListCellRendererComponent(
                                list,
                                value,
                                index,
                                isSelected,
                                cellHasFocus
                        );

                        setBorder(
                                new EmptyBorder(
                                        8,
                                        12,
                                        8,
                                        12
                                )
                        );

                        setFont(
                                new Font(
                                        "Segoe UI",
                                        Font.PLAIN,
                                        13
                                )
                        );

                        if (index == -1)
                        {
                            setBackground(Color.WHITE);
                            setForeground(Color.BLACK);
                        }
                        else if (isSelected)
                        {
                            setBackground(
                                    new Color(255, 240, 224)
                            );
                            setForeground(Color.BLACK);
                        }
                        else
                        {
                            setBackground(Color.WHITE);
                            setForeground(
                                    new Color(60, 60, 60)
                            );
                        }

                        return this;
                    }
                }
        );

        box.addFocusListener(
                new FocusAdapter()
                {
                    @Override
                    public void focusGained(
                            FocusEvent e
                    )
                    {
                        box.repaint();
                    }

                    @Override
                    public void focusLost(
                            FocusEvent e
                    )
                    {
                        box.repaint();
                    }
                }
        );

        box.setPreferredSize(
                new Dimension(
                        width,
                        40
                )
        );
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

                                        if (!getBackground().equals(ORANGE))
                                        {
                                            setForeground(Color.WHITE);
                                        }

                                        repaint();
                                    }

                                    @Override
                                    public void mouseExited(
                                            MouseEvent e
                                    )
                                    {
                                        hover = false;

                                        if (!getBackground().equals(ORANGE))
                                        {
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

                        Color fill = getBackground();

                        if (hover && !fill.equals(ORANGE))
                        {
                            fill = new Color(38, 38, 38);
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
    // SUMMARY CARD
    // ==================================================

    private JPanel createSummaryCard(
            String title,
            String value,
            Color valueColor
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
                        10
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

        // size 21 is kept on purpose: getCardValueLabel() finds
        // this label by its font size
        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        21
                )
        );

        valueLabel.setForeground(
                valueColor
        );

        valueLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        card.add(
                accentBar
        );

        card.add(
                Box.createVerticalStrut(
                        12
                )
        );

        card.add(
                titleLabel
        );

        card.add(
                Box.createVerticalStrut(
                        7
                )
        );

        card.add(
                valueLabel
        );


        return card;
    }


    // ==================================================
    // RESULT ROW
    // ==================================================

    private void addResultRow(
            JPanel parent,
            String title,
            String value,
            Color valueColor
    )
    {
        JPanel row =
                new JPanel(
                        new BorderLayout()
                );

        row.setOpaque(
                false
        );

        row.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(
                                0,
                                0,
                                1,
                                0,
                                new Color(
                                        238,
                                        238,
                                        238
                                )
                        ),
                        new EmptyBorder(
                                13,
                                2,
                                13,
                                2
                        )
                )
        );

        row.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        row.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        50
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
                        80,
                        80,
                        80
                )
        );


        JLabel valueLabel =
                new JLabel(
                        value
                );

        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        valueLabel.setForeground(
                valueColor
        );


        row.add(
                titleLabel,
                BorderLayout.WEST
        );

        row.add(
                valueLabel,
                BorderLayout.EAST
        );


        parent.add(
                row
        );
    }


    // ==================================================
    // PERFORMANCE ROW
    // ==================================================

    private void addPerformanceRow(
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
                )
        );


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
                Box.createVerticalStrut(
                        8
                )
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
                Box.createVerticalStrut(
                        15
                )
        );
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


    // ==================================================
    // ROUNDED FIELD BORDER (period box)
    // ==================================================

    private static class RoundedFieldBorder extends AbstractBorder
    {
        private final Color focusColor;
        private final Color normalColor;

        RoundedFieldBorder(
                Color focusColor,
                Color normalColor
        )
        {
            this.focusColor = focusColor;
            this.normalColor = normalColor;
        }

        @Override
        public void paintBorder(
                Component c,
                Graphics g,
                int x,
                int y,
                int width,
                int height
        )
        {
            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            boolean focused =
                    c.hasFocus();

            g2.setColor(
                    focused
                            ? focusColor
                            : normalColor
            );

            g2.setStroke(
                    new BasicStroke(
                            focused ? 2f : 1f
                    )
            );

            g2.drawRoundRect(
                    x + 1,
                    y + 1,
                    width - 3,
                    height - 3,
                    14,
                    14
            );

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(
                Component c
        )
        {
            return new Insets(2, 4, 2, 4);
        }

        @Override
        public Insets getBorderInsets(
                Component c,
                Insets insets
        )
        {
            insets.set(2, 4, 2, 4);

            return insets;
        }
    }


    // ==================================================
    // MODERN COMBO BOX UI (flat, orange chevron)
    // ==================================================

    private static class ModernComboUI extends BasicComboBoxUI
    {
        private final Color accent;

        ModernComboUI(Color accent)
        {
            this.accent = accent;
        }

        @Override
        protected JButton createArrowButton()
        {
            JButton button =
                    new JButton()
                    {
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

            button.setBorder(
                    BorderFactory.createEmptyBorder()
            );

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
        )
        {
            g.setColor(Color.WHITE);

            g.fillRect(
                    bounds.x,
                    bounds.y,
                    bounds.width,
                    bounds.height
            );
        }
    }


    // ==================================================
    // ROUNDED PROGRESS BAR UI
    // ==================================================

    private static class RoundedProgressUI extends BasicProgressBarUI
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

            int w = progressBar.getWidth();
            int h = progressBar.getHeight();

            // track
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

            // fill
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
                        Math.max(fillWidth, h),
                        h,
                        h,
                        h
                );
            }

            g2.dispose();
        }
    }


    // ==================================================
    // ROUNDED BUTTON UI
    // ==================================================

    private static class RoundedButtonUI extends BasicButtonUI
    {
        private final Color base;
        private final Color hover;
        private final int radius;

        RoundedButtonUI(
                Color base,
                Color hover,
                int radius
        )
        {
            this.base = base;
            this.hover = hover;
            this.radius = radius;
        }

        @Override
        public void paint(
                Graphics g,
                JComponent c
        )
        {
            AbstractButton button =
                    (AbstractButton) c;

            ButtonModel model =
                    button.getModel();

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            Color fill = base;

            if (model.isPressed())
            {
                fill = hover.darker();
            }
            else if (model.isRollover())
            {
                fill = hover;
            }

            g2.setColor(fill);

            g2.fillRoundRect(
                    0,
                    0,
                    c.getWidth(),
                    c.getHeight(),
                    radius,
                    radius
            );

            g2.dispose();

            super.paint(g, c);
        }
    }
}