package dao;


import model.Message;
import model.User;
import model.UserList;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    public List<User> getCurrentUsers(List<User> allUsersList, int id){
        List<User> currentUserList = new ArrayList<>();
        for (User user : allUsersList) {
            if (user.getId() != id){
                currentUserList.add(user);
            }
        }
        return currentUserList;
    }

    public void updateDate(String login){
        String sql = "UPDATE users_bubnov SET last_logined=? WHERE name='" + login + "'";
        ConnectionToDB connectionToDB = new ConnectionToDB();
        try (Connection connection = connectionToDB.getConnection();
            PreparedStatement statement  = connection.prepareStatement(sql)){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime nowDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
            String date = nowDateTime.format(formatter).toString();
            statement.setString(1, date);
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
