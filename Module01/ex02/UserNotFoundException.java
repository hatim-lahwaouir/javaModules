class UserNotFoundException extends Exception{
    UserNotFoundException(){
        super();
    }
    UserNotFoundException(String msg){
        super(msg);
    }
}