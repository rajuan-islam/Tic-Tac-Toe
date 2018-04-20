package com.example.dhrubo.clienttcp;

/**
 * Created by Dhrubo on 7/27/2017.
 */

public class CloseConnectionToServer extends Thread {
    ActivityConnection activity;

    public CloseConnectionToServer(ActivityConnection activity) {
        this.activity = activity;
    }

    @Override
    public void run() {
        try {
            Manager.server.close();
            Manager.server = null;

            activity.showToast("Connection closed!");
        } catch (Exception ex) {
            Manager.server = null;
            activity.showToast(ex.getClass().toString());
        }
    }
}
