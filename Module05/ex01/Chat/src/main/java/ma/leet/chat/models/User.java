package ma.leet.chat.models;



import java.util.List;
import java.util.LinkedList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




public class User{

    private long id;
    private String login;
    private String password;

    private List <Message> messages;
    private List <ChatRoom> chatRooms;
    private List <ChatRoom> socialChatRooms;




    public User(long id, String login, String password){
        this.id = id;
        this.login = login;
        this.password = password;
    }



    public static User getUser(long id, Connection connection) throws SQLException{

        //  geting the User info 

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_table WHERE user_table.user_id = ? ;");
        statement.setLong(1, id);

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
        statement.setLong(1, this.id);
        ResultSet result = statement.executeQuery();


        while (result.next()){
            chatRooms.add(new ChatRoom(result.getLong("room_id") ,this,result.getString("room_name")));
        }
        this.chatRooms = chatRooms;
        return new LinkedList<>(chatRooms);
    }


    public List <ChatRoom> getUserSocialRooms(Connection connection) throws SQLException{
        LinkedList<ChatRoom> chatRooms = new LinkedList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT(chat_room.room_id), chat_room.room_name  FROM chat_room JOIN message ON message.chat_room_id = chat_room.room_id WHERE message.author = ?;");
        statement.setLong(1, this.id);
        ResultSet result = statement.executeQuery();
        while (result.next()){
            chatRooms.add(new ChatRoom(result.getLong("room_id") ,this,result.getString("room_name")));
        }
        this.socialChatRooms = chatRooms;
        return new LinkedList<>(chatRooms);
    }


    public List <Message> getUserMessages(Connection connection) throws SQLException{
        LinkedList<Message> messages = new LinkedList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM message WHERE author = ? ;");
        statement.setLong(1, this.id);
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
        return "{ id=" + id + " login="+login + " messages="+messages + " createDrooms="+chatRooms + "}"; 
    }

}