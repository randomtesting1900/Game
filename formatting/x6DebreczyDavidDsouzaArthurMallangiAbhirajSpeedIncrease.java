import javax.swing.*;
import java.awt.*;

public class x6DebreczyDavidDsouzaArthurMallangiAbhirajSpeedIncrease extends x6DebreczyDavidDsouzaArthurMallangiAbhirajSquare {
   private int x;
   private int y;
   public boolean col;
   
   public x6DebreczyDavidDsouzaArthurMallangiAbhirajSpeedIncrease() {
      super(15, 20, 20, Color.BLACK);
   }
   
   public x6DebreczyDavidDsouzaArthurMallangiAbhirajSpeedIncrease(int x, int y, Color c) {
      super(15, x, y, c);
      this.x = x;
      this.y = y;
   }
   public void drawMe(Graphics g) {
      if (!col) {
         ImageIcon tommy = new ImageIcon("orange.png");
         g.drawImage(tommy.getImage(), getX()+2, getY()+2, 15, 15, null);
      } else {
          g.setColor(Color.BLACK);
          g.fillRect(getX() + 2, getY() + 2, 15, 15);
      }
   }
   
   public int collide(x6DebreczyDavidDsouzaArthurMallangiAbhirajPacman pacman) {
      if (pacman.getX() >= getX() && pacman.getX() <= getX() + 15 &&
          pacman.getY() >= getY() && pacman.getY() <= getY() + 15 && !col) {
         col = true;
         return 1;
      } else {
         return 0;
      }
   }
}