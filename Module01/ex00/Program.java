class Program{


    static public void main(String [] args)
    {    
        try
        {
            User hatim = new User("Hatim", 10000);
            User Walid = new User("Walid", 10000);


            Transaction [] t =  {new Transaction(hatim, Walid, true, 100), new Transaction(hatim, Walid, false, 100)};
        
            for (int i = 0; i < t.length; i++){
                System.out.println(t[i]);
            }
            
        }
        catch (Exception err){
            System.err.println(err.getMessage());
            System.exit(0);
        }
    
    
    }
}