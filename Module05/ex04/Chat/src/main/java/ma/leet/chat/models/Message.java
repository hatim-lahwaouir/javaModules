package ma.leet.chat.models;

import java.util.List;
import java.util.LinkedList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.Map;

import java.time.LocalDateTime;


public class Message{
        
    private User owner;
    private ChatRoom room = null;
    private long id = -1;
    private String text;
    private String date;




    public static Message getMessage(long id, Connection connection) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM message JOIN user_table ON (message.author = user_table.user_id)  JOIN chat_room  ON chat_room.room_id = message.chat_room_id WHERE message.message_id = ?;");
        statement.setLong(1, id);
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



    public Message(String id, User owner,ChatRoom room, String text, LocalDateTime date)
    {
        if (id != null)
            this.id = Long.parseLong(id);
        this.text = text;
        this.owner = owner;
        this.room = room;
        if (date != null)
            this.date = date.toString();
        else
            this.date = null;
    }


    public Message(long id, User owner, String text, LocalDateTime date)
    {
        this.id = id;
        this.text = text;
        this.owner = owner;
        if (date != null)
            this.date = date.toString();
        else
            this.date = null;
    }

    public Message(long id, User owner, String text, String date)
    {
        this.id = id;
        this.text = text;
        this.owner = owner;
        this.date = date;
    }

    @Override 
    public String toString() {
        return "{ " +"id="+id + " author="+owner+" room="+room+ " text="+ text+" date=" + date + " }";
    }

    public boolean equals(Message obj) {
        if (obj == null)
            return false;
        
        return obj.id == obj.id;
    }

    public long getId(){
        return this.id;
    }

    public String getText(){
        return this.text;
    }

    public long getRoomId(){
        if (room != null)
        {
            return room.getId();
        }
        return -1;
    }

    public long getOwnerId(){
        if (owner != null)
        {
            return owner.getId();
        }
        return -1;
    }

    public String getDate(){
        return this.date;
    }
    public void setId(long id)
    {
        this.id = id;
    }


    public void setText(String text){
        this.text = text;
    }

    public void setDateTime(LocalDateTime date){
        
        if (date != null)
            this.date = date.toString();
        else
            date = null;
    }

    
}