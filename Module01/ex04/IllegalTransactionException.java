public class IllegalTransactionException extends Exception{

    IllegalTransactionException(){};

    IllegalTransactionException(String message){
        super(message);
    }
}