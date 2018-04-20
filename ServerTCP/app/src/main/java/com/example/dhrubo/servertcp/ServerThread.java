package com.example.dhrubo.servertcp;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Dhrubo on 7/26/2017.
 */

public class ServerThread extends Thread {
    Context context;
    TextView noticeText;

    public ServerThread(Context context, TextView noticeText) {
        this.context = context;
        this.noticeText = noticeText;
    }

    @Override
    public void run() {
        super.run();
        Toast.makeText(context,"Thread Started",Toast.LENGTH_SHORT).show();
    }
}
