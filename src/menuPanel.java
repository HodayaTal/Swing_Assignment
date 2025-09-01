import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class menuPanel extends JPanel {
    private ScenePanel scenePanel;

    public void setScenePanel (ScenePanel scenePanel) {
        this.scenePanel = scenePanel;
    }


        public menuPanel(int width, int height, Consumer<String> startGameCallback) {
            this.setBounds(0, 0, width, height);
            this.setLayout(new GridLayout(5, 1));

            JLabel label = new JLabel("Select difficulty:", JLabel.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 20));
            this.add(label);

            JButton startButton = new JButton("Start - easy mode");
            startButton.addActionListener(e -> {
                    startGameCallback.accept("easy");
                }
            );
            JButton changeButton = new JButton("Change game level");
            JButton mediumButton = new JButton("Medium");
            JButton hardButton = new JButton("Hard");
                changeButton.addActionListener((event) -> {
                mediumButton.addActionListener(e -> {
                    startGameCallback.accept("medium");
                });
                hardButton.addActionListener(e -> {
                    startGameCallback.accept("hard");
                });

                this.add(mediumButton);
                this.add(hardButton);
                this.revalidate();
                this.repaint();

            });
                this.add(startButton);
                this.add(changeButton);
        }


}
