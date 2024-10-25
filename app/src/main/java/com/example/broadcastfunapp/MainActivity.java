package com.example.broadcastfunapp;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner itemsSpinner;
    EditText customMessage;

    public String broadcastMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemsSpinner = findViewById(R.id.spinner_items);
        customMessage = findViewById(R.id.edit_text_custom_message);

        Item[] itemsList = {new Item("Custom broadcast receiver"),
                new Item("Wifi State Change"),
                new Item("System battery notification receiver"),
        };

        ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this, android.R.layout.simple_spinner_item,itemsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        itemsSpinner.setAdapter(adapter);
        itemsSpinner.setOnItemSelectedListener(this);
    }

// getSelectedItem() gets the "OBJECT" of the SELECTED item when "SELECT" BUTTON is pressed
    public void getSelectedItem(View v){
        Item item = (Item) itemsSpinner.getSelectedItem();
        displayItemInfo(item);
        String itemName = item.getName();
        if (itemName == "Custom broadcast receiver") {
            String broadcastText = customMessage.getText().toString(); // GETTING THE BROADCAST TEXT
            Intent CustomBroadcastIntent = new Intent(MainActivity.this, CustomBroadcastActivity.class);
            CustomBroadcastIntent.putExtra("broadcast",broadcastText);
            startActivity(CustomBroadcastIntent);
        } else if (itemName == "Wifi State Change") {
            Intent WifiStateIntent = new Intent(MainActivity.this, WifiStateActivity.class);
            startActivity(WifiStateIntent);
        } else if(itemName == "System battery notification receiver"){
            Intent BatteryPercentageIntent = new Intent(MainActivity.this, BatteryPercentageActivity.class);
            startActivity(BatteryPercentageIntent);
        }
    }
    public void displayItemInfo(Item item){
        String name = item.getName();
        Toast.makeText(this,name + " SELECTED", Toast.LENGTH_LONG).show();
    }

// GOING TO USE THIS SECTION BELOW TO SET FUNCTIONALITY WHEN SELECTED AN ITEM
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String itemName = parent.getItemAtPosition(position).toString();
        if (itemName == "Custom broadcast receiver"){
            Toast.makeText(parent.getContext(),itemName,Toast.LENGTH_SHORT).show();
            customMessage.setVisibility(View.VISIBLE);
        } else if(itemName == "Wifi State Change") {
            Toast.makeText(parent.getContext(),itemName,Toast.LENGTH_SHORT).show();
            customMessage.setVisibility(View.GONE);
        } else if(itemName == "System battery notification receiver"){
            Toast.makeText(parent.getContext(),itemName,Toast.LENGTH_SHORT).show();
            customMessage.setVisibility(View.GONE);
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    // I WILL DO SOMETHING IF NEEDED
    }

}