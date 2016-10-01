package com.nikita.social_network.dao;

import com.nikita.social_network.ConnectionProvider;
import com.nikita.social_network.model.User;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserDAO {

    public User getUser(String email, String password) throws SQLException {
        Connection c = ConnectionProvider.getConnection();
        String selectSQL = "select u.* from users u where u.email = ? and u.password = ?";
        User user = null;
        try(PreparedStatement preparedStatement = c.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = new User();
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                }
            }
        }
            return user;
    }

    public User getUser(String email) throws SQLException {
        Connection c = ConnectionProvider.getConnection();
        String selectSQL = "select u.* from users u where u.email = ?";
        User user = null;
        try(PreparedStatement preparedStatement = c.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, email);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = new User();
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                }
            }
        }
        return user;
    }

    public void createUser(String name,String email,String password) throws SQLException {
        Connection c = ConnectionProvider.getConnection();
        getUser(email);
    }

}
