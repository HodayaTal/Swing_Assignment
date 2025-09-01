import javax.swing.*;
import java.awt.*;

public class Instructions extends JPanel {
    public Instructions(Runnable startGameCallback) {
        setLayout(new BorderLayout());

        JLabel text = new JLabel(
                "<html><div style='text-align: center; font-family: Arial;'>" +
                        "<h2>Air Hockey</h2>" +
                        "Player 1: Up / Down Key<br>" +
                        "Player 2: E / D Key<br>" +
                        "Time: 7 minutes<br><br>" +
                        "The winner is the first to hit 7 goals, or highest score when time’s up<br>" +
                        "After clicking the button you can choose your difficulty" +
                        "<h3>GOOD LUCK!</h3>" +
                        "</div></html>",
                JLabel.CENTER
        );


        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setBackground(new Color(0, 180, 0));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));
        buttonPanel.add(startButton);

        add(text, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> startGameCallback.run());
    }
}
