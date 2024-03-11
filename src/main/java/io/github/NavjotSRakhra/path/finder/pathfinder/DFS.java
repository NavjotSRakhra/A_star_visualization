package io.github.NavjotSRakhra.path.finder.pathfinder;

import io.github.NavjotSRakhra.path.finder.data.model.CityDetail;
import io.github.NavjotSRakhra.path.finder.data.model.State;
import io.github.NavjotSRakhra.path.finder.ui.VisualizationJPanel;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Queue;
import java.util.*;

import static java.lang.Thread.sleep;

public class DFS {
    public static boolean findPath(CityDetail startCity, CityDetail goalCity, State state, VisualizationJPanel panel) throws InterruptedException, InvocationTargetException {
        final ArrayDeque<CityDetail> openSet = new ArrayDeque<>();
        final Set<CityDetail> openHashSet = new HashSet<>();
        final Set<CityDetail> closedSet = new HashSet<>();
        final Map<CityDetail, CityDetail> cameFrom = new HashMap<>();

        openSet.add(startCity);
        openHashSet.add(startCity);

        while (!openSet.isEmpty()) {
            var current = openSet.pop();
            openHashSet.remove(current);

            repaint(state, openSet, closedSet, cameFrom, current, panel);

            if (current.equals(goalCity)) { // found
                while (current != null) {
                    state.setColorAt(current.x(), current.y(), Color.RED);
                    current = cameFrom.get(current);
                    SwingUtilities.invokeAndWait(panel::repaint);
                    sleep(50);
                }

                return true;
            }

            closedSet.add(current);

            for (CityDetail neighbour : current.getNeighbours().stream().filter(a -> a.x() < state.getRows() && a.y() < state.getCols()).toList()) {
                int x = neighbour.x(), y = neighbour.y();
                neighbour = state.getCityContent(x, y);

                if (neighbour.isWall()) continue;
                if (closedSet.contains(neighbour)) continue;
                if (openHashSet.contains(neighbour)) continue;

                openSet.push(neighbour);
                openHashSet.add(neighbour);

                cameFrom.put(neighbour, current);
            }
        }
        System.out.println("Not found");
        return false;
    }

    private static void repaint(State state, Queue<CityDetail> openSet, Set<CityDetail> closedSet, Map<CityDetail, CityDetail> cameFrom, CityDetail current, VisualizationJPanel panel) throws InterruptedException, InvocationTargetException {
        for (CityDetail cityDetail : openSet) {
            state.setColorAt(cityDetail.x(), cityDetail.y(), Color.YELLOW);
        }
        for (CityDetail cityDetail : closedSet) {
            state.setColorAt(cityDetail.x(), cityDetail.y(), Color.GREEN);
        }

        while (current != null) {
            state.setColorAt(current.x(), current.y(), Color.BLUE);
            current = cameFrom.get(current);
        }

        SwingUtilities.invokeAndWait(panel::repaint);
//        Thread.sleep(50);
    }
}
