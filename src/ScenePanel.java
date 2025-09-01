
import javax.swing.*;
import java.awt.*;

public class ScenePanel extends JPanel {
    public static final int BALLS_AMOUNT = 1;
    public static final int PLAYERS_AMOUNT = 2;

    private Player[] players;
    private Ball[] balls;
    private boolean stopGame;
    private int scoreLeft = 0;
    private int scoreRight = 0;
    private JButton stopButton;
    private JButton backToMenu;
    private MoveMentListener keyListener;
    private int timeLeft;     // left time
    private Timer gameTimer;  // timer
    private JLabel timerLabel; // show on the screen
    private int currentGameLevel;



    public ScenePanel(int x_position , int y_position , int width , int height) {
        this.setBounds(x_position , y_position , width , height);
        this.setLayout(null);

        this.players = new Player[PLAYERS_AMOUNT];
        this.balls = new Ball[BALLS_AMOUNT];

        for (int i = 0; i < this.balls.length; i++) {
            this.balls[i] = new Ball(Main.WINDOW_WIDTH / 2 , Main.WINDOW_HEIGHT / 2);
        }
        for (int i = 0; i < players.length; i++) {
            this.players[i] = new Player(this , i);
        }

        this.stopGame = false;
        keyListener = new MoveMentListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.requestFocus();
        this.addKeyListener(keyListener);

        stopButton = new JButton("Stop");
        stopButton.setBounds(Main.WINDOW_WIDTH/2 - 55, 60, 100, 30);
        stopButton.addActionListener(e -> toggleGame());
        this.add(stopButton);

        backToMenu = new JButton("Back to menu");
        backToMenu.setBounds(Main.WINDOW_WIDTH / 2 - 70, 60, 130, 30);
        backToMenu.addActionListener(e ->backToMenuButton());
        this.add((backToMenu));
        backToMenu.setVisible(false);

        initTimer(420);

        this.game_loop();

}

    private void initTimer(int seconds) {
        timeLeft = seconds;
        timerLabel = new JLabel("Time: " + timeLeft);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 13));
        timerLabel.setForeground(Color.BLACK);
        timerLabel.setBounds(Main.WINDOW_WIDTH / 2 - 34, 20, 120, 30);
        add(timerLabel);

        gameTimer = new Timer(1000, e -> {
            if (!stopGame) {
                timeLeft--;
                timerLabel.setText("Time: " + timeLeft);
                if (timeLeft <= 0) {
                    gameTimer.stop();
                    stopGame = true;
                    backToMenu.setVisible(true);
                    stopButton.setVisible(false);
                    whoIsWinning(null);
                }
            }
        });
        gameTimer.start();
    }


    private void toggleGame() {
        this.stopGame = !this.stopGame;
        stopButton.setText(this.stopGame ? "Resume" : "Stop");
    }



    private void backToMenuButton() {
        //find the frame who hold the ScenePanel
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        // remove this panel
        frame.getContentPane().removeAll();

        menuPanel menu = new menuPanel(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT, difficulty -> {
            ScenePanel gamePanel = new ScenePanel(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
            gamePanel.setDifficulty(this.currentGameLevel);
            frame.getContentPane().removeAll();
            frame.add(gamePanel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });

        frame.add(menu, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private JLabel whoIsWinning(Player player) {
        if (player != null) {
            JLabel TheWinnerIs = new JLabel("Player " + (player.getIdPlayer() + 1) + " has won the game");
            TheWinnerIs.setBounds(Main.WINDOW_WIDTH / 2 - 50 , Main.WINDOW_HEIGHT / 2, 300 , 50);
            TheWinnerIs.setFont(new Font("Ariel" ,Font.BOLD , 15));
            this.add(TheWinnerIs);
            return TheWinnerIs;
        }

        JLabel TheWinnerIs = new JLabel("Both are losers");
        TheWinnerIs.setBounds(Main.WINDOW_WIDTH / 2 - 60 , Main.WINDOW_HEIGHT / 2, 300 , 50);
        TheWinnerIs.setFont(new Font("Ariel" ,Font.BOLD , 15));
        this.add(TheWinnerIs);
        return TheWinnerIs;
    }

    private void game_loop() {
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { }

            this.setFocusable(true);
            this.requestFocus();
            this.addKeyListener(new MoveMentListener(this));
            this.addKeyListener(keyListener);

            while (true) {
                for (Ball ball : this.balls) {
                            if (!this.stopGame) {
                        if (keyListener.up_player_one) {
                            this.players[1].moveUp();
                        }
                        if (keyListener.down_player_one) {
                            this.players[1].moveDown();
                        }
                        if (keyListener.up_player_two) {
                            this.players[0].moveUp();
                        }
                        if (keyListener.down_player_two) {
                            this.players[0].moveDown();
                        }
                        ball.movement();

                        // players hits
                        for (Player player : this.players) {
                            Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), Ball.SIZE, Ball.SIZE);
                            Rectangle playerRect = new Rectangle(player.getX_position(), player.getY_position(), 10, 100);

                            if (ballRect.intersects(playerRect)) {
                                ball.reverseX();
                            }
                        }

                        // upper/Lower limits hits
                        if (ball.getY() <= 0 || ball.getY() + Ball.SIZE >= Main.WINDOW_HEIGHT - 30) {
                            ball.reverseY();
                        }

                        // players points
                        if (ball.getX() <= 0) {
                            System.out.println("Right player's point!");
                            scoreRight++;
                            ball.reset();
                        }
                        if (ball.getX() + Ball.SIZE >= Main.WINDOW_WIDTH) {
                            System.out.println("Left player's point!");
                            scoreLeft++;
                            ball.reset();
                        }

                        if (scoreLeft == 7) {
                            stopButton.setVisible(false);
                            this.stopGame = true;
                            backToMenu.setVisible(true);
                            this.players[1].setWinning();
                            whoIsWinning(this.players[1]);

                        } else if (scoreRight == 7) {
                            stopButton.setVisible(false);
                            this.stopGame = true;
                            backToMenu.setVisible(true);
                            this.players[0].setWinning();
                            whoIsWinning(this.players[0]);

                        }
                        }

                }

                this.repaint();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) { }
            }
        }).start();
    }

    public void setDifficulty(int level) {
        this.currentGameLevel = level;
        switch (level) {
            case 1: // easy
                for (Ball b : balls) b.setSpeed(3);
                break;
            case 2: // medium
                for (Ball b : balls) b.setSpeed(4);
                break;
            case 3: // hard
                for (Ball b : balls) b.setSpeed(6);
                break;
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (Player player : this.players) {
            player.paint(graphics);
        }
        for (Ball ball : this.balls) {
            ball.paint(graphics);
        }

        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial", Font.BOLD, 24));
        graphics.drawString(String.valueOf(scoreLeft), Main.WINDOW_WIDTH/2 - 50, 40);
        graphics.drawString(String.valueOf(scoreRight), Main.WINDOW_WIDTH/2 + 30, 40);
    }

    public void setStopGame() {
        this.stopGame = !this.stopGame;
    }

    public Player[] getPlayers() {
        return this.players;
    }

    public int getCurrentGameLevel() {
        return currentGameLevel;
    }

    public ScenePanel setCurrentGameLevel(int currentGameLevel) {
        this.currentGameLevel = currentGameLevel;
        return this;
    }
}
