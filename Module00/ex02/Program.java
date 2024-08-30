import java.util.Scanner ;


public class Program{

    public static int countDigits(int number){
        int res = 0;
        while (number > 0){
          res += number % 10;
          number /= 10;
        }
        return res;
    }
    
    public static boolean isPrime(int n)
    {
     
        if (n % 2 == 0)
            return false;

        int i = 2;
        while (i < n)
        {
            if (n % i == 0)
                return true;
            i++;
        }
        return true;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   
        int res = 0;

        while (true){
            System.out.printf("-> ");
            int nbr = 0;
            try
            {
                nbr = scanner.nextInt();
            }
            catch(Exception e)
            {
                System.err.printf("error occurred while reading input");
                System.exit(1);
            }
            int sum = countDigits(nbr);
            if (isPrime(sum))
                res++;
            
            if (nbr == 42)
                break;
            }
        scanner.close();
        System.out.printf("Count of coffee-request : %d\n", res);
    }
}