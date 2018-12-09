package Draw;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PaintLine {
    /**
     * Find that belong to line connecting the two points
     **/
    public List<Point> findLine(Point[][] grid, int x0, int y0, int x1, int y1) throws CustomException {

        List<Point> line = new ArrayList<Point>();

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;

        int err = dx - dy;
        int e2;

        while (true) {
            try {
                line.add(grid[x0][y0]);

                if (x0 == x1 && y0 == y1)
                    break;

                e2 = 2 * err;
                if (e2 > -dy) {
                    err = err - dy;
                    x0 = x0 + sx;
                }

                if (e2 < dx) {
                    err = err + dx;
                    y0 = y0 + sy;
                }
            } catch (Exception e) {
                throw new CustomException("Co Ordinates of the command exceeds Canvas Size");
            }
        }

        return line;
    }

    /**
     * function plot() - to visualize grid
     **/
    public void plot(Point[][] grid, List<Point> line) {
        int rows = grid.length;
        int cols = grid[0].length;

        System.out.print("\nCanvas : \n");
        for (int i = 0; i < cols + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print("|");
            for (int j = 0; j < cols; j++) {
                if (line.contains(grid[i][j]))
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.print("|");
            System.out.println();
        }
        for (int i = 0; i < cols + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public Point[][] getGridOfCoordinates(int canvasRows, int canvasCols) {
        Point[][] grid = new Point[canvasRows][canvasCols];
        for (int i = 0; i < canvasRows; i++) {
            for (int j = 0; j < canvasCols; j++) {
                grid[i][j] = new Point(i, j);
            }
        }
        return grid;
    }

}
