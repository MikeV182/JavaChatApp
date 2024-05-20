package server;

import java.net.*;
import java.io.*;

public class GreetingServer {

    public static void main(String[] args) {
        System.out.println("Waiting clients..."); 
        try {
            ServerSocket ss = new ServerSocket(5000);
            Socket client = ss.accept();
            System.out.println("Connection established...");
            ss.close();
            client.close();
        }
        
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
