package BaconDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaconConnection {

    private static BaconConnection instance = new BaconConnection();

    private java.sql.Connection connection = null;
    private Statement statement;
    private String connectionPath;
    private boolean connectionIsOpen = false;

    private BaconConnection() {
        this.connectionPath = "jdbc:sqlite:./data/bacon.db";
        open();
    }

    public static BaconConnection getInstance() {
        return instance;
    }


    private void open() {
        try {
            connection = DriverManager.getConnection(connectionPath);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);
            connectionIsOpen = true;
        } catch (SQLException error) {
            System.err.println(error.getMessage());
            connectionIsOpen = false;
        }
    }

    public boolean close() {
        if (!connectionIsOpen) return false;
        try {
            if (connection != null) connection.close();
            connectionIsOpen = false;
            return true;
        } catch (SQLException error) {
            System.err.println(error.getMessage());
            return false;
        }
    }

    public String getActorNameFromID(int id) {
        if (!connectionIsOpen) return null;
        try {
            ResultSet rs = statement.executeQuery("SELECT name FROM actors where id = " + id + ";");
            rs.next();
            return rs.getString("name");
        } catch (SQLException error) {
            System.err.println(error.getMessage());
            return null;
        }
    }

    public String getMovieNameFromID(int id) {
        if (!connectionIsOpen) return null;
        try {
            ResultSet rs = statement.executeQuery("SELECT name FROM movies where id = " + id + ";");
            rs.next();
            return rs.getString("name");
        } catch (SQLException error) {
            System.err.println(error.getMessage());
            return null;
        }
    }
}
