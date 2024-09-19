public interface TransactionsList {
    void add(Transaction tr);
    void remove(String id) throws TransactionNotFoundException;
    Transaction [] toArray();
} 