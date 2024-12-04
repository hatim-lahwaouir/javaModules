package ma.leet.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Scanner;
import java.util.InputMismatchException;


import java.util.Optional;


import ma.leet.chat.repository.MessagesRepositoryJdbcImpl;
import ma.leet.chat.models.Message;



class Program{
    public static void main(String [] args){
        String endPoint = "localhost:5432/";
        String db = "db";
        String user = "user";
        String password = "user";

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://" + endPoint  + db);
        config.setUsername(user);
        config.setPassword(password);


        HikariDataSource dataSource = new HikariDataSource(config);
        try {
            Connection connection = dataSource.getConnection();
            System.out.println("Entre a message ID ");
            System.out.printf("-> ");
            Scanner scanner = new Scanner(System.in);
            MessagesRepositoryJdbcImpl doa = new MessagesRepositoryJdbcImpl(connection);
            long id = 0;
            try {
                id = scanner.nextLong();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric ID.");
                connection.close();
                return;
            }
            Optional<Message> msg = doa.findById(id);

            if (msg.isPresent())
            {
                System.out.println("Message :" + msg.get());
            }
            else{
                System.out.println("Message not found");
            }

            connection.close();
        } 
        catch (SQLException | InputMismatchException e) {
            e.printStackTrace();
        }


    }


}