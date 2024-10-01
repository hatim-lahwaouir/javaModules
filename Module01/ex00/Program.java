class Program{


    public static void main(String [] argv){

        int size = 10;

        User [] users = new User [size];

        for (int i = 0; i < size; i++){
            if (i % 2 == 0)
                users[i] = new User("Hatim", 1500);
            else
                users[i] = new User("Walid", 1500);
        }


        Transaction [] tr = new Transaction [size];
        for (int i = 1; i < size; i++){
            if (i % 2 == 0)
                tr[i] = new Transaction(users[i], users[i - 1], true, -500);
            else
                tr[i] = new Transaction(users[i], users[i - 1], false, 500);
               
        
            if (!tr[i].isValid())
                System.out.println(tr[i].res);
        }
    }
}