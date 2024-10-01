class TransactionNotFoundException extends Exception{
    TransactionNotFoundException(){
        super();
    }

    TransactionNotFoundException(String msg){
        super(msg);
    }
}