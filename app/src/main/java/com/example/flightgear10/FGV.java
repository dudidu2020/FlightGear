package com.example.flightgear10;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.flightgear10.databinding.FlightgearviewBinding;


public class FGV extends AppCompatActivity implements JSV.listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.flightgearview);


        FlightgearviewBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.flightgearview);
        activityMainBinding.setFlightgearviewmodel(new FGVM());
        activityMainBinding.executePendingBindings();


    }


    @Override
    public void onJoystickMoved(float xPercent, float yPercent, int id) {
        TextView aileron;
        aileron = (TextView) findViewById(R.id.aileron);
        TextView elevator;
        elevator = (TextView) findViewById(R.id.elevator);
        float x = 0;
        float y = 0;
        x = xPercent;
        y = yPercent;
        aileron.setText(Float.toString(x));
        elevator.setText(Float.toString(y));

    }


}