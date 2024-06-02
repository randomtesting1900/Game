import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class x6DebreczyDavidDsouzaArthurMallangiAbhirajPacmanDriver {
    public static void main(String[] args) {
        JFrame frame = new JFrame("PAC-MAN");
        frame.setSize(600, 600);
        frame.setLocation(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create the main panel
        Home homePanel = new Home(frame, args);
        frame.setContentPane(homePanel);
        frame.setVisible(true);
    }
}

class Home extends JPanel {
    private JFrame myOwner;
    private String[] args;
    
    public Home(JFrame f, String[] args) {
        myOwner = f;
        this.args = args;
        
        setLayout(new BorderLayout());
        
        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(5, 1));

        JLabel titleLabel = new JLabel("Welcome to Pacman Ultra!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 24));
        menu.add(titleLabel);
        
        JButton levelOne = new JButton("Level 1");
        menu.add(levelOne);
        JButton levelTwo = new JButton("Level 2");
        menu.add(levelTwo);
        JButton levelThree = new JButton("Level 3");
        menu.add(levelThree);
        JButton instructions = new JButton("How to Play");
        menu.add(instructions);
        
        add(menu, BorderLayout.CENTER);
        
        levelOne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchToLevel("levelOne.txt");
            }
        });
        
        levelTwo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchToLevel("levelTwo.txt");
            }
        });
        
        levelThree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchToLevel("levelThree.txt");
            }
        });
        
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(myOwner, "Welcome to Pacman Ultra! To begin, select one of three levels, Easy, Medium, or Hard. Once you are in the game, you can move up or down using");
            }
        });
    }
    
    private void switchToLevel(String level) {
        MapStepPanel mapStepPanel = new MapStepPanel(level, myOwner);
        myOwner.setContentPane(mapStepPanel);
        myOwner.revalidate();
        myOwner.repaint();
        mapStepPanel.requestFocusForMap(); // Ensure focus is set on the new Map component
    }
}

class MapStepPanel extends JPanel {
    private Map map; // Reference to the Map component

    public MapStepPanel(String mapFile, JFrame myOwner) {
        setLayout(new BorderLayout());
        
        // Map display
        map = new Map(mapFile);
        add(map, BorderLayout.CENTER);
    }
    
    // Method to request focus on the Map component
    public void requestFocusForMap() {
        map.requestFocusInWindow();
    }
}



/*

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PacmanDriver {
    public static void main(String[] args) {
        JFrame frame = new JFrame("PAC-MAN");
        frame.setSize(600, 600);
        frame.setLocation(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create the main panel
        Home homePanel = new Home(frame, args);
        frame.setContentPane(homePanel);
        frame.setVisible(true);
    }
}
class Home extends JPanel {
    private JFrame myOwner;
    private String[] args;
    
    public Home(JFrame f, String[] args) {
        myOwner = f;
        this.args = args;
        
        setLayout(new BorderLayout());
        
        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(5, 1));

        JLabel titleLabel = new JLabel("Welcome to Pacman Ultra!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 24));
        menu.add(titleLabel);
        
        JButton levelOne = new JButton("Level 1");
        menu.add(levelOne);
        JButton levelTwo = new JButton("Level 2");
        menu.add(levelTwo);
        JButton levelThree = new JButton("Level 3");
        menu.add(levelThree);
        JButton instructions = new JButton("How to Play");
        menu.add(instructions);
        
        add(menu, BorderLayout.CENTER);
        
        levelOne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myOwner.setContentPane(new MapStepPanel("levelOne.txt", myOwner));
                myOwner.revalidate();
                myOwner.repaint();
            }
        });
        
        levelTwo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myOwner.setContentPane(new MapStepPanel("levelTwo.txt", myOwner));
                myOwner.revalidate();
                myOwner.repaint();
            }
        });
        
        levelThree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myOwner.setContentPane(new MapStepPanel("levelThree.txt", myOwner));
                myOwner.revalidate();
                myOwner.repaint();
            }
        });
        
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(myOwner, "Welcome to Pacman Ultra! To begin, select one of three levels, Easy, Medium, or Hard. Once you are in the game, you can move up or down using");
            }
        });
    }
}
class MapStepPanel extends JPanel {
    public MapStepPanel(String mapFile, JFrame myOwner) {
        setLayout(new BorderLayout());
        
        // Map display
        Map map = new Map(mapFile);
        add(map, BorderLayout.CENTER);
        
        map.setFocusable(true);
        map.requestFocusInWindow();
        
    }
}
 */
 
 
