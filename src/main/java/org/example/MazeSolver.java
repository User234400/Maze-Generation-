package org.example;

import java.util.*;




public class MazeSolver {
    private static final char WALL = '#';
    private static final char PATH = ' ';
    private static final char VISITED = '.';

    private char[][] maze;
    private boolean[][] visited;
    private int rows, cols;
    private int startX = 1, startY = 1;
    private int endX, endY;

    public MazeSolver(int rows, int cols) {
        // Ensure odd dimensions
        this.rows = (rows % 2 == 0) ? rows + 1 : rows;
        this.cols = (cols % 2 == 0) ? cols + 1 : cols;
        maze = new char[this.rows][this.cols];
        visited = new boolean[this.rows][this.cols];
        endX = this.rows - 2;
        endY = this.cols - 2;

        generateMaze();
    }

    private void generateMaze() {
        // Fill maze with walls
        for (int i = 0; i < rows; i++)
            Arrays.fill(maze[i], WALL);

        // Start carving
        maze[startX][startY] = PATH;
        carve(startX, startY);

        maze[endX][endY] = PATH;
    }

    private void carve(int x, int y) {
        int[][] directions = {{0, 2}, {0, -2}, {2, 0}, {-2, 0}};
        Collections.shuffle(Arrays.asList(directions)); // Randomize directions

        for (int[] d : directions) {
            int nx = x + d[0];
            int ny = y + d[1];

            if (isInBounds(nx, ny) && maze[nx][ny] == WALL) {
                maze[nx][ny] = PATH;
                maze[x + d[0] / 2][y + d[1] / 2] = PATH;
                carve(nx, ny);
            }
        }
    }

    private boolean isInBounds(int x, int y) {
        return x > 0 && y > 0 && x < rows - 1 && y < cols - 1;
    }

    public boolean solve(int x, int y) {
        if (!isInBounds(x, y) || maze[x][y] == WALL || visited[x][y]) return false;
        visited[x][y] = true;

        if (x == endX && y == endY) {
            maze[x][y] = VISITED;
            return true;
        }

        if (solve(x + 1, y) || solve(x - 1, y) || solve(x, y + 1) || solve(x, y - 1)) {
            maze[x][y] = VISITED;
            return true;
        }

        return false;
    }

    public void display() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MazeSolver mazeSolver = new MazeSolver(15, 15);
        System.out.println("Generated Maze:");
        mazeSolver.display();

        System.out.println("Solving Maze...");
        boolean solved = mazeSolver.solve(mazeSolver.startX, mazeSolver.startY);

        if (solved) {
            System.out.println("Path found!");
        } else {
            System.out.println("No path found.");
        }

        mazeSolver.display();
    }
}
