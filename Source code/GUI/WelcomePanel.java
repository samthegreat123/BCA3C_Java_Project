package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WelcomePanel
        extends JPanel
{
    private AppFrame appFrame;

    private JLabel restaurantLabel;

    private final Color ORANGE =
            new Color(245, 140, 30);

    private final Color DARK =
            new Color(15, 15, 15);

    private final Color CARD =
            Color.WHITE;


    // =========================================
    // CONSTRUCTOR
    // =========================================

    public WelcomePanel(
            AppFrame appFrame
    )
    {
        this.appFrame = appFrame;

        setLayout(
                new BorderLayout()
        );

        setBackground(DARK);

        setBorder(
                new EmptyBorder(
                        35,
                        50,
                        30,
                        50
                )
        );


        // =====================================
        // TOP BRANDING
        // =====================================

        JPanel topPanel =
                new JPanel();

        topPanel.setLayout(
                new BoxLayout(
                        topPanel,
                        BoxLayout.Y_AXIS
                )
        );

        topPanel.setOpaque(false);


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

        brandLabel.setForeground(Color.WHITE);

        brandLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        topPanel.add(brandLabel);

        topPanel.add(
                Box.createVerticalStrut(10)
        );


        JPanel orangeLine =
                new JPanel();

        orangeLine.setBackground(ORANGE);

        orangeLine.setMaximumSize(
                new Dimension(
                        55,
                        4
                )
        );

        orangeLine.setPreferredSize(
                new Dimension(
                        55,
                        4
                )
        );

        orangeLine.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        topPanel.add(orangeLine);


        add(
                topPanel,
                BorderLayout.NORTH
        );


        // =====================================
        // CENTER CONTENT
        // =====================================

        JPanel centerPanel =
                new JPanel();

        centerPanel.setLayout(
                new BoxLayout(
                        centerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        centerPanel.setOpaque(false);


        centerPanel.add(
                Box.createVerticalGlue()
        );


        // =====================================
        // WELCOME
        // =====================================

        JLabel welcomeLabel =
                new JLabel(
                        "Hello, Welcome"
                );

        welcomeLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        38
                )
        );

        welcomeLabel.setForeground(Color.WHITE);

        welcomeLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        centerPanel.add(welcomeLabel);

        centerPanel.add(
                Box.createVerticalStrut(14)
        );


        // =====================================
        // RESTAURANT NAME (pill)
        // =====================================

        restaurantLabel =
                new JLabel(
                        "YOUR RESTAURANT"
                );

        restaurantLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        restaurantLabel.setForeground(ORANGE);

        restaurantLabel.setBorder(
                new EmptyBorder(
                        7,
                        22,
                        7,
                        22
                )
        );

        PillPanel restaurantPill =
                new PillPanel(
                        ORANGE
                );

        restaurantPill.add(
                restaurantLabel,
                BorderLayout.CENTER
        );

        restaurantPill.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        centerPanel.add(
                restaurantPill
        );

        centerPanel.add(
                Box.createVerticalStrut(40)
        );


        // =====================================
        // ROLE CARDS
        // =====================================

        JPanel cardsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                28,
                                0
                        )
                );

        cardsPanel.setOpaque(false);

        cardsPanel.setMaximumSize(
                new Dimension(
                        720,
                        250
                )
        );


        // =====================================
        // MANAGER CARD
        // =====================================

        JPanel managerCard =
                createRoleCard(
                        "M",
                        "MANAGER",
                        "Manage budgets & reports"
                );

        managerCard.addMouseListener(
                new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(
                            MouseEvent e
                    )
                    {
                        appFrame.showScreen(
                                "MANAGER_LOGIN"
                        );
                    }
                }
        );


        // =====================================
        // ACCOUNTANT CARD
        // =====================================

        JPanel accountantCard =
                createRoleCard(
                        "A",
                        "ACCOUNTANT",
                        "Record expenses & revenue"
                );

        accountantCard.addMouseListener(
                new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(
                            MouseEvent e
                    )
                    {
                        appFrame.showScreen(
                                "ACCOUNTANT_LOGIN"
                        );
                    }
                }
        );


        cardsPanel.add(
                managerCard
        );

        cardsPanel.add(
                accountantCard
        );

        centerPanel.add(
                cardsPanel
        );

        centerPanel.add(
                Box.createVerticalStrut(28)
        );


        // =====================================
        // INSTRUCTION
        // =====================================

        JLabel instructionLabel =
                new JLabel(
                        "Select your role to continue"
                );

        instructionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        instructionLabel.setForeground(
                new Color(
                        160,
                        160,
                        160
                )
        );

        instructionLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        centerPanel.add(
                instructionLabel
        );


        centerPanel.add(
                Box.createVerticalGlue()
        );


        add(
                centerPanel,
                BorderLayout.CENTER
        );
    }


    // =========================================
    // BACKGROUND GLOW
    // =========================================

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
                    Math.max(w, h) * 0.55f;

            RadialGradientPaint glow =
                    new RadialGradientPaint(
                            new Point(
                                    w / 2,
                                    (int) (h * 0.38)
                            ),
                            radius,
                            new float[] { 0f, 1f },
                            new Color[] {
                                    new Color(
                                            ORANGE.getRed(),
                                            ORANGE.getGreen(),
                                            ORANGE.getBlue(),
                                            38
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


    // =========================================
    // CREATE ROLE CARD
    // =========================================

    private JPanel createRoleCard(
            String icon,
            String title,
            String description
    )
    {
        RoundedCard card =
                new RoundedCard(
                        CARD,
                        ORANGE
                );

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBorder(
                new EmptyBorder(
                        28,
                        25,
                        24,
                        25
                )
        );

        card.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        // =====================================
        // ICON
        // =====================================

        IconCircle iconCircle =
                new IconCircle(
                        icon,
                        ORANGE
                );

        iconCircle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(iconCircle);

        card.add(
                Box.createVerticalStrut(16)
        );


        // =====================================
        // TITLE
        // =====================================

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        19
                )
        );

        titleLabel.setForeground(Color.BLACK);

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(titleLabel);

        card.add(
                Box.createVerticalStrut(6)
        );


        // =====================================
        // DESCRIPTION
        // =====================================

        JLabel descriptionLabel =
                new JLabel(description);

        descriptionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        descriptionLabel.setForeground(
                new Color(
                        90,
                        90,
                        90
                )
        );

        descriptionLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(descriptionLabel);

        card.add(
                Box.createVerticalStrut(18)
        );


        // =====================================
        // CONTINUE HINT
        // =====================================

        JLabel continueLabel =
                new JLabel(
                        "Continue  \u2192"
                );

        continueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        continueLabel.setForeground(ORANGE);

        continueLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(continueLabel);


        // =====================================
        // HOVER EFFECT
        // =====================================

        card.addMouseListener(
                new MouseAdapter()
                {
                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    )
                    {
                        card.hover = true;
                        card.repaint();
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e
                    )
                    {
                        card.hover = false;
                        card.repaint();
                    }
                }
        );

        return card;
    }


    // =========================================
    // SET RESTAURANT NAME
    // =========================================

    public void setRestaurantName(
            String restaurantName
    )
    {
        restaurantLabel.setText(
                restaurantName
        );
    }


    // =========================================
    // ROUNDED ROLE CARD
    // =========================================

    private static class RoundedCard
            extends JPanel
    {
        private final Color fill;
        private final Color accent;

        boolean hover = false;

        RoundedCard(
                Color fill,
                Color accent
        )
        {
            this.fill = fill;
            this.accent = accent;

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

            // soft outer glow on hover
            if (hover)
            {
                for (int i = 6; i >= 1; i--)
                {
                    g2.setColor(
                            new Color(
                                    accent.getRed(),
                                    accent.getGreen(),
                                    accent.getBlue(),
                                    12
                            )
                    );

                    g2.fillRoundRect(
                            4 - i,
                            4 - i,
                            w - 8 + (i * 2),
                            h - 8 + (i * 2),
                            26 + i,
                            26 + i
                    );
                }
            }

            // card body
            g2.setColor(
                    hover
                            ? new Color(255, 248, 238)
                            : fill
            );

            g2.fillRoundRect(
                    4,
                    4,
                    w - 8,
                    h - 8,
                    24,
                    24
            );

            // orange border
            g2.setColor(accent);

            g2.setStroke(
                    new BasicStroke(
                            hover ? 2.5f : 2f
                    )
            );

            g2.drawRoundRect(
                    4,
                    4,
                    w - 9,
                    h - 9,
                    24,
                    24
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }


    // =========================================
    // ICON CIRCLE
    // =========================================

    private static class IconCircle
            extends JComponent
    {
        private final String letter;
        private final Color accent;

        IconCircle(
                String letter,
                Color accent
        )
        {
            this.letter = letter;
            this.accent = accent;

            Dimension size =
                    new Dimension(64, 64);

            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
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

            g2.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON
            );

            boolean active =
                    getParent() instanceof RoundedCard
                            && ((RoundedCard) getParent()).hover;

            int w = getWidth();
            int h = getHeight();

            g2.setColor(
                    active
                            ? accent
                            : new Color(
                                    accent.getRed(),
                                    accent.getGreen(),
                                    accent.getBlue(),
                                    40
                            )
            );

            g2.fillOval(0, 0, w - 1, h - 1);

            g2.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            28
                    )
            );

            g2.setColor(
                    active
                            ? Color.WHITE
                            : accent
            );

            FontMetrics fm =
                    g2.getFontMetrics();

            int x =
                    (w - fm.stringWidth(letter)) / 2;

            int y =
                    (h - fm.getHeight()) / 2
                            + fm.getAscent();

            g2.drawString(letter, x, y);

            g2.dispose();
        }
    }


    // =========================================
    // PILL PANEL (restaurant name)
    // =========================================

    private static class PillPanel
            extends JPanel
    {
        private final Color accent;

        PillPanel(
                Color accent
        )
        {
            super(new BorderLayout());

            this.accent = accent;

            setOpaque(false);
        }

        @Override
        public Dimension getMaximumSize()
        {
            return getPreferredSize();
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
                    new Color(
                            accent.getRed(),
                            accent.getGreen(),
                            accent.getBlue(),
                            32
                    )
            );

            g2.fillRoundRect(0, 0, w - 1, h - 1, h, h);

            g2.setColor(
                    new Color(
                            accent.getRed(),
                            accent.getGreen(),
                            accent.getBlue(),
                            110
                    )
            );

            g2.drawRoundRect(0, 0, w - 1, h - 1, h, h);

            g2.dispose();

            super.paintComponent(g);
        }
    }
}