class Program {
    
    static void exit(String err){
        System.err.println(err);
        System.exit(-1);
    }

    static int getCount(String [] args){
        if (args.length != 1)
            exit("Invalid args!");

        int count;
        String arg = args[0];
        if (arg.startsWith("--count=")) {
            String countValue = arg.substring("--count=".length());
            try {
                count = Integer.parseInt(countValue);
                return count;
            } catch (NumberFormatException e) {
                exit("Invalid number format for count: " + countValue);
            }
        }

        return -1;
    }

    public static void main(String [] args){
        int count = getCount(args);

        if (count < 0)
            exit("Invalid Count number " + count);

        Info info = new Info(1);
    
        Consumer cnmr1 = new Consumer(info, 0, count, "egg");
        Consumer cnmr2 = new Consumer(info, 1, count, "Hen");
        
        Producer producer = new Producer(info);


        Thread cm1 = new Thread(cnmr1);
        Thread cm2 = new Thread(cnmr2);
        Thread prd = new Thread(producer);


        prd.start();
        cm1.start();
        cm2.start();


    }

}