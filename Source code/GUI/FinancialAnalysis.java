package GUI;

import FINANCIAL_ANALYSIS.FinancialAnalysisService;
import FINANCIAL_ANALYSIS.FinancialAnalysisService.BudgetPerformance;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicProgressBarUI;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionListener;


public class FinancialAnalysis extends JPanel
{
    private AppFrame appFrame;


    private final Color ORANGE =
            new Color(245, 140, 30);

    private final Color DARK =
            new Color(15, 15, 15);

    private final Color LIGHT =
            new Color(246, 246, 246);


    // ==================================================
    // SUMMARY VALUE LABELS
    // ==================================================

    private JLabel plannedBudgetValueLabel;
    private JLabel revenueValueLabel;
    private JLabel expensesValueLabel;
    private JLabel profitValueLabel;
    private JLabel statusValueLabel;


    // ==================================================
    // FINANCIAL RESULT VALUE LABELS
    // ==================================================

    private JLabel resultRevenueValueLabel;
    private JLabel resultExpensesValueLabel;
    private JLabel resultProfitValueLabel;
    private JLabel resultBudgetRemainingValueLabel;


    // ==================================================
    // BUDGET PERFORMANCE PANEL
    // ==================================================

    private JPanel budgetPerformancePanel;


    public FinancialAnalysis(
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

        performanceButton.setBackground(
                ORANGE
        );

        performanceButton.setForeground(
                Color.WHITE
        );


        JButton reportsButton =
                createMenuButton(
                        "Reports"
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
                        "Financial Performance"
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
                        "Analyze your restaurant's financial performance"
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

        headerPanel.add(
        titlePanel,
        BorderLayout.WEST
);



mainPanel.add(
        headerPanel,
        BorderLayout.NORTH
);

    
        // ==================================================
        // PERIOD
        // ==================================================

   

JPanel periodPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
periodPanel.setOpaque(false);

JLabel monthLabel = new JLabel("Month:");
JComboBox<String> monthCombo = new JComboBox<>(new String[]{
    "January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
});

JLabel yearLabel = new JLabel("Year:");
JComboBox<String> yearCombo = new JComboBox<>(new String[]{
    "2026", "2027", "2028", "2029", "2030"
});

headerPanel.add(titlePanel, BorderLayout.WEST);
headerPanel.add(periodPanel, BorderLayout.EAST);
mainPanel.add(headerPanel, BorderLayout.NORTH);

periodPanel.add(monthLabel);
periodPanel.add(monthCombo);
periodPanel.add(yearLabel);
periodPanel.add(yearCombo);

styleComboBox(monthCombo, 125);
styleComboBox(yearCombo, 85);


        // ==================================================
        // CENTER AREA
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
                                5,
                                14,
                                0
                        )
                );

        summaryPanel.setBackground(
                LIGHT
        );

        summaryPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        120
                )
        );


        JPanel plannedBudgetCard =
                createSummaryCard(
                        "PLANNED BUDGET",
                        "0.00",
                        Color.BLACK,
                        1
                );


        JPanel revenueCard =
                createSummaryCard(
                        "TOTAL REVENUE",
                        "0.00",
                        ORANGE,
                        2
                );


        JPanel expensesCard =
                createSummaryCard(
                        "TOTAL EXPENSES",
                        "0.00",
                        Color.BLACK,
                        3
                );


        JPanel profitCard =
                createSummaryCard(
                        "PROFIT",
                        "0.00",
                        ORANGE,
                        4
                );


        JPanel statusCard =
                createSummaryCard(
                        "STATUS",
                        "NO DATA",
                        ORANGE,
                        5
                );


        summaryPanel.add(
                plannedBudgetCard
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

        summaryPanel.add(
                statusCard
        );


        centerPanel.add(
                summaryPanel
        );


        centerPanel.add(
                Box.createVerticalStrut(20)
        );


        // ==================================================
        // LOWER AREA
        // ==================================================

        JPanel lowerPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                20,
                                0
                        )
                );

        lowerPanel.setBackground(
                LIGHT
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


        JLabel budgetPerformanceTitle =
                new JLabel(
                        "Budget Performance"
                );

        budgetPerformanceTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        budgetPerformanceTitle.setForeground(
                Color.BLACK
        );


        JLabel budgetPerformanceSubtitle =
                new JLabel(
                        "Actual spending compared with planned budget"
                );

        budgetPerformanceSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        budgetPerformanceSubtitle.setForeground(
                new Color(
                        110,
                        110,
                        110
                )
        );

        budgetPerformanceSubtitle.setBorder(
                new EmptyBorder(
                        0,
                        13,
                        0,
                        0
                )
        );

        budgetPerformanceSubtitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        budgetPerformancePanel.add(
                createSectionTitle(
                        budgetPerformanceTitle
                )
        );

        budgetPerformancePanel.add(
                Box.createVerticalStrut(4)
        );

        budgetPerformancePanel.add(
                budgetPerformanceSubtitle
        );

        budgetPerformancePanel.add(
                Box.createVerticalStrut(20)
        );


        // Budget performance rows are loaded dynamically
        // when a financial period is selected.


        // ==================================================
        // FINANCIAL RESULTS
        // ==================================================

        RoundedPanel resultsPanel =
                new RoundedPanel(
                        new BorderLayout(),
                        Color.WHITE,
                        18,
                        true
                );

        resultsPanel.setLayout(
                new BoxLayout(
                        resultsPanel,
                        BoxLayout.Y_AXIS
                )
        );

        resultsPanel.setBorder(
                new EmptyBorder(
                        22,
                        24,
                        25,
                        24
                )
        );


        JLabel resultsTitle =
                new JLabel(
                        "Financial Results"
                );

        resultsTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        resultsTitle.setForeground(
                Color.BLACK
        );


        JLabel resultsSubtitle =
                new JLabel(
                        "Summary of financial activity"
                );

        resultsSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        resultsSubtitle.setForeground(
                new Color(
                        110,
                        110,
                        110
                )
        );

        resultsSubtitle.setBorder(
                new EmptyBorder(
                        0,
                        13,
                        0,
                        0
                )
        );

        resultsSubtitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        resultsPanel.add(
                createSectionTitle(
                        resultsTitle
                )
        );

        resultsPanel.add(
                Box.createVerticalStrut(4)
        );

        resultsPanel.add(
                resultsSubtitle
        );

        resultsPanel.add(
                Box.createVerticalStrut(20)
        );


        // Create the result rows immediately.
        // Their values are updated when the selected period changes.

        addResultRow(
                resultsPanel,
                "Revenue",
                "0.00",
                ORANGE,
                1
        );

        addResultRow(
                resultsPanel,
                "Expenses",
                "0.00",
                Color.BLACK,
                2
        );

        addResultRow(
                resultsPanel,
                "Profit",
                "0.00",
                ORANGE,
                3
        );

        addResultRow(
                resultsPanel,
                "Budget Remaining",
                "0.00",
                Color.BLACK,
                4
        );


        lowerPanel.add(
                budgetPerformancePanel
        );

        lowerPanel.add(
                resultsPanel
        );


        centerPanel.add(
                lowerPanel
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
        // PERIOD ACTION
        // ==================================================

       ActionListener periodListener = e -> {
    String selectedPeriod =
            monthCombo.getSelectedItem() + " "
            + yearCombo.getSelectedItem();

    appFrame.setSelectedFinancialPeriod(selectedPeriod);
    loadFinancialData(selectedPeriod);
};

monthCombo.addActionListener(periodListener);
yearCombo.addActionListener(periodListener);

 loadFinancialData(
        monthCombo.getSelectedItem() + " "
        + yearCombo.getSelectedItem()
);
    }
/* This connects to the backend, it loads the total for all financial figures. */
// ==================================================
// LOAD FINANCIAL DATA
// ==================================================

private void loadFinancialData(
        String selectedPeriod
)
{
    try
    {
        String[] parts = selectedPeriod.split(" ");

        String monthName = parts[0];
        int year = Integer.parseInt(parts[1]);
        int month = getMonthNumber(monthName);

        int managerId = appFrame.getManagerId();

        // Get the restaurant belonging to the logged-in Manager.
        int restaurantId = appFrame.getRestaurantId();

        // ------------------------------------------
        // PLANNED BUDGET
        // ------------------------------------------

        double plannedBudget =
                FinancialAnalysisService.getPlannedBudget(
                        selectedPeriod,
                        managerId,
                        restaurantId
                );

        // ------------------------------------------
        // TOTAL REVENUE
        // ------------------------------------------

        double totalRevenue =
                FinancialAnalysisService.getTotalRevenue(
                        month,
                        year,
                        restaurantId
                );

        // ------------------------------------------
        // TOTAL EXPENSES
        // ------------------------------------------

        double totalExpenses =
                FinancialAnalysisService.getTotalExpenses(
                        month,
                        year,
                        restaurantId
                );

        // ------------------------------------------
        // PROFIT / LOSS
        // ------------------------------------------

        double netResult =
                FinancialAnalysisService.getNetResult(
                        month,
                        year,
                        restaurantId
                );

        // ------------------------------------------
        // FINANCIAL STATUS
        // ------------------------------------------

        String status =
                FinancialAnalysisService.getFinancialStatus(
                        month,
                        year,
                        restaurantId
                );

        // ------------------------------------------
        // BUDGET REMAINING
        // ------------------------------------------

        double budgetRemaining =
                FinancialAnalysisService.getBudgetRemaining(
                        selectedPeriod,
                        managerId,
                        month,
                        year,
                        restaurantId
                );

        // ------------------------------------------
        // UPDATE SUMMARY CARDS
        // ------------------------------------------

        plannedBudgetValueLabel.setText(
                formatAmount(plannedBudget)
        );

        revenueValueLabel.setText(
                formatAmount(totalRevenue)
        );

        expensesValueLabel.setText(
                formatAmount(totalExpenses)
        );

        profitValueLabel.setText(
                formatAmount(netResult)
        );

        statusValueLabel.setText(status);

        // ------------------------------------------
        // UPDATE FINANCIAL RESULTS
        // ------------------------------------------

        resultRevenueValueLabel.setText(
                formatAmount(totalRevenue)
        );

        resultExpensesValueLabel.setText(
                formatAmount(totalExpenses)
        );

        resultProfitValueLabel.setText(
                formatAmount(netResult)
        );

        resultBudgetRemainingValueLabel.setText(
                formatAmount(budgetRemaining)
        );

        // ------------------------------------------
        // UPDATE BUDGET PERFORMANCE
        // ------------------------------------------

        loadBudgetPerformance(
                selectedPeriod,
                managerId,
                restaurantId,
                month,
                year
        );
    }
    catch (
            SQLException |
            NumberFormatException exception
    )
    {
        JOptionPane.showMessageDialog(
                this,
                "Unable to load financial data.",
                "Database Error",
                JOptionPane.ERROR_MESSAGE
        );

        exception.printStackTrace();
    }
}

/* Loads budget performance, comparing actual spending with planned amounts. */
// ==================================================
// LOAD BUDGET PERFORMANCE
// ==================================================

private void loadBudgetPerformance(
        String budgetPeriod,
        int managerId,
        int restaurantId,
        int month,
        int year
) throws SQLException
{
    List<BudgetPerformance> records =
            FinancialAnalysisService.getBudgetPerformance(
                    budgetPeriod,
                    managerId,
                    restaurantId,
                    month,
                    year
            );

    while (budgetPerformancePanel.getComponentCount() > 4)
    {
        budgetPerformancePanel.remove(4);
    }

    if (records.isEmpty())
    {
        JLabel noDataLabel =
                new JLabel(
                        "No budget data available for this period."
                );

        noDataLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        noDataLabel.setForeground(
                new Color(110, 110, 110)
        );

        noDataLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        budgetPerformancePanel.add(noDataLabel);
    }
    else
    {
        for (BudgetPerformance record : records)
        {
            double planned = record.getPlannedAmount();
            double actual = record.getActualAmount();

            int percentage = 0;

            if (planned > 0)
            {
                percentage =
                        (int) Math.round(
                                (actual / planned) * 100
                        );
            }

            percentage = Math.min(percentage, 100);

            String amount =
                    formatAmount(actual)
                    + " / "
                    + formatAmount(planned);

            addPerformanceRow(
                    budgetPerformancePanel,
                    record.getCategory(),
                    amount,
                    percentage
            );
        }
    }

    budgetPerformancePanel.revalidate();
    budgetPerformancePanel.repaint();
}
    // ==================================================
    // GET MONTH NUMBER
    // ==================================================

    private int getMonthNumber(
            String monthName
    )
    {
        switch (
                monthName
        )
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
        return String.format(
                "%,.2f",
                amount
        );
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
                                    new Color(
                                            255,
                                            240,
                                            224
                                    )
                            );

                            setForeground(Color.BLACK);
                        }
                        else
                        {
                            setBackground(Color.WHITE);

                            setForeground(
                                    new Color(
                                            60,
                                            60,
                                            60
                                    )
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

                        Color fill = getBackground();

                        if (hover && !fill.equals(ORANGE))
                        {
                            fill = new Color(
                                    38,
                                    38,
                                    38
                            );
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
            Color valueColor,
            int cardNumber
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
                        18,
                        21,
                        16
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


        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        cardNumber == 5
                                ? 16
                                : 22
                )
        );

        valueLabel.setForeground(
                valueColor
        );

        valueLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        if (cardNumber == 1)
        {
            plannedBudgetValueLabel =
                    valueLabel;
        }
        else if (cardNumber == 2)
        {
            revenueValueLabel =
                    valueLabel;
        }
        else if (cardNumber == 3)
        {
            expensesValueLabel =
                    valueLabel;
        }
        else if (cardNumber == 4)
        {
            profitValueLabel =
                    valueLabel;
        }
        else if (cardNumber == 5)
        {
            statusValueLabel =
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
                Box.createVerticalStrut(7)
        );

        card.add(
                valueLabel
        );


        return card;
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
    // FINANCIAL RESULT ROW
    // ==================================================

    private void addResultRow(
            JPanel parent,
            String title,
            String value,
            Color valueColor,
            int rowNumber
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


        if (rowNumber == 1)
        {
            resultRevenueValueLabel =
                    valueLabel;
        }
        else if (rowNumber == 2)
        {
            resultExpensesValueLabel =
                    valueLabel;
        }
        else if (rowNumber == 3)
        {
            resultProfitValueLabel =
                    valueLabel;
        }
        else if (rowNumber == 4)
        {
            resultBudgetRemainingValueLabel =
                    valueLabel;
        }


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
    // ROUNDED FIELD BORDER (period box)
    // ==================================================

    private static class RoundedFieldBorder
            extends AbstractBorder
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
            return new Insets(
                    2,
                    4,
                    2,
                    4
            );
        }


        @Override
        public Insets getBorderInsets(
                Component c,
                Insets insets
        )
        {
            insets.set(
                    2,
                    4,
                    2,
                    4
            );

            return insets;
        }
    }


    // ==================================================
    // MODERN COMBO BOX UI (flat, orange chevron)
    // ==================================================

    private static class ModernComboUI
            extends BasicComboBoxUI
    {
        private final Color arrowColor;


        ModernComboUI(
                Color arrowColor
        )
        {
            this.arrowColor = arrowColor;
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

                            g2.setColor(
                                    arrowColor
                            );

                            int centerX =
                                    getWidth() / 2;

                            int centerY =
                                    getHeight() / 2;

                            int[] xPoints =
                                    {
                                            centerX - 5,
                                            centerX,
                                            centerX + 5
                                    };

                            int[] yPoints =
                                    {
                                            centerY - 2,
                                            centerY + 3,
                                            centerY - 2
                                    };

                            g2.fillPolygon(
                                    xPoints,
                                    yPoints,
                                    3
                            );

                            g2.dispose();
                        }
                    };

            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setOpaque(false);

            return button;
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

            int width =
                    progressBar.getWidth();

            int height =
                    progressBar.getHeight();

            int value =
                    progressBar.getValue();

            int max =
                    progressBar.getMaximum();

            double percentage =
                    max == 0
                            ? 0
                            : (double) value / max;

            int progressWidth =
                    (int) (
                            width * percentage
                    );


            g2.setColor(
                    new Color(
                            235,
                            235,
                            235
                    )
            );

            g2.fillRoundRect(
                    0,
                    0,
                    width,
                    height,
                    height,
                    height
            );


            g2.setColor(
                    progressBar.getForeground()
            );

            if (progressWidth > 0)
            {
                g2.fillRoundRect(
                        0,
                        0,
                        progressWidth,
                        height,
                        height,
                        height
                );
            }

            g2.dispose();
        }
    }
}

