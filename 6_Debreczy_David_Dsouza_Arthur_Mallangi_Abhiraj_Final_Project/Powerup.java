import javax.swing.*;
import java.awt.*;
/** 
* Powerup, cherries that allow Pacman to eat ghosts
* @author David Debreczy
* @author Arthur Dsouza
* @author Abhiraj Mallangi
* @version 2 June 2024
*/
public class Powerup extends Square {
/** X position */
   private int x;
/** Y position */
   private int y;
/** State of collision */
   public boolean col = false;
/** Default constructor, instantiates at (20,20) */   
   public Powerup() {
      super(15, 20, 20, Color.BLACK);
   }
/** Constructs a powerup at (x,y) with Color c
* @param x  X location
* @param y  Y location
* @param c  Color (RGB or preset)
*/
   public Powerup(int x, int y, Color c) {
      super(15, x, y, c);
      this.x = x;
      this.y = y;
   }
/** Draws the powerup onto the screen, takes into account collision factors
* @param g  Graphics tool
*/
   public void drawMe(Graphics g) {
      if (!col) {
         ImageIcon tommy = new ImageIcon("cherry.png");
         g.drawImage(tommy.getImage(), getX() + 2, getY() + 2, 15, 15, null);
      } else {
         g.setColor(Color.BLACK);
         g.fillRect(getX() + 2, getY() + 2, 15, 15);
      }
   }
/** Detects and handles collision
* @param pacman   Pacman object
* @return 1,0 dependent on collision
*/
   public int collide(Pacman pacman) {
      if (pacman.getX() >= getX() && pacman.getX() <= getX() + 15 &&
          pacman.getY() >= getY() && pacman.getY() <= getY() + 15 && !col) {
         col = true;
         return 1;
      } else {
         return 0;
      }
   }
/** Returns X position
* @return X position
*/   
   public int getX() {
      return x;
   }
/** Returns Y position
* @return Y position
*/
   public int getY() {
      return y;
   }
/** Sets x to xx
* @param xx    Requested x position
*/
   public void setX(int xx) {
      x = xx;
   }
/** Sets y to yy 
* @param yy    Requested Y position
*/
   public void setY(int yy) {
      y = yy;
   }
}
