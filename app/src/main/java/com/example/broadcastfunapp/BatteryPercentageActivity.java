package com.example.broadcastfunapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class BatteryPercentageActivity extends AppCompatActivity {

    TextInputEditText editTextBatteryGuess;
    Button buttonNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_percentage);

        editTextBatteryGuess = findViewById(R.id.edit_text_battery_percentage);

        buttonNext = findViewById(R.id.button_battery);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String batteryPercentage = Objects.requireNonNull(editTextBatteryGuess.getText()).toString(); // GETTING THE TEXT
                Intent myIntent = new Intent(BatteryPercentageActivity.this, BatteryPercentageActivity2.class);
                myIntent.putExtra("batteryPercentage",batteryPercentage); // SENDING THE TEXT
                startActivity(myIntent);
            }
        });


    }
}