import java.util.Scanner;

public class Program{

    public static int parsWeek(String s, String [] weeks){
        int i;
        for (i = 0; i < 18 ; i++){
            if (s.equals(weeks[i]))
                return i;
        }
        return -1;
    }

    public static String [] createWeeks(){
        String [] arr = new String[18];
    
        for (int i = 1; i <= 18; i++){
            if (i < 10)
                arr[i - 1] = "Week " + (char)(i + '0');
            else
                arr[i - 1] = "Week " + (char)((i / 10)  + '0') + (char)((i % 10)  + '0');
        }
        return arr;
    }

    public static boolean isGrade(char c){
        return (c >= '1' && c <= '9');
    }

    public static boolean parseGrades(String grades){
        int count = 0;

        for (int i = 0; i < grades.length(); i++){
            char r = ' ';
            char c = grades.charAt(i);
            if (i + 1 < grades.length())
                r = grades.charAt(i + 1);
            
            if (isGrade(c))
                count++;
            if (!isGrade(c) && c != ' ')
                return false;

            if (isGrade(c) && r != ' ')
                return false;
        
        }
        return count == 5;
    }

    static public int minGrade(String s){
        
        int m = 9;
    
        for (int i =0; i < s.length(); i++){
            char c = s.charAt(i);

            if (isGrade(c)){
                int n = c - '0';
                if (n < m){
                    m = n;
                }
            }
        }

        return m;
    }

    public static long addGrade(long grade, int week){

        long offset = 1;
        for (int i = 1; i < week; i++){
            offset *= 10;
        }
        return offset * grade;
    }

    public static void printResults(long grades){
        long m = 1;

        for (int i = 1; i <= 18; i++){
            long n = (grades / m) % 10;
            m *= 10;
            if (n == 0)
                continue;
            if (i < 10)
                System.out.printf("Week %d  ", i);
            else
                System.out.printf("Week %d ", i);
            while (n > 0){
                System.out.printf("=");
                n--;
                if (n == 0){
                    System.out.printf(">");
                }
            }
            System.out.printf("\n");
        }
    }

    public static void main(String [] args){

        long grades = 1000000000000000000L;
        String [] weeks = createWeeks();


        try (Scanner scanner = new Scanner(System.in)) {
            


            while (true)
            {
                System.out.printf("-> ");
                String s = scanner.nextLine();
                
                if (s.equals("42"))
                    break;
                
                int n_week = parsWeek(s, weeks);
                if (n_week == -1){
                    throw  new Exception("llegalArgument");
                }

                long m = 1;
                for (int i = 1; i < n_week + 1; i++){
                    m *= 10;
                }
                if ((grades / (m) % 10) != 0){
                    System.out.println("Week already entred");
                    continue;
                }
                System.out.printf("-> ");
                String weekGrades = scanner.nextLine();
                if (!parseGrades(weekGrades)){
                    throw  new Exception("llegalArgument");
                }
                int minWeekGrade = minGrade(weekGrades);
                grades = grades + addGrade(minWeekGrade, n_week + 1);
            }
        }catch(Exception e){
            System.err.println("llegalArgument");
            System.exit(-1);
        }
        
        printResults(grades);
    }
}
