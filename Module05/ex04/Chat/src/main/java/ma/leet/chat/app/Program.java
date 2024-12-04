package ma.leet.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

import java.util.List;

import ma.leet.chat.repository.UsersRepository;
import ma.leet.chat.repository.UsersRepositoryJdbcImpl;
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
            UsersRepository doa = new UsersRepositoryJdbcImpl(connection);

            List<User> users = doa.findAll(4, 10);

            for (int i = 0; i < users.size(); i++){
                System.out.println(users.get(i));
            }
        }
        catch (SQLException e) {
            System.out.println("Message can't be saved ");
        }


    }


}