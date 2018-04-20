package com.example.dhrubo.clienttcp;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Dhrubo on 7/27/2017.
 */

public class Manager {
    // Networking Stuff
    public static Socket server=null;
    public static PrintWriter toServer;
    public static BufferedReader fromServer;

    // Control Stuff
    public static boolean BUSY_CONNECTION=false;
}
