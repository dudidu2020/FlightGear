package com.example.flightgear10;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import com.example.flightgear10.databinding.FlightgearviewBinding;


public class FGV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.flightgearview);
        // ViewModel updates the Model
        // after observing changes in the View

        // model will also update the view
        // via the ViewModel


        FlightgearviewBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.flightgearview);

        activityMainBinding.setFlightgearviewmodel(new FGVM());
        activityMainBinding.executePendingBindings();

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