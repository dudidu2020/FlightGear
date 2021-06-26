package com.example.flightgear10;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;


public class FGM {
    @Nullable
    String address,port;
    int throttle,rudder;

    BlockingDeque<Runnable> dispatchQueue=new LinkedBlockingDeque<Runnable>();
    PrintWriter out=null;

    public void connect() {


        firstConnection();

            addInput();

            new Thread(new Runnable() {
                public void run() {
                    while (true) {

                        try {

                            dispatchQueue.take().run();
                        } catch (InterruptedException e) {}
                    }
                }
            }).start();
        }

    void firstConnection()     {
        dispatchQueue.add(new Runnable() {
            @Override
            public void run() {
                System.out.println("11111");
                try {
                    Socket fg = new Socket(address, Integer.parseInt(port));
                    System.out.println("11112");
                    out = new PrintWriter(fg.getOutputStream(), true);
                    System.out.println("11113");

                } catch (IOException e) { }
            }});}


    void addInput() {
        dispatchQueue.add(new Runnable() {
            @Override
            public void run() {

                if(out!=null)
                {

                    out.print("set /controls/flight/aileron " + Integer.toString(0) + "\r\n");
                    out.print("set /controls/flight/elevator " + Integer.toString(0) + "\r\n");
                    out.print("set /controls/flight/rudder " + rudder + "\r\n");
                    out.print("set /controls/flight/current-engine/throttle " + throttle + "\r\n");
                    out.flush();
                    //192.168.7.67
                    //79.180.2.71
                }
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





}
