public interface UserList{


    void addUser(User user);
    User userById(int id) throws UserNotFoundException ;
    User userByIndex(int index) throws UserNotFoundException ;
    int  size();
}