package data;

import java.awt.*;

public interface Visualizable {
    int rows();

    int cols();

    Color getColorAt(int x, int y);
}
