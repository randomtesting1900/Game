import javax.swing.*;
import java.awt.*;

public class Food {
   public int x;
   public int y;

   public Food(int xValue, int yValue) {
      x = xValue;
      y = yValue;
   }
   public void drawMe(Graphics g) {
      g.setColor(Color.ORANGE);
      g.fillOval(x + 7, y + 7, 6, 6);
   }
}