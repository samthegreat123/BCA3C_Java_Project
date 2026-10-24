
package BUDGET;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DBConnection;

/* This handles the creation, storage and updating of the BUDGET! */

public class BudgetService
{
    // ==================================================
    // SAVE NEW BUDGET
    // ==================================================

    public static void saveBudget(
            String budgetPeriod,
            int managerId,
            String[] categories,
            double[] amounts)
            throws SQLException
    {
        Connection connection = null;

        try
        {
            connection =
                    DBConnection.getConnection();

            connection.setAutoCommit(false);

            // CREATE BUDGET

            String budgetSql =
                    "INSERT INTO BUDGET " +
                    "(budget_id, budget_period, created_by) " +
                    "VALUES " +
                    "(NVL((SELECT MAX(budget_id) " +
                    "FROM BUDGET), 0) + 1, ?, ?)";

            try (
                PreparedStatement statement =
                        connection.prepareStatement(
                                budgetSql
                        )
            )
            {
                statement.setString(
                        1,
                        budgetPeriod
                );

                statement.setInt(
                        2,
                        managerId
                );

                statement.executeUpdate();
            }

            // GET BUDGET ID

            int budgetId;

            String idSql =
                    "SELECT MAX(budget_id) " +
                    "FROM BUDGET";

            try (
                PreparedStatement statement =
                        connection.prepareStatement(
                                idSql
                        );

                ResultSet resultSet =
                        statement.executeQuery()
            )
            {
                resultSet.next();

                budgetId =
                        resultSet.getInt(1);
            }

            // CREATE BUDGET DETAILS

            String detailSql =
                    "INSERT INTO BUDGET_DETAILS " +
                    "(detail_id, budget_id, category, amount) " +
                    "VALUES " +
                    "(NVL((SELECT MAX(detail_id) " +
                    "FROM BUDGET_DETAILS), 0) + 1, ?, ?, ?)";

            try (
                PreparedStatement statement =
                        connection.prepareStatement(
                                detailSql
                        )
            )
            {
                for (int i = 0;
                     i < categories.length;
                     i++)
                {
                    statement.setInt(
                            1,
                            budgetId
                    );

                    statement.setString(
                            2,
                            categories[i]
                    );

                    statement.setDouble(
                            3,
                            amounts[i]
                    );

                    statement.executeUpdate();
                }
            }

            // SAVE EVERYTHING

            connection.commit();
        }
        catch (SQLException ex)
        {
            if (connection != null)
            {
                connection.rollback();
            }

            throw ex;
        }
        finally
        {
            if (connection != null)
            {
                connection.close();
            }
        }
    }


    // ==================================================
    // GET EXISTING BUDGET DETAILS
    // ==================================================

    public static List<String[]> getBudgetDetails(
            String budgetPeriod,
            int managerId)
            throws SQLException
    {
        List<String[]> details =
                new ArrayList<>();

        String sql =
                "SELECT bd.category, bd.amount " +
                "FROM BUDGET b " +
                "JOIN BUDGET_DETAILS bd " +
                "ON b.budget_id = bd.budget_id " +
                "WHERE b.budget_period = ? " +
                "AND b.created_by = ? " +
                "ORDER BY bd.detail_id";

        try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
        )
        {
            statement.setString(
                    1,
                    budgetPeriod
            );

            statement.setInt(
                    2,
                    managerId
            );

            try (
                ResultSet resultSet =
                        statement.executeQuery()
            )
            {
                while (resultSet.next())
                {
                    String category =
                            resultSet.getString(
                                    "category"
                            );

                    String amount =
                            resultSet.getString(
                                    "amount"
                            );

                    details.add(
                            new String[]
                            {
                                category,
                                amount
                            }
                    );
                }
            }
        }

