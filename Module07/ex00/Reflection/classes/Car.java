package classes;


import java.util.StringJoiner;


public class  Car{

    String name;
    int vitess;

    public Car(){
        this.name = "Default Name";
        this.vitess = 0;
    }

    public Car(String name, int vitess){
        this.name = name;
        this.vitess = vitess;
    }




    public void addVitess(int value){
        if (value < 0)
            return;
        this.vitess += value;
    }


    public String changeName(String newName){
        this.name = newName;
        return new String (newName);
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("vitess='" + vitess )
        .toString();
    }
}