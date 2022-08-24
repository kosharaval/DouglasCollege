package model.components;

import javax.swing.*;

public class MessageJLabel extends JLabel {
    private String cellPosition;

    public MessageJLabel(String cellPosition) {
        this.cellPosition = cellPosition;
    }

    public String getCellPosition() {
        return cellPosition;
    }
}
