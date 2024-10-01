
public interface UsersList {

    void add(User user);
    User get(int id) throws  UserNotFoundException;
    int size();

}