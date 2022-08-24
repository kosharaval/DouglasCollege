package controller;

import model.BattleshipModel;
import model.components.MessageJLabel;
import view.BattleshipView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BattleshipController {
    private int guesses = 0;
    private BattleshipModel battleshipModel;
    private BattleshipView battleshipView;

    public BattleshipController(BattleshipModel battleshipModel, BattleshipView battleshipView) {
        this.battleshipModel = battleshipModel;
        this.battleshipView = battleshipView;
    }

    private String parseGuess(String guess){
        List<String> alphabets = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G"));

        if(guess.isEmpty() || guess.length() != 2){
            JOptionPane.showMessageDialog(null,
                    "Oops, please enter a letter and a number on the board.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            char firstChar = guess.charAt(0);
            String firstString = Character.toString(firstChar);
            int rowInt = alphabets.indexOf(firstString);
            char columnChar = guess.charAt(1);
            int colInt = -1;

            boolean colIsNumber = Character.isDigit(columnChar);
            if(colIsNumber){
                colInt = columnChar - '0';
            }

            if(rowInt == -1 || colInt == -1){
                JOptionPane.showMessageDialog(null,
                        "Oops, that isn't on the board.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else if(rowInt < 0 || rowInt >= battleshipModel.getBoardSize() || colInt < 0 || colInt >= battleshipModel.getBoardSize()){
                JOptionPane.showMessageDialog(null,
                        "Oops, that's off the board!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else{
                String row = Integer.toString(rowInt);
                String column = Character.toString(columnChar);
                return row + column;
            }
        }
        return "";
    }

    public void handleFireButton(String guessTextField, JLabel messageLabel, ArrayList<MessageJLabel> myJLabels){
        String guessInput = guessTextField;
        String guess = guessInput.toUpperCase();

        processGuess(guess, messageLabel, myJLabels);
    }

    private void processGuess(String guess, JLabel messageLabel, ArrayList<MessageJLabel> myJLabels){
        String location = parseGuess(guess);
        if(!location.isEmpty()){
            guesses++;
            int hit = battleshipModel.fire(location);
            if((hit == 1 || hit == 2 || hit == 3) && battleshipModel.getShipsSunk() == battleshipModel.getNumShips()){
                String message = "You sank all my battleships, in " + guesses + " guesses";
                battleshipView.displayHit(location, myJLabels);
                battleshipView.displayMessage(messageLabel, message);
            }else if(hit == 1){
                String message = "Oops, you already hit that location!";
                battleshipView.displayMessage(messageLabel, message);
            } else if(hit == 2){
                String message = "HIT!";
                battleshipView.displayHit(location, myJLabels);
                battleshipView.displayMessage(messageLabel, message);
            }  else if(hit == 3){
                String message = "You sank my battleship!";
                battleshipView.displayHit(location, myJLabels);
                battleshipView.displayMessage(messageLabel, message);
            } else if(hit == 0){
                String message = "You missed.";
                battleshipView.displayMiss(location, myJLabels);
                battleshipView.displayMessage(messageLabel, message);
            }
        }
    }
}
