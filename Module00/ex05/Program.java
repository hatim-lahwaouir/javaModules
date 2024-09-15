import java.util.Scanner;

import javax.swing.text.Style;

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
                
                String name = scanner.next();
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


    static public void checkTime(String hour, String day , String [] weekDays) throws Exception
    {

        if (hour.length() != 1 && (hour.charAt(0) >= 1 && hour.charAt(0) <= 6)){
            throw new Exception("Invalid hour format  ` " + hour + "`");
        }
        
        boolean matche = false;
        for (int i  = 0;  i < weekDays.length; i++){
      
            if (weekDays[i].equals(day))
                matche = true;
        }
        
        if (!matche)
            throw new Exception("Invalid day ! `" + day + "`");
    
    }

    static public void setTime(String hour, String weekDay, int [] timeTable, String [] weekDays){
        int day = 0;
        for (;  day < weekDays.length; day++){
      
            if (weekDays[day].equals(weekDay))
                break;
        }

        int time = (hour.charAt(0) - '0');

        int offset = 1;
        for (int i = 1; i < time; i++){
            offset *= 10;
        }

        if ((timeTable[day] / offset) % 10 == 1)
        {
            System.out.print("Time already reserverd\n");
            return;
        }
        timeTable[day] += offset;

    }

    static public int [] getTimeTable(Scanner scanner, String [] weekDays) throws Exception
    {
        int [] timeTable = new int [7];

        for (int i = 0; i < 7; i++){
            timeTable[i] = 1_000_000;
        }
        while (true)
        {
            
            String hour  = scanner.next();

            if (hour.equals("."))
                break;
            String day  = scanner.next();
            
            checkTime(hour, day, weekDays);
            setTime(hour, day , timeTable, weekDays);
        }

        return timeTable;
    }

    static public int getStudent(String s, String [] arr){
        for (int i =0 ; i < arr.length; i++){

            if (arr[i].equals(s))
                return i;
        }
        return -1;
    }

    static public int [][] getAttendance(Scanner scanner, int [] timTable, String [] students) throws Exception{
        
        int [][] res = new int[10][30];

        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 30; j++){
                res[i][j] = 1_000_000;
            }
        }

        while (true){
            
            String s = scanner.next();

            if (s.equals("."))
                break;
            int hours = scanner.nextInt();

            int index = getStudent(s, students);

            if (index == -1)
                throw new Exception("Invalid Student");
            if (hours > 6 || hours < 1)
                throw new Exception("Invalid hour");
            int day  = scanner.nextInt();
            if (day < 1 || day > 30)
                throw new Exception("Invalid Day");

            String attendance = scanner.next();

            
            boolean was_here;
            if (attendance.equals("NOT_HERE"))
                was_here = false;
            else if (attendance.equals("HERE"))
                was_here = true;
            else
                throw new Exception("Invalid input for attendance");
                
            int offset = 1;
            for (int i = 1; i < hours; i++){
                offset *= 10;
            }

            if ((res[index][day] / offset) % 10 != 0){
                System.out.println("You can't add the attendance twice !");
                continue;
            }
            if (!was_here)
                offset *= 2;
            res[index][day - 1] += offset;
        }

        return res;
    }

    static public void PrintInfo(int [] timeTable, String[] students, int [][] attendance){

        System.out.println("Students");

        for (int i = 0; i < 10; i++){
            if (students[i] != null)
                System.out.printf("%s ", students[i]);
        }
        System.out.println("\ntimeTable");
        for (int i = 0; i < 7; i++){
            System.out.printf("%s ", timeTable[i]);
        }
        
        System.out.println("Attendance");
        
        for (int i = 0; i < 10; i++){
            if (students[i] == null)
                continue;
            System.out.printf("\n%s : ", students[i]);
            for (int j = 0; j < 30; j++){
                if (attendance[i][j] != 1_000_000)
                    System.out.printf(" %d -> %d\n", j, attendance[i][j]);
            }
        }

    }

    static public void printResults(String [] students, int [] timTable, int [][] attendance, String [] weekDays){
        
        for (int i = 0; i < 10; i++)
            System.out.printf(" ");
        for (int i = 0; i < 30; i++){
            if (timTable[i % 7] == 1_000_000)
                continue;
            int hours = timTable[i %  7];

            for (int h = 1; h < 6; h++){
                if (hours % 10 != 0)
                    System.out.printf("%d:00 %s %d|", h, weekDays[i % 7], i + 1);
                hours /= 10;
            }
        }

        System.out.print("\n");

        for (int i = 0; i < 10; i++){
            if (students[i] == null)
                continue;
            System.out.printf("%s", students[i]);
            for (int k = students[i].length(); k < 10; k++)
                System.out.printf(" ");
            for (int j = 0; j < 30; j++){
                if (timTable[j % 7] == 1_000_000)
                    continue;
                int hours = attendance[i][j];
                int timeOfclass = timTable[j % 7];

                for (int h = 1; h < 6; h++){
                    if (hours % 10 != 0 && timeOfclass % 10 != 0)
                    {
                        boolean was_here = (hours  % 10) == 1;
                        if (j > 8)
                            System.out.print(" ");
                        if (was_here)
                            System.out.printf("        1|");
                        else
                            System.out.printf("       -1|");
                    }
                    else if (timeOfclass % 10 != 0)
                    {
                        if (j > 8)
                            System.out.print(" ");
                        System.out.printf("         |");
                    }
                    hours /= 10;
                    timeOfclass /= 10;
                }

            }
            System.out.printf("\n");
        }

    }
    static public void main(String [] args){

        String [] weekDays = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};
        try(Scanner scanner = new Scanner(System.in)){
            String [] students = getStudents(scanner);
            int [] timeTable = getTimeTable(scanner , weekDays);
            int [][] attendance = getAttendance(scanner, timeTable, students);


            printResults(students, timeTable, attendance, weekDays);

        }
        catch(Exception e){
            System.err.print(e.getMessage());
            System.exit(-1);
        }



    }


}