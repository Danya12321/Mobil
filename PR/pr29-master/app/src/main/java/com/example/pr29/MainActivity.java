package com.example.pr29;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton buttonCamera;
    private ImageButton buttonMaps;
    private ImageButton buttonPhone;
    private ImageButton buttonBrowser;
    private ImageButton buttonCalendar;
    private ImageView imageView;
    private static final int CAMERA_REQUEST = 1888;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCamera = findViewById(R.id.ButtonCamera);
        buttonMaps = findViewById(R.id.ButtonMaps);
        buttonPhone = findViewById(R.id.ButtonPhone);
        buttonBrowser = findViewById(R.id.ButtonBrowser);
        buttonCalendar = findViewById(R.id.ButtonCalendar);
        imageView = findViewById(R.id.imageView);

        buttonCamera.setOnClickListener(this);
        buttonMaps.setOnClickListener(this);
        buttonPhone.setOnClickListener(this);
        buttonBrowser.setOnClickListener(this);
        buttonCalendar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ButtonCamera) {
            openCamera();
        } else if (id == R.id.ButtonMaps) {
            openMaps();
        } else if (id == R.id.ButtonPhone) {
            openPhone();
        } else if (id == R.id.ButtonBrowser) {
            openBrowser();
        } else if (id == R.id.ButtonCalendar) {
            openCalender();
        }
    }
//    private void openCamera() {
//        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivity(intent);
//    }
    private void openCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }

    private void openMaps() {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=Krasnyy Prospekt,72, Novosibirsk, Novosibirskaya oblast', Russia, 630091");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        startActivity(mapIntent);
    }

    private void openPhone() {
        Uri tel=Uri.parse("tel:89131932312");
        Intent intent = new Intent(Intent.ACTION_DIAL, tel);
        startActivity(intent);
    }

    private void openBrowser() {
        Uri webpage = Uri.parse("https://natk.ru/");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }
    private void openCalender(){
        Intent calendarIntent = new Intent(Intent.ACTION_MAIN);
        calendarIntent.addCategory(Intent.CATEGORY_APP_CALENDAR);
        startActivity(calendarIntent);
    }
}