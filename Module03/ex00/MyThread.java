class MyThread extends Thread{
    int count;
    String answer;


    MyThread(int num, String ans){
        count = num;
        answer = ans;
    }

    @Override
    public void run(){
        for (int i =0 ; i < this.count; i++){
            System.out.println(this.answer);
        }
    }
}