class Map extends JPanel {

   private BufferedImage myImage; // buffer
   private Graphics myBuffer; // buffer
   private Timer t; // timer
   private Timer eatableTimer;
   private Timer speedableTimer;
   private Timer stoppableTimer;
   
   // key bools
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
   
   private x6DebreczyDavidDsouzaArthurMallangiAbhirajPacman pacman;
   public int score = 0;
   private x6DebreczyDavidDsouzaArthurMallangiAbhirajGhost ghost1;
   private x6DebreczyDavidDsouzaArthurMallangiAbhirajGhost ghost2;
   private x6DebreczyDavidDsouzaArthurMallangiAbhirajGhost ghost3;
   private x6DebreczyDavidDsouzaArthurMallangiAbhirajGhost ghost4;
   public x6DebreczyDavidDsouzaArthurMallangiAbhirajHealth h;

   public static final int FRAME = 600; // double check on this
   private static final Color BACKGROUND = Color.BLACK;

   public static int health = 3;

   private final int n = 30; // grid count
   public static int[][] matrix = new int[30][30]; // matrix instantiation
   public ArrayList<x6DebreczyDavidDsouzaArthurMallangiAbhirajFood> foods = new ArrayList<>();
   public ArrayList<x6DebreczyDavidDsouzaArthurMallangiAbhirajWall> walls = new ArrayList<>();
   public ArrayList<x6DebreczyDavidDsouzaArthurMallangiAbhirajBorder> borders = new ArrayList<>();
   public ArrayList<x6DebreczyDavidDsouzaArthurMallangiAbhirajPowerup> powerups = new ArrayList<>();
   public ArrayList<x6DebreczyDavidDsouzaArthurMallangiAbhirajSpeedIncrease> oranges = new ArrayList<>();
   public ArrayList<x6DebreczyDavidDsouzaArthurMallangiAbhirajGhost> ghosts = new ArrayList<>();
   public ArrayList<x6DebreczyDavidDsouzaArthurMallangiAbhirajSpeedStop> trash = new ArrayList<>();
   public ArrayList<x6DebreczyDavidDsouzaArthurMallangiAbhirajAnimatable> animationObjects;
  

