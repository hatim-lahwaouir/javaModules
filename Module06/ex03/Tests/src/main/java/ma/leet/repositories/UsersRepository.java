package ma.leet.repositories;
import ma.leet.exceptions.EntityNotFoundException;
import ma.leet.models.User;




public interface UsersRepository{

    public User findByLogin(String login) throws EntityNotFoundException;
    public void update(User user) throws EntityNotFoundException;

}