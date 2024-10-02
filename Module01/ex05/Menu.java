import java.util.Scanner;

class Menu{
    TransactionsService transactionService = new TransactionsService();



    void addUser(Scanner scanner)  {
        System.out.printf("Enter a user name and balance\n-> ");

        String name = scanner.next();
        int balance = scanner.nextInt();

        if (balance < 0){
            System.out.printf("balence can't be negative\n");
            return;
        }

        User user = new User(name, balance);
    
        transactionService.addUser(user);

        System.out.printf("User with id = %d is added\n", user.getId());
    }

    void getUserBalence(Scanner scanner){
        System.out.printf("Enter a user ID\n-> ");
        int id = scanner.nextInt();

        User user = transactionService.getUser(id);
        if ( user == null){
            System.out.println("User not found !");
            return;
        }
        
        System.out.printf("%s %d\n", user.getName(), user.getBalence());
    }

    void performTransfer(Scanner scanner){
        System.err.printf("Enter a sender ID, a recipient ID, and a transfer amount\n-> ");
        int send_id = scanner.nextInt();
        int recp_id = scanner.nextInt();
        int balance = scanner.nextInt();
    
        try{
            transactionService.transferTransaction(send_id, recp_id, balance);
            System.out.println("The transfer is completed");
        }
        catch(UserNotFoundException e){
            System.out.println("User not found !");
        }
        catch (IllegalTransactionException e){
            System.out.println("Illegal Transaction !");
        }
    }
    

    void transactionOfUser(Scanner scanner){
        System.out.printf("Enter a user ID\n-> ");
        int id = scanner.nextInt();

        try{
            Transaction [] trs = transactionService.getTransactionsOfUser(id);
            for (int i =0 ; i < trs.length; i++){
                System.out.println(trs[i]);
            }
        }
        catch (UserNotFoundException e){
            System.out.println("User not found !");
        }
    }


    void removeTransaction(Scanner scanner){
        System.out.println("Enter a user ID and a transfer ID");
        int userId = scanner.nextInt();
        String trId = scanner.next();
    
        try{
            Transaction tr = transactionService.removeTransactionOfUser(userId, trId);
            if (tr.amount < 0)
                tr.amount *= -1;
            User recp = tr.getRecipient();
            System.out.println("Transfer To " + recp.getName() +"(id = " + recp.getId() + ") " + tr.amount + " removed");
        }
        catch (UserNotFoundException e){
            System.out.println("User not found !");
        }
        catch (TransactionNotFoundException e){

            System.out.println("Transaction not found !");
        }
    
    
    }

    public void transferValidity(){
        Transaction [] transactions = this.transactionService.unpairedTransactions();

        System.out.println("Check results:");
        for (int i =0 ;i < transactions.length; i++){
            User sender = transactions[i].getSender();
            User recipeint = transactions[i].getRecipient();
            int amount = transactions[i].amount;
            if (amount < 0)
                amount *= -1;


            System.out.printf("%s(id = %d) has an unacknowledged transfer id = %s from %s(id = %d) for %d\n",
                    sender.getName(), sender.getId(), transactions[i].getIdentifier(), 
                    recipeint.getName(), recipeint.getId(), amount);
        }
    }
    public void loop(){

        try (Scanner scanner = new Scanner(System.in))
        {
            while (true){

        
            System.out.println("1. Add a user");
            System.out.println("2. View user balances");
            System.out.println("3. Perform a transfer");
            System.out.println("4. View all transactions for a specific user");
            System.out.println("5. DEV - remove a transfer by ID");
            System.out.println("6. DEV - check transfer validity");
            System.out.println("7. Finish execution");
       
            System.out.printf("-> ");
                
            int choice = scanner.nextInt();
            if (choice == 1)
                addUser(scanner);
            else if (choice == 2)
                getUserBalence(scanner);
            else if (choice == 3)
                performTransfer(scanner);                
            else if (choice == 4)
                transactionOfUser(scanner);
            else if (choice == 5)
                removeTransaction(scanner);
            else if (choice == 6)
                transferValidity();
            else if (choice == 7)
                break;

           }
        }
        catch (java.util.InputMismatchException e) {
            System.out.printf("Invalid input sorry \n");
        }

}

}



// 