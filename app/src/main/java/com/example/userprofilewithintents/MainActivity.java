package com.example.userprofilewithintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.startButton).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, IdentificationActivity.class);
            startActivity(intent);
        });
    }
}