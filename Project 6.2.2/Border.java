import javax.swing.*;
import java.awt.*;

public class Border extends Square {
    private int side;
    private int x;
    private int y;

    public Border(int xValue, int yValue) {
        super(20, xValue, yValue, new Color(54, 107, 227));
    }
}