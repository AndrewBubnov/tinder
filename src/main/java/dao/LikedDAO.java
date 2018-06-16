package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikedDAO {
    public void add(User user) {
        String sql = "INSERT INTO liked_bubnov(name, id, url) VALUES(?,?,?)";
        ConnectionToDB connectionToDB = new ConnectionToDB();
        try (Connection connection = connectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setInt(2, user.getId());
            statement.setString(3, user.getUrl());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<User> getUsers(){
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM liked_bubnov";
        ConnectionToDB connectionToDB = new ConnectionToDB();
        try (Connection connection = connectionToDB.getConnection();
             PreparedStatement statement  = connection.prepareStatement(sql);
             ResultSet rSet = statement.executeQuery()){
            while ( rSet.next() ){
                User user = new User();
                user.setId(rSet.getInt("id"));
                user.setName(rSet.getString("name"));
                user.setUrl(rSet.getString("url"));
                userList.add(user);

            }
            return userList;
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
        return null;
    }
}
