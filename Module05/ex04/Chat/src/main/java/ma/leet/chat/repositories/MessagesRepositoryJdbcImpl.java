package ma.leet.chat.repository;
import java.sql.SQLException;
import java.sql.Connection;

import ma.leet.chat.models.Message;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Optional;
import java.sql.Timestamp;
import ma.leet.chat.app.RuntimeexceptionNotSavedSubEntityException;
import java.time.LocalDateTime;




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


    public void update(Message msg) throws RuntimeexceptionNotSavedSubEntityException {

        

        String text = msg.getText();
        String date = msg.getDate();
        try{
            Timestamp messageDate  = null;
            if (date != null)
                messageDate = Timestamp.valueOf(date);


            PreparedStatement statement = connection.prepareStatement("UPDATE  message SET text_message = ?, message_date = ? WHERE message_id = ?;");
            System.out.println(messageDate);
            if (text != null)
                statement.setString(1, text);
            else
                statement.setNull(1, java.sql.Types.VARCHAR);
            if (messageDate != null)
                statement.setTimestamp(2, messageDate);
            else
                statement.setNull(2, java.sql.Types.TIMESTAMP);
            statement.setLong(3, msg.getId());


            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) 
                System.out.println("nothing changed !!");

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeexceptionNotSavedSubEntityException();
        }

    }
}