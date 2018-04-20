package com.example.dhrubo.clienttcp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.*;
import java.io.*;

public class MainActivity extends AppCompatActivity implements ActivityConnection {

    Button connectButton, sendButton, disconnectButton;
    EditText inputText;
    TextView outputText, noticeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectButton = (Button) findViewById(R.id.connect_button);
        sendButton = (Button) findViewById(R.id.send_button);
        disconnectButton = (Button) findViewById(R.id.disconnect_button);
        inputText = (EditText) findViewById(R.id.input_text);
        outputText = (TextView) findViewById(R.id.output_text);
        noticeText = (TextView) findViewById(R.id.notice_text);

        //Manager.BUSY_CONNECTION = false;
        if(Manager.server!=null) {
            new CloseConnectionToServer(MainActivity.this).start();
            Manager.server = null;
        }

        setListeners();
    }

    private void setListeners() {
        // connection
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Manager.server!=null) {
                    noticeText.setText("Already connected!");
                    return;
                }

                if(!Manager.BUSY_CONNECTION) {
                    Manager.BUSY_CONNECTION = true;
                    new ConnectToServer(MainActivity.this).start();
                }
            }
        });

        // sending and receiving
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Manager.server==null) {
                    noticeText.setText("Not connected to server!");
                    return;
                }

                String sentence = inputText.getText().toString();
                new SendMessageToServer(MainActivity.this,sentence).start();

                /*try{
                    String sentence = inputText.getText().toString();
                    toServer.println(sentence);
                    sentence = fromServer.readLine();
                    outputText.setText(sentence);
                } catch (Exception ex) {
                    noticeText.setText("error sending text");
                }*/
            }
        });

        // disconnect button
        disconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Manager.server==null) {
                    noticeText.setText("Not connected yet");
                    return;
                }

                new CloseConnectionToServer(MainActivity.this).start();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "exit", Toast.LENGTH_SHORT).show();
        new CloseConnectionToServer(MainActivity.this).start();
    }

    /*
        * ActivityConnection interface implementation*/
    @Override
    public void showToast(String message) {
        runOnUiThread(new ChangeNoticeText(MainActivity.this,message));
    }

    @Override
    public void changeOutputText(String message) {
        runOnUiThread(new ChangeOutputText(outputText,message));
    }
}

/*
* Run on UI thread runnables*/
class ChangeNoticeText implements Runnable {
    String message;
    Context activity;

    public ChangeNoticeText(Context activity,String message) {
        this.activity = activity;
        this.message = message;
    }

    @Override
    public void run() {
        Toast.makeText(activity,message,Toast.LENGTH_LONG).show();
    }
}

class ChangeOutputText implements Runnable {
    String message;
    TextView outputText;

    public ChangeOutputText(TextView outputText,String message) {
        this.outputText = outputText;
        this.message = message;
    }

    @Override
    public void run() {
        outputText.setText(message);
    }
}