import java.awt.*;

public class Player {
    public static final int SIZE = 40;
    private ScenePanel scenePanel;
    private int x_position,y_position;
    private int idPlayer;
    private int winning;

    public Player(ScenePanel scenePanel , int idPlayer ) {
        if(idPlayer == 0) {
            this.scenePanel = scenePanel;
            this.x_position = 25;
            this.y_position = 50;
            this.idPlayer = idPlayer;
        } else {
            this.scenePanel = scenePanel;
            this.x_position = 750;
            this.y_position = 300;
            this.idPlayer = idPlayer;
        }
    }


    public void moveUp() {
        if (this.y_position > 10) {
            this.y_position -= 6;
        }
    }

    public void moveDown() {
        if (this.y_position + 145 < Main.WINDOW_HEIGHT) {
            this.y_position += 6;
        }
    }


    public void paint(Graphics graphics) {
        graphics.setColor(Color.blue);
        graphics.fillRect(this.x_position , this.y_position , 10 , 100);
    }

    public int getX_position() {
        return x_position;
    }

    public int getY_position() {
        return y_position;
    }

    public void setX_position(int x_position) {
        this.x_position = x_position;
    }

    public void setY_position(int y_position) {
        this.y_position = y_position;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public Player setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
        return this;
    }

    public int isWinning() {
        return winning;
    }

    public Player setWinning() {
        this.winning += 1;
        return this;
    }
}
