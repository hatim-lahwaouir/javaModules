package models;
import java.util.List;
import java.util.LinkedList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Message{
        
    private User owner;
    private ChatRoom room;
    private int id;
    private String text;
    private String date;




    public static Message getMessage(int id, Connection connection) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM message JOIN user_table ON (message.author = user_table.user_id)  JOIN chat_room  ON chat_room.room_id = message.chat_room_id WHERE message.message_id = ?;");
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        if (result.next())
        {
            User owner = new User(result.getInt("user_id"), result.getString("login"), result.getString("password"));
            ChatRoom room = new ChatRoom(result.getInt("room_id"), owner, result.getString("room_name"));
            String text = result.getString("text_message");
            String date = result.getString("message_date");
            Message msg = new Message(result.getInt("message_id") , owner, text, date);

            return msg;
        }

        return null;
    }


    public Message(int id, User owner, String text, String date)
    {
        this.id = id;
        this.text = text;
        this.owner = owner;
        this.date = date;
    }

    @Override 
    public String toString() {
        return "< Message " + id  + " : "  + text +   " >   owner : " +  owner;
    }

    public boolean equals(Message obj) {
        if (obj == null)
            return false;
        
        return obj.id == obj.id;
    }

}