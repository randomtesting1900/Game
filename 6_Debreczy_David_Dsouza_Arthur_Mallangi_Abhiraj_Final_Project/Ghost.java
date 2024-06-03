import javax.swing.*;
import java.awt.*;
/** 
* Ghost class, Pacman enemy, autonomous via random movement
* @author David Debreczy
* @author Arthur Dsouza
* @author Abhiraj Mallangi
* @version 2 June 2024
*/
public class Ghost extends Square implements Animatable {
/** X position
*/
   private int x;
/** Y position
*/
   private int y;
/** North clear?
*/
   public boolean north = false;
/** South clear?
*/
   public boolean south = false;
/** East clear?
*/
   public boolean east = false;
/** West clear?
*/
   public boolean west = false;
/** State of free direction
*/
   public boolean proceed = false;
/** State of Pacman powerup
*/
   public boolean isEat = false;
/** State of death
*/
   public boolean notMove = false;
/** Game map
*/  
   private int[][] matrix = new int[30][30];
/** Constructs a ghost at (X,Y) with a color of c
* @param x  X position
* @param y  Y position
* @param c  Color (Usually black)
*/
   public Ghost(int x, int y, Color c) {
      super(20, x, y, c);
      this.x = x;
      this.y = y;
      this.matrix = Map.getLevel();
   }
/** Draws a ghost onto the screen, changes color dependent on state of ghost
* @param g  Graphics tool
*/  
   public void drawMe(Graphics g) {
      ImageIcon health = new ImageIcon("ghostvul.png");
      ImageIcon vul = new ImageIcon("ghost.png");
      ImageIcon dead = new ImageIcon("ghostdead.png");
      if (notMove == true) {
         g.drawImage(dead.getImage(), getX(), getY(), 20, 20, null);
      } else if (isEat == true && notMove != true) {
         g.drawImage(vul.getImage(), getX(), getY(), 20, 20, null);
      } else {
         g.drawImage(health.getImage(), getX(), getY(), 20, 20, null);
      }
   }
/** Implemented from Animatable, moves the ghost
*/     
   public void step() {
      if (proceed && !notMove) {
         if (north) setY(getY() - 20);
         if (south) setY(getY() + 20);
         if (west) setX(getX() - 20);
         if (east) setX(getX() + 20);
         proceed = false;
      }
   }
/** Ghost move logic, checks if isEat is false and notMove == false. This means the ghost is still alive.
* Checks to see if each direction in the grid is available, and if not, shrinks its scope.
* Ghost then chooses a random direction based on Math.random()
*/   
   public void ghostMove() {
      double choose = Math.random();
      isEat = Map.getEatable();
      if ((isEat == true || isEat == false) && notMove != true) {
         if (north && (matrix[getX() / 20][(getY() / 20) - 1] != 5) && (matrix[getX() / 20][(getY() / 20) - 1] != 0)) {
            proceed = true;
         } else if (south && (matrix[getX() / 20][(getY() / 20) + 1] != 5) && (matrix[getX() / 20][(getY() / 20) + 1] != 0)) {
            proceed = true;
         } else if (west && (matrix[(getX() / 20) - 1][getY() / 20] != 5) && (matrix[(getX() / 20) - 1][getY() / 20] != 0)) {
            proceed = true;
         } else if (east && (matrix[(getX() / 20) + 1][getY() / 20] != 5) && (matrix[(getX() / 20) + 1][getY() / 20] != 0)) {
            proceed = true;
         } else if (!north && !south && !east && !west) {
            if (choose < 0.25 && (matrix[getX() / 20][(getY() / 20) - 1] != 5) && (matrix[getX() / 20][(getY() / 20) - 1] != 0)) {
               north = true;
               proceed = true;
            } else if (choose < 0.5 && (matrix[getX() / 20][(getY() / 20) + 1] != 5) && (matrix[getX() / 20][(getY() / 20) + 1] != 0)) {
               south = true;
               proceed = true;
            } else if (choose < 0.75 && (matrix[(getX() / 20) - 1][getY() / 20] != 5) && (matrix[(getX() / 20) - 1][getY() / 20] != 0)) {
               west = true;
               proceed = true;
            } else if (choose >= 0.75 && (matrix[(getX() / 20) + 1][getY() / 20] != 5) && (matrix[(getX() / 20) + 1][getY() / 20] != 0)) {
               east = true;
               proceed = true;
            }
         } else {
            north = south = east = west = false;
            proceed = false;
         }
      }
   }
/** Returns the state of collision depending on Pacman's state and the ghost's state
* @param pacman   Pacman object
* @return 0,1,2 dependent on state
*/   
   public int ghostCollide(Pacman pacman) {
      isEat = Map.getEatable();
      
      if (notMove == true) {return 0;}
      
     
      if (getX() == pacman.getX() && getY() == pacman.getY() && isEat == false) {
         return 1;
      } else if (getX() == pacman.getX() && getY() == pacman.getY() && isEat == true) {
         notMove = true;
         return 2;
         
      } else {
         return 0;
      }
   }
}