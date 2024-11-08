package catchthefruit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private MenuPanel menuPanel;
    private GamePanel2 gamePanel;
    private GameOverPanel gameOverPanel;
    private Sound sound;

    public MainGame() {
        setTitle("Catch the Fruit Game");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        sound = new Sound();
        menuPanel = new MenuPanel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        menuPanel.setBackgroundImage(getClass().getResource("/catchthefruit/picture/Menuscreen.png"));
        gamePanel = new GamePanel2(this);
        gameOverPanel = new GameOverPanel(0, gamePanel,this);
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(gamePanel, "Game");
        mainPanel.add(gameOverPanel, "GameOver");
        add(mainPanel);
        gamePanel.setisGameRunning();
        gamePanel.stopGame();
        cardLayout.show(mainPanel, "Menu");
    }

    private void startGame() {
        gamePanel.startGame();
        sound.play("/Sound/Gamestart.wav");
        cardLayout.show(mainPanel, "Game");
        gamePanel.requestFocusInWindow();
    }

    public void restartGame() {
        gamePanel.startGame();
        cardLayout.show(mainPanel, "Game");
        gamePanel.requestFocusInWindow();
    }

    public void gameOver(int score) {
        gamePanel.stopGame();
        gameOverPanel.updateScore(score);
        cardLayout.show(mainPanel, "GameOver");
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGame game = new MainGame();
            game.setVisible(true);
        });
    }*/
}
