package com.nikita.social_network.dao;

import com.nikita.social_network.ConnectionProvider;
import com.nikita.social_network.exceptions.UserAlreadyExisted;
import com.nikita.social_network.model.Friendship;
import com.nikita.social_network.model.User;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDAO {

    public User getUser(String email, String password) throws SQLException {
        Connection c = ConnectionProvider.getConnection();
        String selectSQL = "SELECT u.* FROM users u WHERE (u.email = ? AND u.password = ?) AND u.deleted = FALSE";
        User user = null;
        Blob blob = null;
        try (PreparedStatement preparedStatement = c.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = new User();
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(resultSet.getString("role"));
                    user.setDeleted(resultSet.getBoolean("deleted"));
                    blob = resultSet.getBlob("photo");
                    if (blob != null) {
                        user.setPhoto(blob.getBytes(1, (int) blob.length()));
                    }
                }
            }
        }
        return user;
    }

    public void setAvatar(byte[] bytes,String email) throws SQLException {
        Connection c = ConnectionProvider.getConnection();
        String sql = "UPDATE users SET photo = ? WHERE email = ?";
        try(PreparedStatement preparedStatement = c.prepareStatement(sql)){
            if(bytes!=null){

                preparedStatement.setBytes(1,bytes);
            }
            else{
                preparedStatement.setNull(1,java.sql.Types.BLOB);
            }
            preparedStatement.setString(2,email);
            int resultSet = preparedStatement.executeUpdate();
        }
    }

    public User getUser(String email) throws SQLException {
        Connection c = ConnectionProvider.getConnection();
        String selectSQL = "SELECT u.* FROM users u WHERE u.email = ?";
        User user = null;
        Blob blob = null;
        try (PreparedStatement preparedStatement = c.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = new User();
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(resultSet.getString("role"));
                    user.setDeleted(resultSet.getBoolean("deleted"));
                    blob = resultSet.getBlob("photo");
                    if (blob != null) {
                        user.setPhoto(blob.getBytes(1, (int) blob.length()));
                    }
                }
            }
        }
        return user;
    }

    public void createUser(String name, String email, String password) throws SQLException {
        Connection c = ConnectionProvider.getConnection();
        User user = getUser(email);
        if (user == null) {
            String insertSQL = "INSERT INTO users(name,email,password) VALUES(?,?,?)";
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
        String selectSQL = "SELECT * FROM users WHERE (name LIKE ? OR email LIKE ?) AND deleted=FALSE";
        List<User> users = new ArrayList<>();
        Blob blob = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, "%" + filter + "%");
            preparedStatement.setString(2, "%" + filter + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User u = new User();
                    u.setName(resultSet.getString("name"));
                    u.setEmail(resultSet.getString("email"));
                    u.setPassword(resultSet.getString("password"));
                    u.setRole(resultSet.getString("role"));
                    u.setDeleted(resultSet.getBoolean("deleted"));
                    blob = resultSet.getBlob("photo");
                    if(blob!=null){
                        u.setPhoto(blob.getBytes(1, (int) blob.length()));
                    }
                    users.add(u);
                }
            }
        }
        return users;
    }

    public void updateUser(String email) throws SQLException {
        Connection connection = ConnectionProvider.getConnection();
        String insertSQL = "UPDATE users SET deleted = TRUE WHERE email=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, email);
            int resultSet = preparedStatement.executeUpdate();
        }
    }

}
