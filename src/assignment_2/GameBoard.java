/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_2;

/**
 *
 * @author Ayxan
 */
import java.util.Random;

public class GameBoard {
    private final int size;
    private final String[][] board;
    private int whitePebbles;
    private int blackPebbles;

    public GameBoard(int size) {
        this.size = size;
        this.board = new String[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        whitePebbles = size;
        blackPebbles = size;

        Random random = new Random();

        for (int i = 0; i < size * size; i++) {
            int row = i / size;
            int col = i % size;

            if (whitePebbles > 0 && (random.nextBoolean() || blackPebbles == 0)) {
                board[row][col] = "W";
                whitePebbles--;
            } else if (blackPebbles > 0) {
                board[row][col] = "B";
                blackPebbles--;
            } else {
                board[row][col] = "";
            }
        }

        whitePebbles = size;
        blackPebbles = size;
    }

    public String[][] getBoard() {
        return board;
    }

    public boolean movePebble(int row, int col, String player, String direction) {
        if (!board[row][col].equals(player)) return false;

        switch (direction) {
            case "UP":
                return moveVertical(col, row, -1);
            case "DOWN":
                return moveVertical(col, row, 1);
            case "LEFT":
                return moveHorizontal(row, col, -1);
            case "RIGHT":
                return moveHorizontal(row, col, 1);
            default:
                return false;
        }
    }

    private boolean moveHorizontal(int row, int startCol, int direction) {
    int emptyCol = -1;
    int currentCol = startCol + direction;

    while (currentCol >= 0 && currentCol < size) {
        if (board[row][currentCol].isEmpty()) {
            emptyCol = currentCol;
            break;
        }
        currentCol += direction;
    }

    if (emptyCol != -1) {
        if (direction == 1) {
            for (int col = emptyCol; col > startCol; col--) {
                board[row][col] = board[row][col - 1];
            }
        } else {
            for (int col = emptyCol; col < startCol; col++) {
                board[row][col] = board[row][col + 1];
            }
        }
        board[row][startCol] = "";
    } else {
        if (direction == 1) {
            for (int col = size - 1; col > startCol; col--) {
                board[row][col] = board[row][col - 1];
            }
            board[row][startCol] = "";
        } else {
            for (int col = 0; col < startCol; col++) {
                board[row][col] = board[row][col + 1];
            }
            board[row][startCol] = "";
        }
    }
    
    updatePebbleCounts();
    return true;
}

    private boolean moveVertical(int col, int startRow, int direction) {
    int emptyRow = -1;
    int currentRow = startRow + direction;

    while (currentRow >= 0 && currentRow < size) {
        if (board[currentRow][col].isEmpty()) {
            emptyRow = currentRow;
            break;
        }
        currentRow += direction;
    }

    if (emptyRow != -1) {
        if (direction == 1) {
            for (int row = emptyRow; row > startRow; row--) {
                board[row][col] = board[row - 1][col];
            }
        } else {
            for (int row = emptyRow; row < startRow; row++) {
                board[row][col] = board[row + 1][col];
            }
        }
        board[startRow][col] = "";
    } else {
        if (direction == 1) {
            for (int row = size - 1; row > startRow; row--) {
                board[row][col] = board[row - 1][col];
            }
            board[startRow][col] = "";
        } else {
            for (int row = 0; row < startRow; row++) {
                board[row][col] = board[row + 1][col];
            }
            board[startRow][col] = "";
        }
    }
    updatePebbleCounts();
    return true;
}

     private void updatePebbleCounts() {
        whitePebbles = 0;
        blackPebbles = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].equals("W")) {
                    whitePebbles++;
                } else if (board[i][j].equals("B")) {
                    blackPebbles++;
                }
            }
        }
    }
     
    public int getWhitePebbles() {
        return whitePebbles;
    }

    public int getBlackPebbles() {
        return blackPebbles;
    }

    public void reset() {
        initializeBoard();
    }
}