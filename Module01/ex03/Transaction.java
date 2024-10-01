import java.util.UUID;

class Transaction{
    String Identifier;
    User recipient;
    User sender;
    String transferCategory;
    int amount;
    boolean valid;
    public String res;


    Transaction(User send, User recp, boolean isDebit, int Amount){
        Identifier = UUID.randomUUID().toString();
        valid = true;
        amount = Amount;


        
        if ( ( amount > 0 && isDebit )|| ( send.getBalence() + amount < 0 && isDebit )){

            res = "Sender doesn't have that balence or you are doing a debit and balence is positive";
            valid = false;
            return;
        }

        if (amount < 0 && !isDebit || ( recp.getBalence() - amount < 0 && !isDebit )){
            res = "Recipient doesn't have that balence or you are doing a credit and balence is negative";
            valid = false;
            return;
        }
    
        sender = send;
        recipient = recp;
        if (isDebit)
        {
            send.debit(amount);
            recp.credit(-amount);
            transferCategory = "debits";
        }
        else
        {
            send.credit(amount);
            recp.debit(-amount);
            transferCategory = "credits";
        }

    }

    public Boolean isValid(){
        return valid;
    }

    public String getIdentifier(){
        return this.Identifier;
    }


    @Override
    public String toString(){
        return this.Identifier + " | sender " + this.sender + " | recipient " + this.recipient +  " | amount " + this.amount  ;
    }
}