import java.util.Scanner ;


public class Program {

    public static String isPrime(int n)
    {
     
        if (n % 2 == 0)
            return ("false 1");

        int i = 2;
        while (i < n)
        {
            if (n % i == 0)
                return ("false " + String.valueOf(i));
            i++;
        }

        return ("true " + String.valueOf(i));
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
        String result = isPrime(number);
        System.out.println(result);
    }
  }
  