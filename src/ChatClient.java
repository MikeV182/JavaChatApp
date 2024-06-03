import java.net.*;
import java.io.*;

public class ChatClient {
    private Socket socket = null;
    private PrintWriter pr = null;
    private InputStreamReader inputStream = null;
    private BufferedReader in = null;
    private BufferedReader consoleIn = null;

    ChatClient(String adress, int port) {
        try {
            socket = new Socket(adress, port);
            System.out.println("You entered a chat room");

            pr = new PrintWriter(socket.getOutputStream(), true);
            inputStream = new InputStreamReader(socket.getInputStream());
            in = new BufferedReader(inputStream);

            String message;
            consoleIn = new BufferedReader(new InputStreamReader(System.in));
            while ((message = consoleIn.readLine()) != "!quit") {
                pr.println(message);
                System.out.println(in.readLine());
            }

            System.out.println("\nYou have disconnected from the chat...");
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
