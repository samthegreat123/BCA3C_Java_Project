
package AUTHENTICATION;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DBConnection;

/* Verifies the users asscessed.. */

public class AuthenticationService {

    /* Authenticate Restaurant */

   public static int authenticateRestaurant(
        String restaurantName,
        String restaurantPassword)
        throws SQLException {

    String sql =
            "SELECT restaurant_id " +
            "FROM RESTAURANT " +
            "WHERE restaurant_name = ? " +
            "AND password = ?";

    try (
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement =
                connection.prepareStatement(sql)
    ) {
        statement.setString(1, restaurantName);
        statement.setString(2, restaurantPassword);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("restaurant_id");
            }

            return -1;
        }
    }
}

    /* Authenticate Manager */

  public static int authenticateManager(
        String username,
        String password)
        throws SQLException {
        String sql =
                "SELECT user_id " +
                "FROM APP_USER " +
                "WHERE username = ? " +
                "AND password = ? " +
                "AND role = 'Manager'";

        try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
        )
        {
            statement.setString(1, username);
            statement.setString(2, password);

            try (
                ResultSet resultSet =
                        statement.executeQuery()
            )
            {
                if (resultSet.next())
{
    return resultSet.getInt(
            "user_id"
    );
}

return -1;
            }
        }
    }

    /* Get Restaurant ID */

public static int getRestaurantId(int managerId)
        throws SQLException {

    String sql =
            "SELECT restaurant_id " +
            "FROM APP_USER " +
            "WHERE user_id = ?";

    try (
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement =
                connection.prepareStatement(sql)
    ) {
        statement.setInt(1, managerId);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("restaurant_id");
            }

            return -1;
        }
    }
}


   /* Authenticate Accountant */

public static int authenticateAccountant(
        String username,
        String password
) throws SQLException {

    String sql =
            "SELECT user_id " +
            "FROM APP_USER " +
            "WHERE username = ? " +
            "AND password = ? " +
            "AND role = 'Accountant'";

    try (
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement =
                connection.prepareStatement(sql)
    ) {
        statement.setString(1, username);
        statement.setString(2, password);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("user_id");
            }

            return -1;
        }
    }
}
}
