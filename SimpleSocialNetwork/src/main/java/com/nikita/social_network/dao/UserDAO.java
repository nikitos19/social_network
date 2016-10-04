package com.nikita.social_network.dao;

import com.nikita.social_network.ConnectionProvider;
import com.nikita.social_network.exceptions.UserAlreadyExisted;
import com.nikita.social_network.model.User;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserDAO {

    public User getUser(String email, String password) throws SQLException {
        Connection c = ConnectionProvider.getConnection();
        String selectSQL = "select u.* from users u where u.email = ? and u.password = ?";
        User user = null;
        try (PreparedStatement preparedStatement = c.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
        try (PreparedStatement preparedStatement = c.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
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

    public void createUser(String name, String email, String password) throws SQLException {
        Connection c = ConnectionProvider.getConnection();
        User user = getUser(email);
        if (user == null) {
            String insertSQL = "insert into users(name,email,password) values(?,?,?)";
            try (PreparedStatement preparedStatement = c.prepareStatement(insertSQL)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, password);
                int resultSet = preparedStatement.executeUpdate();
            }
        }else {
            throw new UserAlreadyExisted();
        }
    }

    public List<User> searchUsers(String filter) throws SQLException {
        Connection connection = ConnectionProvider.getConnection();
        String selectSQL = "select * from users where name LIKE ? or email LIKE ?";
        List<User> users = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){
            preparedStatement.setString(1,"%" + filter + "%");
            preparedStatement.setString(2,"%" + filter + "%");
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    User u = new User();
                    u.setName(resultSet.getString("name"));
                    u.setEmail(resultSet.getString("email"));
                    u.setPassword(resultSet.getString("password"));
                    users.add(u);
                }
            }
        }
        return users;
    }
}
