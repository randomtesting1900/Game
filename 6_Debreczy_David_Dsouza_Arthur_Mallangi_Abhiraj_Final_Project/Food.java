import javax.swing.*;
import java.awt.*;
/** 
* Food class, used to increase score for Pacman until the player wins
* @author David Debreczy
* @author Arthur Dsouza
* @author Abhiraj Mallangi
* @version 2 June 2024
*/
public class Food extends Circle {
/** X position
*/
   public int x; // X position
/** Y position
*/
   public int y; // Y position
/** Collision state
*/
   public boolean col = false; // Collision state
/** Constructs food at (xValue, yValue)
* @param xValue   X position
* @param yValue   Y position
*/
   public Food(int xValue, int yValue) {
      super(2, xValue+8, yValue+8, new Color(237, 179, 149));
   }
/** Returns the state of collision, disappears once collided, takes an object parameter
* Returns 1 if collided, returns 0 if no collision or if there was a previous collision
* @param pacman   Pacman object
* @return   State of collision
*/
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
}