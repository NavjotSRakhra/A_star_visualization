package io.github.NavjotSRakhra.path.finder;

import io.github.NavjotSRakhra.path.finder.data.model.State;
import io.github.NavjotSRakhra.path.finder.pathfinder.AStar;
import io.github.NavjotSRakhra.path.finder.pathfinder.BFS;
import io.github.NavjotSRakhra.path.finder.pathfinder.DFS;
import io.github.NavjotSRakhra.path.finder.pathfinder.Dijkstra;
import io.github.NavjotSRakhra.path.finder.ui.GUI;
import io.github.NavjotSRakhra.path.finder.ui.VisualizationJPanel;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Enter your choice of pathfinding algorithm:%n1) A*%n2) Dijkstra's%n3) BFS%n4) DFS%n");
        int a = sc.nextInt();


        State state = new State();
        var panel = new VisualizationJPanel(state);
        new GUI(panel);

        switch (a) {
            case 1:
                do {
                    state = new State(100, 100);
                    state.randomAddWalls();
                    panel.setNewData(state);
                    while (state.getCityContent(state.getRows() - 1, state.getCols() - 1).isWall())
                        state.randomAddWalls();

                } while (!AStar.findPath(state.getCityContent(0, 0), state.getCityContent(state.getRows() - 1, state.getCols() - 1), state, panel));
                break;
            case 2:
                do {
                    state = new State(100, 100);
                    state.randomAddWalls();
                    panel.setNewData(state);
                    while (state.getCityContent(state.getRows() - 1, state.getCols() - 1).isWall())
                        state.randomAddWalls();

                } while (!Dijkstra.findPath(state.getCityContent(0, 0), state.getCityContent(state.getRows() - 1, state.getCols() - 1), state, panel));
                break;
            case 3:

                do {
                    state = new State(50, 50);
                    state.randomAddWalls();
                    panel.setNewData(state);
                    while (state.getCityContent(state.getRows() - 1, state.getCols() - 1).isWall())
                        state.randomAddWalls();

                } while (!BFS.findPath(state.getCityContent(0, 0), state.getCityContent(state.getRows() - 1, state.getCols() - 1), state, panel));
                break;
            case 4:
                do {
                    state = new State(50, 50);
                    state.randomAddWalls();
                    panel.setNewData(state);
                    while (state.getCityContent(state.getRows() - 1, state.getCols() - 1).isWall())
                        state.randomAddWalls();

                } while (!DFS.findPath(state.getCityContent(0, 0), state.getCityContent(state.getRows() - 1, state.getCols() - 1), state, panel));
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
}
