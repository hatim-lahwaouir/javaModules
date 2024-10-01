class Program{


    public static void main(String [] argv){

        UsersArrayList db = new UsersArrayList();
        int size = 21;
        
        for (int i = 0; i < size; i++){
            User user = new User("hatim", 1000);
            db.add(user);
        }

        for (int i = 0; i < size + 1; i++){
            try{
                User user = db.get(i);
                System.out.printf("User found %s\n", user);
            }
            catch(UserNotFoundException e){
                System.err.printf("User at index %d not found\n", i);
            }
        }

    }
}