package GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;


import DB.DBConnection;

public class RestaurantRegistrationPanel
        extends JPanel
        implements ActionListener
{
    private AppFrame appFrame;

    private JTextField restaurantNameField;
    private JPasswordField restaurantPasswordField;

    private JTextField managerUsernameField;
    private JPasswordField managerPasswordField;

    private JTextField accountantUsernameField;
    private JPasswordField accountantPasswordField;

    private JButton createRestaurantButton;
    private JButton backButton;

    private final Color ORANGE =
            new Color(245, 140, 30);

    private final Color DARK =
            new Color(25, 25, 25);


    // =========================================
    // CONSTRUCTOR
    // =========================================

    public RestaurantRegistrationPanel(
            AppFrame appFrame
    )
    {
        this.appFrame = appFrame;

        setLayout(
                new GridLayout(
                        1,
                        2
                )
        );

        setBackground(Color.WHITE);


        // =====================================
        // LEFT SIDE - BRANDING
        // =====================================

        JPanel brandingPanel =
                new JPanel()
                {
                    @Override
                    protected void paintComponent(
                            Graphics g
                    )
                    {
                        super.paintComponent(g);

                        Graphics2D g2 =
                                (Graphics2D) g.create();

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        int w = getWidth();
                        int h = getHeight();

                        if (w > 0 && h > 0)
                        {
                            float radius =
                                    Math.max(w, h) * 0.8f;

                            RadialGradientPaint glow =
                                    new RadialGradientPaint(
                                            new Point(
                                                    0,
                                                    (int) (h * 0.95)
                                            ),
                                            radius,
                                            new float[] { 0f, 1f },
                                            new Color[] {
                                                    new Color(
                                                            ORANGE.getRed(),
                                                            ORANGE.getGreen(),
                                                            ORANGE.getBlue(),
                                                            55
                                                    ),
                                                    new Color(
                                                            ORANGE.getRed(),
                                                            ORANGE.getGreen(),
                                                            ORANGE.getBlue(),
                                                            0
                                                    )
                                            }
                                    );

                            g2.setPaint(glow);
                            g2.fillRect(0, 0, w, h);
                        }

                        g2.dispose();
                    }
                };

        brandingPanel.setLayout(
                new BoxLayout(
                        brandingPanel,
                        BoxLayout.Y_AXIS
                )
        );

        brandingPanel.setBackground(DARK);

        brandingPanel.setBorder(
                new EmptyBorder(
                        35,
                        40,
                        30,
                        40
                )
        );


        // Logo (rounded orange badge)

        JLabel logoLabel =
                new JLabel(
                        "R",
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

                        g2.setColor(ORANGE);

                        g2.fillRoundRect(
                                0,
                                0,
                                getWidth(),
                                getHeight(),
                                16,
                                16
                        );

                        g2.dispose();

                        super.paintComponent(g);
                    }
                };

        logoLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        logoLabel.setForeground(Color.WHITE);

        logoLabel.setPreferredSize(
                new Dimension(52, 52)
        );

        logoLabel.setMaximumSize(
                new Dimension(52, 52)
        );

        logoLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        brandingPanel.add(logoLabel);

        brandingPanel.add(
                Box.createVerticalStrut(28)
        );


        // Title

        JLabel titleLabel =
                new JLabel(
                        "<html>ADD YOUR<br>RESTAURANT</html>"
                );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        titleLabel.setForeground(Color.WHITE);

        titleLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        brandingPanel.add(titleLabel);

        brandingPanel.add(
                Box.createVerticalStrut(15)
        );


        // Orange line

        JPanel orangeLine =
                new JPanel();

        orangeLine.setBackground(ORANGE);

        orangeLine.setMaximumSize(
                new Dimension(
                        60,
                        4
                )
        );

        orangeLine.setPreferredSize(
                new Dimension(
                        60,
                        4
                )
        );

        orangeLine.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        brandingPanel.add(orangeLine);

        brandingPanel.add(
                Box.createVerticalStrut(20)
        );


        // Description

        JLabel descriptionLabel =
                new JLabel(
                        "<html>Set up your restaurant<br>" +
                        "and financial team.</html>"
                );

        descriptionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        descriptionLabel.setForeground(
                new Color(190, 190, 190)
        );

        descriptionLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        brandingPanel.add(descriptionLabel);

        brandingPanel.add(
                Box.createVerticalStrut(30)
        );


        // Setup steps

        addStep(
                brandingPanel,
                "1",
                "Restaurant details"
        );

        brandingPanel.add(
                Box.createVerticalStrut(12)
        );

        addStep(
                brandingPanel,
                "2",
                "Manager account"
        );

        brandingPanel.add(
                Box.createVerticalStrut(12)
        );

        addStep(
                brandingPanel,
                "3",
                "Accountant account"
        );

        brandingPanel.add(
                Box.createVerticalGlue()
        );


        // Restaurant image

        ImageIcon imageIcon =
                new ImageIcon(
                        "assets/restaurant-finance-icon.jpeg"
                );

        Image image =
                imageIcon.getImage();

        Image resizedImage =
                image.getScaledInstance(
                        260,
                        150,
                        Image.SCALE_SMOOTH
                );

        JLabel imageLabel =
                new JLabel(
                        new ImageIcon(
                                resizedImage
                        )
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

                        g2.setClip(
                                new RoundRectangle2D.Float(
                                        0,
                                        0,
                                        getWidth(),
                                        getHeight(),
                                        20,
                                        20
                                )
                        );

                        super.paintComponent(g2);

                        g2.setClip(null);

                        g2.setColor(
                                new Color(
                                        ORANGE.getRed(),
                                        ORANGE.getGreen(),
                                        ORANGE.getBlue(),
                                        90
                                )
                        );

                        g2.drawRoundRect(
                                0,
                                0,
                                getWidth() - 1,
                                getHeight() - 1,
                                20,
                                20
                        );

                        g2.dispose();
                    }
                };

        imageLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        brandingPanel.add(imageLabel);


        // =====================================
        // RIGHT SIDE - REGISTRATION
        // =====================================

        JPanel formPanel =
                new JPanel();

        formPanel.setLayout(
                new BoxLayout(
                        formPanel,
                        BoxLayout.Y_AXIS
                )
        );

        formPanel.setBackground(Color.WHITE);

        formPanel.setBorder(
                new EmptyBorder(
                        25,
                        45,
                        20,
                        45
                )
        );


        // Heading

        JLabel headingLabel =
                new JLabel(
                        "RESTAURANT SETUP"
                );

        headingLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        27
                )
        );

        headingLabel.setForeground(Color.BLACK);

        headingLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        formPanel.add(headingLabel);

        formPanel.add(
                Box.createVerticalStrut(5)
        );


        JLabel subtitleLabel =
                new JLabel(
                        "Create your restaurant account"
                );

        subtitleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        subtitleLabel.setForeground(
                new Color(100, 100, 100)
        );

        subtitleLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        formPanel.add(subtitleLabel);

        formPanel.add(
                Box.createVerticalStrut(18)
        );


        // =====================================
        // RESTAURANT SECTION
        // =====================================

        addSectionTitle(
                formPanel,
                "RESTAURANT"
        );

        formPanel.add(
                Box.createVerticalStrut(8)
        );


        addLabel(
                formPanel,
                "RESTAURANT NAME"
        );

        restaurantNameField =
                new JTextField();

        styleTextField(
                restaurantNameField
        );

        formPanel.add(
                restaurantNameField
        );

        formPanel.add(
                Box.createVerticalStrut(10)
        );


        addLabel(
                formPanel,
                "RESTAURANT PASSWORD"
        );

        restaurantPasswordField =
                new JPasswordField();

        styleTextField(
                restaurantPasswordField
        );

        formPanel.add(
                restaurantPasswordField
        );

        formPanel.add(
                Box.createVerticalStrut(15)
        );


        // =====================================
        // MANAGER SECTION
        // =====================================

        addSectionTitle(
                formPanel,
                "MANAGER"
        );

        formPanel.add(
                Box.createVerticalStrut(8)
        );


        addLabel(
                formPanel,
                "USERNAME"
        );

        managerUsernameField =
                new JTextField();

        styleTextField(
                managerUsernameField
        );

        formPanel.add(
                managerUsernameField
        );

        formPanel.add(
                Box.createVerticalStrut(10)
        );


        addLabel(
                formPanel,
                "PASSWORD"
        );

        managerPasswordField =
                new JPasswordField();

        styleTextField(
                managerPasswordField
        );

        formPanel.add(
                managerPasswordField
        );

        formPanel.add(
                Box.createVerticalStrut(15)
        );


        // =====================================
        // ACCOUNTANT SECTION
        // =====================================

        addSectionTitle(
                formPanel,
                "ACCOUNTANT"
        );

        formPanel.add(
                Box.createVerticalStrut(8)
        );


        addLabel(
                formPanel,
                "USERNAME"
        );

        accountantUsernameField =
                new JTextField();

        styleTextField(
                accountantUsernameField
        );

        formPanel.add(
                accountantUsernameField
        );

        formPanel.add(
                Box.createVerticalStrut(10)
        );


        addLabel(
                formPanel,
                "PASSWORD"
        );

        accountantPasswordField =
                new JPasswordField();

        styleTextField(
                accountantPasswordField
        );

        formPanel.add(
                accountantPasswordField
        );

        formPanel.add(
                Box.createVerticalStrut(18)
        );


        // =====================================
        // CREATE BUTTON
        // =====================================

        createRestaurantButton =
                new JButton(
                        "CREATE RESTAURANT"
                );

        styleMainButton(
                createRestaurantButton
        );

        createRestaurantButton.addActionListener(
                this
        );

        formPanel.add(
                createRestaurantButton
        );

        formPanel.add(
                Box.createVerticalStrut(8)
        );


        // =====================================
        // BACK BUTTON
        // =====================================

        backButton =
                new JButton(
                        "Back to Login"
                );

        styleBackButton(
                backButton
        );

        backButton.addActionListener(
                this
        );

        formPanel.add(
                backButton
        );


        // =====================================
        // ADD BOTH SIDES
        // =====================================

        add(brandingPanel);

        add(formPanel);
    }


    // =========================================
    // SETUP STEP (left side)
    // =========================================

    private void addStep(
            JPanel panel,
            String number,
            String text
    )
    {
        JLabel badge =
                new JLabel(
                        number,
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

                        g2.setColor(
                                new Color(
                                        ORANGE.getRed(),
                                        ORANGE.getGreen(),
                                        ORANGE.getBlue(),
                                        130
                                )
                        );

                        g2.drawOval(
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
                        12
                )
        );

        badge.setForeground(ORANGE);

        badge.setPreferredSize(
                new Dimension(28, 28)
        );

        JLabel stepLabel =
                new JLabel(text);

        stepLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        stepLabel.setForeground(
                new Color(215, 215, 215)
        );

        JPanel row =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                12,
                                0
                        )
                );

        row.setOpaque(false);

        row.add(badge);

        row.add(stepLabel);

        row.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        row.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        30
                )
        );

        panel.add(row);
    }


    // =========================================
    // SECTION TITLE
    // =========================================

    private void addSectionTitle(
            JPanel panel,
            String text
    )
    {
        JPanel bar =
                new JPanel();

        bar.setBackground(ORANGE);

        bar.setPreferredSize(
                new Dimension(4, 14)
        );

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        label.setForeground(ORANGE);

        label.setBorder(
                new EmptyBorder(
                        0,
                        8,
                        0,
                        0
                )
        );

        JPanel left =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                0,
                                0
                        )
                );

        left.setOpaque(false);

        left.add(bar);

        left.add(label);

        JComponent line =
                new JComponent()
                {
                    @Override
                    protected void paintComponent(
                            Graphics g
                    )
                    {
                        g.setColor(
                                new Color(232, 232, 232)
                        );

                        g.drawLine(
                                0,
                                getHeight() / 2,
                                getWidth(),
                                getHeight() / 2
                        );
                    }
                };

        JPanel row =
                new JPanel(
                        new BorderLayout(10, 0)
                );

        row.setOpaque(false);

        row.add(
                left,
                BorderLayout.WEST
        );

        row.add(
                line,
                BorderLayout.CENTER
        );

        row.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        row.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        22
                )
        );

        panel.add(row);
    }


    // =========================================
    // LABEL
    // =========================================

    private void addLabel(
            JPanel panel,
            String text
    )
    {
        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        label.setForeground(
                new Color(85, 85, 85)
        );

        label.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        panel.add(label);

        panel.add(
                Box.createVerticalStrut(5)
        );
    }


    // =========================================
    // TEXT FIELD STYLE
    // =========================================

    private void styleTextField(
            JTextField field
    )
    {
        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        field.setForeground(Color.BLACK);

        field.setBackground(Color.WHITE);

        field.setCaretColor(ORANGE);

        field.setBorder(
                new RoundedFieldBorder(
                        ORANGE,
                        new Color(210, 210, 210)
                )
        );

        field.addFocusListener(
                new FocusAdapter()
                {
                    @Override
                    public void focusGained(
                            FocusEvent e
                    )
                    {
                        field.repaint();
                    }

                    @Override
                    public void focusLost(
                            FocusEvent e
                    )
                    {
                        field.repaint();
                    }
                }
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );

        field.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );
    }


    // =========================================
    // MAIN BUTTON
    // =========================================

    private void styleMainButton(
            JButton button
    )
    {
        button.setUI(
                new RoundedButtonUI(
                        ORANGE,
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

        button.setForeground(Color.WHITE);

        button.setBackground(ORANGE);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setContentAreaFilled(false);

        button.setOpaque(false);

        button.setRolloverEnabled(true);

        button.setBorder(
                new EmptyBorder(
                        10,
                        16,
                        10,
                        16
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

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );
    }


    // =========================================
    // BACK BUTTON
    // =========================================

    private void styleBackButton(
            JButton button
    )
    {
        Color normal =
                new Color(
                        90,
                        90,
                        90
                );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        button.setForeground(normal);

        button.setBackground(Color.WHITE);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setContentAreaFilled(false);

        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.addMouseListener(
                new MouseAdapter()
                {
                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    )
                    {
                        button.setForeground(ORANGE);
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e
                    )
                    {
                        button.setForeground(normal);
                    }
                }
        );
    }


    // =========================================
    // ROUNDED FIELD BORDER
    // =========================================

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
                    12,
                    12
            );

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(
                Component c
        )
        {
            return new Insets(9, 13, 9, 13);
        }

        @Override
        public Insets getBorderInsets(
                Component c,
                Insets insets
        )
        {
            insets.set(9, 13, 9, 13);

            return insets;
        }
    }


    // =========================================
    // ROUNDED BUTTON UI
    // =========================================

    private static class RoundedButtonUI
            extends BasicButtonUI
    {
        private final Color base;
        private final int radius;

        RoundedButtonUI(
                Color base,
                int radius
        )
        {
            this.base = base;
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
                fill = base.darker().darker();
            }
            else if (model.isRollover())
            {
                fill = base.darker();
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


    // =========================================
    // BUTTON ACTIONS
    // =========================================


 /* CREATIONS OF ALL ACCOOUNTANTS IN THE APPLICATION*/
 @Override
public void actionPerformed(ActionEvent e)
{
    if (e.getSource() == createRestaurantButton)
    {
        if (restaurantNameField.getText().trim().isEmpty()
                || restaurantPasswordField.getPassword().length == 0
                || managerUsernameField.getText().trim().isEmpty()
                || managerPasswordField.getPassword().length == 0
                || accountantUsernameField.getText().trim().isEmpty()
                || accountantPasswordField.getPassword().length == 0)
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Connection connection = null;

        try
        {
            connection = DBConnection.getConnection();

            connection.setAutoCommit(false);

            // =========================================
            // CREATE RESTAURANT ID
            // =========================================

            String restaurantIdQuery =
                    "SELECT NVL(MAX(restaurant_id), 0) + 1 " +
                    "FROM RESTAURANT";

            PreparedStatement restaurantIdStatement =
                    connection.prepareStatement(restaurantIdQuery);

            ResultSet restaurantIdResult =
                    restaurantIdStatement.executeQuery();

            restaurantIdResult.next();

            int restaurantId =
                    restaurantIdResult.getInt(1);

            // =========================================
            // CREATE USER IDS
            // =========================================

            String userIdQuery =
                    "SELECT NVL(MAX(user_id), 0) + 1 " +
                    "FROM APP_USER";

            PreparedStatement userIdStatement =
                    connection.prepareStatement(userIdQuery);

            ResultSet userIdResult =
                    userIdStatement.executeQuery();

            userIdResult.next();

            int managerId =
                    userIdResult.getInt(1);

            int accountantId =
                    managerId + 1;

            // =========================================
            // INSERT RESTAURANT
            // =========================================

            String restaurantQuery =
                    "INSERT INTO RESTAURANT " +
                    "(restaurant_id, restaurant_name, password) " +
                    "VALUES (?, ?, ?)";

            PreparedStatement restaurantStatement =
                    connection.prepareStatement(restaurantQuery);

            restaurantStatement.setInt(
                    1,
                    restaurantId
            );

            restaurantStatement.setString(
                    2,
                    restaurantNameField.getText().trim()
            );

            restaurantStatement.setString(
                    3,
                    new String(
                            restaurantPasswordField.getPassword()
                    )
            );

            restaurantStatement.executeUpdate();

            // =========================================
            // INSERT MANAGER
            // =========================================

            String userQuery =
                    "INSERT INTO APP_USER " +
                    "(user_id, username, password, role, restaurant_id) " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement managerStatement =
                    connection.prepareStatement(userQuery);

            managerStatement.setInt(
                    1,
                    managerId
            );

            managerStatement.setString(
                    2,
                    managerUsernameField.getText().trim()
            );

            managerStatement.setString(
                    3,
                    new String(
                            managerPasswordField.getPassword()
                    )
            );

            managerStatement.setString(
                    4,
                    "Manager"
            );

            managerStatement.setInt(
                    5,
                    restaurantId
            );

            managerStatement.executeUpdate();

            // =========================================
            // INSERT ACCOUNTANT
            // =========================================

            PreparedStatement accountantStatement =
                    connection.prepareStatement(userQuery);

            accountantStatement.setInt(
                    1,
                    accountantId
            );

            accountantStatement.setString(
                    2,
                    accountantUsernameField.getText().trim()
            );

            accountantStatement.setString(
                    3,
                    new String(
                            accountantPasswordField.getPassword()
                    )
            );

            accountantStatement.setString(
                    4,
                    "Accountant"
            );

            accountantStatement.setInt(
                    5,
                    restaurantId
            );

            accountantStatement.executeUpdate();

            // =========================================
            // SAVE EVERYTHING
            // =========================================

            connection.commit();

            // =========================================
            // CLEAR FIELDS
            // =========================================

            restaurantNameField.setText("");
            restaurantPasswordField.setText("");
            managerUsernameField.setText("");
            managerPasswordField.setText("");
            accountantUsernameField.setText("");
            accountantPasswordField.setText("");

            JOptionPane.showMessageDialog(
                    this,
                    "Restaurant created successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            appFrame.showScreen("LOGIN");
        }
        catch (Exception ex)
        {
            if (connection != null)
            {
                try
                {
                    connection.rollback();
                }
                catch (SQLException rollbackException)
                {
                    rollbackException.printStackTrace();
                }
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to create restaurant.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            ex.printStackTrace();
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException closeException)
                {
                    closeException.printStackTrace();
                }
            }
        }
    }

    else if (e.getSource() == backButton)
    {
        appFrame.showScreen("LOGIN");
    }
}
}