import java.util.LinkedList;

class TransactionsLinkedList implements TransactionsList{

    LinkedList <Transaction> transactions = new LinkedList<Transaction>();
    
    @Override
    public void add(Transaction tr) {
        if (tr.isValid())
            transactions.add(tr);
    }

    public Transaction get(String uuid) throws TransactionNotFoundException {
        for (int i = 0; i < transactions.size(); i++)
        {
            String identifier = transactions.get(i).getIdentifier();
        
            if (identifier.equals(uuid))
                return transactions.get(i);
        }
        throw new TransactionNotFoundException();
    }
    

    @Override
    public void remove(String uuid) throws TransactionNotFoundException {
        for (int i = 0; i < transactions.size(); i++)
        {
            String identifier = transactions.get(i).getIdentifier();
        
            if (identifier.equals(uuid))
            {
                this.transactions.remove(i);   
                return;
            }
        }

        throw new TransactionNotFoundException();
    }


    @Override
    public Transaction[] toArray() {
        return this.transactions.toArray(new Transaction[0]);
    }

}