   public Map(String filen) {
      setFocusable(true);
      try {
          matrix = x6DebreczyDavidDsouzaArthurMallangiAbhirajMapReader.readMap(filen);
      } catch (IOException e) {
          e.printStackTrace();
          matrix = new int[30][30]; // Fallback to an empty map if file read fails
      }
      
      

      myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB); // image
      myBuffer = myImage.getGraphics(); // image
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,FRAME,FRAME);
      animationObjects = new ArrayList<x6DebreczyDavidDsouzaArthurMallangiAbhirajAnimatable>();  // animation arrayList
      
      pacman = new x6DebreczyDavidDsouzaArthurMallangiAbhirajPacman(300, 300);
      animationObjects.add(pacman);
      
      ghost1 = new x6DebreczyDavidDsouzaArthurMallangiAbhirajGhost(80, 80, Color.BLACK);
      ghost2 = new x6DebreczyDavidDsouzaArthurMallangiAbhirajGhost(520, 520, Color.BLACK);
      ghost3 = new x6DebreczyDavidDsouzaArthurMallangiAbhirajGhost(80, 520, Color.BLACK);
      ghost4 = new x6DebreczyDavidDsouzaArthurMallangiAbhirajGhost(520, 80, Color.BLACK);

      ghosts.add(ghost3);
      ghosts.add(ghost4);
      ghosts.add(ghost2);
      ghosts.add(ghost1);
      h = new x6DebreczyDavidDsouzaArthurMallangiAbhirajHealth();

      for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
              if (i == 0 || j == 0 || i == n - 1 || j == n - 1) {
                  matrix[j][i] = 0; // Borders are always 0
              }
          }
      }

      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            if (matrix[i][j] == 1) {foods.add(new x6DebreczyDavidDsouzaArthurMallangiAbhirajFood(i * 20, j * 20));}
            else if (matrix[i][j] == 2) {powerups.add(new x6DebreczyDavidDsouzaArthurMallangiAbhirajPowerup(i * 20, j * 20, Color.BLACK));}
            else if (matrix[i][j] == 5) {walls.add(new x6DebreczyDavidDsouzaArthurMallangiAbhirajWall(i * 20, j * 20));}
            else if (matrix[i][j] == 3) {oranges.add(new x6DebreczyDavidDsouzaArthurMallangiAbhirajSpeedIncrease(i * 20, j * 20, Color.BLACK));}
            else if (matrix[i][j] == 6) {trash.add(new x6DebreczyDavidDsouzaArthurMallangiAbhirajSpeedStop(i * 20, j * 20, Color.BLACK));}
            else if (matrix[i][j] == 0) {borders.add(new x6DebreczyDavidDsouzaArthurMallangiAbhirajBorder(i * 20, j * 20));}
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
     
      
   
      for (x6DebreczyDavidDsouzaArthurMallangiAbhirajFood a : foods) {
         score += a.collide(pacman);
         a.drawMe(myBuffer);
      }
   
      for (x6DebreczyDavidDsouzaArthurMallangiAbhirajWall a : walls) {
         a.drawMe(myBuffer);
      }
      for (x6DebreczyDavidDsouzaArthurMallangiAbhirajBorder a : borders) {
         a.drawMe(myBuffer);
      }
      for (x6DebreczyDavidDsouzaArthurMallangiAbhirajPowerup a : powerups) {
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
      for (x6DebreczyDavidDsouzaArthurMallangiAbhirajSpeedIncrease a : oranges) {
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

               
               
         
      
   
      for (x6DebreczyDavidDsouzaArthurMallangiAbhirajGhost a : ghosts) {
         if (ghost1.ghostCollide(pacman) == 1) {
            pacman.setX(300);
            pacman.setY(300);
            health--;
         }
            a.ghostMove();
            a.step();
            a.drawMe(myBuffer);
      }
      
      for (x6DebreczyDavidDsouzaArthurMallangiAbhirajSpeedStop a : trash) {
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
   
      for (x6DebreczyDavidDsouzaArthurMallangiAbhirajAnimatable animationObject : animationObjects) {
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

class x6DebreczyDavidDsouzaArthurMallangiAbhirajGhost extends x6DebreczyDavidDsouzaArthurMallangiAbhirajSquare implements x6DebreczyDavidDsouzaArthurMallangiAbhirajAnimatable {
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
   
   public x6DebreczyDavidDsouzaArthurMallangiAbhirajGhost(int x, int y, Color c) {
      super(20, x, y, c);
      this.x = x;
      this.y = y;
      this.matrix = Map.getLevel();
   }
   
   public x6DebreczyDavidDsouzaArthurMallangiAbhirajGhost() {
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
   
   public int ghostCollide(x6DebreczyDavidDsouzaArthurMallangiAbhirajPacman pacman) {
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

class x6DebreczyDavidDsouzaArthurMallangiAbhirajFood extends x6DebreczyDavidDsouzaArthurMallangiAbhirajCircle {
   public int x;
   public int y;
   public boolean col = false;

   public x6DebreczyDavidDsouzaArthurMallangiAbhirajFood(int xValue, int yValue) {
      super(2, xValue+8, yValue+8, new Color(237, 179, 149));
   }
   
   public int collide(x6DebreczyDavidDsouzaArthurMallangiAbhirajPacman pacman) {
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