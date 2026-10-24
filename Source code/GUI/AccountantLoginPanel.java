package GUI;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.SQLException;
import AUTHENTICATION.AuthenticationService;

public class AccountantLoginPanel extends JPanel
{
    private AppFrame appFrame;

    private final Color ORANGE =
            new Color(245, 140, 30);

    private final Color DARK =
            new Color(15, 15, 15);


    public AccountantLoginPanel(
            AppFrame appFrame
    )
    {
        this.appFrame = appFrame;

        setLayout(
                new GridLayout(1, 2)
        );


        // =========================
        // LEFT SIDE
        // =========================

        JPanel leftPanel =
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
                                                    w / 2,
                                                    (int) (h * 0.9)
                                            ),
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

        leftPanel.setLayout(
                new BorderLayout()
        );

        leftPanel.setBackground(
                DARK
        );


        // -------------------------
        // Branding
        // -------------------------

        JPanel brandingPanel =
                new JPanel();

        brandingPanel.setLayout(
                new BoxLayout(
                        brandingPanel,
                        BoxLayout.Y_AXIS
                )
        );

        brandingPanel.setOpaque(
                false
        );

        brandingPanel.setBorder(
                new EmptyBorder(
                        35,
                        35,
                        15,
                        35
                )
        );


        JLabel brandLabel =
                new JLabel(
                        "RESTAURANT BUDGET PLANNER"
                );

        brandLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        brandLabel.setForeground(
                Color.WHITE
        );

        brandingPanel.add(
                brandLabel
        );


        brandingPanel.add(
                Box.createVerticalStrut(10)
        );


        JPanel orangeLine =
                new JPanel();

