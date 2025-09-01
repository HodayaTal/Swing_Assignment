import javax.swing.*;

public class Main {

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    public static void main(String[] args) {
        JFrame instructionsFrame = new JFrame("Instructions");
        instructionsFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        instructionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        instructionsFrame.setResizable(false);
        instructionsFrame.setLocationRelativeTo(null);

        Instructions instructionsPanel = new Instructions(() -> {
            instructionsFrame.dispose();

            JFrame menuFrame = new JFrame();
            menuFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menuFrame.setResizable(false);
            menuFrame.setLocationRelativeTo(null);

            menuPanel menuPanel = new menuPanel(WINDOW_WIDTH, WINDOW_HEIGHT, difficulty -> {
                GamePanel gamePanel = new GamePanel(difficulty);
                menuFrame.getContentPane().removeAll();
                menuFrame.add(gamePanel);
                menuFrame.revalidate();
                menuFrame.repaint();
            });

            menuFrame.add(menuPanel);
            menuFrame.setVisible(true);
        });

        instructionsFrame.add(instructionsPanel);
        instructionsFrame.setVisible(true);
    }
}
