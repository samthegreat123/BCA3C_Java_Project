
package REVENUE;

import DB.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RevenueService {

    // =========================================
    // REVENUE RECORD MODEL
    // =========================================

    public static class RevenueRecord {

        private final String date;
        private final String category;
        private final String description;
        private final double amount;

        public RevenueRecord(
                String date,
                String category,
                String description,
                double amount
        ) {
            this.date = date;
            this.category = category;
            this.description = description;
            this.amount = amount;
        }

        public String getDate() {
            return date;
        }

        public String getCategory() {
            return category;
        }

        public String getDescription() {
            return description;
        }

        public double getAmount() {
            return amount;
        }
    }

    // =========================================
    // ACCOUNTANT REVENUE CODE
    // =========================================

    /**
     * Saves revenue entered by an Accountant.
     
     * @param revenueDate date the revenue was received
     * @param category revenue category
     * @param amount revenue amount
     * @param description additional details
     * @param accountantId authenticated accountant's user ID
     * @param restaurantId authenticated restaurant's ID
     */
    public static void saveRevenue(
            LocalDate revenueDate,
            String category,
            double amount,
            String description,
            int accountantId,
            int restaurantId
    ) throws SQLException {

        if (revenueDate == null) {
            throw new IllegalArgumentException(
                    "Please select a revenue date."
            );
        }

        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Please enter a revenue category."
            );
        }

        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException(
                    "Revenue amount must be greater than zero."
            );
        }

        if (accountantId <= 0 || restaurantId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid accountant or restaurant."
            );
        }

        String sql =
                "INSERT INTO REVENUE " +
        "(revenue_id, revenue_date, category, amount, description, recorded_by) " +
        "SELECT REVENUE_SEQ.NEXTVAL, ?, ?, ?, ?, u.user_id " +
        "FROM APP_USER u " +
        "WHERE u.user_id = ? " +
        "AND u.restaurant_id = ? " +
        "AND UPPER(u.role) = 'ACCOUNTANT'";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setDate(1, Date.valueOf(revenueDate));
            statement.setString(2, category.trim());
            statement.setDouble(3, amount);
            statement.setString(
                    4,
                    description == null ? "" : description.trim()
            );
            statement.setInt(5, accountantId);
            statement.setInt(6, restaurantId);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted != 1) {
                throw new SQLException(
                        "Revenue was not saved. Verify the accountant " +
                        "account and restaurant."
                );
            }
        }
    }

    /**
     * Retrieves revenue records for an Accountant's restaurant
     * for the selected month and year.
     */
    public static List<RevenueRecord> getAccountantRevenue(
            int month,
            int year,
            int restaurantId
    ) throws SQLException {

        validatePeriod(month, year);

        if (restaurantId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid restaurant ID."
            );
        }

        List<RevenueRecord> records = new ArrayList<>();

        String sql =
                "SELECT r.revenue_date, r.category, " +
                "r.description, r.amount " +
                "FROM REVENUE r " +
                "JOIN APP_USER u ON r.recorded_by = u.user_id " +
                "WHERE u.restaurant_id = ? " +
                "AND EXTRACT(MONTH FROM r.revenue_date) = ? " +
                "AND EXTRACT(YEAR FROM r.revenue_date) = ? " +
                "ORDER BY r.revenue_date DESC, r.revenue_id DESC";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, restaurantId);
            statement.setInt(2, month);
            statement.setInt(3, year);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    Date sqlDate =
                            resultSet.getDate("revenue_date");

                    String date =
                            sqlDate == null ? "" : sqlDate.toString();

                    records.add(
                            new RevenueRecord(
                                    date,
                                    resultSet.getString("category"),
                                    resultSet.getString("description"),
                                    resultSet.getDouble("amount")
                            )
                    );
                }
            }
        }

        return records;
    }

    // =========================================
    // MANAGER REVENUE CODE
    // =========================================

    /**
     * Retrieves revenue records for the Manager's restaurant
     * for the selected month and year.
     */
    public static List<RevenueRecord> getRevenue(
            int month,
            int year,
            int restaurantId
    ) throws SQLException {

        validatePeriod(month, year);

        if (restaurantId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid restaurant ID."
            );
        }

        List<RevenueRecord> records = new ArrayList<>();

        String sql =
                "SELECT r.revenue_date, r.category, " +
                "r.description, r.amount " +
                "FROM REVENUE r " +
                "JOIN APP_USER u ON r.recorded_by = u.user_id " +
                "WHERE u.restaurant_id = ? " +
                "AND EXTRACT(MONTH FROM r.revenue_date) = ? " +
                "AND EXTRACT(YEAR FROM r.revenue_date) = ? " +
                "ORDER BY r.revenue_date DESC, r.revenue_id DESC";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, restaurantId);
            statement.setInt(2, month);
            statement.setInt(3, year);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    Date sqlDate =
                            resultSet.getDate("revenue_date");

                    String date =
                            sqlDate == null ? "" : sqlDate.toString();

                    records.add(
                            new RevenueRecord(
                                    date,
                                    resultSet.getString("category"),
                                    resultSet.getString("description"),
                                    resultSet.getDouble("amount")
                            )
                    );
                }
            }
        }

        return records;
    }

    /**
     * Calculates total revenue for the Manager's restaurant
     * for the selected month and year.
     */
    public static double getTotalRevenue(
            int month,
            int year,
            int restaurantId
    ) throws SQLException {

        validatePeriod(month, year);

        if (restaurantId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid restaurant ID."
            );
        }

        String sql =
                "SELECT NVL(SUM(r.amount), 0) " +
                "FROM REVENUE r " +
                "JOIN APP_USER u ON r.recorded_by = u.user_id " +
                "WHERE u.restaurant_id = ? " +
                "AND EXTRACT(MONTH FROM r.revenue_date) = ? " +
                "AND EXTRACT(YEAR FROM r.revenue_date) = ?";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, restaurantId);
            statement.setInt(2, month);
            statement.setInt(3, year);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getDouble(1);
                }
            }
        }

        return 0;
    }

    // =========================================
    // SHARED VALIDATION
    // =========================================

    private static void validatePeriod(
            int month,
            int year
    ) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException(
                    "Month must be between 1 and 12."
            );
        }

        if (year < 1) {
            throw new IllegalArgumentException(
                    "Invalid year."
            );
        }
    }
}
