
public interface UsersList {

    void add(User user);
    User getById(int id) throws  UserNotFoundException;
    User getByIndex(int id) throws  UserNotFoundException;
    int size();

}