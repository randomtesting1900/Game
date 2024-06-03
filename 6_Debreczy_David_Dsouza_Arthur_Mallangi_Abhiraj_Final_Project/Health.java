import javax.swing.*;
import java.awt.*;
/** 
* Health class, shows the amount of lives the player has on the bottom
* @author David Debreczy
* @author Arthur Dsouza
* @author Abhiraj Mallangi
* @version 2 June 2024
*/
public class Health extends Square {
/** X location
*/
   private int x;
/** Y location
*/
   private int y;
/** Default constructor, no args, no need for them
*/
   public Health() { 
   }
/** Constructs a Health object at (X,Y)
* @param x  X position
* @param y  Y position
*/   
   public Health(int x, int y) {
      this.x = x;
      this.y = y;
   }
/** Draws the health onto the screen dependent of lives
* @param g  Graphics tool
*/   
   public void drawMe(Graphics g) { // if health == 3, show 3 lives, if 2, show 2, etc.
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