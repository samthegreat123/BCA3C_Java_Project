package GUI;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.sql.SQLException;

import AUTHENTICATION.AuthenticationService;

public class LoginPanel extends JPanel implements ActionListener
{
    private AppFrame appFrame;

    private JTextField restaurantNameField;
    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton createRestaurantButton;

    // Colors
    private static final Color BLACK = new Color(20, 20, 20);
    private static final Color DARK_BLACK = new Color(12, 12, 12);
    private static final Color ORANGE = new Color(232, 116, 59);
    private static final Color WHITE = Color.WHITE;
    private static final Color LIGHT_GRAY = new Color(190, 190, 190);

    public LoginPanel(AppFrame appFrame)
    {
        this.appFrame = appFrame;

        setLayout(new GridLayout(1, 2));
        setBackground(BLACK);

        // =========================================
        // LEFT SIDE - BRANDING
        // =========================================

        JPanel brandingPanel =
                new JPanel(new BorderLayout())
                {
                    @Override
                    protected void paintComponent(Graphics g)
                    {
                        super.paintComponent(g);

                        Graphics2D g2 = (Graphics2D) g.create();

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        int w = getWidth();
                        int h = getHeight();

                        if (w > 0 && h > 0)
                        {
                            float radius = Math.max(w, h) * 0.75f;

                            RadialGradientPaint glow =
                                    new RadialGradientPaint(
                                            new Point(w / 2, (int) (h * 0.85)),
                                            radius,
                                            new float[] { 0f, 1f },
                                            new Color[] {
                                                    new Color(
                                                            ORANGE.getRed(),
                                                            ORANGE.getGreen(),
                                                            ORANGE.getBlue(),
                                                            60
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

        brandingPanel.setBackground(DARK_BLACK);

        // -----------------------------------------
        // Branding title area
        // -----------------------------------------

        JPanel titlePanel = new JPanel();

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        titlePanel.setOpaque(false);

        titlePanel.setBorder(
                new EmptyBorder(
                        30,
                        20,
                        10,
                        20
                )
        );

        // Restaurant icon (rounded orange badge)
        JLabel iconLabel =
                new JLabel("R", SwingConstants.CENTER)
                {
                    @Override
                    protected void paintComponent(Graphics g)
                    {
                        Graphics2D g2 = (Graphics2D) g.create();

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
                                20,
                                20
                        );

                        g2.dispose();

                        super.paintComponent(g);
                    }
                };

        iconLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        32
                )
        );

        iconLabel.setForeground(WHITE);

        iconLabel.setPreferredSize(new Dimension(60, 60));
        iconLabel.setMinimumSize(new Dimension(60, 60));
        iconLabel.setMaximumSize(new Dimension(60, 60));

        iconLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        titlePanel.add(iconLabel);

        titlePanel.add(
                Box.createVerticalStrut(14)
        );

        // Title
        JLabel title1 = new JLabel("RESTAURANT");

        title1.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        27
                )
        );

        title1.setForeground(WHITE);

        title1.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel title2 = new JLabel("BUDGET PLANNER");

        title2.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        27
                )
        );

        title2.setForeground(ORANGE);

        title2.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        titlePanel.add(title1);
        titlePanel.add(title2);

        titlePanel.add(
                Box.createVerticalStrut(12)
        );

        // Orange line
        JPanel orangeLine = new JPanel();

        orangeLine.setBackground(ORANGE);

        orangeLine.setPreferredSize(
                new Dimension(
                        55,
                        4
                )
        );

        orangeLine.setMaximumSize(
                new Dimension(
                        55,
                        4
                )
        );

        orangeLine.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        titlePanel.add(orangeLine);

        titlePanel.add(
                Box.createVerticalStrut(16)
        );

