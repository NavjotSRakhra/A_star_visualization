package pathfinder;

import data.model.CityDetail;
import data.model.State;
import ui.VisualizationJPanel;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Queue;
import java.util.*;

import static java.lang.Thread.sleep;

public class AStar {
    public static boolean findPath(CityDetail startCity, CityDetail goalCity, State state, VisualizationJPanel panel) throws InterruptedException, InvocationTargetException {
        final Queue<CityDetail> openSet = new PriorityQueue<>(Comparator.comparingDouble(a -> calcFScore(a, goalCity)));
        final Set<CityDetail> openHashSet = new HashSet<>();
        final Set<CityDetail> closedSet = new HashSet<>();
        final Map<CityDetail, CityDetail> cameFrom = new HashMap<>();

        openSet.add(startCity);
        openHashSet.add(startCity);

        while (!openSet.isEmpty()) {
            var current = openSet.poll();
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

                var tentativeG = current.cost() + neighbour.cost();
                if (!openHashSet.contains(neighbour)) {
                    state.setCityCost(x, y, tentativeG);
                    openSet.offer(state.getCityContent(x, y));
                    openHashSet.add(state.getCityContent(x, y));
                    cameFrom.put(neighbour, current);
                } else if (tentativeG < neighbour.cost()) {
                    state.setCityCost(x, y, tentativeG);
                    openHashSet.add(state.getCityContent(x, y));
                    openSet.offer(state.getCityContent(x, y));
                    cameFrom.put(neighbour, current);
                }

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

    private static void paintArea(State state, VisualizationJPanel panel, CityDetail cityDetail) throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            double height = (double) panel.getHeight() / state.rows();
            double width = (double) panel.getWidth() / state.cols();

            panel.repaint(new Rectangle((int) (cityDetail.x() * width - 1), (int) (cityDetail.y() * height - 1), (int) (height + 2), (int) (width + 2)));
        });
    }

    private static double calcFScore(CityDetail city, CityDetail goalCity) {
        return heuristic(city, goalCity) + city.cost();
    }

    private static double heuristic(CityDetail city, CityDetail goal) {
        if (city.equals(goal)) return 0;

        int ac = Math.abs(city.x() - goal.x());
        int cb = Math.abs(city.y() - goal.y());

        return Math.hypot(ac, cb);
//        return ac + cb;
    }
}
