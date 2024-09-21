import java.util.UUID;

public class TransactionsService{
    UserList users = new UsersArrayList();
    TransactionsList transactions = new TransactionsLinkedList();


    public void addUser(User u){
        users.addUser(u);
    }

    public User getUserByid(int id) throws UserNotFoundException{
        return users.userById(id);
    }

    public int getUserBalence(int id) throws UserNotFoundException{
        User user = users.userById(id);

        return user.getBalence();
    }

    public void transferTransaction(int sender_id, int recipient_id, int amount) throws UserNotFoundException, IllegalTransactionException{
        User sender = this.users.userById(sender_id);
        User recipient = this.users.userById(recipient_id);
    
        if (sender.getBalence() < amount){
            throw new IllegalTransactionException("%s's transfer amount exceeds his residual balance\n");
        }
        String identifier = UUID.randomUUID().toString();
        Transaction firstTransaction = new Transaction(identifier, sender, recipient, true, amount);
        Transaction secondTransaction = new Transaction(identifier, recipient, sender, false, amount);
        

        System.out.printf("transaction between %s and %s\n",sender, recipient);
        transactions.add(secondTransaction);
        transactions.add(firstTransaction);
    }


    public void removeTransaction(int user_id, String transactionID) throws TransactionNotFoundException{
        this.transactions.removeByUserId(transactionID, user_id);
    }

    public Transaction [] getTransactionsOfUser(User user){
        return this.transactions.transactionsOfUser(user);
    }

    public Transaction [] unpairedTransactions(){
        return this.transactions.unpairedTransaction();
    }
    
}