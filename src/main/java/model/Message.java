package model;

import java.sql.Timestamp;

public class Message {
    private String sender;
    private int senderId;
    private String acceptor;
    private int acceptorId;
    private String message;
    private String time;

    public String getSender() {
        return sender;
    }

    public int getSenderId() {
        return senderId;
    }

    public String getAcceptor() {
        return acceptor;
    }

    public int getAcceptorId() {
        return acceptorId;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setAcceptor(String acceptor) {
        this.acceptor = acceptor;
    }

    public void setAcceptorId(int acceptorId) {
        this.acceptorId = acceptorId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String toString(){
        return "From: " + sender + " to: " + acceptor + " message: \"" + message + "\" at " + time;
    }
}
