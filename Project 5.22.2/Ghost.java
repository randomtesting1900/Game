
import javax.swing.*;
import java.awt.*;

public class Ghost extends Square implements Animatable {
   private int x;
   private int y;
   public boolean north = false;
   public boolean south = false;
   public boolean east = false;
   public boolean west = false;
   public boolean proceed = false;
   
   private int[][] matrix = new int[30][30];
   
   public Ghost(int x, int y, Color c) {
      super(20, x, y, c);
      this.x = x;
      this.y = y;
      this.matrix = Map.getLevel();
   }
   
   public void drawMe(Graphics g) {
      ImageIcon tommy = new ImageIcon("ghost.png");
      g.drawImage(tommy.getImage(), getX(), getY(), 20, 20, null);
   }
      
   public void step() {
      if (proceed) {
         setX(getX());
         setY(getY());
      }
   }
   
   public void ghostMove() {
      
      double choose = Math.random();
      
      
      /********** continuation **************/
      
      if ( (north == true)
         && (matrix[(getX() / 20)][((getY() / 20) - 1)] != 5)
         && (matrix[(getX() / 20)][((getY() / 20) - 1)] != 0)
         && (proceed == true) ) {
         
            setY(getY() - 20);
            proceed = true;
            
            
      } else if ( (south == true)
         && (matrix[((getX() / 20))-1][((getY() / 20) + 1)] != 5)
         && (matrix[((getX() / 20))-1][((getY() / 20) + 1)] != 0)
         && (proceed == true) ) {
         
            setY(getY() - 20);
            proceed = true;
            
            
      } else if ( (west == true)
         && (matrix[(getX() / 20) -1][((getY() / 20))] != 5)
         && (matrix[(getX() / 20)-1][((getY() / 20))] != 0)
         && (proceed == true) ) {
         
            setX(getX() - 20);
            proceed = true;
      
      } else if ( (east == true)
         && (matrix[(getX() / 20)+1][((getY() / 20))] != 5)
         && (matrix[(getX() / 20)+1][((getY() / 20))] != 0)
         && (proceed == true) ) {
         
            setX(getX() + 20);
            proceed = true;
      /********  ************/
      
      /******** begin **********/
      
      } else if (north == false && south == false && east == false && west == false && proceed == false) {
         if (choose < 0.25 && (matrix[(getX() / 20)][((getY() / 20) - 1)] != 5) && (matrix[(getX() / 20)][((getY() / 20) - 1)] != 0) && getX() > 20 && getY() > 20) {
            north = true;
            proceed = true;
         } else if ((0.25 <= choose && choose < 0.50) && (matrix[((getX() / 20))-1][((getY() / 20) + 1)] != 5) && (matrix[((getX() / 20))-1][((getY() / 20) + 1)] != 0)&& getX() > 20 && getY() > 20) {
            south = true;
            proceed = true;
         } else if ((0.5 <= choose && choose < 0.75) && (matrix[(getX() / 20) -1][((getY() / 20))] != 5) && (matrix[(getX() / 20) -1][((getY() / 20))] != 0)&& getX() > 20 && getY() > 20) {
            west = true;
            proceed = true;
         } else if (choose >= 0.75 && (matrix[(getX() / 20)+1][((getY() / 20))] != 5) && (matrix[(getX() / 20)+1][((getY() / 20))] != 0)&& getX() > 20 && getY() > 20) {
            east = true;
            proceed = true;
         } else {
            setX(getX());
            setY(getY());
         }
      /*******************************/
      } else if ( (north == true) && ((matrix[(getX() / 20)][((getY() / 20) - 1)] == 5) || (matrix[(getX() / 20)][((getY() / 20) - 1)] == 0))) {
         north = false;
         proceed = false;
      } else if ( (south == true) && ((matrix[((getX() / 20))-1][((getY() / 20) + 1)] ==5 || (matrix[((getX() / 20))-1][((getY() / 20) + 1)] == 0)))) {
         south = false;
         proceed = false;
      } else if ( (west == true) && ((matrix[(getX() / 20) -1][((getY() / 20))] == 5) || (matrix[(getX() / 20)-1][((getY() / 20))] == 0))) {
         west = false;
         proceed = false;
      } else if ( (east == true) && ((matrix[(getX() / 20)+1][((getY() / 20))] == 5) || (matrix[(getX() / 20)+1][((getY() / 20))] == 0))) {
         east = false;
         proceed = false;
      }
   }
         

         
}

