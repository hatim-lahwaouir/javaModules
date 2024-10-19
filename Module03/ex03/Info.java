import java.util.Queue;

class Info{

    Queue <String> urls;

    Info(Queue<String> urls){
        this.urls = urls;
    }


    public synchronized String getUrl(){

        if (urls.isEmpty())
            return null;
        
        return urls.remove();
    }
}