package ui;

import data.Visualizable;

import javax.swing.*;
import java.awt.*;

public class VisualizationJPanel extends JPanel {
    private Visualizable visualizableData;

    public VisualizationJPanel(Visualizable visualizableData) {
        this.visualizableData = visualizableData;
    }

    public void setNewData(Visualizable newData) {
        visualizableData = newData;
    }

    @Override
    public void paint(Graphics g) {
        var dim = getSize();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, dim.width, dim.height);

        double rectHeight = (double) dim.height / visualizableData.rows();
        double rectWidth = (double) dim.width / visualizableData.cols();

        for (int i = 0; i < visualizableData.rows(); i++) {
            for (int j = 0; j < visualizableData.cols(); j++) {
                g.setColor(visualizableData.getColorAt(i, j));
                g.fillRect((int) (i * rectWidth), (int) (j * rectHeight), (int) rectWidth, (int) rectHeight);
            }
        }
    }
}
