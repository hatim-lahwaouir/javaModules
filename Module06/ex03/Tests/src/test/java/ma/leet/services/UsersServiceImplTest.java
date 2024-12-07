package ma.leet.services;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import ma.leet.repositories.UsersRepository;
import ma.leet.services.UsersServiceImpl;

import ma.leet.models.User;



class UsersServiceImplTest{

    @Test
    void UsersServiceImplCorrectAuthenticationTest() throws Exception{


    // public User(Long id, String login, String password, boolean authenticated){


        UsersRepository mockUserRepo = mock(UsersRepository.class);
        User user =new User(1L, "Hatim", "HatimPassword", false);
        
        when(mockUserRepo.findByLogin("Hatim")).thenReturn(user);
        UsersServiceImpl usersService = new UsersServiceImpl(mockUserRepo);
        
        
        assertTrue(usersService.authenticate("Hatim", "HatimPassword"));
        
        verify(mockUserRepo).update(user);
    
    }

    @Test
    void UsersServiceImplFailAuthenticationIncorectPasswordTest() throws Exception{
    
         UsersRepository mockUserRepo = mock(UsersRepository.class);
        User user =new User(1L, "Hatim", "HatimPas3eeesword", false);
        
        when(mockUserRepo.findByLogin("Hatim")).thenReturn(user);
        UsersServiceImpl usersService = new UsersServiceImpl(mockUserRepo);
        
        
        assertFalse(usersService.authenticate("Hatim", "HatimPassword"));
        
    }



    @Test
    void UsersServiceImplFailAuthenticationIncorectLoginTest() throws Exception{
    
         UsersRepository mockUserRepo = mock(UsersRepository.class);
        User user = new User(1L, "Hateeim", "HatimPas3eeesword", false);
        
        when(mockUserRepo.findByLogin("Hatim")).thenReturn(user);
        UsersServiceImpl usersService = new UsersServiceImpl(mockUserRepo);
        
        
        assertFalse(usersService.authenticate("Hatim", "HatimPassword"));
        
    }




}