package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TestBudgetInsert
{
    public static void main(String[] args)
    {
        Connection connection = null;

        try
        {
            System.out.println("TEST START");

            connection =
                    DBConnection.getConnection();

            System.out.println("CONNECTION CREATED");

            connection.setAutoCommit(false);

            String sql =
                    "INSERT INTO BUDGET_DETAILS " +
                    "(detail_id, budget_id, category, amount) " +
                    "VALUES " +
                    "(NVL((SELECT MAX(detail_id) " +
                    "FROM BUDGET_DETAILS), 0) + 1, ?, ?, ?)";

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            System.out.println("STATEMENT CREATED");

            statement.setInt(1, 1);
            statement.setString(2, "Test");
            statement.setDouble(3, 100);

            System.out.println("VALUES SET");

            statement.executeUpdate();

            System.out.println("INSERT FINISHED");

            connection.rollback();

            System.out.println("ROLLBACK FINISHED");

            statement.close();
            connection.close();

            System.out.println("TEST FINISHED");
        }
        catch (Exception ex)
        {
            System.out.println(
                    "ERROR: " + ex.getMessage()
            );

            ex.printStackTrace();
        }
    }
}