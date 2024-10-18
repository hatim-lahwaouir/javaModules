class Program{

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
        
        MyThread hen = new MyThread(count,"Hen");
        MyThread egg = new MyThread(count,"egg");
        

        hen.start();
        egg.start();

        try {
            // Wait for both threads to finish
            egg.join(); // Wait for egg thread to finish
            hen.join(); // Wait for hen thread to finish
        } catch (InterruptedException e) {
            exit("thread has been interrupted");
        }

        for (int i = 0; i < count; i++)
            System.out.println("Human");
            
    }

}