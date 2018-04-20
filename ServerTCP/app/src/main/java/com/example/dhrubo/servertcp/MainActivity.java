package com.example.dhrubo.servertcp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    TextView noticeText;
    ServerThread serverThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noticeText = (TextView) findViewById(R.id.notice_text);
        serverThread = null;

        startServer();
    }

    private void startServer() {
        Toast.makeText(this,"Server Started",Toast.LENGTH_SHORT).show();
        new ServerThread(this,noticeText).start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            if(serverThread!=null) serverThread.stop();
            serverThread = null;
            Toast.makeText(this,"Server Closed",Toast.LENGTH_SHORT).show();
        } catch(Exception ex) {
            Toast.makeText(this,"error in server closing",Toast.LENGTH_SHORT).show();
        }
    }
}
