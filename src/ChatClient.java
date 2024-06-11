import java.net.*;
import java.io.*;

public class ChatClient {
    private Socket socket = null;
    private PrintWriter pr = null;
    private InputStreamReader inputStream = null;
    private BufferedReader in = null;
    private BufferedReader consoleIn = null;

    public void messageListener(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(socket.isConnected()) {
                        System.out.println(in.readLine());
                    }
                } catch (IOException i) {
                    closeEverything();
                }
            }
        }).start();
    }

    public void messageSender() {
        try {
            String message = "";
            while (socket.isConnected()) {
                message = consoleIn.readLine();
                pr.println(message);
            }
        } catch (IOException i) {
            closeEverything();
        }
    }

    public void closeEverything() {
        try {
            socket.close();
            pr.close();
            inputStream.close();
            in.close();
            consoleIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    ChatClient(String adress, int port) {
        try {
            socket = new Socket(adress, port);
            System.out.println("You entered a chat room");
            pr = new PrintWriter(socket.getOutputStream(), true);
            inputStream = new InputStreamReader(socket.getInputStream());
            in = new BufferedReader(inputStream);
            consoleIn = new BufferedReader(new InputStreamReader(System.in));
        } catch (UnknownHostException u) {
            System.out.println("Host is unknown" + u.getMessage());
        } catch (IOException i) {
            System.out.println("Error occured: " + i.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        ChatClient client = new ChatClient("localhost", 6666);
        client.messageListener();
        client.messageSender();
    }
}
