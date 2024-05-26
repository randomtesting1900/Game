import javax.swing.*;
import java.awt.*;

public class Wall {
   private int side;
   private int x;
   private int y;

   public Wall(int xValue, int yValue)  {
      side = 20;
      x = xValue;
      y = yValue;
   }
   public void drawMe(Graphics g) {
      g.setColor(Color.BLUE);
      g.drawRect(x, y, side, side);
   }
}