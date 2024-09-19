class Program{


    static public void main(String [] args)
    {    
        try
        {
            User [] users = new User[10];

            for (int i =0 ;i < 10; i++){    
                users[i] = new User("Hatim", 10000);
            }
            TransactionsLinkedList trs = new TransactionsLinkedList();


            Transaction tr = null;
            for (int i =1 ;i < 10; i++){    
                tr = new Transaction(users[i], users[i - 1], i % 2 == 1, 100);
                trs.add(tr);
            }

            trs.remove(tr.getIdentifier());


        }
        catch (Exception err){
            System.err.println(err.getMessage());
            System.exit(0);
        }
    
    
    }
}