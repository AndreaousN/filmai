package com.example.filmai.model;

import java.sql.*;

public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/db";
    private static String query;

    public static void create(User user) {
        query = "INSERT INTO users(username, password, email)\n" +
                "VALUES (?, ?, ?);";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("Sėkmingai sukurtas įrašas duomenų bazėje!");
        } catch (SQLException e) {
            System.out.println("Įvyko klaida sukuriant įrašą duomenų bazėje. Plačiau: "
                    + e.getMessage());
        }
    }

    public static String getBCryptPassword(String username) {
        query = "SELECT password FROM users WHERE username = ?;";

        String passwordBCrypt = "";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                passwordBCrypt = resultSet.getString("password");
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("There was an error searching for data. More: " +
                    e.getMessage());
        }
        return passwordBCrypt;
    }
}
