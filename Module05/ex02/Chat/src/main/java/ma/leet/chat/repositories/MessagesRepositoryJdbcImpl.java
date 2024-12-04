package ma.leet.chat.repository;
import java.sql.SQLException;
import java.sql.Connection;

import ma.leet.chat.models.Message;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Optional;

import ma.leet.chat.app.RuntimeexceptionNotSavedSubEntityException;


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

    public void save(Message msg) throws RuntimeexceptionNotSavedSubEntityException {

        


        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO  message (text_message, chat_room_id, author) VALUES (?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, msg.getText());
            statement.setLong(2, msg.getRoomId());
            statement.setLong(3, msg.getOwnerId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) 
                System.out.println("nothing changed !!");
            ResultSet result =  statement.getGeneratedKeys();

            if (result.next())
                msg.setId(result.getLong("message_id"));
        }
        catch (Exception e){
            throw new RuntimeexceptionNotSavedSubEntityException();
        }

    }
}