import controller.BattleshipController;
import model.BattleshipModel;
import model.components.MessageJLabel;
import model.components.Ship;
import view.BattleshipView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;

public class BattleshipApplication {
    public static void main(String[] args) {
        int timeAvailable = 100;

        Ship[] ships = createShips();

        BattleshipModel battleshipModel = new BattleshipModel(7, 3, 3, 0, ships);
        battleshipModel.generateShipLocations();

        BattleshipView battleshipView = new BattleshipView();

        BattleshipController battleshipController = new BattleshipController(battleshipModel, battleshipView);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        ImageIcon boardImage = new ImageIcon("board.jpg");

        JLabel boardImageLabel = new JLabel();
        boardImageLabel.setIcon(boardImage);
        boardImageLabel.setBounds(400, 20, 1024, 863);

        JLabel messageLabel = new JLabel();
        messageLabel.setFont(new Font("Consolas", Font.BOLD, 20));
        messageLabel.setForeground(Color.decode("#53af13"));

        JLabel timerLabel = new JLabel();
        timerLabel.setFont(new Font("Consolas", Font.BOLD, 25));
        timerLabel.setForeground(Color.decode("#53af13"));

        JLabel numberOfMovesLabel = new JLabel();
        numberOfMovesLabel.setFont(new Font("Consolas", Font.BOLD, 25));
        numberOfMovesLabel.setForeground(Color.decode("#53af13"));

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(100, 30));
        textField.setFont(new Font("Consolas", Font.PLAIN, 20));
        textField.setForeground(Color.BLACK);
        textField.setBackground(Color.decode("#98cf71"));

        JButton jButton = new JButton("Fire!");
        jButton.setBackground(Color.decode("#98cf71"));
        jButton.setForeground(Color.BLACK);
        jButton.setFocusPainted(false);
        jButton.setFont(new Font("Tahoma", Font.BOLD, 12));

        JProgressBar jProgressBar = new JProgressBar();
        jProgressBar.setVisible(false);
        jProgressBar.setValue(timeAvailable);
        jProgressBar.setStringPainted(true);
        jProgressBar.setFont(new Font("Consolas", Font.PLAIN, 25));
        jProgressBar.setForeground(Color.decode("#53af13"));
        jProgressBar.setBackground(Color.decode("#98cf71"));

        JPanel gridPanel = createGridPanel();

        JPanel boardPanel = createBoardPanel(gridPanel, boardImageLabel);
        JPanel messagePanel = createMessagePanel(messageLabel);
        JPanel formPanel = createFormPanel(textField, jButton);
        JPanel timerPanel = createTimerPanel(timerLabel, jProgressBar);
        AnimationJPanel animationJPanel = new AnimationJPanel();

        ArrayList<MessageJLabel> myJLabels = createJLables();

        for (MessageJLabel myJLabel : myJLabels) {
            gridPanel.add(myJLabel);
        }

        createFrame(screenSize, messagePanel, boardPanel, formPanel, timerPanel, animationJPanel);

        if (JOptionPane.showConfirmDialog(null,
                "You will be given " + timeAvailable + " seconds to finish. Do you want unlimited time?",
                "INFO",
                JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
            jProgressBar.setVisible(true);
            runTimer(timerLabel, timeAvailable, jProgressBar);
        }

        textField.addActionListener(e -> {
            String guessTextField = textField.getText();
            textField.setText("");
            battleshipController.handleFireButton(guessTextField, messageLabel, myJLabels);
        });

        jButton.addActionListener(e -> {
            String guessTextField = textField.getText();
            textField.setText("");
            battleshipController.handleFireButton(guessTextField, messageLabel, myJLabels);
        });
    }
    
