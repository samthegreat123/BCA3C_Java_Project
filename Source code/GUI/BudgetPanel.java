package GUI;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicComboBoxUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import BUDGET.BudgetService;

public class BudgetPanel extends JPanel
{
    private AppFrame appFrame;

    private final Color ORANGE =
            new Color(245, 140, 30);

    private final Color DARK =
            new Color(15, 15, 15);

    private final Color LIGHT =
            new Color(246, 246, 246);

    private JLabel totalAmount;

    private JPanel categoryPanel;

    private List<JTextField> categoryFields =
            new ArrayList<>();

    private List<JTextField> amountFields =
            new ArrayList<>();

    public BudgetPanel(
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

        JButton budgetButton =
                createMenuButton(
                        "Budget"
                );

        budgetButton.setBackground(
                ORANGE
        );

        budgetButton.setForeground(
                Color.WHITE
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
                logoutButton
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
        // HEADER
        // ==================================================

        JPanel headerPanel =
                new JPanel();

        headerPanel.setLayout(
                new BoxLayout(
                        headerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        headerPanel.setBackground(
                LIGHT
        );

        JLabel titleLabel =
                new JLabel(
                        "Budget Management"
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
                        "Create and manage your restaurant budget"
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

        headerPanel.add(
                titleLabel
        );

        headerPanel.add(
                Box.createVerticalStrut(6)
        );

        headerPanel.add(
                subtitleLabel
        );

        // profile chip: orange avatar + "Manager"
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

        JPanel topRow =
                new JPanel(
                        new BorderLayout()
                );

        topRow.setBackground(
                LIGHT
        );

        topRow.setBorder(
                new EmptyBorder(
                        0,
                        0,
                        22,
                        0
                )
        );

        topRow.add(
                headerPanel,
                BorderLayout.WEST
        );

        topRow.add(
                chipWrap,
                BorderLayout.EAST
        );

        mainPanel.add(
                topRow,
                BorderLayout.NORTH
        );

        // ==================================================
        // FORM
        // ==================================================

        RoundedPanel formPanel =
                new RoundedPanel(
                        new BorderLayout(),
                        Color.WHITE,
                        18,
                        true
                );

        formPanel.setLayout(
                new BoxLayout(
                        formPanel,
                        BoxLayout.Y_AXIS
                )
        );

        formPanel.setBorder(
                new EmptyBorder(
                        24,
                        28,
                        28,
                        28
                )
        );

        // ==================================================
        // BUDGET PERIOD
        // ==================================================

        JLabel periodLabel =
                new JLabel(
                        "Budget Period"
                );

        periodLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        periodLabel.setForeground(
                new Color(
                        60,
                        60,
                        60
                )
        );

        periodLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JComboBox<String> periodBox =
                new JComboBox<>(
                        new String[]
                        {
                                "January 2026",
                                "February 2026",
                                "March 2026",
                                "April 2026",
                                "May 2026",
                                "June 2026",
                                "July 2026",
                                "August 2026",
                                "September 2026",
                                "October 2026",
                                "November 2026",
                                "December 2026"
                        }
                );

        stylePeriodBox(
                periodBox
        );

        formPanel.add(
                periodLabel
        );

        formPanel.add(
                Box.createVerticalStrut(8)
        );

        formPanel.add(
                periodBox
        );

        formPanel.add(
                Box.createVerticalStrut(24)
        );

        // ==================================================
        // CATEGORY TITLE
        // ==================================================

        JPanel titleBar =
                new JPanel();

        titleBar.setBackground(
                ORANGE
        );

        titleBar.setPreferredSize(
                new Dimension(
                        4,
                        16
                )
        );

        JLabel categoryTitle =
                new JLabel(
                        "Budget Categories"
                );

        categoryTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        categoryTitle.setForeground(
                Color.BLACK
        );

        categoryTitle.setBorder(
                new EmptyBorder(
                        0,
                        9,
                        0,
                        0
                )
        );

        JPanel categoryTitleRow =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                0,
                                0
                        )
                );

        categoryTitleRow.setOpaque(
                false
        );

        categoryTitleRow.add(
                titleBar
        );

        categoryTitleRow.add(
                categoryTitle
        );

        categoryTitleRow.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        categoryTitleRow.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        24
                )
        );

        formPanel.add(
                categoryTitleRow
        );

        formPanel.add(
                Box.createVerticalStrut(14)
        );

        // ==================================================
        // CATEGORY PANEL
        // ==================================================

        categoryPanel =
                new JPanel();

        categoryPanel.setLayout(
                new BoxLayout(
                        categoryPanel,
                        BoxLayout.Y_AXIS
                )
        );

        categoryPanel.setOpaque(
                false
        );

        categoryPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        addBudgetRow();
        addBudgetRow();
        addBudgetRow();
        addBudgetRow();

        formPanel.add(
                categoryPanel
        );

        // ==================================================
        // ADD CATEGORY BUTTON
        // ==================================================

        JButton addCategoryButton =
                new JButton(
                        "+ Add Category"
                );

        styleOutlineButton(
                addCategoryButton
        );

        JPanel addCategoryPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                0,
                                0
                        )
                );

        addCategoryPanel.setOpaque(
                false
        );

        addCategoryPanel.setBorder(
                new EmptyBorder(
                        4,
                        0,
                        0,
                        0
                )
        );

        addCategoryPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        addCategoryPanel.add(
                addCategoryButton
        );

        formPanel.add(
                addCategoryPanel
        );

        addCategoryButton.addActionListener(
                e ->
                {
                    addBudgetRow();

                    categoryPanel.revalidate();

                    categoryPanel.repaint();
                }
        );

        formPanel.add(
                Box.createVerticalStrut(22)
        );

        // ==================================================
        // TOTAL
        // ==================================================

        RoundedPanel totalPanel =
                new RoundedPanel(
                        new BorderLayout(),
                        new Color(
                                250,
                                245,
                                238
                        ),
                        14,
                        false
                );

        totalPanel.setOutline(
                new Color(
                        ORANGE.getRed(),
                        ORANGE.getGreen(),
                        ORANGE.getBlue(),
                        90
                )
        );

        totalPanel.setBorder(
                new EmptyBorder(
                        14,
                        18,
                        14,
                        18
                )
        );

        totalPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        totalPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        64
                )
        );

        JLabel totalLabel =
                new JLabel(
                        "Total Planned Budget"
                );

        totalLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        totalLabel.setForeground(
                Color.BLACK
        );

        totalAmount =
                new JLabel(
                        "0"
                );

        totalAmount.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        totalAmount.setForeground(
                ORANGE
        );

        totalPanel.add(
                totalLabel,
                BorderLayout.WEST
        );

        totalPanel.add(
                totalAmount,
                BorderLayout.EAST
        );

        formPanel.add(
                totalPanel
        );

        formPanel.add(
                Box.createVerticalStrut(20)
        );

        // ==================================================
        // BUTTONS
        // ==================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );

        buttonPanel.setOpaque(
                false
        );

        buttonPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JButton cancelButton =
                new JButton(
                        "Cancel"
                );

        styleOutlineButton(
                cancelButton
        );

        JButton saveButton =
                new JButton(
                        "SAVE BUDGET"
                );

        saveButton.setUI(
                new RoundedButtonUI(
                        ORANGE,
                        null,
                        ORANGE.darker(),
                        12
                )
        );

        saveButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        saveButton.setForeground(
                Color.WHITE
        );

        saveButton.setBackground(
                ORANGE
        );

        saveButton.setFocusPainted(
                false
        );

        saveButton.setBorderPainted(
                false
        );

        saveButton.setContentAreaFilled(
                false
        );

        saveButton.setOpaque(
                false
        );

        saveButton.setRolloverEnabled(
                true
        );

        saveButton.setBorder(
                BorderFactory.createEmptyBorder(
                        11,
                        24,
                        11,
                        24
                )
        );

        saveButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        buttonPanel.add(
                cancelButton
        );

        buttonPanel.add(
                saveButton
        );

        formPanel.add(
                buttonPanel
        );

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // ==================================================
        // NAVIGATION
        // ==================================================

        dashboardButton.addActionListener(
                e ->
                {
                    appFrame.showScreen(
                            "MANAGER_DASHBOARD"
                    );
                }
        );

        cancelButton.addActionListener(
                e ->
                {
                    appFrame.showScreen(
                            "MANAGER_DASHBOARD"
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

        logoutButton.addActionListener(
                e ->
                {
                    appFrame.showScreen(
                            "LOGIN"
                    );
                }
        );

        // ==================================================
        // VIEW EXISTING BUDGET
        // ==================================================

        periodBox.addActionListener(
                e ->
                {
                    loadExistingBudget(
                            periodBox
                    );
                }
        );

        // ==================================================
        // SAVE BUDGET
        // ==================================================

        saveButton.addActionListener(
                e ->
                {
                    saveBudget(
                            periodBox
                    );
                }
        );

        // ==================================================
        // ADD TO SCREEN
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
    // STYLE PERIOD BOX
    // ==================================================

    private void stylePeriodBox(
            JComboBox<String> periodBox
    )
    {
        periodBox.setUI(
                new ModernComboUI(
                        ORANGE
                )
        );

        periodBox.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        periodBox.setBackground(
                Color.WHITE
        );

        periodBox.setForeground(
                Color.BLACK
        );

        periodBox.setBorder(
                new RoundedFieldBorder(
                        ORANGE,
                        new Color(
                                210,
                                210,
                                210
                        ),
                        2,
                        4
                )
        );

        periodBox.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        periodBox.setRenderer(
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

        periodBox.addFocusListener(
                new FocusAdapter()
                {
                    @Override
                    public void focusGained(
                            FocusEvent e
                    )
                    {
                        periodBox.repaint();
                    }

                    @Override
                    public void focusLost(
                            FocusEvent e
                    )
                    {
                        periodBox.repaint();
                    }
                }
        );

        periodBox.setPreferredSize(
                new Dimension(
                        100,
                        44
                )
        );

        periodBox.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        44
                )
        );

        periodBox.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );
    }

    // ==================================================
    // OUTLINE BUTTON (Cancel / Add Category)
    // ==================================================

    private void styleOutlineButton(
            JButton button
    )
    {
        button.setUI(
                new RoundedButtonUI(
                        Color.WHITE,
                        ORANGE,
                        new Color(
                                ORANGE.getRed(),
                                ORANGE.getGreen(),
                                ORANGE.getBlue(),
                                28
                        ),
                        12
                )
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        button.setForeground(
                ORANGE
        );

        button.setBackground(
                Color.WHITE
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

        button.setRolloverEnabled(
                true
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        20,
                        10,
                        20
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );
    }

    // ==================================================
    // ADD BUDGET ROW
    // ==================================================

    private void addBudgetRow()
    {
        int number =
                categoryFields.size() + 1;

        RowPanel row =
                new RowPanel(
                        new BorderLayout(
                                12,
                                0
                        )
                );

        row.setBorder(
                new EmptyBorder(
                        0,
                        12,
                        0,
                        4
                )
        );

        row.setPreferredSize(
                new Dimension(
                        100,
                        52
                )
        );

        row.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        52
                )
        );

        row.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // numbered badge

        JLabel badge =
                new JLabel(
                        String.valueOf(number),
                        SwingConstants.CENTER
                )
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
                                new Color(
                                        ORANGE.getRed(),
                                        ORANGE.getGreen(),
                                        ORANGE.getBlue(),
                                        40
                                )
                        );

                        g2.fillOval(
                                0,
                                0,
                                getWidth() - 1,
                                getHeight() - 1
                        );

                        g2.dispose();

                        super.paintComponent(g);
                    }
                };

        badge.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        badge.setForeground(
                ORANGE
        );

        badge.setPreferredSize(
                new Dimension(
                        26,
                        26
                )
        );

        JPanel badgeWrap =
                new JPanel(
                        new GridBagLayout()
                );

        badgeWrap.setOpaque(
                false
        );

        badgeWrap.add(
                badge
        );

        JTextField categoryField =
                new HintTextField(
                        "Category name"
                );

        categoryField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        categoryField.setCaretColor(
                ORANGE
        );

        categoryField.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        4,
                        0,
                        10
                )
        );

        categoryField.setToolTipText(
                "Enter budget category"
        );

        JTextField amountField =
                new HintTextField(
                        "0.00"
                );

        amountField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        amountField.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        amountField.setCaretColor(
                ORANGE
        );

        amountField.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        10,
                        0,
                        10
                )
        );

        amountField.setToolTipText(
                "Enter planned amount"
        );

        JLabel dollarLabel =
                new JLabel(
                        "$"
                );

        dollarLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        dollarLabel.setForeground(
                ORANGE
        );

        JPanel amountPanel =
                new JPanel(
                        new BorderLayout()
                );

        amountPanel.setOpaque(
                false
        );

        amountPanel.setPreferredSize(
                new Dimension(
                        160,
                        52
                )
        );

        amountPanel.add(
                dollarLabel,
                BorderLayout.WEST
        );

        amountPanel.add(
                amountField,
                BorderLayout.CENTER
        );

        row.add(
                badgeWrap,
                BorderLayout.WEST
        );

        row.add(
                categoryField,
                BorderLayout.CENTER
        );

        row.add(
                amountPanel,
                BorderLayout.EAST
        );

        FocusAdapter rowFocus =
                new FocusAdapter()
                {
                    @Override
                    public void focusGained(
                            FocusEvent e
                    )
                    {
                        row.setFocused(true);
                    }

                    @Override
                    public void focusLost(
                            FocusEvent e
                    )
                    {
                        row.setFocused(false);
                    }
                };

        categoryField.addFocusListener(
                rowFocus
        );

        amountField.addFocusListener(
                rowFocus
        );

        categoryFields.add(
                categoryField
        );

        amountFields.add(
                amountField
        );

        categoryField.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener()
                {
                    public void insertUpdate(
                            javax.swing.event.DocumentEvent e
                    )
                    {
                        calculateTotal();
                    }

                    public void removeUpdate(
                            javax.swing.event.DocumentEvent e
                    )
                    {
                        calculateTotal();
                    }

                    public void changedUpdate(
                            javax.swing.event.DocumentEvent e
                    )
                    {
                        calculateTotal();
                    }
                }
        );

        amountField.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener()
                {
                    public void insertUpdate(
                            javax.swing.event.DocumentEvent e
                    )
                    {
                        calculateTotal();
                    }

                    public void removeUpdate(
                            javax.swing.event.DocumentEvent e
                    )
                    {
                        calculateTotal();
                    }

                    public void changedUpdate(
                            javax.swing.event.DocumentEvent e
                    )
                    {
                        calculateTotal();
                    }
                }
        );

        categoryPanel.add(
                row
        );

        categoryPanel.add(
                Box.createVerticalStrut(10)
        );
    }

    // ==================================================
    // CALCULATE TOTAL
    // ==================================================

    private void calculateTotal()
    {
        double total = 0;

        for (
                JTextField amountField :
                amountFields
        )
        {
            String text =
                    amountField.getText().trim();

            if (text.isEmpty())
            {
                continue;
            }

            try
            {
                double amount =
                        Double.parseDouble(
                                text.replace(",", "")
                        );

                if (amount >= 0)
                {
                    total += amount;
                }
            }
            catch (NumberFormatException ex)
            {
                // Ignore invalid value while typing.
            }
        }

        totalAmount.setText(
                String.format(
                        "%.2f",
                        total
                )
        );
    }

    // ==================================================
    // SIDEBAR BUTTON
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
    // ROUNDED PANEL (cards with soft shadow)
    // ==================================================

    private static class RoundedPanel extends JPanel
    {
        private final int radius;
        private final boolean shadow;
        private Color outline = null;

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

        void setOutline(Color outline)
        {
            this.outline = outline;
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

                if (outline != null)
                {
                    g2.setColor(outline);
                    g2.drawRoundRect(0, 0, w - 1, h - 1, radius, radius);
                }
            }

            g2.dispose();
        }
    }

    // ==================================================
    // BUDGET ROW (rounded, highlights when focused)
    // ==================================================

    private class RowPanel extends JPanel
    {
        private boolean focused = false;

        RowPanel(LayoutManager layout)
        {
            super(layout);

            setOpaque(false);
        }

        void setFocused(boolean focused)
        {
            this.focused = focused;

            repaint();
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

            g2.setColor(
                    new Color(250, 250, 250)
            );

            g2.fillRoundRect(0, 0, w - 1, h - 1, 14, 14);

            g2.setColor(
                    focused
                            ? ORANGE
                            : new Color(225, 225, 225)
            );

            g2.setStroke(
                    new BasicStroke(
                            focused ? 2f : 1f
                    )
            );

            g2.drawRoundRect(
                    focused ? 1 : 0,
                    focused ? 1 : 0,
                    focused ? w - 3 : w - 1,
                    focused ? h - 3 : h - 1,
                    14,
                    14
            );

            g2.dispose();
        }
    }

    // ==================================================
    // TEXT FIELD WITH HINT TEXT
    // ==================================================

    private static class HintTextField extends JTextField
    {
        private final String hint;

        HintTextField(String hint)
        {
            this.hint = hint;

            setOpaque(false);
        }

        @Override
        protected void paintComponent(
                Graphics g
        )
        {
            super.paintComponent(g);

            if (getText().isEmpty())
            {
                Graphics2D g2 =
                        (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON
                );

                g2.setColor(
                        new Color(170, 170, 170)
                );

                g2.setFont(getFont());

                FontMetrics fm =
                        g2.getFontMetrics();

                Insets in =
                        getInsets();

                int y =
                        (getHeight() - fm.getHeight()) / 2
                                + fm.getAscent();

                int x =
                        getHorizontalAlignment() == SwingConstants.RIGHT
                                ? getWidth() - in.right - fm.stringWidth(hint)
                                : in.left;

                g2.drawString(hint, x, y);

                g2.dispose();
            }
        }
    }

    // ==================================================
    // ROUNDED FIELD BORDER (period box)
    // ==================================================

    private static class RoundedFieldBorder extends AbstractBorder
    {
        private final Color focusColor;
        private final Color normalColor;
        private final int vertical;
        private final int horizontal;

        RoundedFieldBorder(
                Color focusColor,
                Color normalColor,
                int vertical,
                int horizontal
        )
        {
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
                    vertical,
                    horizontal,
                    vertical,
                    horizontal
            );
        }

        @Override
        public Insets getBorderInsets(
                Component c,
                Insets insets
        )
        {
            insets.set(
                    vertical,
                    horizontal,
                    vertical,
                    horizontal
            );

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
    // ROUNDED BUTTON UI
    // ==================================================

    private static class RoundedButtonUI extends BasicButtonUI
    {
        private final Color fill;
        private final Color outline;
        private final Color hoverFill;
        private final int radius;

        RoundedButtonUI(
                Color fill,
                Color outline,
                Color hoverFill,
                int radius
        )
        {
            this.fill = fill;
            this.outline = outline;
            this.hoverFill = hoverFill;
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

            int w = c.getWidth();
            int h = c.getHeight();

            g2.setColor(fill);

            g2.fillRoundRect(
                    0,
                    0,
                    w,
                    h,
                    radius,
                    radius
            );

            if (model.isPressed() || model.isRollover())
            {
                g2.setColor(hoverFill);

                g2.fillRoundRect(
                        0,
                        0,
                        w,
                        h,
                        radius,
                        radius
                );
            }

            if (outline != null)
            {
                g2.setColor(outline);

                g2.setStroke(
                        new BasicStroke(2f)
                );

                g2.drawRoundRect(
                        1,
                        1,
                        w - 3,
                        h - 3,
                        radius,
                        radius
                );
            }

            g2.dispose();

            super.paint(g, c);
        }
    }

/* BACKEND AND GUI*/

// ==================================================
    // LOAD EXISTING BUDGET
    // ==================================================

    private void loadExistingBudget(
            JComboBox<String> periodBox
    )
    {
        String budgetPeriod =
                periodBox
                        .getSelectedItem()
                        .toString();

        int managerId =
                appFrame.getManagerId();

        try
        {
            List<String[]> details =
                    BudgetService.getBudgetDetails(
                            budgetPeriod,
                            managerId
                    );

            categoryPanel.removeAll();

            categoryFields.clear();

            amountFields.clear();

            if (details.isEmpty())
            {
                addBudgetRow();
                addBudgetRow();
                addBudgetRow();
                addBudgetRow();
            }
            else
            {
                for (String[] detail : details)
                {
                    addBudgetRow();

                    int index =
                            categoryFields.size() - 1;

                    categoryFields
                            .get(index)
                            .setText(
                                    detail[0]
                            );

                    amountFields
                            .get(index)
                            .setText(
                                    detail[1]
                            );
                }

                while (categoryFields.size() < 4)
                {
                    addBudgetRow();
                }
            }

            calculateTotal();

            categoryPanel.revalidate();

            categoryPanel.repaint();
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Database error: "
                    + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ==================================================
    // SAVE BUDGET
    // ==================================================

    private void saveBudget(
            JComboBox<String> periodBox
    )
    {
        String budgetPeriod =
                periodBox
                        .getSelectedItem()
                        .toString();

        List<String> categories =
                new ArrayList<>();

        List<Double> amounts =
                new ArrayList<>();

        for (
                int i = 0;
                i < categoryFields.size();
                i++
        )
        {
            String category =
                    categoryFields
                            .get(i)
                            .getText()
                            .trim();

            String amountText =
                    amountFields
                            .get(i)
                            .getText()
                            .trim();

            if (
                    category.isEmpty()
                    &&
                    amountText.isEmpty()
            )
            {
                continue;
            }

            if (category.isEmpty())
            {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a category name.",
                        "Budget Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            if (amountText.isEmpty())
            {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter an amount for "
                        + category + ".",
                        "Budget Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            try
            {
                double amount =
                        Double.parseDouble(
                                amountText.replace(",", "")
                        );

                if (amount < 0)
                {
                    JOptionPane.showMessageDialog(
                            this,
                            "Amount cannot be negative.",
                            "Budget Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }

                categories.add(
                        category
                );

                amounts.add(
                        amount
                );
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid amount for "
                        + category + ".",
                        "Budget Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }
        }

        if (categories.isEmpty())
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Please add at least one budget category.",
                    "Budget Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        String[] categoryArray =
                categories.toArray(
                        new String[0]
                );

        double[] amountArray =
                new double[amounts.size()];

        for (
                int i = 0;
                i < amounts.size();
                i++
        )
        {
            amountArray[i] =
                    amounts.get(i);
        }

        try
        {
            int managerId =
                    appFrame.getManagerId();

            // ==================================================
            // CHECK WHETHER BUDGET ALREADY EXISTS
            // ==================================================

            System.out.println(
                    "CHECKING EXISTING BUDGET"
            );

            System.out.println(
                    "PERIOD = " + budgetPeriod
            );

            System.out.println(
                    "MANAGER ID = " + managerId
            );

            int budgetId =
                    BudgetService.getBudgetId(
                            budgetPeriod,
                            managerId
                    );

            System.out.println(
                    "FOUND BUDGET ID = " + budgetId
            );

            if (budgetId == -1)
            {
                // CREATE NEW BUDGET

                BudgetService.saveBudget(
                        budgetPeriod,
                        managerId,
                        categoryArray,
                        amountArray
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Budget saved successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
            else
            {
                // UPDATE EXISTING BUDGET

                BudgetService.updateBudget(
                        budgetId,
                        categoryArray,
                        amountArray
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Budget updated successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Database error: "
                    + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
