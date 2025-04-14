package org.example;

import java.util.*;


public class MazeSolver {
    private static final char WALL = '#';
    private static final char PATH = ' ';
    private static final char VISITED = '.';

    public MazeSolver(int rows, int cols) {
        // Ensure odd dimensions
        this.rows = (rows % 2 == 0) ? rows + 1 : rows;
        this.cols = (cols % 2 == 0) ? cols + 1 : cols;
        maze = new char[this.rows][this.cols];
        visited = new boolean[this.rows][this.cols];
        endX = this.rows - 2;
        endY = this.cols - 2;

        //generateMaze();
    }

    private void generateMaze() {
        // Fill maze with walls
        for (int i = 0; i < rows; i++)
            Arrays.fill(maze[i], WALL);

        // Start carving
        maze[startX][startY] = PATH;
        carve(startX, startY)
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

    public static void main(String[] args) {
        MazeSolver mazeSolver = new MazeSolver(15, 15);
        System.out.println("Generated Maze:");
        mazeSolver.display();

        System.out.println("Solving Maze...");
        boolean solved = mazeSolver.solve(mazeSolver.startX, mazeSolver.startY);


    }