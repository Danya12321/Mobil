package com.example.pr20_sim;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class FirebaseDatabaseHelper {

    private DatabaseReference mDatabase;

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void addData(String data) {
        // Генерируем уникальный ключ для новых данных
        String key = mDatabase.child("data").push().getKey();

        // Создаем объект и добавляем его в базу данных
        DataItem item = new DataItem(key, data);
        mDatabase.child("data").child(key).setValue(item);
    }

    public DatabaseReference getDataReference() {
        return mDatabase.child("data");
    }

}