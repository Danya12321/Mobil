package com.example.pr20_sim;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText editTextData;
    private TextView textViewData;
    private Button buttonAdd;
    private FirebaseDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализируем элементы интерфейса
        editTextData = findViewById(R.id.editText_data);
        textViewData = findViewById(R.id.textView_data);
        buttonAdd = findViewById(R.id.button_add);

        // Инициализируем объект для работы с базой данных Firebase
        databaseHelper = new FirebaseDatabaseHelper();

        // Добавляем слушатель клика на кнопку добавления данных
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = editTextData.getText().toString();
                if (!data.isEmpty()) {
                    // Добавляем данные в базу Firebase
                    databaseHelper.addData(data);
                    editTextData.setText("");
                }
            }
        });

        // Устанавливаем слушатель для получения данных из Firebase
        databaseHelper.getDataReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StringBuilder data = new StringBuilder();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DataItem item = snapshot.getValue(DataItem.class);
                    data.append(item.getData()).append("\n");
                }
                textViewData.setText(data.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Обработка ошибки получения данных
            }
        });
    }
}