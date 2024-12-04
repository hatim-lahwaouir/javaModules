package ma.leet.chat.repository;
import java.sql.SQLException;

import ma.leet.chat.models.Message;
import java.util.Optional;



public interface MessagesRepository{
    public Optional<Message> findById(Long id) throws SQLException;



}