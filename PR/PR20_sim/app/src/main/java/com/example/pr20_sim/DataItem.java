package com.example.pr20_sim;

public class DataItem {
    private String key;
    private String data;

    public DataItem() {
        // Пустой конструктор необходим для Firebase
    }

    public DataItem(String key, String data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public String getData() {
        return data;
    }
}

