import java.net.*;
import java.io.*;
import java.util.*;

public class ChatClient {
    private String message = null;
    private Scanner scanner = null;
    private Socket socket = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;

    public ChatClient(String adress, int port) {
        try {
            socket = new Socket(adress, port);
            System.out.println("Client connected...");
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            scanner = new Scanner(System.in);

            while (message != "&quit") {
                message = scanner.nextLine();
                out.writeUTF(message);
                System.out.println(in.readUTF());
            }

            scanner.close();
            socket.close();
        }
        catch (UnknownHostException u) {
            System.out.println("Host is unknown: " + u.getMessage());
        }
        catch (IOException i) {
            System.out.println("Problem occured: " + i.getMessage());
        }
    }
    public static void main(String[] args) {
        ChatClient client = new ChatClient("localhost", 6666);
    }
}
