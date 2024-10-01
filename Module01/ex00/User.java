import javax.print.attribute.standard.MediaSize.NA;

class User{
    static int id = 0;

    final int IDENTIFIER;
    String name;
    int balence;

    User(String Name, int Balence){
        this.IDENTIFIER = User.id++;
        this.name = Name;
        if (Balence > 0)
            this.balence = Balence;
        System.out.printf("Hello from <%s  | id -> %d>\n", name, this.IDENTIFIER);

    }


    public int getBalence(){
        return this.balence;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.IDENTIFIER;
    }

    public void debit(int amount){
        this.balence += amount;
    }

    public void credit(int amount){
        this.balence += amount;
    }
    @Override
    public String toString(){
        return "< " + this.name  + " | id  -> " + this.IDENTIFIER + " >";
    }




}