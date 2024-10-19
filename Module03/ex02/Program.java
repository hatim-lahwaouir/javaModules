class Program{

    static void exit(String err){
        System.err.println(err);
        System.exit(-1);
    }

    static int getInfo(String [] args, String info){

        int count;
        String arg = args[0];
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

    public static void main(String [] args){

        if (args.length != 2)
            exit("Invalid args!");
        
        int arrySize = getInfo(args, "--arraySize=");
        int  threadsCount = getInfo(args, "--threadsCount=");

        if (arrySize < 0 || threadsCount < 0)
            exit("Negative arguments are not allowed");

        if (arrySize  < threadsCount )
            exit("ThreadsCount must be less then the arraySize");
        ArrayGen arrayGen = new ArrayGen(arrySize);

        int [] arry = arrayGen.getArray();
        int chunck = arrySize / threadsCount;

        Consumer [] cnsmrs = new Consumer[threadsCount];
        Thread [] threads = new Thread [threadsCount];
    
        for (int i = 0; i < cnsmrs.length; i++){
            if (i != cnsmrs.length - 1)
                cnsmrs[i] = new Consumer(chunck * i, chunck * i + chunck ,arrayGen);
            else
                cnsmrs[i] = new Consumer(chunck * i, arry.length , arrayGen);
        }
    

        for (int i = 0; i < cnsmrs.length; i++){
            threads[i] = new Thread(cnsmrs[i]);
        }

        
        for (int i = 0; i < cnsmrs.length; i++){
            threads[i].start();
        }

        for (int i = 0; i < cnsmrs.length; i++)
        {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
            }
        }

        boolean finishe = false;
        
        while (!finishe) {
            finishe = true;    
            
            for (int i = 0; i < cnsmrs.length; i++)
            {
                if (!cnsmrs[i].iFinished())
                {
                    finishe = false;
                    break;
                }
            }
        }



        // int sum = 0;
        for (int i = 0; i < threads.length; i++){
            System.out.printf("%s\n", cnsmrs[i]);
            // sum += cnsmrs[i].getSum();
        }


        // System.out.printf("%d == %d\n", sum, arrayGen.getSum());

    }



}