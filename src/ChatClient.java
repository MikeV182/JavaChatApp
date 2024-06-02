import java.net.*;
import java.io.*;

public class ChatClient {
    private Socket socket = null;
    private PrintWriter pr = null;
    private InputStreamReader inputStream = null;
    private BufferedReader in = null;

    ChatClient(String adress, int port) {
        try {
            socket = new Socket(adress, port);
            System.out.println("You entered a chat room");
            pr = new PrintWriter(socket.getOutputStream(), true);
            // TODO while message != "quit" read from buffer and display
            pr.println("Hi brotha!");
            inputStream = new InputStreamReader(socket.getInputStream());
            in = new BufferedReader(inputStream);
            System.out.println(in.readLine());

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
