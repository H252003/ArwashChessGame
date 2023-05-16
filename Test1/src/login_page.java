import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login_page extends JFrame {
    public static String user;

    public JButton BACKButton, LOGINButton;

    public login_page() {
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setResizable(false);
        frame.setTitle("Arwash Chess Game");
        ImageIcon logo =new ImageIcon("Test1/src/resources/logo.jpg");
        frame.setIconImage(logo.getImage());
        JLabel label=new JLabel();
        ImageIcon cover=new ImageIcon("Test1/src/resources/login44_806x800.jpeg");
        label.setIcon(cover);
        label.setBounds(250,100,400,100);

        JLabel Username = new JLabel("Username:");
        Username.setBounds(95, 272, 300, 100);
        Username.setFont(new Font("Callibri", Font.BOLD, 25));
        Username.setForeground(new Color(247, 252, 252));
        label.add(Username);

        JLabel Password = new JLabel("Password:");
        Password.setBounds(95, 350, 300, 100);
        Password.setFont(new Font("Callibri", Font.BOLD, 25));
        Password.setForeground(new Color(247, 252, 252));
        label.add(Password);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(228, 300, 260, 40);
        usernameField.setFont(new Font("Callibri", Font.BOLD, 15));
        usernameField.setForeground(Color.black);
        label.add(usernameField);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(228, 380, 260, 40);
        passwordField.setFont(new Font("Callibri", Font.BOLD, 15));
        passwordField.setForeground(Color.black);
        label.add(passwordField);

        BACKButton =new JButton("Back");
        BACKButton.setBounds(150,500,150,50);
        BACKButton.setFont(new Font("Callibri", Font.BOLD, 20));
        BACKButton.setBackground(new Color(210, 209, 209));
        BACKButton.setForeground(new Color(0, 0, 0));
        BACKButton.setFocusable(false);

        LOGINButton =new JButton("Login");
        LOGINButton.setBounds(340,500,150,50);
        LOGINButton.setFont(new Font("Callibri", Font.BOLD, 20));
        LOGINButton.setBackground(new Color(210, 209, 209));
        LOGINButton.setForeground(new Color(0, 0, 0));
        LOGINButton.setFocusable(false);

        label.add(LOGINButton);
        label.add(BACKButton);

        frame.add(label);
        frame.setVisible(true);


        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new firstPage();
                frame.dispose();

            }
        });
        LOGINButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                user = usernameField.getText();
                String pass = passwordField.getText();

                if (User.LogInData(user,pass)) {

                    frame.dispose();
                    new Main_jr();
                }
                else JOptionPane.showMessageDialog(null, "Invalid Password or Username!");

            }
        });
    }
}

