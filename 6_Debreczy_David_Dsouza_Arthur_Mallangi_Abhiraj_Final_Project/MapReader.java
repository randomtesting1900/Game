import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/** 
* Map Reader, reads the text files which contain the levels in integers
* @author David Debreczy
* @author Arthur Dsouza
* @author Abhiraj Mallangi
* @version 2 June 2024
*/
public class MapReader {
/** Reads the text file, transfers it into a variable
* @param filename    Name of file in directory
*/
    public static int[][] readMap(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        int[][] matrix = new int[30][30];
        int row = 0;

        while ((line = br.readLine()) != null && row < 30) {
            String[] values = line.trim().split("\\s+");
            for (int col = 0; col < 30; col++) {
                matrix[col][row] = Integer.parseInt(values[col]);
            }
            row++;
        }

        br.close();
        return matrix;
    }
/** Tests to make sure the reader is running
*/    
    public int testActivity() {
      return 1;
    }
}