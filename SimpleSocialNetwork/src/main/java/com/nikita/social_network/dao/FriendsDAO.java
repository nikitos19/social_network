package com.nikita.social_network.dao;

import com.nikita.social_network.ConnectionProvider;
import com.nikita.social_network.model.Friendship;
import com.nikita.social_network.model.User;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FriendsDAO {

    public List<User> searchFriends(String currentUserEmail, String filter) throws SQLException {
        Connection connection = ConnectionProvider.getConnection();
        String selectSQL = "SELECT u.name, f.email2 FROM friends f JOIN users u ON " +
                "u.email = f.email2 WHERE f.email1 = ? AND (f.email2 LIKE ? OR u.name LIKE ?) AND u.deleted = FALSE " +
                "AND f.status ='accepted'";
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

    public List<Friendship> getIAmFriendOf(String email) throws SQLException {
        Connection c = ConnectionProvider.getConnection();
        String sql = "SELECT f.*, u.name FROM friends f JOIN users u ON f.email1=u.email" +
                " WHERE email2 = ? AND status ='requested'";
        List<Friendship> friendses = new ArrayList<>();
        try(PreparedStatement preparedStatement = c.prepareStatement(sql)){
            preparedStatement.setString(1,email);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    Friendship friends = new Friendship();
                    friends.setFrom(resultSet.getString("email1"));
                    friends.setTo(resultSet.getString("email2"));
                    friends.setStatus(resultSet.getString("status"));
                    friends.setNameFrom(resultSet.getString("u.name"));
                    friendses.add(friends);
                }
            }
        }
        return friendses;
    }

    public void createFriendshipRequest(String email1, String email2) throws SQLException {
        Connection c = ConnectionProvider.getConnection();
        List<Friendship> friends = getIAmFriendOf(email1);
        for(Friendship f : friends){
            if(f.getFrom().equalsIgnoreCase(email2)){
                String updateSQL = "UPDATE friends SET status = 'accepted' WHERE email1=? AND email2=?";
                try(PreparedStatement preparedStatement = c.prepareStatement(updateSQL)){
                    preparedStatement.setString(1,email2);
                    preparedStatement.setString(2,email1);
                    int resultSet = preparedStatement.executeUpdate();
                }

                String insertSQL = "INSERT INTO friends(email1, email2, status) VALUES(?, ?, 'accepted')";
                try(PreparedStatement preparedStatement = c.prepareStatement(insertSQL)){
                    preparedStatement.setString(1,email1);
                    preparedStatement.setString(2,email2);
                    int resultSet = preparedStatement.executeUpdate();
                }
                return;
            }
        }

        String insertSQL = "INSERT INTO friends(email1, email2, status) VALUES(?, ?, 'requested')";
        try(PreparedStatement preparedStatement = c.prepareStatement(insertSQL)){
            preparedStatement.setString(1,email1);
            preparedStatement.setString(2,email2);
            int resultSet = preparedStatement.executeUpdate();
        }
    }

    public void acceptFriendRequest(String email1,String email2) throws SQLException {
        Connection connection = ConnectionProvider.getConnection();
        String updateSQL = "UPDATE friends SET status = 'accepted' WHERE email1 = ? AND email2 = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)){
            preparedStatement.setString(1,email2);
            preparedStatement.setString(2,email1);
            int resultSet = preparedStatement.executeUpdate();
        }

        String insertSQL = "INSERT INTO friends(email1, email2, status) VALUES(?,?,'accepted')";
        try(PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)){
            preparedStatement.setString(1,email1);
            preparedStatement.setString(2,email2);
            int resultSet = preparedStatement.executeUpdate();
        }
    }

    public void declineFriendRequest(String email1,String email2) throws SQLException {
        Connection connection = ConnectionProvider.getConnection();
        String updateSQL = "UPDATE friends SET status = 'declined' WHERE email1 = ? AND email2 = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, email2);
            preparedStatement.setString(2, email1);
            int resultSet = preparedStatement.executeUpdate();
        }
    }
}
