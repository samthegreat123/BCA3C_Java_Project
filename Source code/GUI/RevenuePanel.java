package GUI;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import REVENUE.RevenueService;
import REVENUE.RevenueService.RevenueRecord;

public class RevenuePanel extends JPanel
{
    private AppFrame appFrame;

    private final Color ORANGE =
            new Color(245, 140, 30);

    private final Color DARK =
            new Color(15, 15, 15);

    private final Color LIGHT =
            new Color(246, 246, 246);

    private JComboBox<String> monthBox;
    private JComboBox<Integer> yearBox;

    private JTable revenueTable;

    private DefaultTableModel tableModel;

    private JLabel totalAmount;


    public RevenuePanel(
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


        revenueButton.setBackground(
                ORANGE
        );

        revenueButton.setForeground(
                Color.WHITE
        );


        JButton performanceButton =
                createMenuButton(
                        "Performance"
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
                        "Revenue Management"
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
                        "View and monitor revenue recorded by the Accountant"
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
                Box.createVerticalStrut(24)
        );


        // ==================================================
        // PERIOD SELECTOR
        // ==================================================

        JPanel periodPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0
                        )
                );

        periodPanel.setBackground(
                LIGHT
        );

        periodPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        periodPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        48
                )
        );


        JLabel periodLabel =
                new JLabel(
                        "Period:"
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

        periodLabel.setBorder(
                new EmptyBorder(
                        0,
                        0,
                        0,
                        2
                )
        );


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


        monthBox =
                new JComboBox<>(
                        months
                );

        styleComboBox(
                monthBox,
                150
        );


        Integer[] years =
        {
                2026,
                2027,
                2028,
                2029,
                2030

        };


        yearBox =
                new JComboBox<>(
                        years
                );

        styleComboBox(
                yearBox,
                100
        );


        periodPanel.add(
                periodLabel
        );

        periodPanel.add(
                monthBox
        );

        periodPanel.add(
                yearBox
        );


        centerPanel.add(
                periodPanel
        );


        centerPanel.add(
                Box.createVerticalStrut(16)
        );


        // ==================================================
        // TOTAL REVENUE CARD
        // ==================================================

        RoundedPanel totalPanel =
                new RoundedPanel(
                        new BorderLayout(),
                        Color.WHITE,
                        18,
                        true
                );

        totalPanel.setBorder(
                new EmptyBorder(
                        18,
                        24,
                        21,
                        24
                )
        );

        totalPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        totalPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        90
                )
        );


        JPanel totalTitleBox =
                new JPanel();

        totalTitleBox.setLayout(
                new BoxLayout(
                        totalTitleBox,
                        BoxLayout.Y_AXIS
                )
        );

        totalTitleBox.setOpaque(
                false
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


        JLabel totalTitle =
                new JLabel(
                        "TOTAL REVENUE"
                );

        totalTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        totalTitle.setForeground(
                new Color(
                        100,
                        100,
                        100
                )
        );

        totalTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        totalTitleBox.add(
                accentBar
        );

        totalTitleBox.add(
                Box.createVerticalStrut(10)
        );

        totalTitleBox.add(
                totalTitle
        );


        totalAmount =
                new JLabel(
                        "$0.00"
                );

        totalAmount.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        totalAmount.setForeground(
                ORANGE
        );


        totalPanel.add(
                totalTitleBox,
                BorderLayout.WEST
        );

        totalPanel.add(
                totalAmount,
                BorderLayout.EAST
        );


        centerPanel.add(
                totalPanel
        );


        centerPanel.add(
                Box.createVerticalStrut(20)
        );


        // ==================================================
        // REVENUE RECORDS
        // ==================================================

        RoundedPanel recordsPanel =
                new RoundedPanel(
                        new BorderLayout(),
                        Color.WHITE,
                        18,
                        true
                );

        recordsPanel.setBorder(
                new EmptyBorder(
                        20,
                        22,
                        23,
                        22
                )
        );

        recordsPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JPanel recordsMarker =
                new JPanel();

        recordsMarker.setBackground(
                ORANGE
        );

        recordsMarker.setPreferredSize(
                new Dimension(
                        4,
                        16
                )
        );


        JLabel recordsTitle =
                new JLabel(
                        "Revenue Records"
                );

        recordsTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        recordsTitle.setForeground(
                Color.BLACK
        );

        recordsTitle.setBorder(
                new EmptyBorder(
                        0,
                        9,
                        0,
                        0
                )
        );


        JPanel recordsTitleRow =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                0,
                                0
                        )
                );

        recordsTitleRow.setOpaque(
                false
        );

        recordsTitleRow.setBorder(
                new EmptyBorder(
                        0,
                        0,
                        14,
                        0
                )
        );

        recordsTitleRow.add(
                recordsMarker
        );

        recordsTitleRow.add(
                recordsTitle
        );


        recordsPanel.add(
                recordsTitleRow,
                BorderLayout.NORTH
        );


        // ==================================================
        // TABLE
        // ==================================================

        String[] columns =
        {
                "Date",
                "Category",
                "Description",
                "Amount"
        };


        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                )
                {
                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    )
                    {
                        return false;
                    }
                };


        revenueTable =
                new JTable(
                        tableModel
                );


        revenueTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        revenueTable.setRowHeight(
                40
        );

        revenueTable.setGridColor(
                new Color(
                        240,
                        240,
                        240
                )
        );

        revenueTable.setShowVerticalLines(
                false
        );

        revenueTable.setShowHorizontalLines(
                true
        );

        revenueTable.setIntercellSpacing(
                new Dimension(
                        0,
                        1
                )
        );

        revenueTable.setBackground(
                Color.WHITE
        );

        revenueTable.setForeground(
                new Color(
                        50,
                        50,
                        50
                )
        );

        revenueTable.setSelectionBackground(
                new Color(
                        255,
                        240,
                        224
                )
        );

        revenueTable.setSelectionForeground(
                Color.BLACK
        );

        revenueTable.setFillsViewportHeight(
                true
        );


        // body cells

        revenueTable.setDefaultRenderer(
                Object.class,
                new DefaultTableCellRenderer()
                {
                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column
                    )
                    {
                        super.getTableCellRendererComponent(
                                table,
                                value,
                                isSelected,
                                false,
                                row,
                                column
                        );

                        setBorder(
                                new EmptyBorder(
                                        0,
                                        14,
                                        0,
                                        14
                                )
                        );

                        if (isSelected)
                        {
                            setBackground(
                                    new Color(255, 240, 224)
                            );
                        }
                        else
                        {
                            setBackground(
                                    row % 2 == 0
                                            ? Color.WHITE
                                            : new Color(251, 251, 251)
                            );
                        }

                        if (column == 3)
                        {
                            setHorizontalAlignment(
                                    SwingConstants.RIGHT
                            );

                            setFont(
                                    new Font(
                                            "Segoe UI",
                                            Font.BOLD,
                                            13
                                    )
                            );

                            setForeground(
                                    new Color(30, 150, 30)
                            );
                        }
                        else
                        {
                            setHorizontalAlignment(
                                    SwingConstants.LEFT
                            );

                            setFont(
                                    new Font(
                                            "Segoe UI",
                                            Font.PLAIN,
                                            13
                                    )
                            );

                            setForeground(
                                    column == 0
                                            ? new Color(110, 110, 110)
                                            : new Color(50, 50, 50)
                            );
                        }

                        return this;
                    }
                }
        );


        // header cells

        JTableHeader tableHeader =
                revenueTable.getTableHeader();

        tableHeader.setReorderingAllowed(
                false
        );

        tableHeader.setPreferredSize(
                new Dimension(
                        0,
                        40
                )
        );

        tableHeader.setDefaultRenderer(
                new DefaultTableCellRenderer()
                {
                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column
                    )
                    {
                        super.getTableCellRendererComponent(
                                table,
                                value,
                                false,
                                false,
                                row,
                                column
                        );

                        setOpaque(true);

                        setBackground(DARK);

                        setForeground(Color.WHITE);

                        setFont(
                                new Font(
                                        "Segoe UI",
                                        Font.BOLD,
                                        12
                                )
                        );

                        setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createMatteBorder(
                                                0,
                                                0,
                                                3,
                                                0,
                                                ORANGE
                                        ),
                                        new EmptyBorder(
                                                0,
                                                14,
                                                0,
                                                14
                                        )
                                )
                        );

                        setHorizontalAlignment(
                                column == 3
                                        ? SwingConstants.RIGHT
                                        : SwingConstants.LEFT
                        );

                        return this;
                    }
                }
        );


        JScrollPane scrollPane =
                new JScrollPane(
                        revenueTable
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                235,
                                235,
                                235
                        )
                )
        );

        scrollPane.getViewport().setBackground(
                Color.WHITE
        );

        scrollPane.getVerticalScrollBar().setUnitIncrement(
                16
        );


        recordsPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        centerPanel.add(
                recordsPanel
        );


        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );


        // ==================================================
        // PERIOD ACTIONS
        // ==================================================

        monthBox.addActionListener(
                e ->
                {
                    loadRevenue();
                }
        );


        yearBox.addActionListener(
                e ->
                {
                    loadRevenue();
                }
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

        monthBox.setSelectedItem(
                "October"
        );

        yearBox.setSelectedItem(
                2026
        );

        loadRevenue();
    }


        
