package  ma.leet.chat;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.JCommander;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



import ma.leet.chat.models.User;
import ma.leet.chat.models.ChatRoom;
import ma.leet.chat.models.Message;


import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner; 
@Parameters(separators = "=")
class Main{

    @Parameter(names = {"--endPoint"}, required = true, description = "end point of the database")
    public String endPoint;


    @Parameter(names = {"--db"}, required = true, description = "database")
    public String db;


    @Parameter(names = {"--username"}, required = true, description = "username")
    public String username;


    @Parameter(names = {"--password"}, required = true, description = "password")
    public String password;




    public static void main(String [] args){

        Main main = new Main();
        try{
            JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        main.run();
    }




    public void excuteSQLFile(String schemaPath, Connection connection) {
        try {
            File myObj = new File(schemaPath);
            StringBuilder str = new StringBuilder();
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()){
                str.setLength(0);
                while (myReader.hasNextLine()) {
                    String line = myReader.nextLine();
                    
                    if (line.contains("--"))
                        continue;
             
                    str.append(line);

                    if (line.contains(";"))
                        break;

                }

                String query = str.toString().trim();

                if (query.length() == 0)
                    continue;
                try{
                    Statement statement  = connection.createStatement();
                    statement.execute(query);
                }
                catch(SQLException e){
                    System.out.printf("----> Porblem in statement  : %s ", query);
                    System.out.println(e.getMessage());
                    continue;
                }
                System.out.println("----> statement was executed successfully : ");
                System.out.println("  " + query);
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    



    }







    public void run(){
        // connection
        String url = "jdbc:postgresql://" + endPoint + "/" + db;
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current working directory: " + currentDir); 
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, username, password);
           System.out.println("Connection established......");
    
            excuteSQLFile("src/main/resources/schema.sql", connection);
            excuteSQLFile("src/main/resources/data.sql", connection);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        System.out.println("*************************************************************************************");
        System.out.println("*************************************************************************************");
        System.out.println("*************************************************************************************");
        System.out.println("                                  queries ");
        System.out.println("*************************************************************************************");
        System.out.println("*************************************************************************************");
        System.out.println("*************************************************************************************");
        System.out.println("*************************************************************************************");

        User user = null;
        try{
            if (connection != null)
                user = User.getUser(1, connection);
                if (user != null)
                {
                    System.out.println("User :");
                    System.out.println(user);
                    System.out.println("User messages :");

                    ChatRoom chatRoom =  ChatRoom.getChatRoom(1, connection);
                    if (chatRoom != null)
                    {
                        System.out.println(chatRoom);
                        System.out.println(chatRoom.getActiveUsers(connection));
                    }

                    Message msg = Message.getMessage(1, connection);
                    if (msg != null){
                        System.out.println(msg);
                    }
                }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            System.exit(-1);

        }
    }
}