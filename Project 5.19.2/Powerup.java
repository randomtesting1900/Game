import javax.swing.*;
import java.awt.*;

public class Powerup extends Square {
   private int x;
   private int y;
   public Powerup(int x, int y, Color c) {
      super(15, x, y, c);
      this.x = x;
      this.y = y;
   }
   public void drawMe(Graphics g) {
      ImageIcon tommy = new ImageIcon("cherry.png");
      g.drawImage(tommy.getImage(), getX()+2, getY()+2, 15, 15, null);
   }
}