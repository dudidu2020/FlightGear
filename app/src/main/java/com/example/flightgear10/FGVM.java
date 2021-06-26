package com.example.flightgear10;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


public class FGVM extends BaseObservable {

    // creating object of Model class
    private FGM model;

    // string variables for
    // toast messages
    private String successMessage = "Login successful";
    private String errorMessage = "Email or Password is not valid";

    @Bindable
    // string variable for
    // toast message
    private String toastMessage = null;

    // getter and setter methods
    // for toast message
    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    // getter and setter methods
    // for email varibale
    @Bindable
    public String getAddress() {

        //return Integer.toString(BR.address);
        return model.getAddress();
    }

    public void setAddress(String address) {
        model.setAddress(address);
        notifyPropertyChanged(BR.address);
    }

    // getter and setter methods
    // for password variable
    @Bindable
    public String getPort() {

        //return Integer.toString(BR.port);
        return model.getPort();
    }

    public void setPort(String port) {
        model.setPort(port);
        notifyPropertyChanged(BR.port);
    }
    @Bindable
    public int getThrottle() {

        //return Integer.toString(BR.port);
        return model.getThrottle();
    }

    public void setThrottle(int throttle) {
        model.setThrottle(throttle);
        notifyPropertyChanged(BR.throttle);
    }

    @Bindable
    public int getRudder() {

        //return Integer.toString(BR.port);
        return model.getRudder();
    }

    public void setRudder(int rudder) {
        model.setRudder(rudder);
        notifyPropertyChanged(BR.rudder);
    }

    // constructor of ViewModel class
    @Bindable
    public String getterAddress() {


        return (model.address);
    }
    @Bindable
    public String getterPort() {

        //return Integer.toString(BR.port);


        return (model.port);

    }
    public FGVM() {

        // instantiating object of
        // model class


        model = new FGM("" ,"");

    }

    public void create()
    {
        model = new FGM("" ,"");

    }
    // actions to be performed
    // when user clicks
    // the LOGIN button
    public void onButtonClicked() {

        model.setAddress(getterAddress());
        model.setPort(getterPort());

        model.setAddress(getAddress());
        model.setPort(getPort());

        model.connect();

        if (isValid())
        { setToastMessage(successMessage);}

        else
        {setToastMessage(errorMessage);}

    }

    // method to keep a check
    // that variable fields must
    // not be kept empty by user
    public boolean isValid() {
        return !TextUtils.isEmpty(getAddress()) && Patterns.EMAIL_ADDRESS.matcher(getAddress()).matches()
                && getPort().length() > 5;
    }
}