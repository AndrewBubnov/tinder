package dao;

import model.Message;
import model.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MessageDAO {
    public void addMessage(User user, String login, String message) {
        UserDAO userDAO = new UserDAO();
        User receiver = userDAO.getUser(login);
        String sql = "INSERT INTO messages_bubnov(sender, sender_id, acceptor, acceptor_id, message, time) VALUES(?,?,?,?,?,?)";
        ConnectionToDB connectionToDB = new ConnectionToDB();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try (Connection connection = connectionToDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            statement.setInt(2, userDAO.getIdByLogin(login));
            statement.setString(3, user.getName());
            statement.setInt(4, user.getId());

            statement.setString(5, message);
            statement.setTimestamp(6, timestamp);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Message> getMessages(int loginId, int receiverId){
        List<Message> outcomingMessageList = new ArrayList<>();
        UserDAO userDAO = new UserDAO();

        String sql = "SELECT * FROM messages_bubnov WHERE acceptor_id=" + receiverId + " AND sender_id=" + loginId;
        ConnectionToDB connectionToDB = new ConnectionToDB();
        try (Connection connection = connectionToDB.getConnection();
             PreparedStatement statement  = connection.prepareStatement(sql);
             ResultSet rSet = statement.executeQuery()){
            while ( rSet.next() ){
                Message message = new Message();
                message.setSender(rSet.getString("sender"));
                message.setSenderId(rSet.getInt("sender_id"));
                message.setAcceptor(rSet.getString("acceptor"));
                message.setAcceptorId(rSet.getInt("sender_id"));
                message.setMessage(rSet.getString("message"));
                Timestamp t = rSet.getTimestamp("time");
                String s = new SimpleDateFormat("MMMM dd, HH:mm a", Locale.US).format(t);
                message.setTime(s);
                outcomingMessageList.add(message);
            }
            return outcomingMessageList;
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
        return null;
    }
}
