/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_2;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author Ayxan
 */
public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Pebble Game");

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setSize(600, 600);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });

        String[] options = {"3x3", "4x4", "6x6"};
        int choice = -1;

        while (choice == JOptionPane.CLOSED_OPTION) {
            choice = JOptionPane.showOptionDialog(
                null,
                "Select Board Size",
                "Pebble Game",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
            );

            if (choice == JOptionPane.CLOSED_OPTION) {
                int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to exit the game?",
                    "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        }

        int boardSize = (choice == 0) ? 3 : (choice == 1) ? 4 : 6;

        add(new GamePanel(boardSize));

        setVisible(true);
    }

    private void confirmExit() {
        int choice = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to exit the game?",
            "Exit Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }
}