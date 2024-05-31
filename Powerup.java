// fix for chat
import javax.swing.*;
import java.awt.*;

public class Powerup extends Square {
    private int x;
    private int y;
    public boolean col = false;

    public Powerup(int x, int y, Color c) {
        super(15, x, y, c);
        this.x = x;
        this.y = y;
    }

    public void drawMe(Graphics g) {
        if (!col) {
            ImageIcon tommy = new ImageIcon("cherry.png");
            g.drawImage(tommy.getImage(), getX() + 2, getY() + 2, 15, 15, null);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(getX() + 2, getY() + 2, 15, 15);
        }
    }

    public int collide(Pacman pacman) {
        if (pacman.getX() >= getX() && pacman.getX() <= getX() + 15 &&
                pacman.getY() >= getY() && pacman.getY() <= getY() + 15 && !col) {
            col = true;
            return 1;
        } else {
            return 0;
        }
    }
}
