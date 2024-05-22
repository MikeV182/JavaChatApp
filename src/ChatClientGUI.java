import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ChatClientGUI {
    private ChatClient client;
    private JFrame frame;
    private JTextField textField;
    private JTextArea textArea;

    public ChatClientGUI(int WIDTH, int HEIGHT) {
        frame = new JFrame("Java Chat");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        textArea = new JTextArea();
        textArea.setEditable(false);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        textField = new JTextField();
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // client sending message
                textField.setText("");
            }
        });
        frame.add(textField, BorderLayout.SOUTH);

        try {
            this.client = new ChatClient("localhost", 6666);
            // need to start client somehow
        }
        catch (IOException e) {
            e.getStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        new ChatClientGUI(400, 500);
    }

}
