class Producer implements Runnable{

    Info info;

    Producer(Info info){
        this.info = info;
    }

    public void run(){
        boolean turn = false;

        while (true){
            if (!info.still())
                break;
            if (info.notReady())
            {
                if (turn == true)
                    info.setTurn();
                else
                    info.setTurn();
                
                info.setReady(true);
                turn = !turn;
            }
        
        }
    }
}