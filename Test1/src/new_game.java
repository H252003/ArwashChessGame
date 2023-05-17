
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class new_game extends JFrame {

    public static String gameData;
    public static boolean guest;
    public static String whitePlayer ;
    public static String blackPlayer ;
    public static String Winner;
    public static String status2;
    public static String Time;
    //method to check password
    public static boolean isNotNumeric(String strNum) {
        if (strNum == null) {
            return true;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    } public new_game(boolean if_guest)
    {
        // to go back to the known
        guest = if_guest;

        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setTitle("Arwash Chess Game");
        ImageIcon image = new ImageIcon("resources/logo.jpg");
        frame.setIconImage(image.getImage());

        JLabel label = new JLabel();
        ImageIcon cover = new ImageIcon("resources/new page44_802x800.jpeg");
        label.setIcon(cover);
        label.setBounds(250, 100, 400, 100);

        JLabel White = new JLabel("White Player: ");
        White.setBounds(200, 100, 300, 100);
        White.setFont(new Font("Callibri", Font.BOLD, 20));
        White.setForeground(new Color(247, 252, 252));
        label.add(White);

        JLabel Black = new JLabel("Black Player: ");
        Black.setBounds(200, 170, 300, 100);
        Black.setFont(new Font("Callibri", Font.BOLD, 20));
        Black.setForeground(new Color(247, 252, 252));
        label.add(Black);

        JLabel Timer = new JLabel("Timer: ");
        Timer.setBounds(230, 220, 300, 100);
        Timer.setFont(new Font("Callibri", Font.BOLD, 22));
        Timer.setForeground(new Color(247, 252, 252));
        label.add(Timer);

        JTextField BlackPlayer = new JTextField();
        BlackPlayer.setBounds(328, 200, 260, 40);
        BlackPlayer.setFont(new Font("Callibri", Font.BOLD, 15));
        BlackPlayer.setForeground(Color.black);
        label.add(BlackPlayer);

        JTextField WhitePlayer = new JTextField();
        WhitePlayer.setBounds(328, 140, 260, 40);
        WhitePlayer.setFont(new Font("Callibri", Font.BOLD, 15));
        WhitePlayer.setForeground(Color.black);
        label.add(WhitePlayer);

        JTextField Time = new JTextField();
        Time.setBounds(328, 260, 260, 40);
        Time.setFont(new Font("Callibri", Font.BOLD, 15));
        Time.setForeground(Color.black);
        label.add(Time);

        JButton BACKButton = new JButton("Back");
        BACKButton.setBounds(328, 350, 120, 50);
        BACKButton.setFont(new Font("Callibri", Font.BOLD, 24));
        BACKButton.setBackground(new Color(210, 209, 209));
        BACKButton.setForeground(new Color(0, 0, 0));
        BACKButton.setFocusable(false);

        JButton start = new JButton("Start");
        start.setBounds(470, 350, 120, 50);
        start.setFont(new Font("Callibri", Font.BOLD, 24));
        start.setBackground(new Color(210, 209, 209));
        start.setForeground(new Color(0, 0, 0));
        start.setFocusable(false);

        label.add(start);
        label.add(BACKButton);
        frame.add(label);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String timer =Time.getText();

                if(isNotNumeric(timer) || Integer.parseInt(timer) < 1)         // check if timer is numbers only
                {

                    JOptionPane.showMessageDialog(null, "input correct timer");

                }
                else {


                    whitePlayer = WhitePlayer.getText();
                    blackPlayer = BlackPlayer.getText();

                    frame.dispose();
                    try {
                        new game(whitePlayer, blackPlayer,timer);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if(if_guest)        //GO back to login or guest page if its a guest
                {
                    firstPage guest_ = new firstPage();
                    frame.dispose();   //close new game page
                }
                else{        //this is a user

                    Main_jr user = new Main_jr();
                    frame. dispose();   //close new game page

                }
            }
        });

    }


    public static void endData(String winner, String time,String status) {
        Winner = winner;
        Time = time;
        status2=status;
    }

}



