package org.dark.repository;

import org.dark.model.Message;
import org.dark.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageRepository implements Repository<Message> {

    private Connection getConnection() throws SQLException {
        return ConnectionDB.getInstance();
    }

    private Connection getConnectionPool()  throws SQLException {
        return ConnectionDB.getConnectionPool();
    }

    @Override
    public List<Message> findAll() throws SQLException {
        List<Message> messages = new ArrayList<>();
        String sentenceSql = "SELECT * FROM message";

        try(Connection connection = getConnectionPool();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sentenceSql)) {

            while (resultSet.next()) {
                Message message = addMessage(resultSet);
                messages.add(message);
            }
        }
        return messages;
    }

    @Override
    public Message getById(Integer id) throws SQLException {
        Message message = null;
        String sentenceSql = "SELECT * FROM message WHERE id_message = ?";

        try (Connection connection = getConnectionPool();
             PreparedStatement statement = connection.prepareStatement(sentenceSql)){

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    message = addMessage(resultSet);
                }
            }
        }

        return message;
    }

    @Override
    public void save(Message message) throws SQLException {
        String sentenceSql = "INSERT INTO message(message, autor) VALUES (?, ?)";

        try(Connection connection = getConnectionPool();
            PreparedStatement statement = connection.prepareStatement(sentenceSql)) {
            statement.setString(1, message.getMessage());
            statement.setString(2, message.getAuthor());

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sentenceSql = "DELETE FROM message WHERE id_message = ?";

        try(Connection connection = getConnectionPool();
            PreparedStatement statement = connection.prepareStatement(sentenceSql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private Message addMessage(ResultSet resultSet) throws SQLException {
        Message message = new Message();

        message.setId(resultSet.getInt("id_message"));
        message.setMessage(resultSet.getString("message"));
        message.setAuthor(resultSet.getString("autor"));
        message.setDate(resultSet.getString("fecha"));

        return message;
    }
}
