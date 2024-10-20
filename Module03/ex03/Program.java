import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class Program{

    static void exit(String err){
        System.err.println(err);
        System.exit(-1);
    }

    static int getInfo(String [] args, String info){

        int count;
        for (int i = 0; i < args.length; i++)
        {
                if (args[i].startsWith(info)) {
                String countValue = args[i].substring(info.length());
                try {
                    count = Integer.parseInt(countValue);
                    return count;
                } catch (NumberFormatException e) {
                    exit("Invalid number format for" + info + " : " + countValue);
                }
            }
        }

        return -1;
    }

    static Queue<String> getUrls(BufferedReader reader) throws IOException{
        String line; 

        Queue <String> urls = new LinkedList<>();
        while ((line = reader.readLine()) != null) {
            urls.add(line);
        }

        return urls;
    }


    static Consumers [] createConsumers(int n, Info info){
        Consumers [] consumers = new Consumers[n];

        for (int i = 0; i < consumers.length ; i++){
            consumers[i] = new Consumers(info);
        }

        return consumers;
    }
    public static void main(String [] args){
        if (args.length != 1)
        exit("Invalid args!");
        Queue <String> urls = new LinkedList<>();
        
        String filePath = "files_urls.txt";
        try{
            BufferedReader reader  = new BufferedReader(new FileReader(filePath));
            urls = getUrls(reader);
        }
        catch(IOException e)
        {
            exit("A problem occurs while reading from the file.");
        }
        int threadsCount = getInfo(args, "--threadsCount=");

        Info info = new Info(urls);
    
        Consumers [] consumers = createConsumers(threadsCount, info);
        Thread [] threads = new Thread[threadsCount];

        for (int i =0 ; i < threadsCount;i++){
            threads[i] = new Thread(consumers[i]);
            threads[i].start();
        }

        for (int i =0 ; i < threadsCount;i++){
            try {threads[i].join();}catch (InterruptedException e) {};
        }
    }


}