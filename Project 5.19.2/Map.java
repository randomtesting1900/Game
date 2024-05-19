

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;


public class Map extends JPanel {

   private BufferedImage myImage; // buffer
   private Graphics myBuffer; // buffer
   private Timer t; // timer
   
   // key bools
   private boolean left = false; 
   private boolean right = false;
   private boolean up = false;
   private boolean down = false;
   
   private Pacman pacman;



   public static final int FRAME = 600; // double check on this
   private static final Color BACKGROUND = Color.BLACK;


   private final int n = 30; // grid count
   public int[][] matrix = new int[n][n]; // matrix instantiation
   public ArrayList<Food> foods = new ArrayList<>();
   public ArrayList<Wall> walls = new ArrayList<>();
   public ArrayList<Border> borders = new ArrayList<>();
   public ArrayList<Powerup> powerups = new ArrayList<>();
   public ArrayList<SpeedIncrease> cherries = new ArrayList<>();
   public ArrayList<Animatable> animationObjects;
   

   public Map() {
      myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB); // image
      myBuffer = myImage.getGraphics(); // image
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,FRAME,FRAME);    
      
      
      animationObjects = new ArrayList<Animatable>();  // animation arrayList
      
      pacman = new Pacman(40, 40);
      animationObjects.add(pacman);
      /*
      for (int i = 0; i < n; i++) {
         double ran = Math.random();

         for (int j = 0; j < n; ++j) {
            if (i == 0 || j == 0 || i == 29 || j == 29) {matrix[i][j] = 0;}
            else if (ran < 0.5) {matrix[i][j] = 1;}
            else if (ran > 0.5) {matrix[i][j] = 5;}
         }
      }*/
      for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
        if (i == 0 || j == 0 || i == n - 1 || j == n - 1) {
            matrix[i][j] = 0; // Borders are always 0
        } else {
            double ran = Math.random();
            if (ran < 0.5) {
                matrix[i][j] = 1; // 50% chance for food
            } else {
                matrix[i][j] = 5; // 50% chance for wall
            }
        }
    }
}

      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            if (matrix[i][j] == 1) {foods.add(new Food(i * 20, j * 20));}
            else if (matrix[i][j] == 2) {powerups.add(new Powerup(i * 20, j * 20, Color.BLACK));}
            else if (matrix[i][j] == 5) {walls.add(new Wall(i * 20, j * 20));}
            else if (matrix[i][j] == 3) {cherries.add(new SpeedIncrease(i * 20, j * 20, Color.BLACK));}
            else if (matrix[i][j] == 0) {borders.add(new Border(i * 20, j * 20));}
         }
      }
      
      t = new Timer(75, new AnimationListener()); // timer
      t.start(); // timer
      
      addKeyListener(new Key()); // key
      setFocusable(true); // key

   }
   
   public void paintComponent(Graphics g) {
      
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }
   
   public void animate() {
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,FRAME,FRAME);
   
      for (Food a : foods) {
         a.drawMe(myBuffer);
      }
      for (Wall a : walls) {
         a.drawMe(myBuffer);
      }
      for (Border a : borders) {
         a.drawMe(myBuffer);
      }
      for (Powerup a : powerups) {
         a.drawMe(myBuffer);
      }
      for (SpeedIncrease a : cherries) {
         a.drawMe(myBuffer);
      }
      
      
      
      for (Animatable animationObject : animationObjects) {
         animationObject.step();
         animationObject.drawMe(myBuffer);
      }
      
      repaint();
   }
   
   private class AnimationListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         animate();
      }
   }
   
   private class Key extends KeyAdapter
   {
      public void keyPressed(KeyEvent e) 
      {
      
         if( (e.getKeyCode() == KeyEvent.VK_LEFT) && (!left)
            && (matrix[(pacman.getX() / 20)-1][(pacman.getY() / 20)] != 5)
            && (matrix[(pacman.getX() / 20)-1][(pacman.getY() / 20)] != 0)
            && (pacman.getY() % 20 == 0))
         {
            
            pacman.setDX(pacman.getDX() - 20);
            pacman.setDY(0);  
            left = true;  
           
         }
         
         
         
         if ((e.getKeyCode() == KeyEvent.VK_RIGHT && (!right)
            && (matrix[(pacman.getX() / 20)+1][(pacman.getY() / 20)] != 5)
            && (matrix[(pacman.getX() / 20)+1][(pacman.getY() / 20)] != 0) 
            && (pacman.getY() % 20 == 0)))
         {
            
            pacman.setDX(pacman.getDX() + 20);
            pacman.setDY(0);  
            right = true;
            
         }
         
         
         if ((e.getKeyCode() == KeyEvent.VK_UP && (!up)
            && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) - 1)] != 5)
            && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) - 1)] != 0)
            && (pacman.getX() % 20 == 0)))
         {
            pacman.setDY(pacman.getDY() - 20);
            pacman.setDX(0);
            up = true;
            
         }
         
         if ((e.getKeyCode() == KeyEvent.VK_DOWN && (!down)
            && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) + 1)] != 5)
            && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) + 1)] != 0)
            && (pacman.getX() % 20 == 0)))
         {
            pacman.setDY(pacman.getDY() + 20);
            pacman.setDX(0);
            down = true;
            
         }
         

         
         
      }
      
      public void keyReleased(KeyEvent e) //Also overridden; ONE method that will be called any time a key is released
      {
         if(e.getKeyCode() == KeyEvent.VK_LEFT && left) // If the user lets go of the left arrow
         {
            pacman.setDX(0);  
            left = false;  //User is no longer holding the left key, so set this back to false.
         }
         if (e.getKeyCode() == KeyEvent.VK_RIGHT && right) {
            pacman.setDX(0);
            right = false;
         }
         if (e.getKeyCode() == KeyEvent.VK_UP && up) {
            pacman.setDY(0);
            up = false;
         }
          if (e.getKeyCode() == KeyEvent.VK_DOWN && down) {
            pacman.setDY(0);
            down = false;
         }
         
         
         
      }
   }

   
}