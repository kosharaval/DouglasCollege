package view;

import model.components.MessageJLabel;

import javax.swing.*;
import java.util.ArrayList;

public class BattleshipView {
    public void displayMessage(JLabel messageLabel, String message){
        messageLabel.setText(message);
    }

    public void displayMiss(String location, ArrayList<MessageJLabel> myJLabels){
        ImageIcon missImage = new ImageIcon("miss.png");

        int index = -1;

        for(int i = 0; i < myJLabels.size(); i++){
            if(location.equals(myJLabels.get(i).getCellPosition())){
                index = i;
            }
        }

        if(index != -1){
            MessageJLabel myJLabel = myJLabels.get(index);
            myJLabel.setIcon(missImage);
        }
    }

    public void displayHit(String location, ArrayList<MessageJLabel> myJLabels){
        ImageIcon shipImage = new ImageIcon("ship.png");

        int index = -1;

        for(int i = 0; i < myJLabels.size(); i++){
            if(location.equals(myJLabels.get(i).getCellPosition())){
                index = i;
            }
        }

        if(index != -1){
            MessageJLabel myJLabel = myJLabels.get(index);
            myJLabel.setIcon(shipImage);
        }
    }
}
