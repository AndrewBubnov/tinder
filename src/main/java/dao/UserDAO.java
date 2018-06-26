package dao;


import model.User;
import model.UserList;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class UserDAO {
    public User getUser(String login){
        UserList userList = new UserList();
        User user = new User();
        for (User userOfList : userList.get()){
            if (userOfList.getName().equals(login)){
                user = userOfList;
                break;
            }
        }
        return user;
    }

    public void updateDate(String login){
        String sql = "UPDATE users_bubnov SET last_logined=? WHERE name='" + login + "'";
        ConnectionToDB connectionToDB = new ConnectionToDB();
        try (Connection connection = connectionToDB.getConnection();
             PreparedStatement statement  = connection.prepareStatement(sql)){
//            Timestamp t = new Timestamp(System.currentTimeMillis());
//            String s = new SimpleDateFormat("MMMM dd, HH:mm a", Locale.US).format(t);
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            LocalDate date = LocalDate.parse(LocalDate.now().toString(), formatter);
            statement.setString(1, date.toString());
            statement.executeUpdate();
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
    }

    public int getIdByLogin(String login){
        String sql = "SELECT id FROM users_bubnov WHERE name='" + login + "'";
        ConnectionToDB connectionToDB = new ConnectionToDB();
        try (Connection connection = connectionToDB.getConnection();
             PreparedStatement statement  = connection.prepareStatement(sql);
             ResultSet rSet = statement.executeQuery()){
            rSet.next();
                    return rSet.getInt("id");
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
        return 0;
    }

    public String getLoginById(int id){
        String sql = "SELECT name FROM users_bubnov WHERE id=" + id;
        ConnectionToDB connectionToDB = new ConnectionToDB();
        try (Connection connection = connectionToDB.getConnection();
             PreparedStatement statement  = connection.prepareStatement(sql);
             ResultSet rSet = statement.executeQuery()){
            rSet.next();
                    return rSet.getString("name");
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
        return null;
    }

    public String getUrlById(int id){
        String sql = "SELECT url FROM users_bubnov WHERE id=" + id;
        ConnectionToDB connectionToDB = new ConnectionToDB();
        try (Connection connection = connectionToDB.getConnection();
             PreparedStatement statement  = connection.prepareStatement(sql);
             ResultSet rSet = statement.executeQuery()){
            rSet.next();
                    return rSet.getString("url");
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
        return null;
    }
}
