import data.model.State;
import pathfinder.AStar;
import ui.GUI;
import ui.VisualizationJPanel;

import java.lang.reflect.InvocationTargetException;

public class Run {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        State state = new State();
        var panel = new VisualizationJPanel(state);
        new GUI(panel);
        do {
            state = new State(100, 100);
            state.randomAddWalls();
            panel.setNewData(state);
            while (state.getCityContent(state.getRows() - 1, state.getCols() - 1).isWall()) state.randomAddWalls();

        } while (!AStar.findPath(state.getCityContent(0, 0), state.getCityContent(state.getRows() - 1, state.getCols() - 1), state, panel));
    }
}
