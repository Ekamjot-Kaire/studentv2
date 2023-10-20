package project_plans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

// Class.forName("org.sqlite.JDBC");

public class SQLiteExample {
    // private static final String JDBC_URL = "jdbc:sqlite:meetings.db";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/meetings";

    public static void main(String[] args) {
        try {
            // creating a connection
            Connection connection = DriverManager.getConnection(JDBC_URL, "ekamjot.kaurr@gmail.com", "Will0w*0");

            // creating database + tables
            createDatabase(connection);

            // CRUD
            insertMeeting(connection, "Example Meeting", "Discuss project progress", 
                          new Date(), parseTime("9:00"));
            selectMeetings(connection);

            // closing connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createDatabase(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS meetings (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, description TEXT, date DATE, time TIME)")) {
            statement.execute();
        }
    }

    private static void insertMeeting(Connection connection, String name, String description,
                                      Date date, Timestamp time) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO meetings (name, description, date, time) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setDate(3, new java.sql.Date(date.getTime()));
            statement.setTimestamp(4, time);
            statement.executeUpdate();
        }
    }

    private static void selectMeetings(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM meetings");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Date date = resultSet.getDate("date");
                Timestamp time = resultSet.getTimestamp("time");

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

                System.out.println("Meeting ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Description: " + description);
                System.out.println("Date: " + dateFormat.format(date));
                System.out.println("Time: " + timeFormat.format(time));
                System.out.println();
            }
        }
    }

    private static Timestamp parseTime(String time) {
        try {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            Date parsedTime = timeFormat.parse(time);
            return new Timestamp(parsedTime.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
