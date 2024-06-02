import javax.swing.*;
import java.awt.*;

public class Health extends Square {
   private int x;
   private int y;
   public Health() {
      // hardcode
   }
   public void drawMe(Graphics g) {
      ImageIcon h = new ImageIcon("healthbar.png");
      ImageIcon x = new ImageIcon("xBar.png");
      
      if (Map.getHealth() == 3) {
         g.drawImage(h.getImage(), 279, 581, 18, 18, null);
         g.drawImage(h.getImage(), 279+22, 581, 18, 18, null);
         g.drawImage(h.getImage(), 279+44, 581, 18, 18, null);
      } else if (Map.getHealth() == 2) {
         g.drawImage(h.getImage(), 280, 581, 18, 18, null);
         g.drawImage(h.getImage(), 280+22, 581, 18, 18, null);
      } else if (Map.getHealth() == 1) {
         g.drawImage(h.getImage(), 291, 581, 18, 18, null);
      } else {
         g.drawImage(x.getImage(), 291, 581, 18, 18, null);
      }
    
   }
}