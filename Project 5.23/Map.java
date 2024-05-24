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
   public int score = 0;
   private Ghost ghost1;
   public Health h;

   public static final int FRAME = 600; // double check on this
   private static final Color BACKGROUND = Color.BLACK;

   public static int health = 3;

   private final int n = 30; // grid count
   public static int[][] matrix = new int[30][30]; // matrix instantiation
   public ArrayList<Food> foods = new ArrayList<>();
   public ArrayList<Wall> walls = new ArrayList<>();
   public ArrayList<Border> borders = new ArrayList<>();
   public ArrayList<Powerup> powerups = new ArrayList<>();
   public ArrayList<SpeedIncrease> oranges = new ArrayList<>();
   public ArrayList<Animatable> animationObjects;
   
   

   public Map() {
      myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB); // image
      myBuffer = myImage.getGraphics(); // image
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,FRAME,FRAME);    
      
      
      animationObjects = new ArrayList<Animatable>();  // animation arrayList
      
      pacman = new Pacman(200, 200);
      animationObjects.add(pacman);
      ghost1 = new Ghost(80, 80, Color.BLACK);
      animationObjects.add(ghost1);
      h = new Health();

      for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
              if (i == 0 || j == 0 || i == n - 1 || j == n - 1) {
                  matrix[i][j] = 0; // Borders are always 0
              } else {
                  double ran = Math.random();
                  if (ran < 0.7) {
                      matrix[i][j] = 1; // 50% chance for food
                  } else if (0.7 <= ran && ran <= 0.95) {
                      matrix[i][j] = 5; // 50% chance for wall
                  } else {
                      matrix[i][j] = 2;
                  }
              }
          }
      }

      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            if (matrix[i][j] == 1) {foods.add(new Food(i * 20, j * 20));}
            else if (matrix[i][j] == 2) {powerups.add(new Powerup(i * 20, j * 20, Color.BLACK));}
            else if (matrix[i][j] == 5) {walls.add(new Wall(i * 20, j * 20));}
            else if (matrix[i][j] == 3) {oranges.add(new SpeedIncrease(i * 20, j * 20, Color.BLACK));}
            else if (matrix[i][j] == 0) {borders.add(new Border(i * 20, j * 20));}
         }
      }
      
      t = new Timer(75, new AnimationListener()); // timer
      t.start(); // timer
      
      addKeyListener(new Key()); // key
      setFocusable(true); // key

   }

   
   public static int[][] getLevel() {
      return matrix;
   }
   public static int getHealth() {
      return health;
   }
   
   public void paintComponent(Graphics g) {
      
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
      g.setColor(Color.WHITE);
      g.setFont(new Font("San-Serif",Font.BOLD,15));
      g.drawString("" + score, 290, 15);
   }
   
   public void animate() {
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,FRAME,FRAME);
      
      for (Food a : foods) {
         score += a.collide(pacman);
         a.drawMe(myBuffer);
      }
      
      for (Wall a : walls) {
         a.drawMe(myBuffer);
      }
      for (Border a : borders) {
         a.drawMe(myBuffer);
      }
      for (Powerup a : powerups) {
         a.collide(pacman);
         a.drawMe(myBuffer);
      }
      for (SpeedIncrease a : oranges) {
         a.drawMe(myBuffer);
      }
      
      ghost1.ghostMove();
      if (ghost1.ghostCollide(pacman) == 1) {
         pacman.setX(300);
         pacman.setY(300);
         health--;
      }
      
      for (Animatable animationObject : animationObjects) {
         animationObject.step();
         animationObject.drawMe(myBuffer);
      }
      
      h.drawMe(myBuffer);
      
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
            
            pacman.setX(pacman.getX() - 20);
           
         }
         
         if ((e.getKeyCode() == KeyEvent.VK_RIGHT && (!right)
            && (matrix[(pacman.getX() / 20)+1][(pacman.getY() / 20)] != 5)
            && (matrix[(pacman.getX() / 20)+1][(pacman.getY() / 20)] != 0) 
            && (pacman.getY() % 20 == 0)))
         {
            
            pacman.setX(pacman.getX() + 20);
              
            
         }
         
         
         if ((e.getKeyCode() == KeyEvent.VK_UP && (!up)
            && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) - 1)] != 5)
            && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) - 1)] != 0)
            && (pacman.getX() % 20 == 0)))
         {
            pacman.setY(pacman.getY() - 20);
            
            
         }
         
         if ((e.getKeyCode() == KeyEvent.VK_DOWN && (!down)
            && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) + 1)] != 5)
            && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) + 1)] != 0)
            && (pacman.getX() % 20 == 0)))
         {
            pacman.setY(pacman.getY() + 20);
            
            
         } 
         
      }
      }

   
}