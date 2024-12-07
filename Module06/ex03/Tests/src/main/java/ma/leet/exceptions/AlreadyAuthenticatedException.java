package ma.leet.exceptions;



public class AlreadyAuthenticatedException extends Exception{
    public AlreadyAuthenticatedException(String msg){
        super(msg);
    }

    public AlreadyAuthenticatedException(){
        super();
    }
}