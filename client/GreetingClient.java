package client;

import java.net.*;
import java.io.*;

public class GreetingClient {

    public static void main(String[] args) {
        System.out.println("Client is ready to connect...");
        try {
            Socket soc = new Socket("localhost",5000);
            soc.close();
        }
        
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