        orangeLine.setBackground(
                ORANGE
        );

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
                Component.LEFT_ALIGNMENT
        );

        brandingPanel.add(
                orangeLine
        );


        brandingPanel.add(
                Box.createVerticalStrut(30)
        );


        JLabel taglineLabel =
                new JLabel(
                        "<html>Better Planning.<br>Bigger Profits.</html>"
                );

        taglineLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        taglineLabel.setForeground(
                new Color(
                        235,
                        235,
                        235
                )
        );

        brandingPanel.add(
                taglineLabel
        );


        brandingPanel.add(
                Box.createVerticalStrut(20)
        );


        // Small chips under the tagline

        JPanel chipsPanel =
                new JPanel();

        chipsPanel.setLayout(
                new BoxLayout(
                        chipsPanel,
                        BoxLayout.X_AXIS
                )
        );

        chipsPanel.setOpaque(
                false
        );

        chipsPanel.add(
                new PillLabel(
                        "Revenue",
                        ORANGE
                )
        );

        chipsPanel.add(
                Box.createHorizontalStrut(8)
        );

        chipsPanel.add(
                new PillLabel(
                        "Expenses",
                        ORANGE
                )
        );

        chipsPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        chipsPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        30
                )
        );

        brandingPanel.add(
                chipsPanel
        );


        leftPanel.add(
                brandingPanel,
                BorderLayout.NORTH
        );


        add(
                leftPanel
        );


        // =========================
        // RIGHT SIDE
        // =========================

        JPanel rightPanel =
                new JPanel();

        rightPanel.setLayout(
                new GridBagLayout()
        );

        rightPanel.setBackground(
                Color.WHITE
        );


        JPanel formPanel =
                new JPanel();

        formPanel.setLayout(
                new BoxLayout(
                        formPanel,
                        BoxLayout.Y_AXIS
                )
        );

        formPanel.setBackground(
                Color.WHITE
        );

        formPanel.setPreferredSize(
                new Dimension(
                        360,
                        500
                )
        );


        // =========================
        // ACCOUNTANT ICON
        // =========================

        JLabel accountantIcon =
                new JLabel(
                        "A",
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
                                        35
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
                                        140
                                )
                        );

                        g2.setStroke(
                                new BasicStroke(2f)
                        );

                        g2.drawOval(
                                1,
                                1,
                                getWidth() - 3,
                                getHeight() - 3
                        );

                        g2.dispose();

                        super.paintComponent(g);
                    }
                };

        accountantIcon.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        accountantIcon.setForeground(
                ORANGE
        );

        accountantIcon.setPreferredSize(
                new Dimension(68, 68)
        );

        accountantIcon.setMinimumSize(
                new Dimension(68, 68)
        );

        accountantIcon.setMaximumSize(
                new Dimension(68, 68)
        );

        accountantIcon.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        formPanel.add(
                accountantIcon
        );


        formPanel.add(
                Box.createVerticalStrut(14)
        );


        // =========================
        // TITLE
        // =========================

        JLabel titleLabel =
                new JLabel(
                        "ACCOUNTANT LOGIN"
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

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        formPanel.add(
                titleLabel
        );


        formPanel.add(
                Box.createVerticalStrut(6)
        );


        // =========================
        // SUBTITLE
        // =========================

        JLabel subtitleLabel =
                new JLabel(
                        "Sign in to access the accountant dashboard"
                );

        subtitleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        subtitleLabel.setForeground(
                new Color(
                        120,
                        120,
                        120
                )
        );

        subtitleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        formPanel.add(
                subtitleLabel
        );


        formPanel.add(
                Box.createVerticalStrut(12)
        );


        JPanel titleAccent =
                new JPanel();

        titleAccent.setBackground(
                ORANGE
        );

        titleAccent.setPreferredSize(
                new Dimension(
                        40,
                        4
                )
        );

        titleAccent.setMaximumSize(
                new Dimension(
                        40,
                        4
                )
        );

        titleAccent.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        formPanel.add(
                titleAccent
        );


        formPanel.add(
                Box.createVerticalStrut(28)
        );


        // =========================
        // USERNAME LABEL
        // =========================

        JLabel usernameLabel =
                new JLabel(
                        "Username"
                );

        usernameLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        usernameLabel.setForeground(
                new Color(
                        60,
                        60,
                        60
                )
        );

        usernameLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // keep the label aligned with the left edge of the field
        usernameLabel.setPreferredSize(
                new Dimension(280, 18)
        );

        usernameLabel.setMaximumSize(
                new Dimension(280, 18)
        );

        formPanel.add(
                usernameLabel
        );


        formPanel.add(
                Box.createVerticalStrut(7)
        );


        // =========================
        // USERNAME FIELD
        // =========================

        JTextField usernameField =
                new JTextField();

        styleTextField(
                usernameField
        );

        formPanel.add(
                usernameField
        );


        formPanel.add(
                Box.createVerticalStrut(18)
        );


        // =========================
        // PASSWORD LABEL
        // =========================

        JLabel passwordLabel =
                new JLabel(
                        "Password"
                );

        passwordLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        passwordLabel.setForeground(
                new Color(
                        60,
                        60,
                        60
                )
        );

        passwordLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        passwordLabel.setPreferredSize(
                new Dimension(280, 18)
        );

        passwordLabel.setMaximumSize(
                new Dimension(280, 18)
        );

        formPanel.add(
                passwordLabel
        );


        formPanel.add(
                Box.createVerticalStrut(7)
        );


        // =========================
        // PASSWORD FIELD
        // =========================

        JPasswordField passwordField =
                new JPasswordField();

        styleTextField(
                passwordField
        );

        formPanel.add(
                passwordField
        );


        formPanel.add(
                Box.createVerticalStrut(28)
        );


        // =========================
        // LOGIN BUTTON
        // =========================

        JButton loginButton =
                new JButton(
                        "LOG IN"
                );

        loginButton.setUI(
                new RoundedButtonUI(
                        ORANGE,
                        ORANGE.darker(),
                        14
                )
        );

        loginButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        loginButton.setForeground(
                Color.WHITE
        );

        loginButton.setBackground(
                ORANGE
        );

        loginButton.setFocusPainted(
                false
        );

        loginButton.setBorderPainted(
                false
        );

        loginButton.setContentAreaFilled(
                false
        );

        loginButton.setOpaque(
                false
        );

        loginButton.setRolloverEnabled(
                true
        );

        loginButton.setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        20,
                        12,
                        20
                )
        );

        loginButton.setPreferredSize(
                new Dimension(
                        280,
                        46
                )
        );

        loginButton.setMaximumSize(
                new Dimension(
                        280,
                        46
                )
        );

        loginButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loginButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


 //Login Action Buttom..
        loginButton.addActionListener(
    e ->
    {
        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        if (username.isEmpty() ||
            password.isEmpty())
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password.",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        try
        {
            int authenticatedAccountantId =
        AuthenticationService.authenticateAccountant(
                username,
                password
        );

if (authenticatedAccountantId > 0) {
    appFrame.setAccountantId(authenticatedAccountantId);

    usernameField.setText("");
    passwordField.setText("");

    appFrame.showScreen("ACCOUNTANT_DASHBOARD");
} else {
    JOptionPane.showMessageDialog(
            this,
            "Invalid accountant username or password.",
            "Login Error",
            JOptionPane.ERROR_MESSAGE
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
);


        formPanel.add(
                loginButton
        );


        formPanel.add(
                Box.createVerticalStrut(14)
        );


        // =========================
        // BACK BUTTON
        // =========================

        JButton backButton =
                new JButton(
                        "\u2190  Back to Welcome"
                );

        backButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        backButton.setForeground(
                ORANGE
        );

        backButton.setBackground(
                Color.WHITE
        );

        backButton.setFocusPainted(
                false
        );

        backButton.setBorderPainted(
                false
        );

        backButton.setContentAreaFilled(
                false
        );

        backButton.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        10,
                        5,
                        10
                )
        );

        backButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        backButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        backButton.addMouseListener(
                new MouseAdapter()
                {
                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    )
                    {
                        backButton.setForeground(
                                ORANGE.darker()
                        );
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e
                    )
                    {
                        backButton.setForeground(
                                ORANGE
                        );
                    }
                }
        );


        backButton.addActionListener(
                e ->
                {
                    appFrame.showScreen(
                            "WELCOME"
                    );
                }
        );


        formPanel.add(
                backButton
        );


        // =========================
        // ADD FORM
        // =========================

        rightPanel.add(
                formPanel
        );


        add(
                rightPanel
        );
    }


    // =========================
    // TEXT FIELD STYLE
    // =========================

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

        field.setForeground(
                Color.BLACK
        );

        field.setBackground(
                Color.WHITE
        );

        field.setCaretColor(
                ORANGE
        );

        field.setBorder(
                new RoundedFieldBorder(
                        ORANGE,
                        new Color(
                                210,
                                210,
                                210
                        )
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

        field.setPreferredSize(
                new Dimension(
                        280,
                        46
                )
        );

        field.setMaximumSize(
                new Dimension(
                        280,
                        46
                )
        );

        field.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );
    }


    // =========================
    // PILL LABEL (small chips)
    // =========================

    private static class PillLabel
            extends JLabel
    {
        private final Color accent;

        PillLabel(
                String text,
                Color accent
        )
        {
            super(text);

            this.accent = accent;

            setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            11
                    )
            );

            setForeground(
                    new Color(
                            190,
                            190,
                            190
                    )
            );

            setBorder(
                    new EmptyBorder(
                            6,
                            14,
                            6,
                            14
                    )
            );

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

            g2.setColor(
                    new Color(
                            accent.getRed(),
                            accent.getGreen(),
                            accent.getBlue(),
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
                            accent.getRed(),
                            accent.getGreen(),
                            accent.getBlue(),
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


    // =========================
    // ROUNDED FIELD BORDER
    // =========================

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


    // =========================
    // ROUNDED BUTTON UI
    // =========================

    private static class RoundedButtonUI
            extends BasicButtonUI
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