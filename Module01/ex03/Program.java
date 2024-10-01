class Program{


    public static void main(String [] argv){
        User user1 = new User("walid", 1500);
        User user2 = new User("Hatim", 1500);

        Transaction [] transactions  =  new Transaction[3];
        
        for (int i = 0; i < 3; i++)
            transactions[i] = new Transaction(user1, user2, false, 500);
        for (int i = 0; i < 3; i++)
            user1.myTransactions.add(transactions[i]);
        

        Transaction [] myTrs = user1.myTransactions.toArray();
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.printf("My transaction %s\n", user1);
        for (int i = 0; i < myTrs.length; i++)
        System.out.printf("%s\n", myTrs[i]);
        
        try
        {
            System.out.println("----------------------------------------------------------------------------------------------------------------");
            System.out.printf("removing my transaction %s\n", user1);
            for (int i = 0; i < 2; i++)
            {
                System.out.println(transactions[i]);
                user1.myTransactions.remove(transactions[i].getIdentifier());
            }    
            user1.myTransactions.remove("ee1e12e12e12e");
            
        }
        catch (TransactionNotFoundException e)
        {
            System.out.println("Transaction not found");
        }
        myTrs = user1.myTransactions.toArray();
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.printf("My transaction %s\n", user1);
        for (int i = 0; i < myTrs.length; i++)
        System.out.printf("%s\n", myTrs[i]);
    
    }
}