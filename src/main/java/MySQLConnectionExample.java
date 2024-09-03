import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionExample {
    public static void main(String[] args) {
    String url = "jdbc:mysql://localhost:3306/academy";
    String user = "root";
    String password = "kavyaa";

        try {
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("Connection successful!");
        // Use the connection to interact with the database
    } catch (
    SQLException e) {
        e.printStackTrace();
        System.out.println("Connection failed.");
    }
}
}
