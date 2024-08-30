import java.util.Scanner;

public class Program{

    public static int parsWeek(String s){
        String [] arr ={"Week 1", "Week 2", "Week 3", "Week 4", "Week 5"};
        int i;
        for (i = 0; i < 5 ; i++){
            if (s.equals(arr[i]))
                return i;
        }
        return -1;
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

    public static void main(String [] args){

        Scanner scanner = new Scanner(System.in);


        int minGrades = 0;
        
        while (true)
        {
            String s = scanner.nextLine();

            if (s.equals("42"))
                break;

            int n_week = parsWeek(s);
            if (n_week == -1){
                System.err.println("llegalArgument");
                System.exit(-1);
            }
            
            int min_week = (minGrades >> n_week * 4) & (15);
            
            if (min_week != 0){
                System.out.println("You already entered this week");
                continue;
            }
            String grades = scanner.nextLine();
            
            if (!parseGrades(grades)){
                System.err.println("llegalArgument");
                System.exit(-1);
            }
            int min = minGrade(grades);
            minGrades = minGrades | (min << n_week * 4);
            min_week = (minGrades >> n_week * 4);
        
        }
    }


}