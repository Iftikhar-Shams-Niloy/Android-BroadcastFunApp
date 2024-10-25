package com.example.broadcastfunapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class BatteryPercentageActivity2 extends AppCompatActivity {
    TextView batteryGuess;
    TextView battery;
    TextView batteryCharging;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_percentage2);

        batteryGuess = findViewById(R.id.text_view_battery_guess);
        battery = findViewById(R.id.text_view_battery_percentage);
        batteryCharging = findViewById(R.id.text_view_battery_status);

        Intent myIntent = getIntent();
        String batteryPercentage = myIntent.getStringExtra("batteryPercentage");
        batteryGuess.setText(batteryPercentage);

        this.registerReceiver(this.batteryLevelReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        IntentFilter powerConnectedFilter = new IntentFilter(Intent.ACTION_POWER_CONNECTED);
        this.registerReceiver(PowerStatusReceiver, powerConnectedFilter);

        IntentFilter powerDisconnectedFilter = new IntentFilter(Intent.ACTION_POWER_DISCONNECTED);
        this.registerReceiver(PowerStatusReceiver, powerDisconnectedFilter);

    }
    private BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
            int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            battery.setText(level+"%");
            if (plugged == 0){
                batteryCharging.setText("NOT CHARGING");
            } else {
                batteryCharging.setText("CHARGING");
            }
        }
    };
    private BroadcastReceiver PowerStatusReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = context.registerReceiver(null, ifilter);

            // Are we charging / charged?
            int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            System.out.println(status);
            Boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING;
            if (status == 1){
                batteryCharging.setText("CHARGED");
            } else if(status == 2){
                batteryCharging.setText("CHARGING");
            } else if(status == 3){
                batteryCharging.setText("NOT CHARGING");
            }
        }
    };
}