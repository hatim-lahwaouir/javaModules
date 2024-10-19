import java.util.Random;
class ArrayGen{

    int [] array;
    int sum = 0; 

    ArrayGen(int size){
        array = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++){
            array[i] = rand.nextInt(1000);
            this.sum += array[i];
        }

    }

    public int [] getArray(){
        return this.array;
    }

    public int getSum(){
        return this.sum;
    }
    public void print(){

        for (int i = 0; i < array.length; i++){
            System.out.printf("%d ", array[i]);
        }
    }
}