import javax.swing.*;
import java.awt.Color;

class GamePanel extends JPanel {
    public GamePanel() {
        this.setBackground(Color.GREEN);
        JLabel label = new JLabel("Welcome to the Game!");
        this.add(label);
    }
}