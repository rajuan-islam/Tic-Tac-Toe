package com.example.dhrubo.clienttcp;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Dhrubo on 7/27/2017.
 */

public class ConnectToServer extends Thread {
    ActivityConnection activity;

    public ConnectToServer(ActivityConnection activity) {
        this.activity = activity;
    }

    @Override
    public void run() {
        //super.run();

        //Toast.makeText(this.activity,"connecting",Toast.LENGTH_SHORT).show();
        activity.showToast("connecting");

        try {
            Manager.server = new Socket("192.168.0.108",6255);
            Manager.fromServer = new BufferedReader(new InputStreamReader(Manager.server.getInputStream()));
            Manager.toServer = new PrintWriter(Manager.server.getOutputStream(),true);

            activity.showToast("Successfully Connected!");
            Manager.BUSY_CONNECTION = false;

            new GetMessageFromServer(activity).start();
        } catch (Exception ex) {
            activity.showToast(ex.getClass().toString());

            Manager.BUSY_CONNECTION = false;
            Manager.server = null;
        }
    }
}
