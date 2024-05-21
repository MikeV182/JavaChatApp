package server;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class GreetingServer {
    public static void main(String[] args) {
        System.out.println("Waiting clients..."); 
        try {
            ServerSocket ss = new ServerSocket(5000);
            Socket client = ss.accept();
            System.out.println("Connection established...");

            InputStream inputClient = client.getInputStream();
            DataInputStream in = new DataInputStream(inputClient);
            System.out.println(in.readUTF());

            OutputStream outputServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outputServer);
            Scanner scan = new Scanner(System.in);
            String output = scan.nextLine();
            out.writeUTF(output);
            scan.close();

            ss.close();
            client.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
