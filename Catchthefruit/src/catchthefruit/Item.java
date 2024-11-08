package catchthefruit;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.ImageIcon;

public class Item {
    protected final int width = 75;
    protected final int height = 75;
    protected final int speed = 6;
    protected LinkedList<GroupItem> list = new LinkedList<>();
    protected Random random = new Random();
    protected Image img;

    public class GroupItem {
        int x;
        int y;

        GroupItem(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void update() {
            this.y += speed;
        }
    }

    public void updateItems() {
        for (GroupItem item : list) {
            item.update();
        }
    }

    public void drawItems(Graphics g) {
        for (GroupItem item : list) {
            g.drawImage(img, item.x, item.y, width, height, null);
        }
    }

    public void addItem() {
        int xPosition = random.nextInt(1000 - width);
        list.add(new GroupItem(xPosition, -height));
    }
    
    public void clear() {
        list.clear();
    }

    public LinkedList<GroupItem> getList() {
        return list;
    }
}
