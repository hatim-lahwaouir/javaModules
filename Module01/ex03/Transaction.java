import java.util.UUID;


class Transaction{
    String Identifier;
    User Sender;
    User Recipient; 
    boolean Credit;
    int  TransferAmount;


    Transaction(User sender, User recipient, boolean credit, int transferAmount) throws Exception{
        if (credit && sender.getBalence() < transferAmount )
        {
            throw new Exception("Transaction => Invalid Transaction you can't send more then what you have !");
        }
        if (!credit && recipient.getBalence() < transferAmount )
        {
            throw new Exception("Transaction => Invalid Transaction you can't send more then what you have !");
        }

        if (credit)
        {
            sender.removeBalence(transferAmount);
            recipient.addBalence(transferAmount);
        }
        if (!credit){
            recipient.removeBalence(transferAmount);
            sender.addBalence(transferAmount);
        }
        
        Credit = credit;
        Identifier = UUID.randomUUID().toString();
        Sender = sender;
        Recipient = recipient;
        TransferAmount = transferAmount;
    }


    @Override
    public String toString(){
        String amount = TransferAmount + "";
        return (Identifier + "|" + Sender + "|" + Recipient + "|" + amount + "|" + (Credit ? "Credit" : "Debit"));
    }

    public String getIdentifier(){
        return this.Identifier;
    }
}