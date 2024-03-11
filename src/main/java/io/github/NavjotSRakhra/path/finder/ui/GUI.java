package io.github.NavjotSRakhra.path.finder.ui;

import javax.swing.*;
import java.awt.*;

public class GUI {
    public GUI(VisualizationJPanel panel) {
        var mainFrame = new JFrame("Data visualizer");
        panel.setPreferredSize(new Dimension(900, 900));
//        visualizationPanel.setLayout(new GridBagLayout());

        mainFrame.add(panel);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}
