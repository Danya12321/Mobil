package com.example.pr28;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView balloonImageView = findViewById(R.id.balloonImageView);
        Animation balloonAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.balloon_animation);
        balloonImageView.startAnimation(balloonAnimation);
    }
}
