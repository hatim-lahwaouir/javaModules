import java.util.LinkedList;

public class TransactionsLinkedList implements TransactionsList{

    private LinkedList<Transaction> transactionsList = new LinkedList<>();

    @Override
    public void add(Transaction tr) {
        this.transactionsList.add(tr);

        System.out.printf("Transaction added ! : %s\n" ,tr );
    }

    @Override
    public void remove(String id) throws TransactionNotFoundException {
        for (int i = 0; i < transactionsList.size(); i++) {

            if (transactionsList.get(i).getIdentifier().equals(id)){
                System.out.printf("Transaction removed : %s \n", transactionsList.get(i));
                transactionsList.remove(i);
                return;
            }
        }
        throw new TransactionNotFoundException("Transaction with this id `" + id + "` not found");
    }

    @Override
    public Transaction[] toArray() {
        return this.transactionsList.toArray(new Transaction [0]);
    }
}