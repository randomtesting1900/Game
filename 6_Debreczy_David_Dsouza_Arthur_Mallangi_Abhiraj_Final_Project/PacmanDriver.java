import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/** 
* PacmanDriver, runs the Pacman game
* @author David Debreczy
* @author Arthur Dsouza
* @author Abhiraj Mallangi
* @version 2 June 2024
*/
public class PacmanDriver {
/** main function
* @param args  Functional
*/
    public static void main(String[] args) {
        JFrame frame = new JFrame("David Debreczy, Arthur Dsouza, Abhiraj Mallangi, Period 6, Pacman Ultra");
        frame.setSize(600, 600);
        frame.setLocation(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Home first = new Home(frame, args);
        frame.setContentPane(first);
        frame.setVisible(true);
    }
}
/** 
* Home Panel, introduces to the user
* @author David Debreczy
* @author Arthur Dsouza
* @author Abhiraj Mallangi
* @version 2 June 2024
*/
class Home extends JPanel {
/** Current JFrame */
    private JFrame myOwner;
/** args */
    private String[] args;
/** Constructs a homepage, sets myOwner to p 
* @param p  JFrame for myOwner
* @param args  Standard
*/    
    public Home(JFrame p, String[] args) {
        myOwner = p;
        this.args = args;
        
        setLayout(new BorderLayout());
        
        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(5, 1));

        JLabel title = new JLabel("Welcome to Pacman Ultra!", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 24));
        menu.add(title);
        
        JButton levelOne = new JButton("Easy");
        menu.add(levelOne);
        JButton levelTwo = new JButton("Medium");
        menu.add(levelTwo);
        JButton levelThree = new JButton("Hard");
        menu.add(levelThree);
        JButton instructions = new JButton("How to Play");
        menu.add(instructions);
        
        add(menu, BorderLayout.CENTER);
        
        
        instructions.addActionListener(new ActionListener() {
/** Conducts an action when instructions button is clicked
* @param e  ActionEvent
*/
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(myOwner, "Welcome to Pacman Ultra! \nTo begin, select one of three levels, Easy, Medium, or Hard. \nOnce you are in the game, you can move up or down using arrow keys. \nCherries allow you to eat ghosts for a short period of time. \nOranges allow you to move 2 squares at once, but it limits you near walls. \n Trash cans stop you in your tracks for a few seconds. \nOnce you kill a ghost with the cherries, they are still deadly! It increases the danger. \n If sound or collisions are not working, please recompile the Map.java file! \n To win the game, eat all the white dots (food). \n If you get killed three times, you lose! \n To pause the game, press P, to unpause, press P again.");
            }
        });
/** Opens Level 1 when Easy button is clicked
* @param e  ActionEvent
*/
        levelOne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchToLevel("levelOne.txt");
            }
        });
/** Opens Level 2 when Medium button is clicked
* @param e  ActionEvent
*/
        levelTwo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchToLevel("levelTwo.txt");
            }
        });
/** Opens Level 3 when Hard button is clicked
* @param e  ActionEvent
*/        
        levelThree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchToLevel("levelThree.txt");
            }
        });
    }
/** Switches to the level in question, opens the map panel
* @param level    level in question, 1, 2, 3    
*/
    private void switchToLevel(String level) {
        StepPanel s = new StepPanel(level, myOwner);
        myOwner.setContentPane(s);
        myOwner.revalidate();
        myOwner.repaint();
        s.requestFocusMap();
    }
}
/** 
* Step Panel, changes the JFrame to display the map panel
* @author David Debreczy
* @author Arthur Dsouza
* @author Abhiraj Mallangi
* @version 2 June 2024
*/
class StepPanel extends JPanel {
/** Map object, cur (current) */
    private Map cur; 
/** Constructs a StepPanel using "f" for the file name, sets myOwner
* @param f  Filename
* @param myOwner  Current
*/
    public StepPanel(String f, JFrame myOwner) {
        setLayout(new BorderLayout());
        
        cur = new Map(f);
        add(cur, BorderLayout.CENTER);
    }
/** Requests focus to to the map, simplified to a function for accessibility */   
    public void requestFocusMap() {
        cur.requestFocusInWindow();
    }
}


//////////////////////////////////////////////////////////////////////////////////////////////////////////
// The following code below is crucial to the PacmanDriver, as it keeps the game in focus.
// We are not sure why it works, however after spending hours trying to find a fix to setting focus,
// this magically worked.
// The classes below are the exact same as the ones in their dedicated .java files.
// No JavaDoc will be placed on the code below as it can be found on the other API sheets.
//////////////////////////////////////////////////////////////////////////////////////////////////////////
 
 
 
 
 
