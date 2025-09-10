package grid;

public class Grid {
    private final char[][] grid;

    public Grid() {
        this.grid = new char[3][3];
    }

    public char[][] getGrid() {
        return grid;
    }

    public static void populateGrid(char[][] grid, String input) {
        StringBuilder str = new StringBuilder(input);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = str.charAt(0);
                str.delete(0, 1);
            }
        }
    }

    public void printGrid() {
        StringBuilder output = new StringBuilder("---------\n");
        for (char[] chars : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                if (j == 0) {
                    output.append("| ").append(chars[j]).append(" ");
                } else if (j == 2) {
                    output.append(chars[j]).append(" ").append("|\n");
                } else {
                    output.append(chars[j]).append(" ");
                }
            }
        }
        output.append("---------");
        System.out.println(output);
    }

    public static void printGameStatus(String gameStatus) {
        switch (gameStatus) {
            case "Game not finished":
                break;
            case "Draw":
                System.out.println("Draw\n");
                break;
            case "X wins":
                System.out.println("X wins\n");
                break;
            case "O wins":
                System.out.println("O wins\n");
                break;
        }
    }

    public String analyzeGameState(char[][] grid) {
//        Game ongoing check
        if (!checkWin(grid, 'X') && !checkWin(grid, 'O') && getMarkerCount(grid, '_') > 0) {
            return "Game not finished";
        }

//        Draw check
        if (!checkWin(grid, 'X') && !checkWin(grid, 'O') && getMarkerCount(grid, '_') == 0) {
            return "Draw";
        }

//        X won check
        if (checkWin(grid, 'X')) {
            return "X wins";
        }

//        O won if all other options are false.
        return "O wins";
    }

    public static int getMarkerCount(char[][] grid, char marker) {
        int markerCount = 0;
        for (char[] chars : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                if (chars[j] == marker) {
                    markerCount++;
                }
            }
        }
        return markerCount;
    }

    public static boolean checkWin(char[][] grid, char marker) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (checkRowWin(grid, marker, i, j) || checkColumnWin(grid, marker, i, j) || checkDiagonalWin(grid, marker, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkRowWin(char[][] grid, char marker, int i, int j) {
        if (j == 0) {
            return grid[i][j] == marker &&
                    grid[i][j + 1] == marker &&
                    grid[i][j + 2] == marker;
        } else if (j == 1) {
            return grid[i][j] == marker &&
                    grid[i][j - 1] == marker &&
                    grid[i][j + 1] == marker;
        } else if (j == 2) {
            return grid[i][j] == marker &&
                    grid[i][j - 1] == marker &&
                    grid[i][j - 2] == marker;
        } else {
            return false;
        }
    }

    public static boolean checkColumnWin(char[][] grid, char marker, int i, int j) {
        if (i == 0) {
            return grid[i][j] == marker &&
                    grid[i + 1][j] == marker &&
                    grid[i + 2][j] == marker;
        } else if (i == 1) {
            return grid[i][j] == marker &&
                    grid[i - 1][j] == marker &&
                    grid[i + 1][j] == marker;
        } else if (i == 2) {
            return grid[i][j] == marker &&
                    grid[i - 1][j] == marker &&
                    grid[i - 2][j] == marker;
        } else {
            return false;
        }
    }

    public static boolean checkDiagonalWin(char[][] grid, char marker, int i, int j) {
        if (i == 0) {
            if (j == 0) {
                return grid[i][j] == marker &&
                        grid[i + 1][j + 1] == marker &&
                        grid[i + 2][j + 2] == marker;
            } else if (j == 2) {
                return grid[i][j] == marker &&
                        grid[i + 1][j - 1] == marker &&
                        grid[i + 2][j - 2] == marker;
            } else {
                return false;
            }
        } else if (i == 1) {
            if (j == 1) {
                return (grid[i][j] == marker &&
                        grid[i - 1][j - 1] == marker &&
                        grid[i + 1][j + 1] == marker) ||
                        (grid[i][j] == marker &&
                                grid[i - 1][j + 1] == marker &&
                                grid[i + 1][j - 1] == marker);
            } else {
                return false;
            }
        } else if (i == 2) {
            if (j == 0) {
                return grid[i][j] == marker &&
                        grid[i - 1][j + 1] == marker &&
                        grid[i - 2][j + 2] == marker;
            } else if (j == 2) {
                return grid[i][j] == marker &&
                        grid[i - 1][j - 1] == marker &&
                        grid[i - 2][j - 2] == marker;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
