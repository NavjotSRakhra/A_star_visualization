package data.model;

import java.util.List;

public record CityDetail(int x, int y, int cost, boolean isWall) {
    public List<CityDetail> getNeighbours() {
        List<CityDetail> neighbours = List.of(
                new CityDetail(x + 1, y + 1, -1, false),
                new CityDetail(x + 1, y - 1, -1, false),
                new CityDetail(x - 1, y + 1, -1, false),
                new CityDetail(x - 1, y - 1, -1, false),

                new CityDetail(x, y + 1, -1, false),
                new CityDetail(x + 1, y, -1, false),
                new CityDetail(x - 1, y, -1, false),
                new CityDetail(x, y - 1, -1, false)

        );
        return neighbours.stream().filter(a -> a.x() >= 0 && a.y >= 0).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityDetail that)) return false;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "CityDetail{" +
                "x=" + x +
                ", y=" + y +
                ", cost=" + cost +
                '}';
    }
}
