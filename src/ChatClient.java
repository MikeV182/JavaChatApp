import java.net.*;
import java.io.*;

public class ChatClient {
    private Socket socket = null;

    ChatClient(String adress, int port) {
        try {
            socket = new Socket(adress, port);
            System.out.println("Client connected");
            
            socket.close();
        }
        catch (UnknownHostException u) {
            System.out.println("Host is unknown" + u.getMessage());
        }
        catch (IOException i) {
            System.out.println("Error occured: " + i.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        ChatClient client = new ChatClient("localhost", 6666);
    }
}
