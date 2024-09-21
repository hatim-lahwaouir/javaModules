import java.util.Scanner;

class Menu{
    private TransactionsService tr;


    Menu(){
        tr = new TransactionsService();
    }

    
    public void addUser(Scanner scanner){

        System.out.println("Enter a user name and a balance");
        System.out.printf("-> ");
        String name = scanner.next();
        int balence = scanner.nextInt();

        User user = new User(name, balence);
        tr.addUser(user);
        System.out.println("User with id " + user.getId() + " is added");
    }


    public void viewUserBalence(Scanner scanner) throws UserNotFoundException{
        System.out.println("Enter a user ID");
        System.out.printf("-> ");
        int id = scanner.nextInt();
        User user = this.tr.getUserByid(id);
        System.out.println(user.getName() + " " + user.getBalence());
    }


    public void perfomTransfer(Scanner scanner) throws UserNotFoundException, IllegalTransactionException{
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        System.out.printf("-> ");
        int sender_id = scanner.nextInt();
        System.out.printf("-> ");
        int recipient_id = scanner.nextInt();
        int amount = scanner.nextInt();


        this.tr.transferTransaction(sender_id, recipient_id, amount);
    }

    public void transactionOfUser(Scanner scanner) throws UserNotFoundException, IllegalTransactionException {
        System.out.println("Enter a user ID");
        System.out.printf("-> ");
        int id = scanner.nextInt();
        User user = this.tr.getUserByid(id);
        Transaction [] listOfTuTransactions = this.tr.getTransactionsOfUser(user);

        for (int i = 0; i < listOfTuTransactions.length; i++){
            System.out.println(listOfTuTransactions[i]);
        }
    }
    void loop(){
        try (Scanner scanner = new Scanner(System.in)){
        while (true)
        {
            
                System.out.println("1. Add a user");
                System.out.println("2. View user balances");
                System.out.println("3. Perfom transfer");
                System.out.println("3. View all transactions for a specific user");
                System.out.println("5. DEV - remove a transfer by ID");
                System.out.println("6. DEV - check transfer validity");
                System.out.println("7. Finish execution");

                System.out.printf("-> ");
                int choice = scanner.nextInt();

                if (choice < 1 || choice > 7)
                {
                    System.out.println("Invalid choice !");
                    continue;                
                }

                try{
                    if (choice == 1)
                        addUser(scanner);
                    else if (choice == 2)
                        viewUserBalence(scanner);
                    if (choice == 3)
                        perfomTransfer(scanner);
                    if (choice == 4)
                        transactionOfUser(scanner);
                    if (choice == 7)
                        break;
                }
                catch (UserNotFoundException | IllegalTransactionException e){
                    System.out.println(e.getMessage());
                }
                System.out.println("---------------------------------------------------------");
        }

    }        
    catch(Exception e){
        System.out.println(e.getMessage());
    }

}

}