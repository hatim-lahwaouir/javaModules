import java.util.Scanner;

public class Program{


    static void parseInput(String inp) throws Exception {
        
        int len = inp.length();

        if (len <= 2 && inp.equals("42"))
            throw new Exception("Invalid Input");

        
        for (int i = 0; i < len - 2; i++){
            char c = inp.charAt(i);

            if (!(c >= 'A' && c <= 'Z'))
                throw new Exception("Invalid Input");
            }
        
            if (inp.charAt(len - 1) != '2' || inp.charAt(len - 2) != '4')
                throw new Exception("Invalid Input");
        }


    static  int [] countCharacters(String s){
        int len = s.length() - 2;

        int max = 0;
        int min = 999;

        int [] arr = new int [28];


        for (int i = 0; i < len;i++){
            char c = s.charAt(i);
            arr[c -  'A']++;

            if (max < arr[c - 'A'])
                max =  arr[c - 'A'];
            if (min > arr[c - 'A'])
                min =  arr[c - 'A'];

        }
        for (int i = 0; i < 26; i++){
            System.out.printf("%d ", arr[i]);
        }

        return arr;
    } 
    public static void main(String[] args) {
        String inp;

        try (Scanner scanner = new Scanner(System.in))
        {
            System.out.printf("-> ");
            inp = scanner.nextLine();
            parseInput(inp);
            countCharacters(inp);
        }
        catch ( Exception error ) {

            System.err.println(error.getMessage());
            System.exit(-1);
        }

        
    }




}