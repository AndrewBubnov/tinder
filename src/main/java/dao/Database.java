package dao;

public interface Database {
    void put(String k, String v);
    String get(String k);
}
