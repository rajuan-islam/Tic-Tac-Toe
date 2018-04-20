package com.example.dhrubo.clienttcp;

/**
 * Created by Dhrubo on 7/27/2017.
 */

public class GetMessageFromServer extends Thread {
    ActivityConnection activity;
    String message;

    public GetMessageFromServer(ActivityConnection activity) {
        this.activity = activity;
    }

    @Override
    public void run() {
        while (true) {
            try {
                message = Manager.fromServer.readLine();
                activity.changeOutputText(message);
            } catch (Exception ex) {
                activity.showToast(ex.getClass().toString());
                activity.showToast("Server Disconnected!!!");
                new CloseConnectionToServer(activity).start();
                break;
            }
        }
    }
}
