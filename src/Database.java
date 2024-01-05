import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private final String DATABASE_URL = "jdbc:mysql://localhost:3306/db_mahasiswa";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    // singleton
    private static Database instance = null;
    private Connection connection = null;

    private Database() {
        //
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                System.out.println("Connection failed: " + e.getMessage());
            }
        }
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                System.out.println("Connection failed: " + e.getMessage());
            }
        }
    }

    public ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return resultSet;
    }

    public int executeUpdate(String query) {
        int rowsAffected = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error executing update: " + e.getMessage());
        }
        return rowsAffected;
    }
}