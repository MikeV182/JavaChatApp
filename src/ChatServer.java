import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class ChatServer {
    private static ArrayList<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6666);
        System.out.println("Waiting for clients...");

        while (!server.isClosed()) {
            Socket clientSocket = server.accept();
            System.out.println("Connection established " + clientSocket);
            
            ClientHandler newClient = new ClientHandler(clientSocket, clients);
            clients.add(newClient);
            new Thread(newClient).start();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket cliSocket = null;
    private ArrayList<ClientHandler> clients = new ArrayList<>();
    private PrintWriter pr = null;
    private InputStreamReader inputStream = null;
    private BufferedReader in = null;
    
    ClientHandler(Socket cliSocket, ArrayList<ClientHandler> clients) throws IOException {
        this.cliSocket = cliSocket;
        this.clients = clients;
        inputStream = new InputStreamReader(cliSocket.getInputStream());
        in = new BufferedReader(inputStream);
        pr = new PrintWriter(cliSocket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            String message = "";
            while ((message = in.readLine()) != null) {
                for (ClientHandler client : clients) {
                    client.pr.println(cliSocket + ":" + message);
                }
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
        finally {
            try {
                cliSocket.close();
                pr.close();
                inputStream.close();
                in.close();
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }
}