package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class User {
    private String name;
    private String url;
    private int id;
    private String lastLogined;
    private String timeAfterLogin;
    private String details;

    public User() {
    }

    public User(String name, String url, int id, String lastLogined, String timeAfterLogin, String details) {
        this.name = name;
        this.url = url;
        this.id = id;
        this.lastLogined = lastLogined;
        this.timeAfterLogin = timeAfterLogin;
        this.details = details;
    }

    public User(String name, String url, int id) {
        this.name = name;
        this.url = url;
        this.id = id;
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

    public String getLastLogined() {
        return lastLogined;
    }

    public String getTimeAfterLogin() {
        return timeAfterLogin;
    }

    public String getDetails() {
        return details;
    }

    public String toString(){
        return name + " " + id + " " + url + " " + lastLogined + " -" + timeAfterLogin + " days ago";
    }
}
