package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class User {
    private String name;
    private String url;
    private int id;
    private String lastLogined;
    private int daysSinseLogin;

    public User(String name, String url) {
        this.name = name;
        this.url = url;
        this.id = Math.abs(Integer.parseInt((name.hashCode() + "").substring(0, 4)));
        Random random = new Random();
        int finishDay = (int) LocalDate.now().toEpochDay();
        int startDay = finishDay - 30;
        long randomDay = startDay + random.nextInt(finishDay - startDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        this.lastLogined = formatter.format(randomDate);
        this.daysSinseLogin = (int)(LocalDate.now().toEpochDay() - randomDay);
    }

    public User() {

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

    public int getDaysSinseLogin() {
        return daysSinseLogin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return name + " " + id + " " + url + " " + lastLogined +  " " + daysSinseLogin;
    }
}
