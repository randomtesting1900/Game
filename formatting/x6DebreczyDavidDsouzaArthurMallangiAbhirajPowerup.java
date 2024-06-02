// fix for chat
import javax.swing.*;
import java.awt.*;

public class x6DebreczyDavidDsouzaArthurMallangiAbhirajPowerup extends x6DebreczyDavidDsouzaArthurMallangiAbhirajSquare {
   private int x;
   private int y;
   public boolean col = false;
   
   public x6DebreczyDavidDsouzaArthurMallangiAbhirajPowerup() {
      super(15, 20, 20, Color.BLACK);
   }
   
   public x6DebreczyDavidDsouzaArthurMallangiAbhirajPowerup(int x, int y, Color c) {
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

   public int collide(x6DebreczyDavidDsouzaArthurMallangiAbhirajPacman pacman) {
      if (pacman.getX() >= getX() && pacman.getX() <= getX() + 15 &&
          pacman.getY() >= getY() && pacman.getY() <= getY() + 15 && !col) {
         col = true;
         return 1;
      } else {
         return 0;
      }
   }
   
   public int getX() {
      return x;
   }
   public int getY() {
      return y;
   }
   public void setX(int xx) {
      x = xx;
   }
   public void setY(int yy) {
      y = yy;
   }
}
