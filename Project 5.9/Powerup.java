import javax.swing.*;
import java.awt.*;

public class Powerup {
    public int x;
    public int y;

    public Powerup(int xValue, int yValue) {
        x = xValue;
        y = yValue;
    }
    public void drawMe(Graphics g) {
        g.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
        g.fillOval(x + 7, y + 7, 6, 6);
    }
}