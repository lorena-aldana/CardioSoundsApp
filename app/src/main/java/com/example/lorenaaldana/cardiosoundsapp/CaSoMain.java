package com.example.lorenaaldana.cardiosoundsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CaSoMain extends AppCompatActivity {

    TextView txtDevCon;
    Button btnstartECGAc;
    Button btnstopECGAc;
    //IP. It will be updated when connecting to a new device.
    String ip = "127.0.0.1";

    int stateconnection;
    //int port = 5001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca_so_main);
        txtDevCon=(TextView) findViewById(R.id.textDevCon);
        btnstartECGAc= (Button) findViewById(R.id.buttonStartECG);
        btnstopECGAc= (Button) findViewById(R.id.buttonstopECG);
        btnstartECGAc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                stateconnection=0;
                Thread connecttoPiT = new Thread(new OSCSend(ip, stateconnection)); //creates thread
                connecttoPiT.start(); //starts the thread
                txtDevCon.setText("Connected to RaspberryPi");

            }

        });
        btnstopECGAc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                stateconnection=1;
                Thread disconnectPi = new Thread(new OSCSend(ip, stateconnection)); //creates thread
                disconnectPi.start(); //starts the thread
                txtDevCon.setText("Disconnected from RaspberryPi");

            }

        });

    }
}
