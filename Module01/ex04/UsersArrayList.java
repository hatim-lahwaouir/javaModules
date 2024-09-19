import java.util.ArrayList;


public class UsersArrayList implements UserList{

    ArrayList<User> users = new ArrayList<User>();

    @Override
    public void addUser(User user) {
        users.add(user);
        System.out.println(user + " is added!");
    }

    @Override
    public User userById(int id) throws UserNotFoundException {
        int i = 0;
        if (id < 0)
            throw new UserNotFoundException("The id must be a positive integer");
        for (; i < users.size(); i++){
            
            if (users.get(i).getId() == id)
                break;
        }

        if (i == users.size())
            throw new UserNotFoundException("User with this id : " + id  +" not found");

        return users.get(i);
    }

    @Override
    public User userByIndex(int index) throws UserNotFoundException  {
        if (index < 0 || index >= users.size())
            throw new UserNotFoundException("User with this index : " + index  + " not found!");        
        return users.get(index);
    }

    @Override
    public int size() {
        return users.size();
    }

}
