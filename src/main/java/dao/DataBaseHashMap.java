package dao;

import java.util.HashMap;

public class DataBaseHashMap implements Database {
    private final HashMap<String, String> data = new HashMap<>();

    @Override
    public void put(String key, String value) {
        data.put(key, value);
    }

    @Override
    public String get(String key) {
        return data.get(key);
    }
}
