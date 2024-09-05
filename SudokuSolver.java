public class SudokuSolver {

    public static boolean solveSudoku(int[][] grid) {
        int N = grid.length;
        int row = -1;
        int col = -1;
        boolean isEmpty = true;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        if (isEmpty) {
            return true;
        }

        for (int num = 1; num <= N; num++) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;

                if (solveSudoku(grid)) {
                    return true;
                }

                grid[row][col] = 0;
            }
        }

        return false;
    }

    private static boolean isSafe(int[][] grid, int row, int col, int num) {
        return !usedInRow(grid, row, num) && !usedInCol(grid, col, num)
                && !usedInBox(grid, row - row % 3, col - col % 3, num);
    }

    private static boolean usedInRow(int[][] grid, int row, int num) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[row][i] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean usedInCol(int[][] grid, int col, int num) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean usedInBox(int[][] grid, int boxStartRow, int boxStartCol, int num) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + boxStartRow][j + boxStartCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void printGrid(int[][] grid) {
        int N = grid.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                { 0, 0, 3, 0, 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 0, 2, 0, 0 },
                { 0, 0, 1, 0, 5, 0, 0, 0, 0 },
                { 0, 0, 0, 3, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 8, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 6, 0 },
                { 0, 4, 0, 0, 0, 0, 0, 0, 0 },
                { 2, 0, 0, 0, 0, 0, 8, 0, 0 }
        };
        if (solveSudoku(grid)) {
            printGrid(grid);
        } else {
            System.out.println("No solution exists");
        }
    }
}
