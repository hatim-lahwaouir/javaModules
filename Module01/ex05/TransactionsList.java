public interface TransactionsList {
    void add(Transaction tr);
    void remove(String id) throws TransactionNotFoundException;
    Transaction [] toArray();
    void removeByUserId(String id, int user_id) throws TransactionNotFoundException;
    Transaction[] transactionsOfUser(User user);
    Transaction [] unpairedTransaction();

} 