import javax.swing.*;
import java.awt.*;

public class x6DebreczyDavidDsouzaArthurMallangiAbhirajFood extends x6DebreczyDavidDsouzaArthurMallangiAbhirajCircle {
   public int x;
   public int y;
   public boolean col = false;

   public x6DebreczyDavidDsouzaArthurMallangiAbhirajFood(int xValue, int yValue) {
      super(2, xValue+8, yValue+8, new Color(237, 179, 149));
   }
   
   public int collide(x6DebreczyDavidDsouzaArthurMallangiAbhirajPacman pacman) {
      if (((getX() - 8) == (pacman.getX()) && ((getY() - 8) == (pacman.getY())
         && (col == false)))) {
         col = true;
         setColor(Color.BLACK);
         return 1;
      } else {
         return 0;
      }
   }
      
      
}