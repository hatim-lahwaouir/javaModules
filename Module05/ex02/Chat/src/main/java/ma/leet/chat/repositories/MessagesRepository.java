package ma.leet.chat.repository;
import java.sql.SQLException;
import ma.leet.chat.app.RuntimeexceptionNotSavedSubEntityException;
import ma.leet.chat.models.Message;
import java.util.Optional;



public interface MessagesRepository {
    public Optional<Message> findById(Long id) throws SQLException;
    public void save(Message msg) throws RuntimeexceptionNotSavedSubEntityException;



}