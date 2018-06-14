package model;

public class User {
    private String name;
    private String url;
    private int id;

    public User(String name, String url) {
        this.name = name;
        this.url = url;
        this.id = Math.abs(Integer.parseInt((name.hashCode() + "").substring(0, 4)));
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }
}
