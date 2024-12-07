package ma.leet.exceptions;



public class EntityNotFoundException extends Exception{
    public EntityNotFoundException(String msg){
        super(msg);
    }

    public EntityNotFoundException(){
        super();
    }
}