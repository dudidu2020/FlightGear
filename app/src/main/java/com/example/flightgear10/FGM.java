package com.example.flightgear10;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;


public class FGM {
    @Nullable
    String address, port, aileron, elevator;
    int throttle, rudder;

    BlockingDeque<Runnable> dispatchQueue = new LinkedBlockingDeque<Runnable>();
    PrintWriter out = null;

    public void connect() {


        firstConnection();

        addInput();

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    //addInput();

                    try {

                        dispatchQueue.take().run();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();
    }

    void firstConnection() {
        dispatchQueue.add(new Runnable() {
            @Override
            public void run() {

                try {
                    Socket fg = new Socket(address, Integer.parseInt(port));

                    out = new PrintWriter(fg.getOutputStream(), true);


                    out.flush();


                } catch (IOException e) {
                }
            }
        });
    }


    void addInput() {
        dispatchQueue.add(new Runnable() {
            @Override
            public void run() {


                float t = (float) throttle;
                float t1 = t / 100;
                float r = (float) rudder;
                float r0 = 2 * r - 100;
                float r1 = r0 / 100;

                if (aileron != null) {
                    out.print("set /controls/flight/aileron " + Float.parseFloat(aileron) + "\r\n");
                    out.flush();
                }
                if (elevator != null) {
                    out.print("set /controls/flight/elevator " + -1* Float.parseFloat(elevator) + "\r\n");
                    out.flush();
                }

                out.print("set /controls/flight/rudder " + r1 + "\r\n");
                out.flush();

                out.print("set /controls/engines/current-engine/throttle " + t1 + "\r\n");
                out.flush();


            }
        });
    }


    // constructor to initialize
    // the variables
    public FGM(String address1, String port1) {
        this.address = address1;
        this.port = port1;
    }

    // getter and setter methods
    // for email variable
    @Nullable
    public String getAddress() {
        return address;
    }

    public void setAddress(@Nullable String address) {
        this.address = address;
    }

    // getter and setter methods
    // for password variable
    @Nullable
    public String getPort() {
        return port;
    }

    public void setPort(@Nullable String port) {
        this.port = port;
    }

    @Nullable
    public int getThrottle() {
        return throttle;
    }

    public void setThrottle(@Nullable int throttle) {
        this.throttle = throttle;
    }

    @Nullable
    public int getRudder() {
        return rudder;
    }

    public void setRudder(@Nullable int rudder) {
        this.rudder = rudder;
    }

    @Nullable
    public String getAileron() {
        return aileron;
    }

    public void setAileron(@Nullable String aileron) {
        this.aileron = aileron;
    }

    @Nullable
    public String getElevator() {
        return elevator;
    }

    public void setElevator(@Nullable String elevator) {
        this.elevator = elevator;
    }


}
