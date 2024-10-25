package com.example.broadcastfunapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CustomBroadcastActivity extends AppCompatActivity {

    TextView bText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_broadcast);

        bText = findViewById(R.id.text_view_broadcast);

        Intent myIntent = getIntent();
        String myBroadcastText = myIntent.getStringExtra("broadcast");
        bText.setText(myBroadcastText);

    }
}