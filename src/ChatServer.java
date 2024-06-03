import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class ChatServer {
    private static ArrayList<Socket> clients = new ArrayList<>();
    private static PrintWriter pr = null;
    private static InputStreamReader inputStream = null;
    private static BufferedReader in = null;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6666);
        System.out.println("Waiting for clients...");

        while (!server.isClosed()) {
            Socket clientSocket = server.accept();
            System.out.println("Connection established " + clientSocket);
            clients.add(clientSocket);

            for (Socket client : clients) {
                inputStream = new InputStreamReader(client.getInputStream());
                in = new BufferedReader(inputStream);
                String message = in.readLine();
                
                if (!message.equals("") && !message.equals("!quit")) {
                    for (int i = 0; i < clients.size(); i++) {
                        Socket messageTo = clients.get(i);
                        pr = new PrintWriter(messageTo.getOutputStream(), true);
                        pr.println(client + " : " + message);
                    }
                }

                else if (message.equals("!quit")){
                    for (int i = 0; i < clients.size(); i++) {
                        Socket messageTo = clients.get(i);
                        pr = new PrintWriter(messageTo.getOutputStream(), true);
                        pr.println(client + " have disconnected from the chat...");
                    }
                    clients.remove(client);
                }
            }
        }
    }
}
