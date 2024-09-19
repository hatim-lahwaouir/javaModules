class Program{


    static public void main(String [] args)
    {    
        UsersArrayList userList = new UsersArrayList();
            
        try
        {
            for (int i =0 ;i < 10; i++){    

                User user = new User("Hatim_" + i, 10000);
                userList.addUser(user);
            }
            userList.userById(1);
            userList.userByIndex(0);
            userList.userByIndex(-1);

        }
        catch (Exception err){
            System.err.println(err.getMessage());
            System.exit(0);
        }
    
    
    }
}