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

    @Override
    public Transaction[] transactionsOfUser(User user){

        LinkedList<Transaction> result = new LinkedList<>();
        for (int i = 0; i < this.transactionsList.size(); i++){


            if (transactionsList.get(i).Sender.getId() == user.getId())
                result.add(transactionsList.get(i));
        }

        return result.toArray(new Transaction [0]);
    }

    @Override
    public void removeByUserId(String id, int user_id) throws TransactionNotFoundException {
        for (int i = 0; i < transactionsList.size(); i++) {

            if (transactionsList.get(i).getIdentifier().equals(id) && transactionsList.get(i).Sender.getId() == user_id){
                System.out.printf("Transaction removed : %s \n", transactionsList.get(i));
                transactionsList.remove(i);
                return;
            }
        }
        throw new TransactionNotFoundException("Transaction with this id `" + id + "` not found");
    }

    @Override
    public Transaction [] unpairedTransaction(){
        LinkedList<Transaction> result = new LinkedList<>();
       
        for (int i = 0; i < this.transactionsList.size(); i++){
            boolean isPair = false;
            String firstId = this.transactionsList.get(i).getIdentifier();
            for (int j = 0; j < this.transactionsList.size(); j++){
                if (i == j)
                    continue;
                
                if (transactionsList.get(j).getIdentifier().equals(firstId)){
                    isPair = true;
                    break;
                }
            }
            if (!isPair)
                result.add(this.transactionsList.get(i));
        }

        return result.toArray(new Transaction [0]);
    }
}