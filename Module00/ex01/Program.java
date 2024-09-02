import java.util.Scanner ;


public class Program {

    public static int [] isPrime(int n)
    {
        int [] ret = new int[2];
     
        if (n % 2 == 0)
        {
            ret[0] = 0;
            ret[1] = 1;
            return ret;
        }
        int i = 2;
        while (i < n)
        {
            if (n % i == 0)
            {
                ret[0] = 0;
                ret[1] = i;
                return ret;
            }
            i++;
        }
        ret[0] = 1;
        ret[1] = i;
        return ret;
    }

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.printf("-> ");
        int number = 0;
        try{
            number = myObj.nextInt();
            if (number <= 1)
            {
                myObj.close();
                throw new Exception();                
            }
        }
        catch (Exception e){
            System.err.println("IllegalArgument");
            System.exit(1);
        }

        myObj.close();
        int [] result = isPrime(number);
        if (result[0] == 1)
            System.out.printf("true %d\n", result[1]);
        else
            System.out.printf("false %d\n", result[1]);

    }
  }
  