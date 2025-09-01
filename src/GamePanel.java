import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private ScenePanel scenePanel;
    private int gameLevel;

    public GamePanel(String difficulty) {
        setLayout(null);

        scenePanel = new ScenePanel(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);


        switch (difficulty.toLowerCase()) {
            case "easy":

                scenePanel.setDifficulty(1);
                gameLevel = 1;
                JLabel easyJLabel = new JLabel("easy Mode");
                this.add(easyJLabel);
                break;
            case "medium":
                scenePanel.setDifficulty(2);
                gameLevel = 2;
                JLabel mediumJLabel = new JLabel("medium Mode");
                this.add(mediumJLabel);
                break;
            case "hard":
                scenePanel.setDifficulty(3);
                gameLevel = 3;
                JLabel hardJLabel = new JLabel("hard Mode");
                this.add(hardJLabel);
                break;
        }


        this.add(scenePanel);

        scenePanel.requestFocus();
        scenePanel.setFocusable(true);
    }

    public ScenePanel getScenePanel() {
        return scenePanel;
    }

    public int getGameLevel() {
        return gameLevel;
    }

    public GamePanel setGameLevel(int gameLevel) {
        this.gameLevel = gameLevel;
        return this;
    }
}
