import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;
/** 
* Map class, main panel for the game to run
* @author David Debreczy
* @author Arthur Dsouza
* @author Abhiraj Mallangi
* @version 2 June 2024
*/
public class Map extends JPanel {
/** Pasted image on screen
*/
   private BufferedImage myImage;
/** Buffer
*/
   private Graphics myBuffer;
/** Animation timer
*/
   private Timer t;
/** Timer for powerup */ 
   private Timer eatableTimer;
/** Timer for powerup */
   private Timer speedableTimer;
/** Timer for powerup */
   private Timer stoppableTimer;
   
/** Boolean key for left */
   private boolean left = false; 
/** Boolean key for right */
   private boolean right = false;
/** Boolean key for up */
   private boolean up = false;
/** Boolean key for down */
   private boolean down = false;
/** File i/o string */
   public String file;
/** Pacman direction status */
   public static String status = "right";
/** Powerup status */
   public static boolean eatable = false;
/** Powerup status */
   public static boolean speedable = false;
/** Powerup status */
   public static boolean stoppable = false;
/** Food needed to win game */
   public int winAmount;
/** Pause screen boolean */
   public static boolean paused = false;
/** Pacman instantiation */
   private Pacman pacman;
/** Current score */
   public int score = 0;
/** Ghost */
   private Ghost ghost1;
/** Ghost */
   private Ghost ghost2;
/** Ghost */
   private Ghost ghost3;
/** Ghost */
   private Ghost ghost4;
/** Lives */
   public Health h;
/** Frame size */
   public static final int FRAME = 600; 
/** Background color */
   private static final Color BACKGROUND = Color.BLACK;
/** Starting health */
   public static int health = 3;
/** Amount of squares on grid */
   private final int n = 30; // grid count
/** 2D Array */
   public static int[][] matrix = new int[30][30]; 
/** Object list */
   public ArrayList<Food> foods = new ArrayList<>();
/** Object list */
   public ArrayList<Wall> walls = new ArrayList<>();
/** Object list */
   public ArrayList<Border> borders = new ArrayList<>();
/** Object list */
   public ArrayList<Powerup> powerups = new ArrayList<>();
/** Object list */
   public ArrayList<SpeedIncrease> oranges = new ArrayList<>();
/** Object list */
   public ArrayList<Ghost> ghosts = new ArrayList<>();
/** Object list */
   public ArrayList<SpeedStop> trash = new ArrayList<>();
/** Object list */
   public ArrayList<Animatable> animationObjects;
  

   public Map(String filen) {
      setFocusable(true);
      try {
          matrix = MapReader.readMap(filen);
      } catch (IOException e) {
          e.printStackTrace();
          matrix = new int[30][30]; // Fallback to an empty map if file read fails
      }
      
      

      myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB); // image
      myBuffer = myImage.getGraphics(); // image
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,FRAME,FRAME);
      animationObjects = new ArrayList<Animatable>();  // animation arrayList
      
      pacman = new Pacman(300, 300);
      animationObjects.add(pacman);
      
      ghost1 = new Ghost(80, 80, Color.BLACK);
      ghost2 = new Ghost(520, 520, Color.BLACK);
      ghost3 = new Ghost(80, 520, Color.BLACK);
      ghost4 = new Ghost(520, 80, Color.BLACK);

      ghosts.add(ghost3);
      ghosts.add(ghost4);
      ghosts.add(ghost2);
      ghosts.add(ghost1);
      h = new Health();

      for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
              if (i == 0 || j == 0 || i == n - 1 || j == n - 1) {
                  matrix[j][i] = 0; // Borders are always 0
              }
          }
      }

      for (int i = 0; i < n; i++) { // instantiates objects on grid
         for (int j = 0; j < n; j++) {
            if (matrix[i][j] == 1) {foods.add(new Food(i * 20, j * 20));}
            else if (matrix[i][j] == 2) {powerups.add(new Powerup(i * 20, j * 20, Color.BLACK));}
            else if (matrix[i][j] == 5) {walls.add(new Wall(i * 20, j * 20));}
            else if (matrix[i][j] == 3) {oranges.add(new SpeedIncrease(i * 20, j * 20, Color.BLACK));}
            else if (matrix[i][j] == 6) {trash.add(new SpeedStop(i * 20, j * 20, Color.BLACK));}
            else if (matrix[i][j] == 0) {borders.add(new Border(i * 20, j * 20));}
         }
      }
      winAmount = foods.size();
      
      t = new Timer(75, new AnimationListener()); // timer
      t.start(); // timer
      
      addKeyListener(new Key()); // key
      setFocusable(true); // key
      playSound("pacman_beginning.wav"); // plays sound

   }
