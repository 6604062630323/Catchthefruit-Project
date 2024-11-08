/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catchthefruit;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Heart {
    private Image heartImage;
    private int lives;

    public Heart() {
        URL imgHeartURL = getClass().getResource("/catchthefruit/picture/heart.png");
        heartImage = new ImageIcon(imgHeartURL).getImage();
        lives = 3;
    }

    public void drawHearts(Graphics g) {
        for (int i = 0; i < lives; i++) {
            g.drawImage(heartImage, 20 + i * 40, 20, 30, 30, null); // Draws hearts 40px apart
        }
    }

    public void loseLife() {
        if (lives > 0) {
            lives--;
        }
    }

    public int getLives() {
        return lives;
    }
}
