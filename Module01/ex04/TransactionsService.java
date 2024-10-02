import java.util.LinkedList;

class TransactionsService{

    UsersList users = new UsersArrayList();
    LinkedList<Transaction> unpairedTrs  = new LinkedList<Transaction>();
    
    public void addUser(User user){
        users.add(user);
    }

    public int getUserBalence(int id) {
        try{
            return users.getById(id).getBalence();
        }
        catch (UserNotFoundException e){
            System.out.printf("No user with this Id %d\n", id);
            return -1;
        }        
    }



    public void transferTransaction(int senderId, int recipientId, int amount) throws IllegalTransactionException , UserNotFoundException{
        User sender;
        User recipient;
        sender = users.getById(senderId);
        recipient = users.getById(recipientId);

        Transaction senderTr = new Transaction(sender, recipient, true, -amount);
        
        
        Transaction recipientTr = new Transaction(senderTr, true);
        
        if (!recipientTr.isValid() || !senderTr.isValid())
            throw new IllegalTransactionException("Invalid Transaction \n");
        sender.myTransactions.add(senderTr);
        recipient.myTransactions.add(recipientTr);
    }


    public Transaction [] getTransactionsOfUser(int id) throws UserNotFoundException{
        User user = users.getById(id);
        return user.myTransactions.toArray();
    }


    public void removeTransactionOfUser(int id, String transactionId) throws UserNotFoundException, TransactionNotFoundException{
        User user = users.getById(id);
        User recipient = user.myTransactions.get(transactionId).recipient;
        user.myTransactions.remove(transactionId);
        
        for (int i = 0; i < unpairedTrs.size(); i++){
            if (unpairedTrs.get(i).Identifier.equals(transactionId) )
            {
                unpairedTrs.remove(i);
                return;
            }
        }

        try{
            recipient.myTransactions.get(transactionId);
        }
        catch (TransactionNotFoundException e) {
            return;
        }

        unpairedTrs.add(recipient.myTransactions.get(transactionId));
    }

    public Transaction []  unpairedTransactions(){
        return unpairedTrs.toArray(new Transaction [0]);
    }
}