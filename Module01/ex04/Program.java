import java.text.ListFormat.Style;

class Program{


    static public void main(String [] args)
    {    
        TransactionsService transactionsService = new TransactionsService();
            
        try
        {
            int n = 5;
            // create users
            User [] users = new User[n];
            for (int i =0 ;i < n; i++){    

                users[i] = new User("Hatim_" + i, 10000);
                transactionsService.addUser(users[i]);
            }

            for (int i = 1; i < n; i++)
            {
                transactionsService.transferTransaction(users[i - 1].getId(), users[i].getId(), 50);
            }

            Transaction [] userTransactions=  transactionsService.getTransactionsOfUser(users[0]);

            transactionsService.removeTransaction(users[0].getId(), userTransactions[0].getIdentifier());
        
            Transaction [] unpTransactions = transactionsService.unpairedTransactions();


            System.out.println("********* Unpaired Transactions *********");
            for (int i = 0; i < unpTransactions.length; i++){
                System.out.println(unpTransactions[i]);
            }
        

        }


        catch (Exception err){
            System.err.println(err.getMessage());
            System.exit(0);
        }
    
    
    }
}