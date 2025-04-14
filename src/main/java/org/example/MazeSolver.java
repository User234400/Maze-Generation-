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

}