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
        sender = send;
        recipient = recp;


        
        if ( ( amount > 0 && isDebit )|| ( sender.getBalence() + amount < 0 && isDebit )){
            res = "Sender doesn't have that balence or you are doing a debit and balence is positive";
            valid = false;
            return;
        }

        if (amount < 0 && !isDebit || ( recipient.getBalence() - amount < 0 && !isDebit )){
            res = "Recipient doesn't have that balence or you are doing a credit and balence is negative";
            valid = false;
            return;
        }
    
        if (isDebit)
        {
            sender.debit(amount);
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

    Transaction (Transaction tr, boolean reverse){
        this.valid = true;
        this.sender = tr.recipient;
        this.recipient = tr.sender;
        this.amount = -tr.amount;
        this.Identifier = tr.Identifier;
        this.transferCategory = tr.transferCategory == "credits"  ? "debits"  : "credits";  
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

    public User  getSender(){
        return this.sender;
    }
    public User  getRecipient(){
        return this.recipient;
    }
}