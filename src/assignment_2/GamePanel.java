/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Ayxan
 */

public class GamePanel extends JPanel {
    private final int size;
    private final JButton[][] buttons;
    private final GameBoard gameBoard;
    private int turnsRemaining;

    public GamePanel(int size) {
        this.size = size;
        this.buttons = new JButton[size][size];
        this.gameBoard = new GameBoard(size);
        this.turnsRemaining = (size % 2 == 0) ? size * 5 : size * 5 + 1;


        setLayout(new GridLayout(size, size));
        initializeUI();
    }

    private void initializeUI() {
        String[][] board = gameBoard.getBoard();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j] = createButton(i, j, board[i][j]);
                add(buttons[i][j]);
            }
        }
    }

    private JButton createButton(int row, int col, String initialText) {
        JButton button = new JButton(initialText);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePebbleSelection(row, col);
            }
        });
        return button;
    }

    private void handlePebbleSelection(int row, int col) {
        String currentPlayer = (turnsRemaining % 2 == 0) ? "W" : "B";
        
        if (!gameBoard.getBoard()[row][col].equals(currentPlayer)) return;
       
        String direction = chooseDirection();

        if (direction != null && gameBoard.movePebble(row, col, currentPlayer, direction)) {
            turnsRemaining--;
            updateButtons();
            checkForWinner();

            if (turnsRemaining == 0) {
                endGame();
            }
        }
    }
    private void checkForWinner() {
    int whitePebbles = gameBoard.getWhitePebbles();
    int blackPebbles = gameBoard.getBlackPebbles();

    if (whitePebbles == 0 || blackPebbles == 0) {
        String winnerMessage = (whitePebbles == 0) ? "Black Wins!" : "White Wins!";
        JOptionPane.showMessageDialog(this, winnerMessage);

        resetGame();
        }
    }

    private String chooseDirection() {
        String[] directions = {"UP", "DOWN", "LEFT", "RIGHT"};
        return (String) JOptionPane.showInputDialog(
                this,
                "Choose direction to move:",
                "Direction Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                directions,
                directions[0]
        );
    }

    private void updateButtons() {
        String[][] board = gameBoard.getBoard();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j].setText(board[i][j]);
            }
        }
    }

    private void endGame() {
        int whitePebbles = gameBoard.getWhitePebbles();
        int blackPebbles = gameBoard.getBlackPebbles();

        String message;
        if (whitePebbles > blackPebbles) {
            message = "White Wins!";
        } else if (blackPebbles > whitePebbles) {
            message = "Black Wins!";
        } else {
            message = "It's a Draw!";
        }

        JOptionPane.showMessageDialog(this, message);
        resetGame();
        System.exit(0);

    }

    private void resetGame() {
        turnsRemaining = (size % 2 == 0) ? size * 5 : size * 5 + 1;
        gameBoard.reset();
        removeAll();
        initializeUI();
        revalidate();
        repaint();
    }
}