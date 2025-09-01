import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MoveMentListener implements KeyListener {
    private ScenePanel scenePanel;
    public boolean up_player_one;
    public boolean down_player_one;
    public boolean up_player_two;
    public boolean down_player_two;

    public MoveMentListener(ScenePanel scenePanel) {
        this.scenePanel = scenePanel;
        this.up_player_one = false;
        this.down_player_one = false;
        this.up_player_two = false;
        this.down_player_two = false;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
                // player one movement
                case KeyEvent.VK_UP -> this.up_player_one = true;
                case KeyEvent.VK_DOWN -> this.down_player_one = true;
                // player two movement
                case KeyEvent.VK_E -> this.up_player_two = true;
                case KeyEvent.VK_D -> this.down_player_two = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN -> this.down_player_one = false;
            case KeyEvent.VK_UP -> this.up_player_one = false;
            case KeyEvent.VK_E -> this.up_player_two = false;
            case KeyEvent.VK_D -> this.down_player_two = false;
        }
    }
}
