package computer;

import grid.Grid;
import player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Computer extends Player {
    String difficulty;

    public Computer(char marker, String difficulty) {
        super(marker);
        this.difficulty = difficulty;
    }

    public int[] generateMove(char[][] grid) {
        return switch (difficulty) {
            case "easy" -> generateMoveEasy(grid, false);
            case "medium" -> generateMoveMedium(grid);
            case "hard" -> generateMoveHard(grid);
            default -> new int[0];
        };
    }

    private int[] generateMoveEasy(char[][] grid, boolean isNested) {
        if (!isNested) {
            System.out.println("Making move level \"easy\"");
        }
        int row = generateRandomInt();
        int col = generateRandomInt();

        while (grid[row][col] != '_') {
            row = generateRandomInt();
            col = generateRandomInt();
        }

        return new int[]{row, col};
    }

    static int generateRandomInt() {
        Random random = new Random();
        return random.nextInt(3);
    }

    private int[] generateMoveMedium(char[][] grid) {
        System.out.println("Making move level \"medium\"");
        char marker = getMarker();
        char opponentMarker = marker == 'X' ? 'O' : 'X';

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '_') {
                    // rule 1: Winning move
                    if (checkPotentialWin(grid, getMarker(), i, j)) {
                        return new int[]{i, j};
                    }

                    // rule 2: Blocking move
                    if (checkPotentialWin(grid, opponentMarker, i, j)) {
                        return new int[]{i, j};
                    }

                }
            }
        }

        // rule 3: Fallback move
        return generateMoveEasy(grid, true);
    }

    static boolean checkPotentialWin(char[][] grid, char marker, int i, int j) {
        char[][] gridCopy = new char[grid.length][grid[0].length];
        for (int x = 0; x < grid.length; x++) {
            gridCopy[x] = Arrays.copyOf(grid[x], grid[x].length);
        }
        gridCopy[i][j] = marker;

        return Grid.checkRowWin(gridCopy, marker, i, j) ||
                Grid.checkColumnWin(gridCopy, marker, i, j) ||
                Grid.checkDiagonalWin(gridCopy, marker, i, j);
    }

    private int[] generateMoveHard(char[][] grid) {
        System.out.println("Making move level \"hard\"");

        ArrayList<Move> moves = new ArrayList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '_') {
                    grid[i][j] = this.getMarker();
                    Move move = new Move(i, j, minimax(grid, false));
                    moves.add(move);
                    grid[i][j] = '_';
                }
            }
        }

        int bestScore = Integer.MIN_VALUE;
        Move bestMove = moves.getFirst();
        for (Move move : moves) {
            if (move.score > bestScore) {
                bestScore = move.score;
                bestMove = move;
            }
        }

        return new int[]{bestMove.i, bestMove.j};
    }

    private int minimax(char[][] grid, boolean isCurrentPlayerTurn) {
        if (Grid.checkWin(grid, getMarker())) {
            return 1;
        }

        if (Grid.checkWin(grid, switchMarker(getMarker()))) {
            return -1;
        }

        if (Grid.getMarkerCount(grid, '_') == 0) {
            return 0;
        }

        if (isCurrentPlayerTurn) { // Current Player aims to get the highest score (score grows towards positive infinity)
            int maxScore = Integer.MIN_VALUE;
            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[0].length; y++) {
                    if (grid[x][y] == '_') {
                        grid[x][y] = getMarker();
                        int score = minimax(grid, false);
                        grid[x][y] = '_';
                        maxScore = Math.max(maxScore, score);
                    }
                }
            }
            return maxScore;
        } else { // Opponent aims to get the smallest score (opponent's score grows towards negative infinity)
            int minScore = Integer.MAX_VALUE;
            for (int p = 0; p < grid.length; p++) {
                for (int q = 0; q < grid[0].length; q++) {
                    if (grid[p][q] == '_') {
                        grid[p][q] = switchMarker(getMarker());
                        int score = minimax(grid, true);
                        grid[p][q] = '_';
                        minScore = Math.min(minScore, score);
                    }
                }
            }
            return minScore;
        }
    }

    static char switchMarker(char marker) {
        return marker == 'X' ? 'O' : 'X';
    }

    static class Move {
        int i;
        int j;
        int score;

        Move(int i, int j, int score) {
            this.i = i;
            this.j = j;
            this.score = score;
        }
    }
}
