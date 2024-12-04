package ma.leet.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;


import java.util.Optional;

import ma.leet.chat.repository.MessagesRepository;
import ma.leet.chat.repository.MessagesRepositoryJdbcImpl;
import ma.leet.chat.models.Message;
import ma.leet.chat.models.ChatRoom;
import ma.leet.chat.models.User;


import java.time.LocalDateTime;
import ma.leet.chat.app.RuntimeexceptionNotSavedSubEntityException;



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
            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(connection);
            Optional<Message> messageOptional = messagesRepository.findById(11L);
            if (messageOptional.isPresent()) {
                Message message = messageOptional.get();
                System.out.println("Message : before "  + message);
                message.setText("Bye");
                message.setDateTime(null);
                messagesRepository.update(message);
                System.out.println("Message : After "  + message);
            }
        }
        catch (SQLException | RuntimeexceptionNotSavedSubEntityException e) {
            System.out.println("Message can't be saved ");
        }


    }


}