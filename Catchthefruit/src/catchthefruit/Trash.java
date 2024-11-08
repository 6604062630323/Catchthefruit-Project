package catchthefruit;

import java.net.URL;
import javax.swing.ImageIcon;

public class Trash extends Item {
    public Trash() {
        URL imgTrashURL = getClass().getResource("/Item/trash.png");
        img = new ImageIcon(imgTrashURL).getImage();
    }
}
