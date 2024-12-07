package ma.leet.services;

import ma.leet.exceptions.AlreadyAuthenticatedException;
import ma.leet.exceptions.EntityNotFoundException;
import ma.leet.repositories.UsersRepository;
import ma.leet.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsersServiceImpl{


    UsersRepository userRepo;


    public UsersServiceImpl(UsersRepository userRepo) {
        this.userRepo = userRepo;
    }





    public boolean authenticate(String login, String password) throws AlreadyAuthenticatedException,EntityNotFoundException{

        User user = userRepo.findByLogin(login);
        System.out.println(user);
        if (user.isAuthenticated())
            throw new AlreadyAuthenticatedException();
        if (!user.validPassword(password))
            return false;
        user.authenticate();
        userRepo.update(user);
        return true;
    }

}