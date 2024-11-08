package catchthefruit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class MenuPanel extends JPanel {
    private Image imgBackground;
    private Image imgStart;
    private Image imgExit;
    private Image imgLogo; // โลโก้
    private Rectangle startRect;
    private Rectangle exitRect;

    public MenuPanel(ActionListener startListener) {
        setLayout(null);
        URL logoURL = getClass().getResource("/catchthefruit/picture/Logo.png");
        imgLogo = new ImageIcon(logoURL).getImage().getScaledInstance(750, 500, Image.SCALE_SMOOTH);
        
        URL startURL = getClass().getResource("/catchthefruit/picture/Start.png");
        imgStart = new ImageIcon(startURL).getImage().getScaledInstance(200, 80, Image.SCALE_SMOOTH);
        startRect = new Rectangle(400, 400, 200, 80);

        URL exitURL = getClass().getResource("/catchthefruit/picture/Exit.png");
        imgExit = new ImageIcon(exitURL).getImage().getScaledInstance(200, 80, Image.SCALE_SMOOTH);
        exitRect = new Rectangle(400, 500, 200, 80);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point clickPoint = e.getPoint();
                if (startRect.contains(clickPoint)) {
                    startListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                } else if (exitRect.contains(clickPoint)) {
                    System.exit(0);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgBackground, 0, 0, getWidth(), getHeight(), this);
        int logoX = (getWidth() - imgLogo.getWidth(this)) / 2;
        int logoY = 10;
        g.drawImage(imgLogo, logoX, logoY, this);
        g.drawImage(imgStart, startRect.x, startRect.y, startRect.width, startRect.height, this);
        g.drawImage(imgExit, exitRect.x, exitRect.y, exitRect.width, exitRect.height, this);
    }

    public void setBackgroundImage(URL imgBgURL) {
        imgBackground = new ImageIcon(imgBgURL).getImage();
    }
}
