import javax.swing.*;
import java.awt.*;

public class x6DebreczyDavidDsouzaArthurMallangiAbhirajHealth extends x6DebreczyDavidDsouzaArthurMallangiAbhirajSquare {
   private int x;
   private int y;
   
   public x6DebreczyDavidDsouzaArthurMallangiAbhirajHealth() {
      
   }
   
   public x6DebreczyDavidDsouzaArthurMallangiAbhirajHealth(int x, int y) {
      this.x = x;
      this.y = y;
   }
   
   public void drawMe(Graphics g) {
      ImageIcon h = new ImageIcon("healthbar.png");
      ImageIcon x = new ImageIcon("xBar.png");
      
      if (x6DebreczyDavidDsouzaArthurMallangiAbhirajMap.getHealth() == 3) {
         g.drawImage(h.getImage(), 279, 581, 18, 18, null);
         g.drawImage(h.getImage(), 279+22, 581, 18, 18, null);
         g.drawImage(h.getImage(), 279+44, 581, 18, 18, null);
      } else if (x6DebreczyDavidDsouzaArthurMallangiAbhirajMap.getHealth() == 2) {
         g.drawImage(h.getImage(), 280, 581, 18, 18, null);
         g.drawImage(h.getImage(), 280+22, 581, 18, 18, null);
      } else if (x6DebreczyDavidDsouzaArthurMallangiAbhirajMap.getHealth() == 1) {
         g.drawImage(h.getImage(), 291, 581, 18, 18, null);
      } else {
         g.drawImage(x.getImage(), 291, 581, 18, 18, null);
      }
    
   }
}