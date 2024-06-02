import javax.swing.*;
import java.awt.*;

public class Food extends Circle {
   public int x;
   public int y;
   public boolean col = false;
   
   public Food() {
      super(2, 8, 8, new Color(237, 179, 149));
   }
   
   public Food(int xValue, int yValue) {
      super(2, xValue+8, yValue+8, new Color(237, 179, 149));
   }
   
   public int collide(Pacman pacman) {
      if (((getX() - 8) == (pacman.getX()) && ((getY() - 8) == (pacman.getY())
         && (col == false)))) {
         col = true;
         setColor(Color.BLACK);
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