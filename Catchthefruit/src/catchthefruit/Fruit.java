package catchthefruit;

import java.net.URL;
import javax.swing.ImageIcon;

public class Fruit extends Item {
    public Fruit() {
        URL imgfruitURL = getClass().getResource("/Item/fruit.png");
        img = new ImageIcon(imgfruitURL).getImage();
    }
}
