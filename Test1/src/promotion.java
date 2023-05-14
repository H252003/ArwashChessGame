
import javax.swing.*;
import javax.swing.JFrame ;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;

public class promotion extends JFrame implements ActionListener {
    JButton queen= new JButton("queen");
    JButton rock= new JButton("rock");
    JButton bishop= new JButton("bishop");
    JButton knight= new JButton("knight");
    JFrame frame = new JFrame();
    promotion(){
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setTitle("Chess Game");
        frame.getContentPane().setBackground(new Color(0x494949));
        frame.setVisible(true);


    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
