package catchthefruit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Random;

public class GamePanel2 extends JPanel {

    private Player player = new Player();
    private Heart heart = new Heart();
    private Fruit fruit = new Fruit();
    private Trash trash = new Trash();
    private Watermelon watermelon = new Watermelon();
    private Image imgBgDay, imgBgNight;
    private Timer spawnTimer, gameTimer, backgroundTimer;
    private Random rand = new Random();
    private int timeLeft = 90;
    private boolean isNight = false;
    private JButton pauseButton;
    private boolean isPaused = false; 
    private MainGame mainGame; 
    private boolean isGameRunning = false;
    private Sound sound; 

    public GamePanel2(MainGame mainGame) {
        this.mainGame = mainGame; 
        sound = new Sound();
        setFocusable(true);
        requestFocusInWindow();
        URL imgBgDayURL = getClass().getResource("/catchthefruit/picture/Background.png");
        URL imgBgNightURL = getClass().getResource("/catchthefruit/picture/BackgroundNight.png");
        imgBgDay = new ImageIcon(imgBgDayURL).getImage();
        imgBgNight = new ImageIcon(imgBgNightURL).getImage();
        ImageIcon pauseIcon = new ImageIcon(getClass().getResource("/catchthefruit/picture/Pausepic.png"));
        Image scaledPauseIcon = pauseIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        pauseButton = new JButton(new ImageIcon(scaledPauseIcon));
        pauseButton.setContentAreaFilled(false);
        pauseButton.setBorderPainted(false);
        pauseButton.setFocusPainted(false);
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    togglePause();
                } else if (!isPaused) {
                    player.keyPressed(e);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (!isPaused) {
                    player.keyReleased(e);
                }
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePause();
                requestFocusInWindow();
            }
        });
        setLayout(null);
        add(pauseButton);

        //Time for spawn Item
        spawnTimer = new Timer(600, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spawnObject();
            }
        });
        spawnTimer.start();

        Timer updateTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPaused) {
                    player.update(); // Update player position
                    fruit.updateItems();
                    trash.updateItems();
                    watermelon.updateItems();
                    checkCollision(); // Check for collisions
                    checkDead();
                    repaint();
                }
            }
        });
        updateTimer.start();

        //Time countdown
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    timeLeft--;
                } else {
                    gameTimer.stop();
                    endGame();
                }
                repaint();
            }
        });
        gameTimer.start();

        //Day & night
        backgroundTimer = new Timer(30000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isNight = !isNight;
                repaint();
            }
        });
        backgroundTimer.start();

    }
    
    //rate spawn item Fruit 70:30 Trash
    private void spawnObject() {
        int randValue = rand.nextInt(100);
        if (randValue < 60) {
            fruit.addItem();
        }else if(randValue >=60 && randValue <=70){
            watermelon.addItem();
        }else {
            trash.addItem();
        }
    }
    
    private void checkCollision() {
        // Check for fruit collision
        for (int i = 0; i < fruit.getList().size(); i++) {
            Fruit.GroupItem fruitItem = fruit.getList().get(i);
            if (player.checkCollisionFruit(fruitItem)) {
                fruit.getList().remove(i);
                player.scoreplus();
                sound.play("/Sound/collectsound.wav");
                break;
            }
        }
        for (int i = 0; i < watermelon.getList().size(); i++) {
            Watermelon.GroupItem watermelonItem = watermelon.getList().get(i);
            if (player.checkCollisionWatermelon(watermelonItem)) {
                watermelon.getList().remove(i);
                player.scoreplusbonus();
                sound.play("/Sound/collectsound.wav");
                break;
            }
        }
        // Check for trash collision
        for (int i = 0; i < trash.getList().size(); i++) {
            Trash.GroupItem trashItem = trash.getList().get(i);
            if (player.checkCollisionTrash(trashItem)) {
                trash.getList().remove(i);
                sound.play("/Sound/Trash.wav");
                heart.loseLife();
                break;
            }
        }
    }

    private void checkDead() {
        if (heart.getLives() == 0) {
            endGame();
        }
    }

    private void togglePause() {
        isPaused = !isPaused;

        if (isPaused) {
            spawnTimer.stop();
            gameTimer.stop();
            backgroundTimer.stop();
            // Change button icon resume
            ImageIcon resumeIcon = new ImageIcon(getClass().getResource("/catchthefruit/picture/Resumepic.png"));
            Image scaledResumeIcon = resumeIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            pauseButton.setIcon(new ImageIcon(scaledResumeIcon));
        } else {
            spawnTimer.start();
            gameTimer.start();
            backgroundTimer.start();
            // Change button icon back to pause
            ImageIcon pauseIcon = new ImageIcon(getClass().getResource("/catchthefruit/picture/Pausepic.png"));
            Image scaledPauseIcon = pauseIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            pauseButton.setIcon(new ImageIcon(scaledPauseIcon));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isNight) {
            g.drawImage(imgBgNight, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.drawImage(imgBgDay, 0, 0, getWidth(), getHeight(), this);
        }

        player.draw(g);
        heart.drawHearts(g);
        fruit.drawItems(g);
        trash.drawItems(g);
        watermelon.drawItems(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Time: " + timeLeft + "s", getWidth() / 2 - 50, 50);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString("Score: " + player.getscore(), getWidth() - 220, 50);
        pauseButton.setBounds(getWidth() - 70, 10, 50, 50);
    }

    public void startGame() {
        requestFocusInWindow();
        if (!isGameRunning) {
            player = new Player(); 
            heart = new Heart(); 
            fruit.clear();
            trash.clear();
            watermelon.clear();
            timeLeft = 90; 
            player.resetscore(); 
            spawnTimer.start();
            gameTimer.start();
            backgroundTimer.start();
            isNight = false;
            isGameRunning = true;
            isPaused = false;
            ImageIcon pauseIcon = new ImageIcon(getClass().getResource("/catchthefruit/picture/Pausepic.png"));
            Image scaledPauseIcon = pauseIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            pauseButton.setIcon(new ImageIcon(scaledPauseIcon));
            requestFocusInWindow();
        }
    }

    public void stopGame() {
        if (isGameRunning) {
            spawnTimer.stop();
            gameTimer.stop();
            backgroundTimer.stop();
            isGameRunning = false;
            isPaused = false;
            requestFocusInWindow();
        }
    }
    
    private void endGame() {
        mainGame.gameOver(player.getscore());
        isGameRunning = false; 
        spawnTimer.stop();
        gameTimer.stop();
        backgroundTimer.stop();
    }
    
    public void restart() {
        startGame();
    }
    
    public void setisGameRunning(){
        isGameRunning = true;
    }
    
}
