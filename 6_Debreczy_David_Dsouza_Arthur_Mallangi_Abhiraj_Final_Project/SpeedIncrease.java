import javax.swing.*;
import java.awt.*;
/** 
* SpeedIncrease, doubles Pacman's movement
* @author David Debreczy
* @author Arthur Dsouza
* @author Abhiraj Mallangi
* @version 2 June 2024
*/
public class SpeedIncrease extends Square {
/** X position */
   private int x;
/** Y position */
   private int y;
/** Collision state */
   public boolean col;
/** Default constructor at (20,20) */
   public SpeedIncrease() {
      super(15, 20, 20, Color.BLACK);
   }
/** Constructs a SpeedIncrease at (x,y) with the Color c 
* @param x  X position
* @param y  Y position
* @param c  Color (RGB or preset)
*/  
   public SpeedIncrease(int x, int y, Color c) {
      super(15, x, y, c);
      this.x = x;
      this.y = y;
   }
/** Draws SpeedIncrease onto the screen
* @param g  Graphics tool
*/
   public void drawMe(Graphics g) {
      if (!col) {
         ImageIcon tommy = new ImageIcon("orange.png");
         g.drawImage(tommy.getImage(), getX()+2, getY()+2, 15, 15, null);
      } else {
          g.setColor(Color.BLACK);
          g.fillRect(getX() + 2, getY() + 2, 15, 15);
      }
   }
/** Detects and handles Pacman collision based on previous instances
* @param pacman   Pacman object
* @return 1,0 dependent on state
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
}