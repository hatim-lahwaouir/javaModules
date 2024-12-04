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
            User creator = new User(2, "user", "user", new ArrayList(), new ArrayList());
            User author = creator;
            ChatRoom room = new ChatRoom(8L, "room", creator, new ArrayList());
            Message message = new Message(null, author, room, "Hello!", LocalDateTime.now());
            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(connection);
            messagesRepository.save(message);
            System.out.println(message.getId());
            connection.close();
        } 
        catch (SQLException | RuntimeexceptionNotSavedSubEntityException e) {
            System.out.println("Message can't be saved ");
        }


    }


}