class Map extends JPanel {
   private BufferedImage myImage;
   private Graphics myBuffer;
   private Timer t;
   private Timer eatableTimer;
   private Timer speedableTimer;
   private Timer stoppableTimer;
   private boolean left = false; 
   private boolean right = false;
   private boolean up = false;
   private boolean down = false;
   public String file;
   public static String status = "right";
   public static boolean eatable = false;
   public static boolean speedable = false;
   public static boolean stoppable = false;
   public int winAmount;
   public static boolean paused = false;
   private Pacman pacman;
   public int score = 0;
   private Ghost ghost1;
   private Ghost ghost2;
   private Ghost ghost3;
   private Ghost ghost4;
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
   public ArrayList<Ghost> ghosts = new ArrayList<>();
   public ArrayList<SpeedStop> trash = new ArrayList<>();
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
      for (int i = 0; i < n; i++) {
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
   }
   public static int[][] getLevel() {
      return matrix;
   }
   public static int getHealth() {
      return health;
   }
   public static boolean getEatable() {   
      return eatable;
   }
   public static String getStatus() {
      return status;
   }
   public void paintComponent(Graphics g) {
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);    
      if (health == 0) {
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
   public void animate() {
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0, 0, FRAME, FRAME);
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
         if (a.collide(pacman) == 1) {
            if (!eatable) {
               eatable = true;
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
         if (ghost1.ghostCollide(pacman) == 1) {
            pacman.setX(300);
            pacman.setY(300);
            health--;
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
      public void actionPerformed(ActionEvent e) {
         animate();
      }
   }
   private class Key extends KeyAdapter
   {
      public void keyPressed(KeyEvent e) 
      {
         if (e.getKeyCode() == KeyEvent.VK_P) {
            paused = !paused; // Toggle pause status
            if (paused) {
                t.stop();
            } else {
                t.start();
            }
            repaint();
        }
         
         if (speedable == true && stoppable == false) {
            if( (e.getKeyCode() == KeyEvent.VK_LEFT) && (!left)
               && (matrix[(pacman.getX() / 20)-2][(pacman.getY() / 20)] != 5)
               && (matrix[(pacman.getX() / 20)-2][(pacman.getY() / 20)] != 0)
               && (pacman.getY() % 20 == 0))
            {
               
               pacman.setX(pacman.getX() - 40);
               status = "left";
              
            }
            
            if ((e.getKeyCode() == KeyEvent.VK_RIGHT && (!right)
               && (matrix[(pacman.getX() / 20)+2][(pacman.getY() / 20)] != 5)
               && (matrix[(pacman.getX() / 20)+2][(pacman.getY() / 20)] != 0) 
               && (pacman.getY() % 20 == 0)))
            {
               
               pacman.setX(pacman.getX() + 40);
               status = "right";  
               
            }
            if ((e.getKeyCode() == KeyEvent.VK_UP && (!up)
               && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) - 2)] != 5)
               && (matrix[(pacman.getX() / 20)][((pacman.getY() / 20) - 2)] != 0)
               && (pacman.getX() % 20 == 0)))
            {
               pacman.setY(pacman.getY() - 40);
               status = "up";
               
            }
            
            if ((e.getKeyCode() == KeyEvent.VK_DOWN && (!down)
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

class Ghost extends Square implements Animatable {
   private int x;
   private int y;
   public boolean north = false;
   public boolean south = false;
   public boolean east = false;
   public boolean west = false;
   public boolean proceed = false;
   
   public boolean isEat = false;
   public boolean notMove = false;
   
   private int[][] matrix = new int[30][30];
   
   public Ghost(int x, int y, Color c) {
      super(20, x, y, c);
      this.x = x;
      this.y = y;
      this.matrix = Map.getLevel();
   }
   
   public Ghost() {
      super(20, 20, 20, Color.BLACK);
      this.matrix = Map.getLevel();
   }
   
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
      
   public void step() {
      if (proceed && !notMove) {
         // Update position based on direction
         if (north) setY(getY() - 20);
         if (south) setY(getY() + 20);
         if (west) setX(getX() - 20);
         if (east) setX(getX() + 20);
         
         proceed = false; // Reset proceed after moving
      }
   }
   
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
   
   public int ghostCollide(Pacman pacman) {
      isEat = Map.getEatable();
      if (getX() == pacman.getX() && getY() == pacman.getY() && isEat == false) {
         return 1;
      } else if (getX() == pacman.getX() && getY() == pacman.getY() && isEat == true) {
         notMove = true;
         return 2;
         
      } else {
         return 0;
      }
   }
   
   public int getX() {
      return x;
   }
   public int getY() {
      return y;
   }
   public void setX(int xx) {
      x = xx;
   }
   public void setY(int yy) {
      y = yy;
   }
}

class Food extends Circle {
   public int x;
   public int y;
   public boolean col = false;

   public Food(int xValue, int yValue) {
      super(2, xValue+8, yValue+8, new Color(237, 179, 149));
   }
   
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