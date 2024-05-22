import java.net.*;
import java.io.*;
import java.util.*;

public class ChatServer {
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    public static void main(String[] args) throws IOException {
            ServerSocket server = new ServerSocket(6666);
            System.out.println("Waiting for clients...");

            while (true) {
                Socket clientSocket = server.accept();
                System.out.println("Connection established: " + clientSocket);

                ClientHandler client = new ClientHandler(clientSocket, clients);
                clients.add(client);
                Thread newClient = new Thread(client);
                newClient.start();
            }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket = null;
    private ArrayList<ClientHandler> clients = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;

    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException{
        this.clientSocket = clientSocket;
        this.clients = clients;
        this.out = new DataOutputStream(clientSocket.getOutputStream());
        this.in = new DataInputStream(clientSocket.getInputStream());
    }

    public void run() {
        try {
            String message = "";
            while ((message = in.readUTF()) != null) {
                for (ClientHandler client : clients) {
                    client.out.writeUTF(message);
                }
            }
        }
        catch (IOException i) {
            System.out.println("Error occured: " + i.getMessage());
        }
        finally {
            try {
                in.close();
                out.close();
                clientSocket.close();
            }
            catch (IOException e) {
                e.getStackTrace();
            }
        }
    }
}