/** Plays sound on speakers
* @param filename    String name of .wav file
*/   
    public static void playSound(String filename) {
        try {
            File soundFile = new File(filename);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
/** Returns matrix
* @return 2D array
*/   
   public static int[][] getLevel() {
      return matrix;
   }
/** Returns health count
* @return health
*/
   public static int getHealth() {
      return health;
   }
/** Returns powerup status
* @return Eatable status
*/
   public static boolean getEatable() {   
      return eatable;
   }
/** Returns pacman direction status
* @return Directional status
*/
   public static String getStatus() {
      return status;
   }
/** Paints the buffered image and the health status, 
* If there are any special cases, end the game or pause, otherwise, display the score
* @param g  Graphics tool
*/   
   public void paintComponent(Graphics g) {
      
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
            
      if (health == 0) { // else-ifs that correspond to each different scenario ingame
         t.stop();
         g.setColor(Color.WHITE);
         g.setFont(new Font("San-Serif", Font.BOLD, 15));
         g.drawString("You lost this level!", 240, 15);
      } else if (score == winAmount) {
         t.stop();
         g.setColor(Color.WHITE);
         g.setFont(new Font("San-Serif",Font.BOLD,15));
         g.drawString("You won this level!", 240, 15);
      } else if (paused == true) {
         g.setColor(Color.WHITE);
         g.setFont(new Font("San-Serif", Font.BOLD, 15));
         g.drawString("Paused", 270, 15);
      } else {
         g.setColor(Color.WHITE);
         g.setFont(new Font("San-Serif",Font.BOLD,15));
         g.drawString("" + score + "/" + winAmount, 285, 15);
      }


   }
/** Animates the game, buffers all objects
*/   
   public void animate() {
      myBuffer.setColor(BACKGROUND); // sets background
      myBuffer.fillRect(0, 0, FRAME, FRAME);
      for (Food a : foods) {
         score += a.collide(pacman);
         a.drawMe(myBuffer);
      }
      for (Wall a : walls) { // buffers all objects, including below
         a.drawMe(myBuffer);
      }
      for (Border a : borders) {
         a.drawMe(myBuffer);
      }
      for (Powerup a : powerups) {
         if (a.collide(pacman) == 1) {
            if (!eatable) {
               eatable = true;
                playSound("pacman_eatfruit.wav");
               if (eatableTimer != null && eatableTimer.isRunning()) {
                  eatableTimer.stop();
               }
               eatableTimer = new Timer(5000, new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                     eatable = false;
                     eatableTimer.stop();
                  }
               });
               eatableTimer.setRepeats(false);
               eatableTimer.start();
            }
         }
         a.drawMe(myBuffer);
      }
      for (SpeedIncrease a : oranges) {
         if (a.collide(pacman) == 1) {
            if (!speedable) {
               speedable = true;
               stoppable = false;
               playSound("pacman_eatfruit.wav");
               if (speedableTimer != null && speedableTimer.isRunning()) {
                  speedableTimer.stop();
               } 
               if (stoppableTimer != null && stoppableTimer.isRunning()) {
                  stoppableTimer.stop();
               } 
               speedableTimer = new Timer(5000, new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                     speedable = false;
                     speedableTimer.stop();
                  }
               });
               speedableTimer.setRepeats(false);
               speedableTimer.start();
            }
         }
         a.drawMe(myBuffer);
      }
      for (Ghost a : ghosts) {
         if (a.ghostCollide(pacman) == 1) {
            pacman.setX(300);
            pacman.setY(300);
            health--;
            playSound("pacman_death.wav");
         }
            a.ghostMove();
            a.step();
            a.drawMe(myBuffer);
      }
      for (SpeedStop a : trash) {
         if (a.collide(pacman) == 1) {
            if (!stoppable) {
               stoppable = true;
               speedable = false;
               playSound("pacman_eatfruit.wav");
               if (stoppableTimer != null && stoppableTimer.isRunning()) {
                  stoppableTimer.stop();
               } 
               if (speedableTimer != null && speedableTimer.isRunning()) {
                  speedableTimer.stop();
               } 
               
               stoppableTimer = new Timer(3000, new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                     stoppable = false;
                     stoppableTimer.stop();
                  }
               });
               stoppableTimer.setRepeats(false);
               stoppableTimer.start();
            }
         }
         a.drawMe(myBuffer);
      }
      for (Animatable animationObject : animationObjects) {
         animationObject.step();
         animationObject.drawMe(myBuffer);
      }
      h.drawMe(myBuffer);
      repaint();
}

   
   private class AnimationListener implements ActionListener {
/** Action performed
* @param e  ActionEvent
*/
      public void actionPerformed(ActionEvent e) {
         animate();
      }
   }
