package models;
import java.util.List;
import java.util.LinkedList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




public class ChatRoom{


    private User owner;
    private int id;
    private String name;
    private List <User> users = new LinkedList<>();




    public static ChatRoom getChatRoom(int id, Connection connection) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM chat_room WHERE room_id = ?;");
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();

        if (result.next())
        {
            int ownerId = result.getInt("owner");
            User owner = User.getUser(ownerId, connection);

            return new ChatRoom(id, owner, result.getString("room_name"));
        }


        return null;
        
    }
    public ChatRoom(int id, User owner, String name)
    {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }


    public List<User> getActiveUsers(Connection connection) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT(user_table.user_id), room_name, login, password, room_id  FROM message JOIN chat_room ON chat_room.room_id = message.chat_room_id JOIN user_table ON user_table.user_id = message.author WHERE chat_room.room_id = ?;");
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();

        while (result.next())
        {
            users.add(new User(result.getInt("user_id"),result.getString("login"), result.getString("password")));
        }

        return new LinkedList<>(this.users);
    }


    @Override 
    public String toString() {
        return "< ChatRoom " + id  + " : "  + name +   " >";
    }


    public boolean equals(ChatRoom obj) {
        if (obj == null)
            return false;
        
        return obj.id == obj.id;
    }


}