
package GUI;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel screenPanel;
    private WelcomePanel welcomePanel;

    private int managerId;
    private int restaurantId;
    private int accountantId;

    private String selectedFinancialPeriod;

    private ManagerDash managerDash;
    private RevenuePanel revenuePanel;
    private AccountantExpense accountantExpense;
    private TransactionPanel transactionPanel;
    private AccountantDash accountantDash;

    public AppFrame() {

        setTitle("Restaurant Budget Planner");

        setSize(900, 600);

        setMinimumSize(new Dimension(850, 550));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        // CardLayout
        cardLayout = new CardLayout();
        screenPanel = new JPanel(cardLayout);

        // Login screen
        LoginPanel loginPanel = new LoginPanel(this);
        screenPanel.add(loginPanel, "LOGIN");

        // Registration screen
        RestaurantRegistrationPanel registrationPanel =
                new RestaurantRegistrationPanel(this);
        screenPanel.add(registrationPanel, "REGISTRATION");

        // Welcome screen
        welcomePanel = new WelcomePanel(this);
        screenPanel.add(welcomePanel, "WELCOME");

        // Manager Login screen
        ManagerLoginPanel managerLoginPanel =
                new ManagerLoginPanel(this);
        screenPanel.add(managerLoginPanel, "MANAGER_LOGIN");

        // Accountant Login screen
        AccountantLoginPanel accountantLoginPanel =
                new AccountantLoginPanel(this);
        screenPanel.add(accountantLoginPanel, "ACCOUNTANT_LOGIN");

        // Manager Dashboard screen
        managerDash = new ManagerDash(this);
        screenPanel.add(managerDash, "MANAGER_DASHBOARD");

        // Accountant Dashboard screen
         accountantDash = new AccountantDash(this);
        screenPanel.add(accountantDash, "ACCOUNTANT_DASHBOARD");

        // Budget Management screen
        BudgetPanel budgetPanel = new BudgetPanel(this);
        screenPanel.add(budgetPanel, "BUDGET");

        // Manager Revenue screen
        revenuePanel = new RevenuePanel(this);
        screenPanel.add(revenuePanel, "REVENUE");

        // Financial Analysis screen
        FinancialAnalysis financialAnalysis = new FinancialAnalysis(this);
        screenPanel.add(financialAnalysis, "FINANCIAL_ANALYSIS");

        // Reports screen
        Report report = new Report(this);
        screenPanel.add(report, "REPORT");

        // Accountant Revenue screen
        AccountantRevenue accountantRevenue = new AccountantRevenue(this);
        screenPanel.add(accountantRevenue, "ACCOUNTANT_REVENUE");

        // Accountant Expense screen
        accountantExpense = new AccountantExpense(this);
screenPanel.add(accountantExpense, "ACCOUNTANT_EXPENSE");

       // Accountant Transactions screen
     transactionPanel = new TransactionPanel(this);
      screenPanel.add(transactionPanel, "ACCOUNTANT_TRANSACTIONS");

        // Add screen panel
        add(screenPanel);

        // Show Login first
        cardLayout.show(screenPanel, "LOGIN");

        setVisible(true);
    }

    // Switch between screens
   public void showScreen(String screenName) {

        if (screenName.equals("MANAGER_DASHBOARD")) {
            managerDash.refreshDashboard();
        }

        if (screenName.equals("ACCOUNTANT_DASHBOARD")) {
            accountantDash.refreshDashboard();
            accountantDash.refreshRecentTransactions();
        }

        if (screenName.equals("REVENUE")) {
            revenuePanel.refreshRevenue();
        }

        if (screenName.equals("ACCOUNTANT_EXPENSE")) {
            accountantExpense.refreshExpenses();
        }

        if (screenName.equals("ACCOUNTANT_TRANSACTIONS")) {
            transactionPanel.refreshTransactions();
        }

        cardLayout.show(screenPanel, screenName);
    }

    // Manager ID setter and getter
    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getManagerId() {
        return managerId;
    }

    // Restaurant ID setter and getter
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    // Accountant ID setter and getter
    public void setAccountantId(int accountantId) {
        this.accountantId = accountantId;
    }

    public int getAccountantId() {
        return accountantId;
    }

    // Financial period setter and getter
    public void setSelectedFinancialPeriod(String selectedFinancialPeriod) {
        this.selectedFinancialPeriod = selectedFinancialPeriod;
    }

    public String getSelectedFinancialPeriod() {
        return selectedFinancialPeriod;
    }

    // Show Welcome screen with restaurant name
    public void showWelcomeScreen(String restaurantName) {

        welcomePanel.setRestaurantName(restaurantName);

        cardLayout.show(screenPanel, "WELCOME");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppFrame::new);
    }
}

