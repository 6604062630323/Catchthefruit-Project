package catchthefruit;

import javax.swing.*;

public class Catchthefruit {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGame game = new MainGame();
            game.setVisible(true);
        });
    }
}