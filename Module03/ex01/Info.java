class Info{
    int turn;
    boolean ready = false;
    boolean end = false;

    Info(int turn){
        this.turn = turn;
    }

    public void setTurn(){
        if (this.turn == 0)
            this.turn = 1;
        else
            this.turn = 0;
    }

    public int getTurn(){
        return this.turn;
    }

    public  synchronized boolean isMyturn(int id){
        return id == turn;
    }

    public synchronized boolean  notReady(){
        return !ready;
    }

    public synchronized void  setReady(boolean a){
        ready = a;
    }


    public synchronized boolean still(){
        return !this.end;
    }

    public synchronized void  setEnd(){
        this.end = true;
    }

}