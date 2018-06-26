package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class User {
    private String name;
    private String url;
    private int id;
    private String lastLogined;
    private int daysAfterLogin;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastLogined() {
        return lastLogined;
    }

    public void setLastLogined(String lastLogined) {
        this.lastLogined = lastLogined;
    }

    public int getDaysAfterLogin() {
        return daysAfterLogin;
    }

    public void setDaysAfterLogin(int daysAfterLogin) {
        this.daysAfterLogin = daysAfterLogin;
    }

    public String toString(){
        return name + " " + id + " " + url + " " + lastLogined + " -" + daysAfterLogin + " days ago";
    }
}
