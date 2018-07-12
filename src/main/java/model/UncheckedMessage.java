package model;

public class UncheckedMessage {
    private String sender;
    private int senderId;
    private String url;

    public UncheckedMessage(String sender, int senderId, String url) {
        this.sender = sender;
        this.senderId = senderId;
        this.url = url;
    }

    public String getSender() {
        return sender;
    }

    public int getSenderId() {
        return senderId;
    }

    public String getUrl() {
        return url;
    }
    public String toString(){
        return "From " + sender + " with Url: " + url;
    }
}
