package tictactoe;

import computer.Computer;
import grid.Grid;
import player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static boolean programOn = true;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (programOn) {
            String command;
            do {
                System.out.print("Input command: ");
                command = sc.nextLine();
            } while (!validateCommandInput(command));
            menu(command);
        }
    }

    static boolean validateCommandInput(String command) {
        ArrayList<String> commands = new ArrayList<>(
                Arrays.asList(
                        "start easy easy",
                        "start easy medium",
                        "start easy hard",
                        "start medium easy",
                        "start medium medium",
                        "start medium hard",
                        "start hard easy",
                        "start hard medium",
                        "start hard hard",
                        "start easy user",
                        "start user easy",
                        "start medium user",
                        "start user medium",
                        "start hard user",
                        "start user hard",
                        "start user user",
                        "exit"));

        if (commands.contains(command)) {
            return true;
        }

        System.out.println("Bad parameters!");
        return false;
    }

    static void menu(String command) {
        Grid grid = new Grid();

        if (!command.equals("exit")) {
            Grid.populateGrid(grid.getGrid(), "_________");
            grid.printGrid();
        }

        switch (command) {
            case "start easy easy":
                playGameComputerComputer(grid, "easy", "easy");
                break;

            case "start easy medium":
                playGameComputerComputer(grid, "easy", "medium");
                break;

            case "start easy hard":
                playGameComputerComputer(grid, "easy", "hard");
                break;

            case "start medium easy":
                playGameComputerComputer(grid, "medium", "easy");
                break;

            case "start medium medium":
                playGameComputerComputer(grid, "medium", "medium");
                break;

            case "start medium hard":
                playGameComputerComputer(grid, "medium", "hard");
                break;

            case "start hard easy":
                playGameComputerComputer(grid, "hard", "easy");
                break;

            case "start hard medium":
                playGameComputerComputer(grid, "hard", "medium");
                break;

            case "start hard hard":
                playGameComputerComputer(grid, "hard", "hard");
                break;

            case "start easy user":
                playGameHumanComputer(grid, 'O', 'X', "easy");
                break;

            case "start user easy":
                playGameHumanComputer(grid, 'X', 'O', "easy");
                break;

            case "start medium user":
                playGameHumanComputer(grid, 'O', 'X', "medium");
                break;

            case "start user medium":
                playGameHumanComputer(grid, 'X', 'O', "medium");
                break;

            case "start hard user":
                playGameHumanComputer(grid, 'O', 'X', "hard");
                break;

            case "start user hard":
                playGameHumanComputer(grid, 'X', 'O', "hard");
                break;

            case "start user user":
                playGameHumanHuman(grid);
                break;

            case "exit":
                programOn = false;
                break;
        }
    }

    static void playGameComputerComputer(Grid grid, String computer1Difficulty, String computer2Difficulty) {
        Computer computer1 = new Computer('X', computer1Difficulty);
        Computer computer2 = new Computer('O', computer2Difficulty);
        Computer currentComputer = computer1;

        boolean isGameOnComputerComputer = true;
        while (isGameOnComputerComputer) {
            int[] gridInputs = currentComputer.generateMove(grid.getGrid());
            int row = gridInputs[0];
            int col = gridInputs[1];

            grid.getGrid()[row][col] = currentComputer.getMarker();
            grid.printGrid();
            String gameStatus = grid.analyzeGameState(grid.getGrid());
            Grid.printGameStatus(gameStatus);

            if (gameStatus.equals("Impossible") || gameStatus.equals("Draw") || gameStatus.equals("X wins") || gameStatus.equals("O wins")) {
                isGameOnComputerComputer = false;
            }

            currentComputer = currentComputer.equals(computer1) ? computer2 : computer1;
        }
    }

    static void playGameHumanComputer(Grid grid, char humanMarker, char computerMarker, String computerDifficulty) {
        Player human = new Player(humanMarker);
        Computer computer = new Computer(computerMarker, computerDifficulty);
        Player currentPlayer = humanMarker == 'X' ? human : computer;

        boolean isGameOnHumanComputer = true;
        while (isGameOnHumanComputer) {
            if (currentPlayer.equals(human)) {
                boolean inputNotTaken = true;
                while (inputNotTaken) {
                    try {
                        System.out.print("Enter the coordinates: ");
                        String gridInput = sc.nextLine();
                        int[] gridInputs = Arrays.stream(gridInput.split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
                        int row = gridInputs[0];
                        int col = gridInputs[1];

                        if (!(row >= 1 && row <= 3) || !(col >= 1 && col <= 3)) {
                            System.out.println("Coordinates should be from 1 to 3!");
                        } else if (grid.getGrid()[row - 1][col - 1] != '_') {
                            System.out.println("This cell is occupied! Choose another one!");
                        } else {
                            grid.getGrid()[row - 1][col - 1] = human.getMarker();
                            grid.printGrid();
                            inputNotTaken = false;
                            String gameStatus = grid.analyzeGameState(grid.getGrid());
                            Grid.printGameStatus(gameStatus);
                            if (gameStatus.equals("Impossible") || gameStatus.equals("Draw") || gameStatus.equals("X wins") || gameStatus.equals("O wins")) {
                                isGameOnHumanComputer = false;
                            }
                            currentPlayer = computer;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("You should enter numbers!");
                    }
                }
            } else {
                int[] gridInputs = computer.generateMove(grid.getGrid());
                int row = gridInputs[0];
                int col = gridInputs[1];

                grid.getGrid()[row][col] = computer.getMarker();
                grid.printGrid();

                String gameStatus = grid.analyzeGameState(grid.getGrid());
                Grid.printGameStatus(gameStatus);

                if (gameStatus.equals("Impossible") || gameStatus.equals("Draw") || gameStatus.equals("X wins") || gameStatus.equals("O wins")) {
                    isGameOnHumanComputer = false;
                }
                currentPlayer = human;
            }
        }
    }

    static void playGameHumanHuman(Grid grid) {
        Player human1 = new Player('X');
        Player human2 = new Player('O');
        Player currentHuman = human1;

        boolean isGameOnHumanHuman = true;
        while (isGameOnHumanHuman) {
            boolean inputNotTaken = true;
            while (inputNotTaken) {
                try {
                    System.out.print("Enter the coordinates: ");
                    String gridInput = sc.nextLine();
                    int[] gridInputs = Arrays.stream(gridInput.split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
                    int row = gridInputs[0];
                    int col = gridInputs[1];

                    if (!(row >= 1 && row <= 3) || !(col >= 1 && col <= 3)) {
                        System.out.println("Coordinates should be from 1 to 3!");
                    } else if (grid.getGrid()[row - 1][col - 1] != '_') {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        grid.getGrid()[row - 1][col - 1] = currentHuman.getMarker();
                        grid.printGrid();
                        inputNotTaken = false;

                        String gameStatus = grid.analyzeGameState(grid.getGrid());
                        Grid.printGameStatus(gameStatus);

                        if (gameStatus.equals("Impossible") || gameStatus.equals("Draw") || gameStatus.equals("X wins") || gameStatus.equals("O wins")) {
                            isGameOnHumanHuman = false;
                        }
                        currentHuman = currentHuman.equals(human1) ? human2 : human1;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!");
                }
            }
        }
    }
}
