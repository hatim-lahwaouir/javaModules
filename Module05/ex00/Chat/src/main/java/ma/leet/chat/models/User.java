package  models;


import java.util.List;
import java.util.LinkedList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class User{

    private int id;
    private String login;
    private String password;

    private List <Message> messages;
    private List <ChatRoom> chatRooms;




    public User(int id, String login, String password){
        this.id = id;
        this.login = login;
        this.password = password;
    }



    public static User getUser(int id, Connection connection) throws SQLException{

        //  geting the User info 

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_table WHERE user_table.user_id = ? ;");
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();
        User user = null;
        if (result.next())
        {
            user = new User(id,result.getString("login"),result.getString("password"));
            user.getUserChatRooms(connection);
            user.getUserMessages(connection);
        }
        else{
            return null;
        }

        return user;
    }



    public List <ChatRoom> getUserChatRooms(Connection connection) throws SQLException{
        LinkedList<ChatRoom> chatRooms = new LinkedList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM chat_room WHERE owner = ? ;");
        statement.setInt(1, this.id);
        ResultSet result = statement.executeQuery();


        while (result.next()){
            chatRooms.add(new ChatRoom(result.getInt("room_id") ,this,result.getString("room_name")));
        }
        this.chatRooms = chatRooms;
        return new LinkedList<>(chatRooms);
    }




    public List <Message> getUserMessages(Connection connection) throws SQLException{
        LinkedList<Message> messages = new LinkedList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM message WHERE author = ? ;");
        statement.setInt(1, this.id);
        ResultSet result = statement.executeQuery();


        while (result.next()){
            messages.add(new Message(result.getInt("message_id") ,this,result.getString("text_message"), result.getString("message_date")));
        }
        this.messages = messages;
        return new LinkedList<>(messages);
    }



    public boolean equals(User obj) {
        if (obj == null)
            return false;
        
        return obj.id == obj.id;
    }

    @Override 
    public String toString() {
        return "< User " + id  + " : "  + login +   " >";
    }

}