/** Key class, uses key listeners to move Pacman
*/
   private class Key extends KeyAdapter
   {
/** Detects if key is pressed
* @param e  KeyEvent
*/
      public void keyPressed(KeyEvent e) 
      {
         if (e.getKeyCode() == KeyEvent.VK_P) {
            paused = !paused; // Start pause
            if (paused) {
                t.stop();
            } else {
                t.start();
            }
            repaint();
        }
         if (speedable == true && stoppable == false) { // left move
            if( (e.getKeyCode() == KeyEvent.VK_LEFT) && (!left)
               && (matrix[(pacman.getX() / 20)-2][(pacman.getY() / 20)] != 5)
               && (matrix[(pacman.getX() / 20)-2][(pacman.getY() / 20)] != 0)
               && (pacman.getY() % 20 == 0))
            {
               pacman.setX(pacman.getX() - 40);
               status = "left";
            }
            if ((e.getKeyCode() == KeyEvent.VK_RIGHT && (!right) // right
               && (matrix[(pacman.getX() / 20)+2][(pacman.getY() / 20)] != 5)
               && (matrix[(pacman.getX() / 20)+2][(pacman.getY() / 20)] != 0) 
               && (pacman.getY() % 20 == 0)))
            {
               pacman.setX(pacman.getX() + 40);
               status = "right";  
            }
            if ((e.getKeyCode() == KeyEvent.VK_UP && (!up) // up
               && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) - 2)] != 5)
               && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) - 2)] != 0)
               && (pacman.getX() % 20 == 0)))
            {
               pacman.setY(pacman.getY() - 40);
               status = "up"; 
            }
            if ((e.getKeyCode() == KeyEvent.VK_DOWN && (!down) // down
               && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) + 2)] != 5)
               && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) + 2)] != 0)
               && (pacman.getX() % 20 == 0)))
            {
               pacman.setY(pacman.getY() + 40);
               status = "down";
            } 
         } else if (stoppable == true && speedable == false) {
            int x = 0;
         } else if (stoppable == false && speedable == false) {
            if( (e.getKeyCode() == KeyEvent.VK_LEFT) && (!left)
               && (matrix[(pacman.getX() / 20)-1][(pacman.getY() / 20)] != 5)
               && (matrix[(pacman.getX() / 20)-1][(pacman.getY() / 20)] != 0)
               && (pacman.getY() % 20 == 0))
            {
               
               pacman.setX(pacman.getX() - 20);
               status = "left";
            }
            if ((e.getKeyCode() == KeyEvent.VK_RIGHT && (!right)
               && (matrix[(pacman.getX() / 20)+1][(pacman.getY() / 20)] != 5)
               && (matrix[(pacman.getX() / 20)+1][(pacman.getY() / 20)] != 0) 
               && (pacman.getY() % 20 == 0)))
            { 
               pacman.setX(pacman.getX() + 20);
               status = "right"; 
            }
            if ((e.getKeyCode() == KeyEvent.VK_UP && (!up)
               && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) - 1)] != 5)
               && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) - 1)] != 0)
               && (pacman.getX() % 20 == 0)))
            {
               pacman.setY(pacman.getY() - 20);
               status = "up";
               
            }
            
            if ((e.getKeyCode() == KeyEvent.VK_DOWN && (!down)
               && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) + 1)] != 5)
               && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) + 1)] != 0)
               && (pacman.getX() % 20 == 0)))
            {
               pacman.setY(pacman.getY() + 20);
               status = "down";
               
            }
         }  
      }
   }
}