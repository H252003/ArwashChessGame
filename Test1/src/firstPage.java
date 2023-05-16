import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class firstPage extends JFrame {


    //    check if it is a guest
    public boolean guest = false;
    //
    public firstPage() {
        JFrame frame=new JFrame();
        frame.setSize(800,800);
        frame.setResizable(false);
        frame.setTitle("Arwash Chess Game");
        ImageIcon logo=new ImageIcon("Test1/src/resources/logo.jpg");
        frame.setIconImage(logo.getImage());
        JLabel label=new JLabel();
        ImageIcon cover=new ImageIcon("Test1/src/resources/chess game44_804x800.jpeg");
        label.setIcon(cover);
        label.setBounds(250,100,400,100);
        JButton loginButton=new JButton("Login");
        loginButton.setBounds(150,410,200,50);
        loginButton.setFont(new Font("Callibri", Font.BOLD, 22));
        loginButton.setBackground(new Color(210, 209, 209));
        loginButton.setForeground(new Color(0, 0, 0));
        loginButton.setFocusable(false);
        JButton guestButton=new JButton("Guest");
        guestButton.setBounds(400,410,200,50);
        guestButton.setFont(new Font("Callibri", Font.BOLD, 22));
        guestButton.setBackground(new Color(210, 209, 209));
        guestButton.setForeground(new Color(0, 0, 0));
        guestButton.setFocusable(false);
        label.add(guestButton);
        label.add(loginButton);
        frame.add(label);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new login_page();
                frame.dispose(); //close it to open login page
            }
        });
        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                guest = true; //to know that he is a guest
                new new_game(guest);      //guest
                frame.dispose();                  //close it to open guest page
            }
        });
    }



    public static void main(String args[])
    {
        new firstPage();
    }
}
