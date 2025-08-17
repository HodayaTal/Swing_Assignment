import javax.swing.*;

public class Main {

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    public static void main(String[] args) {
        JFrame window = new JFrame("Instructions Example");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        Instructions instructionsPanel = new Instructions(() -> openGameWindow(window));

        window.add(instructionsPanel);
        window.setVisible(true);
    }


    private static void openGameWindow(JFrame oldFrame) {
        oldFrame.dispose();

        JFrame GameFrame = new JFrame("Game");
        GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        GameFrame.add(new GamePanel());
        GameFrame.setVisible(true);
    }
}