import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class x6DebreczyDavidDsouzaArthurMallangiAbhirajMapReader {
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
    
    public int testActivity() {
      return 1;
    }
}