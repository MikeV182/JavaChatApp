import java.awt.Color;
import javax.swing.*;

public class Interface {
    public enum Init {
        SCREEN_HEIGHT(1024),
        SCREEN_WIDTH(1280);

        public final int number;

        private Init(int number) {
            this.number = number;
        }
    }

    private static JPanel createPanel(int x, int y, int screenWidth, int screenHeight, Color color) {
        JPanel panel = new JPanel(true);
        panel.setBounds(x, y, (screenWidth / 2), screenHeight);
        panel.setBackground(color);
        panel.setLayout(null);
        JTextField field = new JTextField();
        field.setBounds(0, (screenHeight - 24), ((screenWidth / 2) - 100), 24);
        JButton button = new JButton("SEND");
        button.setBounds(((screenWidth / 2) - 100), (screenHeight - 24), 100, 24);
        panel.add(field);
        panel.add(button);
        return panel;
    } 
    public static void main(String[] args) {
        JFrame frame = new JFrame("JavaChat");

        JPanel panel1 = createPanel(0, 0, Init.SCREEN_WIDTH.number, Init.SCREEN_HEIGHT.number, Color.BLACK);
        JPanel panel2 = createPanel((Init.SCREEN_WIDTH.number / 2), 0, Init.SCREEN_WIDTH.number, Init.SCREEN_HEIGHT.number, Color.GRAY);
        
        frame.add(panel1);
        frame.add(panel2);
        frame.setSize(Init.SCREEN_WIDTH.number, Init.SCREEN_HEIGHT.number);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);
    }
}
