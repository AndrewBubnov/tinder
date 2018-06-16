package dao;

public interface Database {
    void put(String k, Object v);
    Object get(String k);
}