    public static void runTimer(JLabel timerLabel, int timeAvailable, JProgressBar jProgressBar){
        java.util.Timer timer = new java.util.Timer();

        TimerTask task = new TimerTask() {
            int counter = timeAvailable;
            public void run() {
                counter--;
                jProgressBar.setValue(counter);
                timerLabel.setText("Time left: " + counter);
                if(counter < 1){
                    timer.cancel();
                    timer.purge();
                    System.out.println("I am done!!!");
                    JOptionPane.showMessageDialog(null,
                            "Oops, time's over!",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        };

        int delay = 100;
        int period = 1000;
        timer.scheduleAtFixedRate(task, delay, period);
    }

    public static Ship[] createShips(){
        java.util.List<String> locations = new ArrayList<>(Arrays.asList("0", "0", "0"));
        java.util.List<String> hits1 = new ArrayList<>(Arrays.asList("", "", ""));
        java.util.List<String> hits2 = new ArrayList<>(Arrays.asList("", "", ""));
        List<String> hits3 = new ArrayList<>(Arrays.asList("", "", ""));

        return new Ship[]{new Ship(locations, hits1), new Ship(locations, hits2), new Ship(locations, hits3)};
    }

    public static void createFrame(Dimension screenSize, JPanel messagePanel, JPanel boardPanel, JPanel formPanel, JPanel timerPanel, AnimationJPanel animationJPanel){
        JFrame myFrame = new JFrame("Battleship");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(screenSize);
        myFrame.setLayout(new BorderLayout());
        myFrame.add(messagePanel, BorderLayout.EAST);
        myFrame.add(boardPanel, BorderLayout.CENTER);
        myFrame.add(formPanel, BorderLayout.SOUTH);
        myFrame.add(timerPanel, BorderLayout.WEST);
        myFrame.add(animationJPanel, BorderLayout.NORTH);

        myFrame.setVisible(true);
    }

    public static JPanel createGridPanel(){
        JPanel gridPanel = new JPanel();

        gridPanel.setLayout(new GridLayout(7, 7));
        gridPanel.setBounds(573, 118, 672, 672);
        gridPanel.setOpaque(false);

        return gridPanel;
    }

    public static JPanel createBoardPanel(JPanel gridPanel, JLabel boardImageLabel){
        JPanel boardPanel = new JPanel();

        boardPanel.setLayout(null);
        boardPanel.add(gridPanel);
        boardPanel.add(boardImageLabel);
        boardPanel.setBackground(Color.black);

        return boardPanel;
    }

    public static JPanel createMessagePanel(JLabel messageLabel){
        JPanel messagePanel = new JPanel();

        messagePanel.setBackground(Color.black);
        messagePanel.add(messageLabel);

        return messagePanel;
    }

    public static JPanel createTimerPanel(JLabel timerLabel, JProgressBar jProgressBar){
        JPanel timerPanel = new JPanel();

        timerPanel.setLayout(new BorderLayout());
        timerPanel.setBackground(Color.BLACK);
        timerPanel.add(timerLabel, BorderLayout.CENTER);
        timerPanel.add(jProgressBar, BorderLayout.SOUTH);

        return timerPanel;
    }

    public static JPanel createFormPanel(JTextField textField, JButton jButton){
        JPanel formPanel = new JPanel();

        formPanel.setBackground(Color.BLACK);
        formPanel.setPreferredSize(new Dimension(100, 100));
        formPanel.add(textField);
        formPanel.add(jButton);

        return formPanel;
    }

    public static ArrayList<MessageJLabel> createJLables(){
        ArrayList<MessageJLabel> myJLabels = new ArrayList<>();

        myJLabels.add(new MessageJLabel("00"));
        myJLabels.add(new MessageJLabel("01"));
        myJLabels.add(new MessageJLabel("02"));
        myJLabels.add(new MessageJLabel("03"));
        myJLabels.add(new MessageJLabel("04"));
        myJLabels.add(new MessageJLabel("05"));
        myJLabels.add(new MessageJLabel("06"));

        myJLabels.add(new MessageJLabel("10"));
        myJLabels.add(new MessageJLabel("11"));
        myJLabels.add(new MessageJLabel("12"));
        myJLabels.add(new MessageJLabel("13"));
        myJLabels.add(new MessageJLabel("14"));
        myJLabels.add(new MessageJLabel("15"));
        myJLabels.add(new MessageJLabel("16"));

        myJLabels.add(new MessageJLabel("20"));
        myJLabels.add(new MessageJLabel("21"));
        myJLabels.add(new MessageJLabel("22"));
        myJLabels.add(new MessageJLabel("23"));
        myJLabels.add(new MessageJLabel("24"));
        myJLabels.add(new MessageJLabel("25"));
        myJLabels.add(new MessageJLabel("26"));

        myJLabels.add(new MessageJLabel("30"));
        myJLabels.add(new MessageJLabel("31"));
        myJLabels.add(new MessageJLabel("32"));
        myJLabels.add(new MessageJLabel("33"));
        myJLabels.add(new MessageJLabel("34"));
        myJLabels.add(new MessageJLabel("35"));
        myJLabels.add(new MessageJLabel("36"));

        myJLabels.add(new MessageJLabel("40"));
        myJLabels.add(new MessageJLabel("41"));
        myJLabels.add(new MessageJLabel("42"));
        myJLabels.add(new MessageJLabel("43"));
        myJLabels.add(new MessageJLabel("44"));
        myJLabels.add(new MessageJLabel("45"));
        myJLabels.add(new MessageJLabel("46"));

        myJLabels.add(new MessageJLabel("50"));
        myJLabels.add(new MessageJLabel("51"));
        myJLabels.add(new MessageJLabel("52"));
        myJLabels.add(new MessageJLabel("53"));
        myJLabels.add(new MessageJLabel("54"));
        myJLabels.add(new MessageJLabel("55"));
        myJLabels.add(new MessageJLabel("56"));

        myJLabels.add(new MessageJLabel("60"));
        myJLabels.add(new MessageJLabel("61"));
        myJLabels.add(new MessageJLabel("62"));
        myJLabels.add(new MessageJLabel("63"));
        myJLabels.add(new MessageJLabel("64"));
        myJLabels.add(new MessageJLabel("65"));
        myJLabels.add(new MessageJLabel("66"));

        return myJLabels;
    }
}