/*


import javax.swing.*;
import java.awt.*;

public class Ghost extends Square implements Animatable {
   private int x;
   private int y;
   public boolean north = false;
   public boolean south = false;
   public boolean east = false;
   public boolean west = false;
   public boolean proceed = false;
   
   private int[][] matrix = new int[30][30];
   
   public Ghost(int x, int y, Color c) {
      super(20, x, y, c);
      this.x = x;
      this.y = y;
      this.matrix = Map.getLevel();
   }
   
   public void drawMe(Graphics g) {
      ImageIcon tommy = new ImageIcon("ghost.png");
      g.drawImage(tommy.getImage(), getX(), getY(), 20, 20, null);
   }
      
   public void step() {
      setX(getX());
      setY(getY());
   }
   
   public void ghostMove() {
      
      double choose = Math.random();
      
      
      /********** continuation **************
      if ( (north == true)
         && (matrix[(ghost.getX() / 20)][((ghost.getY() / 20) - 1)] != 5)
         && (matrix[(ghost.getX() / 20)][((ghost.getY() / 20) - 1)] != 0)
         && (proceed == true) ) {
         
            ghost.setY(ghost.getY() - 20);
            proceed = true;
            
            
      } else if ( (south == true)
         && (matrix[((ghost.getX() / 20))-1][((ghost.getY() / 20) + 1)] != 5)
         && (matrix[((ghost.getX() / 20))-1][((ghost.getY() / 20) + 1)] != 0)
         && (proceed == true) ) {
         
            ghost.setY(ghost.getY() - 20);
            proceed = true;
            
            
      } else if ( (west == true)
         && (matrix[(ghost.getX() / 20) -1][((ghost.getY() / 20))] != 5)
         && (matrix[(ghost.getX() / 20)-1][((ghost.getY() / 20))] != 0)
         && (proceed == true) ) {
         
            ghost.setX(ghost.getX() - 20);
            proceed = true;
      
      } else if ( (east == true)
         && (matrix[(ghost.getX() / 20)+1][((ghost.getY() / 20))] != 5)
         && (matrix[(ghost.getX() / 20)+1][((ghost.getY() / 20))] != 0)
         && (proceed == true) ) {
         
            ghost.setX(ghost.getX() + 20);
            proceed = true;
      /********  ************/
      
      /******** begin **********
      
      } else if (north == false && south == false && east == false && west == false && proceed == false) {
         if (choose < 0.25 && (matrix[(ghost.getX() / 20)][((ghost.getY() / 20) - 1)] != 5) && (matrix[(ghost.getX() / 20)][((ghost.getY() / 20) - 1)] != 0)) {
            north = true;
            proceed = true;
         } else if (0.25 <= choose < 0.5 && (matrix[((ghost.getX() / 20))-1][((ghost.getY() / 20) + 1)] != 5) && (matrix[((ghost.getX() / 20))-1][((ghost.getY() / 20) + 1)] != 0)) {
            south = true;
            proceed = true;
         } else if (0.5 <= choose < 0.75 && (matrix[(ghost.getX() / 20) -1][((ghost.getY() / 20))] != 5) && (matrix[(ghost.getX() / 20) -1][((ghost.getY() / 20))] != 0)) {
            west = true;
            proceed = true;
         } else if (choose >= 0.75 && (matrix[(ghost.getX() / 20)+1][((ghost.getY() / 20))] != 5) && (matrix[(ghost.getX() / 20)+1][((ghost.getY() / 20))] != 0)) {
            east = true;
            proceed = true;
         } else {
            ghostMove();
         }
      /*******************************
      } else if ( (north == true) && ((matrix[(ghost.getX() / 20)][((ghost.getY() / 20) - 1)] == 5) || (matrix[(ghost.getX() / 20)][((ghost.getY() / 20) - 1)] == 0))) {
         north = false;
         proceed = false;
      } else if ( (south == true) && ((matrix[((ghost.getX() / 20))-1][((ghost.getY() / 20) + 1)] ==5 || (matrix[((ghost.getX() / 20))-1][((ghost.getY() / 20) + 1)] == 0)))) {
         south = false;
         proceed = false;
      } else if ( (west == true) && ((matrix[(ghost.getX() / 20) -1][((ghost.getY() / 20))] == 5) || (matrix[(ghost.getX() / 20)-1][((ghost.getY() / 20))] == 0))) {
         west = false;
         proceed = false;
      } else if ( (east == true) && ((matrix[(ghost.getX() / 20)+1][((ghost.getY() / 20))] == 5) || (matrix[(ghost.getX() / 20)+1][((ghost.getY() / 20))] == 0))) {
         east = false;
         proceed = false;
      }
   }
         

         
} */