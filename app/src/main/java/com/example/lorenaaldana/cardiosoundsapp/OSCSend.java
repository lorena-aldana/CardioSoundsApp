package com.example.lorenaaldana.cardiosoundsapp;

import android.util.Log;

import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortOut;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by lorenaaldana on 12/09/17.
 */
/**
public class OSCSend {
} */


public class OSCSend implements Runnable{
    String myIP;
    int myPort = 5001;
    OSCPortOut oscPortOut;
    int state;


    // Updating parameters and setup OSC port out.
    // Takes 5 parameters.
    public OSCSend(String myIP, int state){
        this.myIP = myIP;
        this.state = state;

        try{
            // Connect to IP and port
            this.oscPortOut  = new OSCPortOut(InetAddress.getByName(myIP), myPort);
        } catch(UnknownHostException e) {
            Log.d("OSCSendInitalisation", "OSC Port Out UnknownHoseException");
        } catch (SocketException e){
            // Report error
            Log.d("OSCSendInitalisation", "Socket exception error!");
        }
    }

    // This function is for the ECG signal acquisition button
    private void connecttoPi(){
        ArrayList<Object> sendBang = new ArrayList<>();
        sendBang.add("1");
        OSCMessage message = new OSCMessage("/connect", sendBang);
        Log.d("OSCRun", "OSC sent.");
        try{
            // Send messages
            oscPortOut.send(message);
        } catch (Exception e){
            Log.d("OSC2", "Failed to send.");
        }
    }

    //To disconnect from Pi
    private void disconnectPi(){
        ArrayList<Object> sendInfo = new ArrayList<>();
        sendInfo.add("0");
        OSCMessage message = new OSCMessage("/connect", sendInfo);
        Log.d("OSCRun", "OSC sent.");
        try{
            // Send messages
            oscPortOut.send(message);
        } catch (Exception e){
            Log.d("OSC2", "Failed to send.");
        }

    }


    // Run the thread.
    @Override
    public void run(){
        if (oscPortOut != null){

            if (this.state==0) {
                connecttoPi();

            }
            else if (this.state==1) {
                disconnectPi();
            }
        }
    }
}
