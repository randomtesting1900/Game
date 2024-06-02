import javax.swing.*;
import java.awt.*;

public class x6DebreczyDavidDsouzaArthurMallangiAbhirajSpeedStop extends x6DebreczyDavidDsouzaArthurMallangiAbhirajSquare {
   private int x;
   private int y;
   public boolean col;
   public x6DebreczyDavidDsouzaArthurMallangiAbhirajSpeedStop(int x, int y, Color c) {
      super(16, x, y, c);
      this.x = x;
      this.y = y;
   }
   public void drawMe(Graphics g) {
      if (!col) {
         ImageIcon tommy = new ImageIcon("trash.png");
         g.drawImage(tommy.getImage(), getX()+2, getY()+2, 16, 16, null);
      } else {
          g.setColor(Color.BLACK);
          g.fillRect(getX() + 2, getY() + 2, 16, 16);
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