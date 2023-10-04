package data.model;

import data.Visualizable;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public final class State implements Visualizable {
    private final static int DEFAULT_CITY_SIZE = 10;
    private final int rows, cols;
    private final CityDetail[][] city;
    private final CityDetail[][] originalState;

    private final Color[][] cityColorForVisualization;

    public State() {
        this(DEFAULT_CITY_SIZE, DEFAULT_CITY_SIZE);
    }

    public State(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        city = new CityDetail[rows][cols];
        originalState = new CityDetail[rows][cols];

        for (int i = 0; i < city.length; i++) {
            for (int j = 0; j < city[i].length; j++) {
                city[i][j] = new CityDetail(i, j, 1, false);
                originalState[i][j] = new CityDetail(i, j, 1, false);
            }
        }
        cityColorForVisualization = new Color[rows][cols];

        for (Color[] colors : cityColorForVisualization) {
            Arrays.fill(colors, Color.WHITE);
        }


    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCityCost(int i, int j, int cost) {
        city[i][j] = new CityDetail(i, j, cost, city[i][j].isWall());
    }

    public void setOriginalState(int i, int j, int cost) {
        city[i][j] = new CityDetail(i, j, cost, city[i][j].isWall());
        originalState[i][j] = new CityDetail(i, j, cost, city[i][j].isWall());
    }

    public CityDetail getCityContent(int i, int j) {
        return city[i][j];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State stateToCheck)) return false;

        if (getRows() != stateToCheck.getRows()) return false;
        if (getCols() != stateToCheck.getCols()) return false;
        return Arrays.deepEquals(city, stateToCheck.city);
    }

    @Override
    public int hashCode() {
        int result = getRows();
        result = 31 * result + getCols();
        result = 31 * result + Arrays.deepHashCode(city);
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
                "rows=" + rows +
                ", cols=" + cols +
                ", city=" + Arrays.deepToString(city) +
                '}';
    }

    @Override
    public int rows() {
        return getRows();
    }

    @Override
    public int cols() {
        return getCols();
    }

    @Override
    public Color getColorAt(int x, int y) {
        Color currCul = cityColorForVisualization[x][y];
        return new Color(currCul.getRed(), currCul.getGreen(), currCul.getBlue(), city[x][y].isWall() ? 0 : 255)

                ;
    }

    public void setColorAt(int x, int y, Color color) {
        cityColorForVisualization[x][y] = color;
    }

    public void randomAddWalls() {
        Random random = new Random();
        long seed = random.nextLong();
        random = new Random(seed); //4711118688924170882 7604364099595058164 522880717000417519 8254739268449912458 217971863448049020 -5634624935144158410
        System.out.println(seed);

        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < cols; j++) {
                CityDetail cityDetail = city[i][j];
                if (random.nextInt(100) <= 50)
                    city[i][j] = new CityDetail(cityDetail.x(), cityDetail.y(), cityDetail.cost(), true);
                else
                    city[i][j] = new CityDetail(cityDetail.x(), cityDetail.y(), cityDetail.cost(), false);

            }
        }
    }
}
