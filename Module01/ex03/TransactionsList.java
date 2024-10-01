
/**
 * TransactionsList
 */
public interface TransactionsList {

    void add(Transaction tr);
    void remove(String uuid) throws TransactionNotFoundException;
    Transaction [] toArray();
}