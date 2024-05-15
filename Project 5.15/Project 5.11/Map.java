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
   
   
   Pacman pacman = new Pacman(0, 0);

   public Map() {
      t = new Timer(5, new AnimationListener());
      t.start(); 
      
      addKeyListener(new Key()); // key
      setFocusable(true); // key
      
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; ++j) {
            if (i == 0 || j == 0 || i == 29 || j == 29) {matrix[i][j] = 4;}
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

      for (Food a : foods) {a.drawMe(g);}
      for (Powerup a : powerups) {a.drawMe(g);}
      for (Wall a : walls) {a.drawMe(g);}
      for (Border a : borders) {a.drawMe(g);}
      for (SpeedIncrease a : cherries) {a.drawMe(g);}
   }
   
   public void animate() {
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
            && (pacman.getX() % 20 == 0)
            && (pacman.getY() % 20 == 0)
            && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) - 1)] != 5)
            && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) - 1)] != 0)) {
               // push
               pacman.setDX(0);
               pacman.setDY(-1);
         }
         
          if( (e.getKeyCode() == KeyEvent.VK_DOWN) // down
            && (!down) 
            && (pacman.getX() % 20 == 0) 
            && (pacman.getY() % 20 == 0)
            && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) + 1)] != 5)
            && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) + 1)] != 0)) {
               // push
               pacman.setDX(0);
               pacman.setDY(1);
         }
         
         if( (e.getKeyCode() == KeyEvent.VK_LEFT) // up
            && (!left) 
            && (pacman.getX() % 20 == 0)
            && (pacman.getY() % 20 == 0)
            && (matrix[(pacman.getX() / 20)-1][(pacman.getY() / 20)] != 5)
            && (matrix[(pacman.getX() / 20)-1][(pacman.getY() / 20)] != 0)) {
               // push
               pacman.setDX(-1);
               pacman.setDY(0);
         }
         
         if( (e.getKeyCode() == KeyEvent.VK_RIGHT) // up
            && (!right) 
            && (pacman.getX() % 20 == 0)
            && (pacman.getY() % 20 == 0)
            && (matrix[(pacman.getX() / 20)+1][(pacman.getY() / 20)] != 5)
            && (matrix[(pacman.getX() / 20)+1][(pacman.getY() / 20)] != 0)) {
               // push
               pacman.setDX(1);
               pacman.setDY(0);
         }
      }
   }
      
}