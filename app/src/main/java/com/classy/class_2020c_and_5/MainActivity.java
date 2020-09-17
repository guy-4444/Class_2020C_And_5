package com.classy.class_2020c_and_5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView main_LBL_value;

    private MyMovementSensor myMovementSensor;
    int counter = 0;


    Object locker = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_LBL_value = findViewById(R.id.main_LBL_value);

        myMovementSensor = new MyMovementSensor(getApplicationContext());
        myMovementSensor.setCallBack(new MyMovementSensor.CallBack_Movement() {
            @Override
            public void movementDetected() {
                   main_LBL_value.setText("" + counter++);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        myMovementSensor.startSample();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myMovementSensor.stopSample();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}

/*
// filter on google play market - user doesn't see the app
<uses-feature android:name="android.hardware.sensor.accelerometer" android:required="true" />


        // Validate missing sensor function.
        PackageManager PM = this.getPackageManager();
        boolean acc = PM.hasSystemFeature(PackageManager.FEATURE_SENSOR_ACCELEROMETER);
        new AlertDialog.Builder(this)
                .setTitle("Sensor Missing")
                .setMessage("Unfortunately, your device does not have a gyroscope sensor and will therefore not be able to use existing tests in the app.")
                .setIcon(R.drawable.ic_no_sensor)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();

                main_LBL_value.setText(
                        String.format(Locale.US, "%.2f",x) + "\n" +
                                String.format(Locale.US, "%.2f",y) + "\n" +
                                String.format(Locale.US, "%.2f",z));


 */