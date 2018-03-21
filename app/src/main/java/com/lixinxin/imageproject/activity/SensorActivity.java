package com.lixinxin.imageproject.activity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;

import java.util.List;

/**
 * 传感器
 */
@Route(path = "/activity/SensorActivity")
public class SensorActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        textView = (TextView) findViewById(R.id.tv);

        //传感器
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor sensor : sensors) {
            textView.append(sensor.toString()+ "    \n");
        }
    }
}
