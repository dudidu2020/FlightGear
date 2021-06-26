package com.example.flightgear10;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;


public class FGV extends AppCompatActivity implements JSV.JoystickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);




        JSV joystick = new JSV(this);
        setContentView(joystick);
        setContentView(R.layout.flightgearview);
       // setContentView(R.layout.flightgearview);
       //FlightgearviewBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.flightgearview);
      // activityMainBinding.setFlightgearviewmodel(new FGVM());
       //activityMainBinding.executePendingBindings();








    }






    @Override
      public void onJoystickMoved(float xPercent,float yPercent,int id)
    {
        TextView aileron;
       aileron = (TextView)findViewById(R.id.aileron);
        TextView elevator;
        elevator = (TextView)findViewById(R.id.elevator);
       float x=0;
        float y=0;
        x=xPercent;
       y=yPercent;
      aileron.setText(Float.toString(x));
        elevator.setText(Float.toString(y));
         //Log d("Main Method", "X percent: " + xPercent + " Y percent: " + yPercent);
    }



    // any change in toastMessage attribute
    // defined on the Button with bind prefix
    // invokes this method
    @BindingAdapter({"toastMessage"})
    public static void runMe( View view, String message) {

        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}