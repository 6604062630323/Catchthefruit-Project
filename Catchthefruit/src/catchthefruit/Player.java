package catchthefruit;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.ImageIcon;

public class Player {
    int x = 425;
    int y = 560;
    int actorWidth = 150; 
    int actorHeight = 150; 
    private int score = 0;
    private int speed = 15;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private Image[] walkImages = new Image[7];
    private int currentFrame = 0;
    private int animationSpeed = 5;
    private int frameCount = 0; 

    public Player() {
        for (int i = 0; i < 7; i++) {
            URL imgURL = getClass().getResource("/PlayerPicture/Playerwalk" + (i + 1) + ".png");
            walkImages[i] = new ImageIcon(imgURL).getImage();
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            movingLeft = true;
        }
        if (key == KeyEvent.VK_D) {
            movingRight = true;
        }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            movingLeft = false;
        }
        if (key == KeyEvent.VK_D) {
            movingRight = false;
        }
    }

    public void update() {
        if (movingLeft) {
            x -= speed;
            updateAnimation(); 
        }
        if (movingRight) {
            x += speed;
            updateAnimation(); 
        }

        if (x < 0) {
            x = 0;
        } else if (x + actorWidth > 1000) {
            x = 1000 - actorWidth;
        }
    }

    private void updateAnimation() {
        frameCount++;
        if (frameCount >= animationSpeed) {
            currentFrame = (currentFrame + 1) % walkImages.length;
            frameCount = 0;
        }
    }

    public void draw(Graphics g) {
        if (movingLeft || movingRight) {
            g.drawImage(walkImages[currentFrame], x, y, actorWidth, actorHeight, null);
        } else {
            g.drawImage(walkImages[0], x, y, actorWidth, actorHeight, null);
        }
    }

    public boolean checkCollisionFruit(Fruit.GroupItem fruit) {
        int width = 75, height = 75;
        return (x < fruit.x + width && x + actorWidth > fruit.x &&
                y < fruit.y + height && y + 20 > fruit.y);
    }
    
    public boolean checkCollisionWatermelon(Watermelon.GroupItem watermelon) {
        int width = 75, height = 75;
        return (x < watermelon.x + width && x + actorWidth > watermelon.x &&
                y < watermelon.y + height && y + 20 > watermelon.y);
    }

    public boolean checkCollisionTrash(Trash.GroupItem trash) {
        int width = 75, height = 75;
        return (x < trash.x + width && x + actorWidth > trash.x &&
                y < trash.y + height && y + 20 > trash.y);
    }
    
    public void scoreplus(){
        this.score++;
    }
    public void scoreplusbonus(){
        this.score+=10;
    }
    public int getscore(){
        return score;
    }
    public void resetscore(){
        score = 0;
    }
}
