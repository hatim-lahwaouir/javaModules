package ma.leet.chat.repository;
import java.sql.SQLException;
import java.sql.Connection;

import ma.leet.chat.models.Message;


import java.util.Optional;


public class MessagesRepositoryJdbcImpl implements MessagesRepository{

    private Connection connection;



    public MessagesRepositoryJdbcImpl(Connection connection)
    {
        this.connection = connection;
    }

    public Optional <Message> findById(Long id) throws SQLException{

        Message message = Message.getMessage(id, connection);
        return Optional.ofNullable(message);
    }


}