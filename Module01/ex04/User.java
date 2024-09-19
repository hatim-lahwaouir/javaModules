// package Module01.ex00;

class User{
    private int Identifier;
    private String Name;
    private int Balence;

    public User(String name, int balence) throws Exception{
        Name = name;
        Balence = balence;
        Identifier = UserIdsGenerator.getInstance().generateId();

        if (balence < 0){
            throw new Exception("User => Invalid negative balence `" + name + "`" );
        }
    }


    public int getBalence(){
        return Balence;
    }

    public void addBalence(int balence){
        Balence += balence;
    }
   
    public void removeBalence(int balence) throws  IllegalTransactionException{
        if (balence > Balence){
            throw new IllegalTransactionException("User => Balence can't be negative `" + this + "`" );

        }
        
        Balence -= balence;
    }

    public int getId(){
        return this.Identifier;
    }


    @Override
    public String toString(){
        String id = this.Identifier + "";
        return ("<" +id + ": User :"  + Name + ">");
    }
}