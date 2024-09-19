class Program{


    static public void main(String [] args)
    {    
        try
        {
            User [] users = new User[10];

            for (int i =0 ;i < 10; i++){    
                users[i] = new User("Hatim", 10000);
            }
            for (int i =0 ;i < 10; i++){    
                System.out.println(users[i]);
            }


        }
        catch (Exception err){
            System.err.println(err.getMessage());
            System.exit(0);
        }
    
    
    }
}