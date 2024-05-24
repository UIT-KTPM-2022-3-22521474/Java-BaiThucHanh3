package ThucHanh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface SQLConnection {
    String url = "jdbc:sqlserver://localhost:1433;databaseName=QLSV;trustServerCertificate=true;encrypt=false";
    String username = "22521474";
    String password = "tovinhtien";
    public static Connection getConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
