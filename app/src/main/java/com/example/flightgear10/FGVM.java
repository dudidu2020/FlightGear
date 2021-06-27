package com.example.flightgear10;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


public class FGVM extends BaseObservable {


    private FGM model;


    @Bindable
    public String getAddress() {


        return model.getAddress();
    }

    public void setAddress(String address) {
        model.setAddress(address);
        notifyPropertyChanged(BR.address);

    }

    @Bindable
    public String getPort() {


        return model.getPort();
    }

    public void setPort(String port) {
        model.setPort(port);
        notifyPropertyChanged(BR.port);

    }

    @Bindable
    public int getThrottle() {


        return model.getThrottle();
    }

    public void setThrottle(int throttle) {
        model.setThrottle(throttle);
        notifyPropertyChanged(BR.throttle);
        model.addInput();
    }

    @Bindable
    public int getRudder() {


        return model.getRudder();
    }

    public void setRudder(int rudder) {
        model.setRudder(rudder);
        notifyPropertyChanged(BR.rudder);
        model.addInput();
    }


    @Bindable
    public String getterAddress() {


        return (model.address);
    }

    @Bindable
    public String getterPort() {


        return (model.port);

    }

    @Bindable
    public String getAileron() {
        return model.aileron;
    }

    public void setAileron(String aileron) {
        model.setAileron(aileron);
        notifyPropertyChanged(BR.aileron);
        model.addInput();
    }

    @Bindable
    public String getElevator() {
        return model.elevator;
    }

    public void setElevator(String elevator) {
        model.setElevator(elevator);
        notifyPropertyChanged(BR.elevator);
        model.addInput();
    }


    public FGVM() {

        model = new FGM("", "");

    }


    public void onButtonClicked() {

        model.setAddress(getterAddress());
        model.setPort(getterPort());

        model.setAddress(getAddress());
        model.setPort(getPort());

        if ((getPort() != "") && (getAddress() != "")) {
            model.connect();
        }


    }


}