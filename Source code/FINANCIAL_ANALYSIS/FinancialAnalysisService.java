
package FINANCIAL_ANALYSIS;

import DB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FinancialAnalysisService {

    // 1. Get Planned Budget
   public static double getPlannedBudget(
        String budgetPeriod,
        int managerId,
        int restaurantId
) throws SQLException {

    String sql =
            "SELECT NVL(SUM(bd.amount), 0) " +
            "FROM BUDGET b " +
            "JOIN BUDGET_DETAILS bd " +
            "ON b.budget_id = bd.budget_id " +
            "JOIN APP_USER u " +
            "ON b.created_by = u.user_id " +
            "WHERE b.budget_period = ? " +
            "AND b.created_by = ? " +
            "AND u.restaurant_id = ?";

    try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)
    ) {
        statement.setString(1, budgetPeriod);
        statement.setInt(2, managerId);
        statement.setInt(3, restaurantId);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        }
    }

    return 0;
}

    // 2. Get Total Revenue for one restaurant and month
    public static double getTotalRevenue(
            int month,
            int year,
            int restaurantId
    ) throws SQLException {

        String sql =
                "SELECT NVL(SUM(r.amount), 0) " +
                "FROM REVENUE r " +
                "JOIN APP_USER u ON r.recorded_by = u.user_id " +
                "WHERE EXTRACT(MONTH FROM r.revenue_date) = ? " +
                "AND EXTRACT(YEAR FROM r.revenue_date) = ? " +
                "AND u.restaurant_id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, month);
            statement.setInt(2, year);
            statement.setInt(3, restaurantId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble(1);
                }
            }
        }

        return 0;
    }

    // 3. Get Total Expenses for one restaurant and month
    public static double getTotalExpenses(
            int month,
            int year,
            int restaurantId
    ) throws SQLException {

        String sql =
                "SELECT NVL(SUM(e.amount), 0) " +
                "FROM EXPENSE e " +
                "JOIN APP_USER u ON e.recorded_by = u.user_id " +
                "WHERE EXTRACT(MONTH FROM e.expense_date) = ? " +
                "AND EXTRACT(YEAR FROM e.expense_date) = ? " +
                "AND u.restaurant_id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, month);
            statement.setInt(2, year);
            statement.setInt(3, restaurantId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble(1);
                }
            }
        }

        return 0;
    }

    // 4. Calculate Profit / Loss for one restaurant
    public static double getNetResult(
            int month,
            int year,
            int restaurantId
    ) throws SQLException {

        double totalRevenue =
                getTotalRevenue(month, year, restaurantId);

        double totalExpenses =
                getTotalExpenses(month, year, restaurantId);

        return totalRevenue - totalExpenses;
    }

    // 5. Budget Performance record
    public static class BudgetPerformance {

        private String category;
        private double plannedAmount;
        private double actualAmount;

        public BudgetPerformance(
                String category,
                double plannedAmount,
                double actualAmount
        ) {
            this.category = category;
            this.plannedAmount = plannedAmount;
            this.actualAmount = actualAmount;
        }

        public String getCategory() {
            return category;
        }

        public double getPlannedAmount() {
            return plannedAmount;
        }

        public double getActualAmount() {
            return actualAmount;
        }

        public double getDifference() {
            return plannedAmount - actualAmount;
        }
    }

    // 6. Get Budget Performance for the selected budget, manager, and restaurant
    public static List<BudgetPerformance> getBudgetPerformance(
            String budgetPeriod,
            int managerId,
            int restaurantId,
            int month,
            int year
    ) throws SQLException {

        List<BudgetPerformance> records = new ArrayList<>();

        String sql =
                "SELECT bd.category, " +
                "bd.amount AS planned_amount, " +
                "NVL(SUM(e.amount), 0) AS actual_amount " +
                "FROM BUDGET b " +
                "JOIN BUDGET_DETAILS bd " +
                "ON b.budget_id = bd.budget_id " +
                "LEFT JOIN EXPENSE e " +
                "ON bd.detail_id = e.budget_detail_id " +
                "AND EXTRACT(MONTH FROM e.expense_date) = ? " +
                "AND EXTRACT(YEAR FROM e.expense_date) = ? " +
                "WHERE b.budget_period = ? " +
                "AND b.created_by = ? " +
                "AND EXISTS (" +
                "SELECT 1 FROM APP_USER u " +
                "WHERE u.user_id = b.created_by " +
                "AND u.restaurant_id = ? " +
                ") " +
                "GROUP BY bd.category, bd.amount " +
                "ORDER BY bd.category";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, month);
            statement.setInt(2, year);
            statement.setString(3, budgetPeriod);
            statement.setInt(4, managerId);
            statement.setInt(5, restaurantId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String category =
                            resultSet.getString("category");

                    double plannedAmount =
                            resultSet.getDouble("planned_amount");

                    double actualAmount =
                            resultSet.getDouble("actual_amount");

                    records.add(
                            new BudgetPerformance(
                                    category,
                                    plannedAmount,
                                    actualAmount
                            )
                    );
                }
            }
        }

        return records;
    }

    // 7. Get Budget Remaining for one restaurant
    public static double getBudgetRemaining(
            String budgetPeriod,
            int managerId,
            int month,
            int year,
            int restaurantId
    ) throws SQLException {

        double plannedBudget =
                getPlannedBudget(budgetPeriod, managerId, restaurantId);

        double totalExpenses =
                getTotalExpenses(month, year, restaurantId);

        return plannedBudget - totalExpenses;
    }

    // 8. Get Financial Status for one restaurant
    public static String getFinancialStatus(
            int month,
            int year,
            int restaurantId
    ) throws SQLException {

        double netResult =
                getNetResult(month, year, restaurantId);

        if (netResult > 0) {
            return "PROFITABLE";
        } else if (netResult < 0) {
            return "LOSS";
        } else {
            return "BREAK-EVEN";
        }
    }
}
