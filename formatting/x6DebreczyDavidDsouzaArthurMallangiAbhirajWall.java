import javax.swing.*;
import java.awt.*;

public class x6DebreczyDavidDsouzaArthurMallangiAbhirajWall {
   private int side;
   private int x;
   private int y;

   public x6DebreczyDavidDsouzaArthurMallangiAbhirajWall(int xValue, int yValue)  {
      side = 20;
      x = xValue;
      y = yValue;
   }
   public void drawMe(Graphics g) {
      g.setColor(Color.BLUE);
      g.drawRect(x, y, side, side);
   }
}