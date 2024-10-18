import java.util.concurrent.Exchanger;

class Consumer implements Runnable{

    int id;
    Info info;
    int count;
    String name;

    Consumer(Info info, int id, int count, String name){
        this.info = info;
        this.id = id;
        this.count = count;
        this.name = name;
    }


    public void run(){
        for(int i =0 ; i < count; i++){
            while (info.notReady())
                try{wait();} catch (Exception e){} ;
            
            if (info.isMyturn(this.id))
            {
                System.out.println(this.name);
                info.setReady(false);
            }
            else{
                i--;
                continue;
            }
        }

        info.setEnd();
    }


}