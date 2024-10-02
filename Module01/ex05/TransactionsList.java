
/**
 * TransactionsList
 */
public interface TransactionsList {

    void add(Transaction tr);
    void remove(String uuid) throws TransactionNotFoundException;
    Transaction [] toArray();
    public Transaction get(String uuid) throws TransactionNotFoundException;
}