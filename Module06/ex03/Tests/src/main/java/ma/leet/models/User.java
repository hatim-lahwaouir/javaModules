package ma.leet.models;




public class User{

    Long id;
    String login;
    String password;
    boolean authenticated = false;

    public User(Long id, String login, String password, boolean authenticated){
        this.id = id;
        this.login = login;
        this.password = password;
        this.authenticated = authenticated;
    }


    public void authenticate(){
        this.authenticated = true;
    }

    public void logout(){
        this.authenticated = false;
    }
    public boolean isAuthenticated(){
        return this.authenticated;
    }



    public String getPassword(){
        return new String(this.password);
    }
    public String getLogin(){
        return new String(this.login);
    }
    public Long getId(){
        return new Long(this.id);
    }

    public void setPassword(String newPassword){
        this.password = newPassword;
    }

    public void setLogin(String newLogin){
        this.login = newLogin;
    }

    public void setId(Long newId){
        this.id = newId;
    }

    public boolean validPassword(String givenPassword){
        return this.password.equals(givenPassword);
    }



}