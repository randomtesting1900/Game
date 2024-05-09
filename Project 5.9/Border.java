import javax.swing.*;
import java.awt.*;

public class Border {
    private int side;
    private int x;
    private int y;

    public Border(int xValue, int yValue) {
        side = 20;
        x = xValue;
        y = yValue;
    }
    public void drawMe(Graphics g) {
        g.setColor(new Color(54, 107, 227));
        g.fillRect(x, y, side, side);
    }
}