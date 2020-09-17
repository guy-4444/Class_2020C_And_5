package com.classy.class_2020c_and_5;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.Locale;

public class MyMovementSensor {

    public interface CallBack_Movement {
        void movementDetected();
    }

    private SensorManager mSensorManager;
    private Sensor sensorAccelerometer;
    private Sensor sensorGyroscope;
    private CallBack_Movement callBack_movement;

    public MyMovementSensor(Context context) {
        // init sensor
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }


    public boolean isSensorsAvailable(Context context) {
        PackageManager PM = context.getPackageManager();
        boolean acc = PM.hasSystemFeature(PackageManager.FEATURE_SENSOR_ACCELEROMETER);
        boolean gyr = PM.hasSystemFeature(PackageManager.FEATURE_SENSOR_GYROSCOPE);

        return (acc && gyr);
    }

    public void setCallBack(CallBack_Movement callBack_movement) {
        this.callBack_movement = callBack_movement;
    }

    public void startSample() {
        mSensorManager.registerListener(sensorEventListener, sensorAccelerometer, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(sensorEventListener, sensorGyroscope    , SensorManager.SENSOR_DELAY_UI);
    }

    public void stopSample() {
        mSensorManager.unregisterListener(sensorEventListener);
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {

        private final double THRESHOLD = 3.0;
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float accX = sensorEvent.values[0];
                float accY = sensorEvent.values[1];
                float accZ = sensorEvent.values[2];
            } else if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                float gyrX = sensorEvent.values[0];
                float gyrY = sensorEvent.values[1];
                float gyrZ = sensorEvent.values[2];

                if (Math.sqrt(gyrX*gyrX + gyrY*gyrY + gyrZ*gyrZ) > THRESHOLD) {
                    if (callBack_movement != null) {
                        callBack_movement.movementDetected();
                    }
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) { }
    };

}
