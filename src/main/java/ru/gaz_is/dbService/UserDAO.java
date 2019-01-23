package ru.gaz_is.dbService;

import java.sql.*;

public class UserDAO {
    private final Connection connection;
    private static final String SELECT_USER = "SELECT * FROM Users WHERE name = ?";
    private static final String UPDATE_USER = "UPDATE Users SET surname = ? WHERE name = ?";

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public String load(String name) {
        String result = "User isn't found";
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String userName = resultSet.getString("name");
                String userSurname = resultSet.getString("surname");
                result = "Name='" + userName + "', surname='" + userSurname + "'";
            }

        } catch (SQLException e) {
            result = "User isn't found";
        }
        return result;
    }

    public void update(String name, String surname) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, surname);
            statement.setString(2, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("User isn't update");
        }
    }

}
