    package catchthefruit;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.MouseAdapter;
    import java.awt.event.MouseEvent;
    import java.net.URL;

    public class GameOverPanel extends JPanel {
        private Image imgBg;
        private Image imgPlayAgain;
        private Image imgExit;
        private Rectangle playAgainRect;
        private Rectangle exitRect;
        private JLabel gameOverLabel;
        private JLabel scoreLabel;
        private MainGame mainGame; 
        
        public GameOverPanel(int finalScore, GamePanel2 gamePanel,MainGame mainGame) {
            this.mainGame = mainGame;
            setLayout(null);
            URL imgBgURL = getClass().getResource("/catchthefruit/picture/Menuscreen.png");
            imgBg = new ImageIcon(imgBgURL).getImage();

            URL playAgainURL = getClass().getResource("/catchthefruit/picture/Playagain.png");
            imgPlayAgain = new ImageIcon(playAgainURL).getImage().getScaledInstance(200, 80, Image.SCALE_SMOOTH); // Set button size
            playAgainRect = new Rectangle(320, 400, 200, 80); 

            URL exitURL = getClass().getResource("/catchthefruit/picture/Exit.png");
            imgExit = new ImageIcon(exitURL).getImage().getScaledInstance(200, 80, Image.SCALE_SMOOTH); // Set button size
            exitRect = new Rectangle(530, 400, 200, 80); 

            gameOverLabel = new JLabel("GAME OVER", JLabel.CENTER);
            gameOverLabel.setFont(new Font("Arial", Font.BOLD, 50));
            gameOverLabel.setForeground(Color.WHITE);
            gameOverLabel.setBounds(250, 100, 500, 100);
            add(gameOverLabel);

            scoreLabel = new JLabel("Final Score: " + finalScore, JLabel.CENTER);
            scoreLabel.setFont(new Font("Arial", Font.PLAIN, 30));
            scoreLabel.setForeground(Color.WHITE);
            scoreLabel.setBounds(300, 200, 400, 50);
            add(scoreLabel);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Point clickPoint = e.getPoint();
                    if (playAgainRect.contains(clickPoint)) {
                        mainGame.restartGame();
                    } else if (exitRect.contains(clickPoint)) {
                        System.exit(0);
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(imgBg, 0, 0, getWidth(), getHeight(), this);
            g.drawImage(imgPlayAgain, playAgainRect.x, playAgainRect.y, playAgainRect.width, playAgainRect.height, this);
            g.drawImage(imgExit, exitRect.x, exitRect.y, exitRect.width, exitRect.height, this);
        }

        public void updateScore(int score) {
            scoreLabel.setText("Final Score: " + score);
        }
    }
