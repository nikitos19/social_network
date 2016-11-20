package com.nikita.social_network.dao;

import com.nikita.social_network.ConnectionProvider;
import com.nikita.social_network.model.Message;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesDAO {
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
                while (resultSet.next()) {
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

    public void addMessage(String currentUser, String recipient, String message) throws SQLException {
        Connection connection = ConnectionProvider.getConnection();
        String insertSQL = "INSERT INTO chat(sender,recipient,sendTime,message) VALUES(?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, currentUser);
            preparedStatement.setString(2, recipient);
            preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(4, message);
            int resultSet = preparedStatement.executeUpdate();
        }
    }
}
