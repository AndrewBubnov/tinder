package model;

import java.util.ArrayList;
import java.util.List;

public class UserList {
public List<User> get(){
//    return new ArrayList<User>(){{
//        add(new User("Anna", "http://img.izismile.com/img/img6/20130920/640/pretty_girls_that_make_the_world_a_little_more_beautiful_640_06.jpg"));
//        add(new User("Victoria", "http://img.izismile.com/img/img6/20130920/640/pretty_girls_that_make_the_world_a_little_more_beautiful_640_14.jpg"));
//        add(new User("Johanna", "http://img.izismile.com/img/img6/20130920/640/pretty_girls_that_make_the_world_a_little_more_beautiful_640_15.jpg"));
//        add(new User("Mary", "http://img.izismile.com/img/img6/20130920/640/pretty_girls_that_make_the_world_a_little_more_beautiful_640_18.jpg"));
//        add(new User("Kristina", "http://img.izismile.com/img/img11/20180605/640/beautiful_girls_make_the_world_go_around_640_high_28.jpg"));
//        add(new User("Sandy", "http://img.izismile.com/img/img11/20180605/640/beautiful_girls_make_the_world_go_around_640_high_34.jpg"));
//        add(new User("Mia", "http://img.izismile.com/img/img11/20180605/640/beautiful_girls_make_the_world_go_around_640_high_03.jpg"));
//    }};
    return new ArrayList<User>(){{
        add(new User("Anna", "/images/src/main/java/resources/static/images/1.jpg"));
        add(new User("Victoria", "/images/src/main/java/resources/static/images/2.jpg"));
        add(new User("Johanna", "/images/src/main/java/resources/static/images/3.jpg"));
        add(new User("Mary", "/images/src/main/java/resources/static/images/4.jpg"));
        add(new User("Kristina", "/images/src/main/java/resources/static/images/5.jpg"));
        add(new User("Sandy", "/images/src/main/java/resources/static/images/6.jpg"));
        add(new User("Mia", "/images/src/main/java/resources/static/images/7.jpg"));
        add(new User("me", "/images/src/main/java/resources/static/images/8.jpg"));
    }};
 }
}
