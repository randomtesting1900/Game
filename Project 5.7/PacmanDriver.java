    //Torbert, e-mail: smtorbert@fcps.edu	
	 //version 7.14.2003

   import javax.swing.JFrame;
    public class PacmanDriver
   {
       public static void main(String[] args)
      {
         JFrame frame = new JFrame("Project: Battleship!");
         frame.setSize(400, 400);
         frame.setLocation(200, 100);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setContentPane(new Map());
         frame.setVisible(true);
      }
   }