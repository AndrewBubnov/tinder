package model;

import java.sql.Timestamp;

public class Message {
    private String sender;
    private int senderId;
    private String acceptor;
    private int acceptorId;
    private String message;
    private String time;

    public Message(String sender, int senderId, String acceptor, int acceptorId, String message, String time) {
        this.sender = sender;
        this.senderId = senderId;
        this.acceptor = acceptor;
        this.acceptorId = acceptorId;
        this.message = message;
        this.time = time;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getAcceptorId() {
        return acceptorId;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public String getAcceptor() {
        return acceptor;
    }

    public String getTime() {
        return time;
    }


    public String toString(){
        return "From: " + sender + " to: " + acceptor + " message: \"" + message + "\" at " + time;
    }
}
