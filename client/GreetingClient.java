package client;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class GreetingClient {
    public static void main(String[] args) {
        System.out.println("Client is ready to connect...");
        try {
            Socket socketClient = new Socket("localhost",5000);
            OutputStream outToServer = socketClient.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            
            Scanner scan = new Scanner(System.in);
            System.out.print("Message to server: ");
            String userInput = scan.nextLine();
            out.writeUTF(userInput);
            scan.close();
            
            InputStream inFromServer = socketClient.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            String serverOutput = in.readUTF();
            System.out.println(serverOutput);

            socketClient.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
