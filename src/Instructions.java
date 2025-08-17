import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Instructions extends JPanel {
    public Instructions(Runnable startGameCallback) {
        this.setLayout(new FlowLayout());

        JLabel text = new JLabel("Instructions");
        JButton button1 = new JButton("Start");

        this.add(text);
        this.add(button1);

        button1.addActionListener(e -> startGameCallback.run());
    }
}


