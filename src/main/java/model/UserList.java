package model;

import dao.ConnectionToDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UserList {

 public List<User> get(){
    List<User> userList = new ArrayList<>();
    String sql = "SELECT * FROM users_bubnov";
    ConnectionToDB connectionToDB = new ConnectionToDB();
    try (Connection connection = connectionToDB.getConnection();
         PreparedStatement statement  = connection.prepareStatement(sql);
         ResultSet rSet = statement.executeQuery()){
        while ( rSet.next() ){
            String lastLogined = rSet.getString("last_logined");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime dateOfLastlogin = LocalDateTime.parse(lastLogined, formatter);
            LocalDateTime nowDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
            LocalDateTime lastLoginedDateTime = LocalDateTime.from(dateOfLastlogin);

            int minutes = (int)lastLoginedDateTime.until( nowDateTime, ChronoUnit.MINUTES);
            int years = minutes / 525600;
            minutes %= 525600;
            int months = minutes / 43800;
            minutes %= 43800;
            int days = minutes / 1440;
            minutes %= 1440;
            int hours = minutes / 60;
            minutes %= 60;
            String timeAfterlogin = "";
            if (years> 0) {
                timeAfterlogin += years + " years ";
                days = 0;
                hours = 0;
                minutes = 0;
            }
            if (months> 0) {
                timeAfterlogin += months + " months ";
                hours = 0;
                minutes = 0;
            }
            if (days> 0) {
                timeAfterlogin += days + " days ";
                minutes = 0;
            }
            if (hours> 0) timeAfterlogin += hours + " hours ";
            if (minutes> 0) timeAfterlogin += minutes + " minutes";

            Instant instant = dateOfLastlogin.atZone(ZoneId.systemDefault()).toInstant();
            Date res = Date.from(instant);
            String setingLastLogined = new SimpleDateFormat("MMMM dd, YYYY", Locale.US).format(res);
            User user = new User(
            rSet.getString("name"),
            rSet.getString("url"),
            rSet.getInt("id"),
            setingLastLogined,
            timeAfterlogin,
            rSet.getString("details"));
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
