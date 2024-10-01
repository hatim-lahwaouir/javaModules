import java.util.ArrayList;

class UsersArrayList implements UsersList{

    ArrayList <User> users;
    int n_users;

    UsersArrayList(){
        users = new ArrayList<User>();
    }

    @Override
    public void add(User user) {
        this.n_users++;
        users.add(user);
    }


    @Override
    public User get(int index)  throws UserNotFoundException {
        if (index < 0 || index >= users.size() || users.get(index) == null)
            throw new UserNotFoundException();
        
        return users.get(index);
    }

    @Override
    public int size() {
        return this.n_users;
    }

    
}