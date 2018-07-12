package dao;

import model.Message;
import model.UncheckedMessage;
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

    public List<Message> getMessages(int loginId, int receiverId, int userId){
        List<Message> messageList = new ArrayList<>();
        String sql = "SELECT * FROM messages_bubnov WHERE acceptor_id=" + receiverId + " AND sender_id=" + loginId;
        String sqlUpd = "UPDATE messages_bubnov SET checked=true WHERE sender_id=? AND acceptor_id=? AND message=?";
        ConnectionToDB connectionToDB = new ConnectionToDB();
        try (Connection connection = connectionToDB.getConnection();
             PreparedStatement statement  = connection.prepareStatement(sql);
             PreparedStatement statement1  = connection.prepareStatement(sqlUpd);
             ResultSet rSet = statement.executeQuery()) {

                while (rSet.next()) {
                    Timestamp t = rSet.getTimestamp("time");
                    String s = new SimpleDateFormat("MMMM dd, HH:mm a", Locale.US).format(t);
                    Message message = new Message(
                            rSet.getString("sender"),
                            rSet.getInt("sender_id"),
                            rSet.getString("acceptor"),
                            rSet.getInt("acceptor_id"),
                            rSet.getString("message"),
                            s);
                    messageList.add(message);
                    if (!rSet.getBoolean("checked") && loginId == userId) {
                        statement1.setInt(1, message.getSenderId());
                        statement1.setInt(2, message.getAcceptorId());
                        statement1.setString(3, message.getMessage());
                        statement1.executeUpdate();
                    }
                }
                if (messageList.size() == 0){
                    UserDAO userDAO = new UserDAO();
                    Message message = new Message(
                       userDAO.getLoginById(loginId),
                       loginId,
                       userDAO.getLoginById(receiverId),
                       receiverId,
                       "",
                       "");
                    messageList.add(message);
                }
                return messageList;
            }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
        return null;
    }

//    public List<Message> getUncheckedMessages(int acceptorId){
//        List<Message> uncheckedList = new ArrayList<>();
//        String sql = "SELECT * FROM messages_bubnov WHERE acceptor_id=" + acceptorId + " AND checked=" + false;
//        ConnectionToDB connectionToDB = new ConnectionToDB();
//        try (Connection connection = connectionToDB.getConnection();
//             PreparedStatement statement  = connection.prepareStatement(sql);
//             ResultSet rSet = statement.executeQuery()){
//            while ( rSet.next() ){
//                Message message = new Message(
//                        rSet.getString("sender"),
//                        rSet.getInt("sender_id"),
//                        rSet.getString("acceptor"),
//                        rSet.getInt("acceptor_id"),
//                        rSet.getString("message"),
//                        rSet.getTimestamp("time").toString()
//                );
//                uncheckedList.add(message);
//            }
//            return uncheckedList;
//        }
//        catch ( SQLException e )
//        {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public List<UncheckedMessage> getUncheckedMessages(int acceptorId){
        List<UncheckedMessage> uncheckedList = new ArrayList<>();
        String sql = "SELECT * FROM messages_bubnov WHERE acceptor_id=" + acceptorId + " AND checked=" + false;
        ConnectionToDB connectionToDB = new ConnectionToDB();
        try (Connection connection = connectionToDB.getConnection();
             PreparedStatement statement  = connection.prepareStatement(sql);
             ResultSet rSet = statement.executeQuery()){
            while ( rSet.next() ){
                UserDAO userDAO = new UserDAO();
                int senderId = rSet.getInt("sender_id");
                UncheckedMessage message = new UncheckedMessage(
                        rSet.getString("sender"),
                        senderId,
                        userDAO.getUrlById(senderId)
                );
                uncheckedList.add(message);
            }
            return uncheckedList;
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
        return null;
    }
}
