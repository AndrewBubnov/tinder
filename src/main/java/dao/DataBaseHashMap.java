package dao;

import java.util.HashMap;

public class DataBaseHashMap implements Database {
    private final HashMap<String, Object> data = new HashMap<>();

    @Override
    public void put(String key, Object value) {
        data.put(key, value);
    }

    @Override
    public Object get(String key) {
        return data.get(key);
    }
}
