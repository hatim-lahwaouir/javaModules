import java.util.Scanner;

class Program{


    static public Boolean isAlpha(char c){

        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'); 
    }
    static public void checkStudentName(String s) throws Exception
    {
        if (s.length() > 10){
            throw new Exception("Name must have a maximum of 10 characters");
        }

        int size = s.length();
        for (int i = 0; i < size; i++){
            char c = s.charAt(i);

            if (! isAlpha(c))
                throw new Exception("Name must have only alphabet characters");
        }
    }

    static public String [] getStudents(Scanner scanner) throws Exception
    {
        String [] students = new String [10];
        int index = 0;

            while (true)
            {
                System.out.printf("-> ");
                String name = scanner.nextLine();
                if (name.equals("."))
                    break;
                
                checkStudentName(name);
                
                if (index >= 10)
                    throw new Exception("You can't have more then 10 Students");
                students[index] =  name;
                index++;
            }

        if (index == 0)
            throw new Exception("We can't create a time table for no student !");
        return students;
    }


    static public void checkTime(String date , String [] weekDays) throws Exception
    {

        if (date.length() != 4)
            throw new Exception("Invalid date format");


        char h = date.charAt(0); 
        if (h > '6' || h < '1')
            throw new Exception("Invalid time format");
        
        boolean matche = false;
        for (int i  = 0;  i < weekDays.length; i++){
      
            if (weekDays[i].charAt(0) == date.charAt(2) && weekDays[i].charAt(1) == date.charAt(3))
                matche = true;
        }
        
        if (!matche)
            throw new Exception("Invalid day !");
    
    }

    static public int [] getTimeTable(Scanner scanner, String [] weekDays) throws Exception
    {
        int [] timeTable = new int [10];

        for (int i = 0; i < 10; i++){
            timeTable[i] = 1_000_000;
        }
        while (true)
        {
            System.out.printf("-> ");
            String date  = scanner.nextLine();

            if (date.equals("."))
                break;
            checkTime(date, weekDays);
 



        }

        return timeTable;
    }
    static public void main(String [] args){

        String [] weekDays = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};
        try(Scanner scanner = new Scanner(System.in)){
            String [] students = getStudents(scanner);
            int [] timeTable = getTimeTable(scanner , weekDays);

        }
        catch(Exception e){
            System.err.print(e.getMessage());
            System.exit(-1);
        }



    }


}