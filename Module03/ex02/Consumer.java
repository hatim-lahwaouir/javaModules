class Consumer  implements Runnable{
    static int idIncre= 1;

    int id;
    int start;
    int end;
    int [] array;
    int sum = 0;
    boolean finished = false;


    Consumer(int start, int end, ArrayGen array){
        this.id = Consumer.idIncre++;
        this.start = start;
        this.end = end;
        this.array = array.getArray();
    }

    public void run(){

        for(int i = start; i < end; i++){
            sum += this.array[i];
        }

        this.setFinish();
    }

    @Override
    public String toString(){
        return "Thread " + id +" from " + start + " to "  + end + " sum is " + this.sum;
    }

    public synchronized void setFinish(){
        this.finished = true;
    }
    
    public synchronized Boolean iFinished(){
        return this.finished;
    }

    public int getSum(){
        return this.sum;
    }



}