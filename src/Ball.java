import java.awt.*;
import java.util.Random;

public class Ball {
    public static final int SIZE = 30;
    private ScenePanel scenePanel;
    private int x,y;
    private int vx, vy;
    private GamePanel gamePanel;



    public Ball (int x , int y) {
        this.x = x;
        this.y = y;
        reset();
    }

    public void reset() {
        this.x = Main.WINDOW_WIDTH / 2 - SIZE / 2;
        this.y = Main.WINDOW_HEIGHT / 2 - SIZE / 2;

        Random rand = new Random();
        int num = rand.nextInt(2) + 1;
        if (num == 1) {
            this.vx = -vx;
            this.vy = -vy;
        }
    }


    public void movement() {
        this.x += vx;
        this.y += vy;
   }

    public void reverseX() {
        this.vx = -vx;
    }

    public void reverseY() {
        this.vy = -vy;
    }

    public void setSpeed(int velocity) {
        this.vx = (vx > 0 ? 1 : -1) * velocity;
        this.vy = (vy > 0 ? 1 : -1) * velocity;
    }


    public void paint(Graphics graphics) {
        graphics.setColor(Color.green);
        graphics.fillOval(this.x , this.y , SIZE , SIZE);
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVx() {
        return vx;
    }

    public Ball setVx(int vx) {
        this.vx = vx;
        return this;
    }

    public int getVy() {
        return vy;
    }

    public Ball setVy(int vy) {
        this.vy = vy;
        return this;
    }

}

