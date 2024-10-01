
class Program{

    static  void printTransaction(Transaction [] trs){
        for (int i =0 ;i < trs.length; i++){
            System.out.printf("%s\n", trs[i]);
        }

    }
    public static void main(String [] argv){
        User user1 = new User("hatim", 1000);
        User user2 = new User("Walid", 1000);



        TransactionsService transactionService = new TransactionsService();


        transactionService.users.add(user2);
        transactionService.users.add(user1);


        try{
            transactionService.transferTransaction(0, 1, 400);
            transactionService.transferTransaction(0, 1, 400);
            
            Transaction [] user1Transactions = transactionService.getTransactionsOfUser(0);
            Transaction [] user2Transactions = transactionService.getTransactionsOfUser(1);
            System.out.printf("------------- Transactions ---------------\n");
            printTransaction(user1Transactions);
            System.out.printf("------------- Transactions ---------------\n");        
            printTransaction(user2Transactions);
            
            transactionService.removeTransactionOfUser(0, user1Transactions[0].getIdentifier());
            user1Transactions = transactionService.getTransactionsOfUser(0);
            
            System.out.printf("------------- Transactions after removing  --------------- \n");        
            printTransaction(user1Transactions);
            System.out.printf("------------- unpaired Transactions ---------------\n");
            printTransaction(transactionService.unpairedTransactions());
        } 
        catch (IllegalTransactionException | TransactionNotFoundException |UserNotFoundException e){
            System.err.println(e.getMessage());
        }
    }
}