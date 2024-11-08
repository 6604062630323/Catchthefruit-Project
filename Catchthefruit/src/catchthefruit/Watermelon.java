package catchthefruit;

import java.net.URL;
import javax.swing.ImageIcon;

public class Watermelon extends Item {
    public Watermelon() {
        URL imgwatermelonURL = getClass().getResource("/Item/Watermelon.png");
        img = new ImageIcon(imgwatermelonURL).getImage();
    }
}