import javax.swing.*;
import java.awt.BorderLayout;


public class Main {

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    public static void main(String[] args) {
        JFrame window = new JFrame("Instructions Example");
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setLayout(new BorderLayout());

        menuPanel menuPanel = new menuPanel(WINDOW_WIDTH, WINDOW_HEIGHT, difficulty -> {

            GamePanel gamePanel = new GamePanel(difficulty);

            // change the window content
            window.getContentPane().removeAll();
            window.add(gamePanel, BorderLayout.CENTER);
            window.revalidate();
            window.repaint();

        });

        window.add(menuPanel, BorderLayout.CENTER);

        window.setVisible(true);

    }

}