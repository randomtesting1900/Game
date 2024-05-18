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
   private boolean left; 
   private boolean right;
   private boolean up;
   private boolean down;


   public static final int FRAME = 640; // double check on this


   private final int n = 30; // grid count
   public int[][] matrix = new int[n][n]; // matrix instantiation
   public ArrayList<Food> foods = new ArrayList<>();
   public ArrayList<Wall> walls = new ArrayList<>();
   public ArrayList<Border> borders = new ArrayList<>();
   public ArrayList<Powerup> powerups = new ArrayList<>();
   public ArrayList<SpeedIncrease> cherries = new ArrayList<>();
   public ArrayList<Animatable> animationObjects;
   
   Pacman pacman = new Pacman(40, 40);

   public Map() {
      myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB); // image
      myBuffer = myImage.getGraphics(); // image
      t = new Timer(5, new AnimationListener()); // timer
      t.start(); // timer
      
      animationObjects = new ArrayList<Animatable>();  // animation arrayList
      
      
      addKeyListener(new Key()); // key
      setFocusable(true); // key
      
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; ++j) {
            if (i == 0 || j == 0 || i == 29 || j == 29) {matrix[i][j] = 0;}
            else {matrix[i][j] = 1;}
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
   }
   
   public void paintComponent(Graphics g) {
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, 600, 600);
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }
   
   public void animate() {
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
      
      
      
      pacman.step();
      pacman.drawMe(myBuffer);
      
      repaint();
   }
   
   private class AnimationListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         animate();
      }
   }
   
   private class Key extends KeyAdapter {
      public void keyPressed(KeyEvent e) {
         if( (e.getKeyCode() == KeyEvent.VK_UP) // up
            && (!up) 
            && (pacman.getX() >= 20)
            && (pacman.getY() >= 20)
            && (pacman.getX() % 20 == 0)
            && (pacman.getY() % 20 == 0)
            && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) - 1)] != 5)
            && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) - 1)] != 0)) {
               // push
               pacman.setDX(pacman.getDX());
               pacman.setDY(pacman.getDY()+1);
         }
         
          if( (e.getKeyCode() == KeyEvent.VK_DOWN) // down
            && (!down)
            && (pacman.getX() >= 20)
            && (pacman.getY() >= 20)
            && (pacman.getX() % 20 == 0) 
            && (pacman.getY() % 20 == 0)
            && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) + 1)] != 5)
            && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) + 1)] != 0)) {
               // push
               pacman.setDX(pacman.getDX());
               pacman.setDY(pacman.getDX()-1);
         }
         
         if( (e.getKeyCode() == KeyEvent.VK_LEFT) // left
            && (!left) 
            && (pacman.getX() >= 20)
            && (pacman.getY() >= 20)
            && (pacman.getX() % 20 == 0)
            && (pacman.getY() % 20 == 0)
            && (matrix[(pacman.getX() / 20)-1][(pacman.getY() / 20)] != 5)
            && (matrix[(pacman.getX() / 20)-1][(pacman.getY() / 20)] != 0)) {
               // push
               pacman.setDX(pacman.getDX()-1);
               pacman.setDY(pacman.getDY());
         }
         
         if( (e.getKeyCode() == KeyEvent.VK_RIGHT) //r
            && (!right) 
            && (pacman.getX() >= 20)
            && (pacman.getY() >= 20)
            && (pacman.getX() % 20 == 0)
            && (pacman.getY() % 20 == 0)
            && (matrix[(pacman.getX() / 20)+1][(pacman.getY() / 20)] != 5)
            && (matrix[(pacman.getX() / 20)+1][(pacman.getY() / 20)] != 0)) {
               // push
               pacman.setDX(pacman.getDX() + 1);
               pacman.setDY(pacman.getDY());
         }
      }
      public void keyReleased(KeyEvent e) //Also overridden; ONE method that will be called any time a key is released
      {
         if(e.getKeyCode() == KeyEvent.VK_LEFT) // If the user lets go of the left arrow
         {
            pacman.setDX(pacman.getDX() + 2);  //Again: add 2, don't set to 0 precisely.  
            left = false;  //User is no longer holding the left key, so set this back to false.
         }
         if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            pacman.setDX(pacman.getDX() - 2);
            right = false;
         }
         if (e.getKeyCode() == KeyEvent.VK_UP) {
            pacman.setDY(pacman.getDY() + 2);
            up = false;
         }
          if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            pacman.setDY(pacman.getDY() - 2);
            down = false;
         }
      } 
   }
}