// ==================================================
// LOAD REVENUE
// ==================================================

private void loadRevenue()
{
    // Do not load data before a restaurant is authenticated
    if (appFrame.getRestaurantId() <= 0)
    {
        return;
    }

    int month = monthBox.getSelectedIndex() + 1;
    int year = (Integer) yearBox.getSelectedItem();

    try
    {
        List<RevenueRecord> records =
                RevenueService.getRevenue(
                        month,
                        year,
                        appFrame.getRestaurantId()
                );

        tableModel.setRowCount(0);

        for (RevenueRecord record : records)
        {
            tableModel.addRow(
                    new Object[]
                    {
                            record.getDate(),
                            record.getCategory(),
                            record.getDescription(),
                            String.format("$%.2f", record.getAmount())
                    }
            );
        }

        double total =
                RevenueService.getTotalRevenue(
                        month,
                        year,
                        appFrame.getRestaurantId()
                );

        totalAmount.setText(String.format("$%.2f", total));
    }
    catch (SQLException ex)
    {
        JOptionPane.showMessageDialog(
                this,
                "Unable to load revenue data.",
                "Database Error",
                JOptionPane.ERROR_MESSAGE
        );

        ex.printStackTrace();
    }
}

// ==================================================
// REFRESH REVENUE WHEN THE SCREEN IS OPENED
// ==================================================

public void refreshRevenue()
{
    loadRevenue();
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
                        42
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
    // ROUNDED FIELD BORDER (period boxes)
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
}