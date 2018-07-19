package com.jldroid25.android.sensorapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


/*
* A basic Light Sensor Monitor App to check the level of a source of light in room
*  so that User interface can be updated with Appropriate Availability Status.
*
* */

public class MainActivity extends AppCompatActivity {

    TextView roomAvailable;
    TextView lightSiLuxUnitReading;
    TextView appUiTitle;
    float lightSensorData = -1;
    Sensor light = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView id references
        roomAvailable = findViewById(R.id.roomAvailable);
        lightSiLuxUnitReading = findViewById(R.id.Light_reading);

        appUiTitle = findViewById(R.id.appUiTitle);
        appUiTitle.setText(R.string.app_ui_title);

        //Build a Sensor Manager Object
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //The type of sensor we are interested in (Type Light)
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);


        //Update a textView with appropriate Availability status.
        sensorManager.registerListener(new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent event) {

                //check if we're getting some light source & the Value/level
                if (event.sensor.getType() == Sensor.TYPE_LIGHT){

                    if(event.values[0] < 1 || event.values[0] > 15 ){
                        lightSensorData = event.values[0];

                        lightSiLuxUnitReading.setText("Ambient Light Level is:\t" +
                                event.values[0] + "\tSi Lux Units");
                    }

                    //If the light "Si Lux Unit" is greater than 1, the lights are ON,
                    //Conference room is not available.
                    if (lightSensorData > 1) {

                        roomAvailable.setText(R.string.unavailable_room);

                        lightSiLuxUnitReading.setText("Ambient Light Level is:\t" +
                                event.values[0] + "\tSI Lux Units");
                    }
                    //If if lights are OFF , or level is less than 1 , then room is available
                    else {
                        roomAvailable.setText(R.string.available_room);
                    }

                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },
        light, sensorManager.SENSOR_DELAY_NORMAL);
    }

}
