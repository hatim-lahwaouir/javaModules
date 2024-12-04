package ma.leet.chat.repository;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import ma.leet.chat.models.User;


public interface UsersRepository {
    public List<User> findAll(int page, int size)throws SQLException;





    
}