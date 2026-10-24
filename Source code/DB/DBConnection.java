package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    
public class DBConnection {

    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521/XEPDB1";

    private static final String USERNAME =
            "RESTURANT";

    private static final String PASSWORD =
            "Restaurant123";

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USERNAME,
                PASSWORD
        );
    }
}