        return details;
    }


    // ==================================================
    // GET EXISTING BUDGET ID
    // ==================================================

    public static int getBudgetId(
            String budgetPeriod,
            int managerId)
            throws SQLException
    {
        String sql =
                "SELECT budget_id " +
                "FROM BUDGET " +
                "WHERE budget_period = ? " +
                "AND created_by = ? " +
                "ORDER BY budget_id DESC";

        try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
        )
        {
            statement.setString(
                    1,
                    budgetPeriod
            );

            statement.setInt(
                    2,
                    managerId
            );

            try (
                ResultSet resultSet =
                        statement.executeQuery()
            )
            {
                if (resultSet.next())
                {
                    return resultSet.getInt(
                            "budget_id"
                    );
                }
            }
        }

        return -1;
    }


    // ==================================================
    // UPDATE EXISTING BUDGET
    // ==================================================

    public static void updateBudget(
            int budgetId,
            String[] categories,
            double[] amounts)
            throws SQLException
    {
        Connection connection = null;

        try
        {
            System.out.println(
                    "UPDATE START"
            );

            connection =
                    DBConnection.getConnection();

            System.out.println(
                    "CONNECTION CREATED"
            );

            connection.setAutoCommit(false);

            System.out.println(
                    "AUTO COMMIT OFF"
            );


            // GET EXISTING DETAIL IDS

            List<Integer> detailIds =
                    new ArrayList<>();

            String selectSql =
                    "SELECT detail_id " +
                    "FROM BUDGET_DETAILS " +
                    "WHERE budget_id = ? " +
                    "ORDER BY detail_id";

            try (
                PreparedStatement statement =
                        connection.prepareStatement(
                                selectSql
                        )
            )
            {
                statement.setInt(
                        1,
                        budgetId
                );

                System.out.println(
                        "GETTING DETAIL IDS"
                );

                try (
                    ResultSet resultSet =
                            statement.executeQuery()
                )
                {
                    System.out.println(
                            "DETAIL IDS QUERY FINISHED"
                    );

                    while (resultSet.next())
                    {
                        detailIds.add(
                                resultSet.getInt(
                                        "detail_id"
                                )
                        );
                    }
                }
            }


            // UPDATE EXISTING DETAILS

            String updateSql =
                    "UPDATE BUDGET_DETAILS " +
                    "SET category = ?, amount = ? " +
                    "WHERE detail_id = ?";

            try (
                PreparedStatement statement =
                        connection.prepareStatement(
                                updateSql
                        )
            )
            {
                int existingCount =
                        Math.min(
                                detailIds.size(),
                                categories.length
                        );

                for (int i = 0;
                     i < existingCount;
                     i++)
                {
                    statement.setString(
                            1,
                            categories[i]
                    );

                    statement.setDouble(
                            2,
                            amounts[i]
                    );

                    statement.setInt(
                            3,
                            detailIds.get(i)
                    );

                    System.out.println(
                            "UPDATING DETAIL ID: "
                            + detailIds.get(i)
                    );

                    statement.executeUpdate();

                    System.out.println(
                            "DETAIL UPDATED"
                    );
                }
            }


            // ADD NEW DETAILS IF NEEDED

            if (categories.length > detailIds.size())
            {
                String insertSql =
                        "INSERT INTO BUDGET_DETAILS " +
                        "(detail_id, budget_id, category, amount) " +
                        "VALUES " +
                        "(NVL((SELECT MAX(detail_id) " +
                        "FROM BUDGET_DETAILS), 0) + 1, ?, ?, ?)";

                try (
                    PreparedStatement statement =
                            connection.prepareStatement(
                                    insertSql
                            )
                )
                {
                    for (int i = detailIds.size();
                         i < categories.length;
                         i++)
                    {
                        statement.setInt(
                                1,
                                budgetId
                        );

                        statement.setString(
                                2,
                                categories[i]
                        );

                        statement.setDouble(
                                3,
                                amounts[i]
                        );

                        System.out.println(
                                "ADDING NEW DETAIL"
                        );

                        statement.executeUpdate();

                        System.out.println(
                                "NEW DETAIL ADDED"
                        );
                    }
                }
            }


            // REMOVE EXTRA DETAILS

            if (categories.length < detailIds.size())
            {
                String deleteSql =
                        "DELETE FROM BUDGET_DETAILS " +
                        "WHERE detail_id = ?";

                try (
                    PreparedStatement statement =
                            connection.prepareStatement(
                                    deleteSql
                            )
                )
                {
                    for (int i = categories.length;
                         i < detailIds.size();
                         i++)
                    {
                        statement.setInt(
                                1,
                                detailIds.get(i)
                        );

                        System.out.println(
                                "DELETING DETAIL ID: "
                                + detailIds.get(i)
                        );

                        statement.executeUpdate();

                        System.out.println(
                                "DETAIL DELETED"
                        );
                    }
                }
            }


            // SAVE UPDATE

            System.out.println(
                    "COMMIT START"
            );

            connection.commit();

            System.out.println(
                    "COMMIT FINISHED"
            );
        }
        catch (SQLException ex)
        {
            System.out.println(
                    "SQL EXCEPTION: "
                    + ex.getMessage()
            );

            if (connection != null)
            {
                connection.rollback();
            }

            throw ex;
        }
        finally
        {
            if (connection != null)
            {
                connection.close();

                System.out.println(
                        "CONNECTION CLOSED"
                );
            }
        }
    }
}