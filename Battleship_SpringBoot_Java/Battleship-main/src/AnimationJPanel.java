import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationJPanel extends JPanel implements ActionListener {
    final int PANEL_WIDTH = 100;
    final int PANEL_HEIGHT = 30;
    Image battle;
    Timer timer;
    int xVelocity = 1;
    int x = 0;
    int y = 0;

    AnimationJPanel(){
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        battle = new ImageIcon("battleship.png").getImage();
        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(battle, x, y, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(x >= 2000 || x < 0){
            xVelocity = xVelocity * -1;
        }
        x = x + xVelocity;
        repaint();
    }
}
