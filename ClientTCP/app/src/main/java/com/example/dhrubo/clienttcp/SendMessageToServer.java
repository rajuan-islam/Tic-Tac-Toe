package com.example.dhrubo.clienttcp;

/**
 * Created by Dhrubo on 7/27/2017.
 */

public class SendMessageToServer extends Thread {
    String message;
    ActivityConnection activity;

    public SendMessageToServer(ActivityConnection activity,String message) {
        this.activity = activity;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            Manager.toServer.println(message);
        } catch (Exception ex) {
            activity.showToast(ex.getClass().toString());
        }
    }
}
