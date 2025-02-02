package DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Database {

    private static Connection connection = null;

    public static Connection getConnection() {
        if(connection == null) {
            try {
                Properties properties = loadProperties();
                String databaseUrl = properties.getProperty("db.url");
                String databaseName = properties.getProperty("db.username");
                String databasePassword = properties.getProperty("db.password");

                connection = DriverManager.getConnection(databaseUrl, databaseName, databasePassword);
            } catch(SQLException sqlException) {
                throw new DatabaseException(sqlException.getMessage());
            }
        }

        return connection;
    }

    public static void closeConnection() {
        if(connection != null) {

            try {

                connection.close();
                System.out.println("A conexão foi encerrada");

            } catch(SQLException sqlException) {
                throw new DatabaseException(sqlException.getMessage());
            }

        }
    }

    public static void closeStatement(Statement statement) {
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DatabaseException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DatabaseException(e.getMessage());
            }
        }
    }

    private static Properties loadProperties() {

        try(FileInputStream fileInputStream = new FileInputStream("database.properties")) {

            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties;

        } catch(IOException ioException) {
            throw new DatabaseException(ioException.getMessage());
        }

    }

}
