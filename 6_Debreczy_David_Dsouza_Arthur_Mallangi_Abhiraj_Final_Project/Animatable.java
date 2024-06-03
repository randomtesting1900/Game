import javax.swing.*;
import java.awt.*;
/** 
* Animatable Interface, used for animation
* @author David Debreczy
* @author Arthur Dsouza
* @author Abhiraj Mallangi
* @version 2 June 2024
*/
public interface Animatable
{
/** Used to change the X and Y of animatable objects
*/
   public void step();
/** Used to draw a buffer of animatable objects onto the screen
* @param g  Graphics tool
*/
   public void drawMe(Graphics g);
}