        // Tagline (three small chips)
        JPanel taglinePanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                8,
                                0
                        )
                );

        taglinePanel.setOpaque(false);

        taglinePanel.add(new PillLabel("Track Expenses"));
        taglinePanel.add(new PillLabel("Manage Revenue"));
        taglinePanel.add(new PillLabel("Grow Your Business"));

        taglinePanel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        taglinePanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        34
                )
        );

        titlePanel.add(taglinePanel);

        brandingPanel.add(
                titlePanel,
                BorderLayout.NORTH
        );

        // -----------------------------------------
        // Restaurant image
        // -----------------------------------------

        JLabel imageLabel = new JLabel();

        imageLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        imageLabel.setVerticalAlignment(
                SwingConstants.BOTTOM
        );

        ImageIcon originalIcon =
                new ImageIcon("assets/restaurant.png");

        if (originalIcon.getIconWidth() > 0)
        {
            Image image =
                    originalIcon.getImage();

            Image scaledImage =
                    image.getScaledInstance(
                            610,
                            400,
                            Image.SCALE_SMOOTH
                    );

            imageLabel.setIcon(
                    new ImageIcon(scaledImage)
            );
        }

        brandingPanel.add(
                imageLabel,
                BorderLayout.CENTER
        );

        // =========================================
        // RIGHT SIDE - LOGIN
        // =========================================

        JPanel loginPanel =
                new JPanel(new GridBagLayout());

        loginPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1.0;

        // Container for login content
        JPanel formPanel = new JPanel();

        formPanel.setLayout(
                new BoxLayout(
                        formPanel,
                        BoxLayout.Y_AXIS
                )
        );

        formPanel.setBackground(Color.WHITE);

        formPanel.setBorder(
                new EmptyBorder(
                        20,
                        55,
                        20,
                        55
                )
        );

        // =========================================
        // WELCOME
        // =========================================

        JLabel welcomeLabel =
                new JLabel("WELCOME BACK");

        welcomeLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        32
                )
        );

        welcomeLabel.setForeground(Color.BLACK);

        welcomeLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        formPanel.add(welcomeLabel);

        formPanel.add(
                Box.createVerticalStrut(8)
        );

        // =========================================
        // DESCRIPTION
        // =========================================

        JLabel descriptionLabel =
                new JLabel(
                        "Sign in to manage your restaurant"
                );

        descriptionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        descriptionLabel.setForeground(
                new Color(
                        100,
                        100,
                        100
                )
        );

        descriptionLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        formPanel.add(descriptionLabel);

        formPanel.add(
                Box.createVerticalStrut(12)
        );

        // short orange accent under the heading
        JPanel headingLine = new JPanel();

        headingLine.setBackground(ORANGE);

        headingLine.setPreferredSize(
                new Dimension(
                        45,
                        4
                )
        );

        headingLine.setMaximumSize(
                new Dimension(
                        45,
                        4
                )
        );

        headingLine.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        formPanel.add(headingLine);

        formPanel.add(
                Box.createVerticalStrut(30)
        );

        // =========================================
        // RESTAURANT NAME
        // =========================================

        JLabel restaurantLabel =
                new JLabel("RESTAURANT NAME");

        restaurantLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        restaurantLabel.setForeground(
                new Color(85, 85, 85)
        );

        restaurantLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        formPanel.add(restaurantLabel);

        formPanel.add(
                Box.createVerticalStrut(8)
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
                Box.createVerticalStrut(22)
        );

        // =========================================
        // PASSWORD
        // =========================================

        JLabel passwordLabel =
                new JLabel("PASSWORD");

        passwordLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        passwordLabel.setForeground(
                new Color(85, 85, 85)
        );

        passwordLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        formPanel.add(passwordLabel);

        formPanel.add(
                Box.createVerticalStrut(8)
        );

        passwordField =
                new JPasswordField();

        styleTextField(
                passwordField
        );

        formPanel.add(
                passwordField
        );

        formPanel.add(
                Box.createVerticalStrut(30)
        );

        // =========================================
        // LOGIN BUTTON
        // =========================================

        loginButton =
                new JButton("LOG IN");

        styleMainButton(
                loginButton
        );

        loginButton.addActionListener(this);

        formPanel.add(loginButton);

        formPanel.add(
                Box.createVerticalStrut(18)
        );

        // =========================================
        // OR DIVIDER
        // =========================================

        addDivider(formPanel);

        formPanel.add(
                Box.createVerticalStrut(18)
        );

        // =========================================
        // CREATE RESTAURANT BUTTON
        // =========================================

        createRestaurantButton =
                new JButton("ADD RESTAURANT");

        styleSecondaryButton(
                createRestaurantButton
        );

        createRestaurantButton.addActionListener(this);

        formPanel.add(
                createRestaurantButton
        );

        gbc.gridy = 0;

        loginPanel.add(
                formPanel,
                gbc
        );

        // Add both sides
        add(brandingPanel);
        add(loginPanel);
    }

    // =========================================
    // OR DIVIDER
    // =========================================

    private void addDivider(JPanel panel)
    {
        JComponent leftLine = createLine();
        JComponent rightLine = createLine();

        JLabel orLabel = new JLabel("OR");

        orLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        orLabel.setForeground(
                new Color(150, 150, 150)
        );

        JPanel row =
                new JPanel(new GridBagLayout());

        row.setOpaque(false);

        GridBagConstraints c =
                new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.gridy = 0;

        c.gridx = 0;
        row.add(leftLine, c);

        c.gridx = 1;
        c.weightx = 0;
        c.insets = new Insets(0, 12, 0, 12);
        row.add(orLabel, c);

        c.gridx = 2;
        c.weightx = 1.0;
        c.insets = new Insets(0, 0, 0, 0);
        row.add(rightLine, c);

        row.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        row.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        20
                )
        );

        panel.add(row);
    }

    private JComponent createLine()
    {
        JComponent line =
                new JComponent()
                {
                    @Override
                    protected void paintComponent(Graphics g)
                    {
                        g.setColor(
                                new Color(228, 228, 228)
                        );

                        g.drawLine(
                                0,
                                getHeight() / 2,
                                getWidth(),
                                getHeight() / 2
                        );
                    }
                };

        line.setPreferredSize(
                new Dimension(10, 1)
        );

        return line;
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
                        14
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
                    public void focusGained(FocusEvent e)
                    {
                        field.repaint();
                    }

                    @Override
                    public void focusLost(FocusEvent e)
                    {
                        field.repaint();
                    }
                }
        );

        field.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        46
                )
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
                        null,
                        ORANGE.darker(),
                        14
                )
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
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
                        46
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );
    }

    // =========================================
    // SECONDARY BUTTON
    // =========================================

    private void styleSecondaryButton(
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
                        14
                )
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        button.setForeground(ORANGE);

        button.setBackground(Color.WHITE);

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
                        44
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );
    }

    // =========================================
    // PILL LABEL (tagline chips)
    // =========================================

    private static class PillLabel extends JLabel
    {
        PillLabel(String text)
        {
            super(text);

            setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            11
                    )
            );

            setForeground(LIGHT_GRAY);

            setBorder(
                    new EmptyBorder(
                            6,
                            12,
                            6,
                            12
                    )
            );

            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(
                    new Color(
                            ORANGE.getRed(),
                            ORANGE.getGreen(),
                            ORANGE.getBlue(),
                            28
                    )
            );

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth() - 1,
                    getHeight() - 1,
                    getHeight(),
                    getHeight()
            );

            g2.setColor(
                    new Color(
                            ORANGE.getRed(),
                            ORANGE.getGreen(),
                            ORANGE.getBlue(),
                            100
                    )
            );

            g2.drawRoundRect(
                    0,
                    0,
                    getWidth() - 1,
                    getHeight() - 1,
                    getHeight(),
                    getHeight()
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }

    // =========================================
    // ROUNDED FIELD BORDER
    // =========================================

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
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            boolean focused = c.hasFocus();

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
        public Insets getBorderInsets(Component c)
        {
            return new Insets(11, 15, 11, 15);
        }

        @Override
        public Insets getBorderInsets(
                Component c,
                Insets insets
        )
        {
            insets.set(11, 15, 11, 15);

            return insets;
        }
    }

    // =========================================
    // ROUNDED BUTTON UI
    // =========================================

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

    // =========================================
    // BUTTON ACTIONS
    // =========================================

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == loginButton)
        {
            String restaurantName =
                    restaurantNameField.getText().trim();

            String restaurantPassword =
                    new String(
                            passwordField.getPassword()
                    );

            if (restaurantName.isEmpty() ||
                restaurantPassword.isEmpty())
            {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter restaurant name and password.",
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            try
            {
            int authenticatedRestaurantId =
        AuthenticationService.authenticateRestaurant(
                restaurantName,
                restaurantPassword
        );

if (authenticatedRestaurantId != -1) {

    appFrame.setRestaurantId(authenticatedRestaurantId);

    restaurantNameField.setText("");
    passwordField.setText("");

    appFrame.showWelcomeScreen(restaurantName);

} else {
    JOptionPane.showMessageDialog(
            this,
            "Invalid restaurant name or password."
    );
                }
            }
            catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(
                        this,
                        "Database error: " + ex.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
        else if (e.getSource() == createRestaurantButton)
        {
            appFrame.showScreen("REGISTRATION");
        }
    }
}