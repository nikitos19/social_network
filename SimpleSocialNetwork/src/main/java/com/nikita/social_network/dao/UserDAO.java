package com.nikita.social_network.dao;

import com.nikita.social_network.ConnectionProvider;
import com.nikita.social_network.exceptions.UserAlreadyExisted;
import com.nikita.social_network.model.Message;
import com.nikita.social_network.model.User;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
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
        } else {
            throw new UserAlreadyExisted();
        }
    }

    public List<User> searchUsers(String filter) throws SQLException {
        Connection connection = ConnectionProvider.getConnection();
        String selectSQL = "select * from users where name LIKE ? or email LIKE ?";
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, "%" + filter + "%");
            preparedStatement.setString(2, "%" + filter + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
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

    public void addFriend(String email1, String email2) throws SQLException {
        Connection connection = ConnectionProvider.getConnection();
        String insertSQL = "insert into friends(email1,email2) values(?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, email1);
            preparedStatement.setString(2, email2);
            int resultSet = preparedStatement.executeUpdate();
        }
    }

    public List<User> searchFriends(String currentUserEmail, String filter) throws SQLException {
        Connection connection = ConnectionProvider.getConnection();
        String selectSQL = "SELECT u.name, f.email2 FROM friends f JOIN users u ON " +
                "u.email = f.email2 WHERE f.email1 = ? AND (f.email2 LIKE ? OR u.name LIKE ?)";
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, currentUserEmail);
            preparedStatement.setString(2, "%" + filter + "%");
            preparedStatement.setString(3, "%" + filter + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User u = new User();
                    u.setName(resultSet.getString("u.name"));
                    u.setEmail(resultSet.getString("f.email2"));
                    users.add(u);
                }
            }
        }
        return users;
    }

    public List<Message> getAllMessages(String sender, String recipient) throws SQLException {
        Connection connection = ConnectionProvider.getConnection();
        String selectSQL = "SELECT c.* FROM chat c WHERE (c.sender = ? AND c.recipient = ?) OR (c.sender = ? AND c.recipient = ?)  ORDER BY c.sendTime";
        List<Message> messages = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, sender);
            preparedStatement.setString(2, recipient);
            preparedStatement.setString(3, recipient);
            preparedStatement.setString(4, sender);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    Message message = new Message();
                    message.setSender(resultSet.getString("sender"));
                    message.setRecipient(resultSet.getString("recipient"));
                    message.setSendTime(resultSet.getTimestamp("sendTime"));
                    message.setMessage(resultSet.getString("message"));
                    messages.add(message);
                }
            }
        }
        return messages;
    }

    public void addMessage(String currentUser, String recipient,String message) throws SQLException {
        Connection connection = ConnectionProvider.getConnection();
        String insertSQL = "INSERT INTO chat(sender,recipient,sendTime,message) VALUES(?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)){
            preparedStatement.setString(1,currentUser);
            preparedStatement.setString(2,recipient);
            preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(4,message);
            int resultSet = preparedStatement.executeUpdate();
        }
    }
}
