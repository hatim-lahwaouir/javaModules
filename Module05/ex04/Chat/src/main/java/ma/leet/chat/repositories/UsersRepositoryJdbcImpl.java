package ma.leet.chat.repository;
import ma.leet.chat.models.User;
import java.sql.SQLException;
import java.util.LinkedList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository{
    //RuntimeexceptionNotSavedSubEntityException;
    private Connection connection;
    public UsersRepositoryJdbcImpl (Connection connection){
        this.connection = connection;
    }

    public List<User> findAll(int page, int size) throws SQLException{
        List<User> users = new LinkedList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_table ORDER BY user_id LIMIT ? OFFSET ?;");
        statement.setLong(1, size);
        statement.setLong(2, size * page);
        ResultSet result =  statement.executeQuery();

        while (result.next()){
            users.add(new User(result.getLong("user_id"), result.getString("login"), result.getString("password")));
        }
        return users;
    };

}