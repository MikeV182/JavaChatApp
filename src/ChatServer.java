import java.net.*;
import java.io.*;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6666);
        System.out.println("Waiting for clients...");

        Socket client = server.accept();
        System.out.println("Connection established " + client);

        client.close();
        server.close();
    }
}
