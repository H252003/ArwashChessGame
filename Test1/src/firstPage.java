import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class firstPage extends JFrame {

    private JLabel game_lab = new JLabel();
    public boolean guest = false;

    public firstPage() {
        super("Swing App");

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel gameLabel = new JLabel("team twooo chess game");
        gameLabel.setFont(new Font("Algerian", Font.PLAIN, 48));
        GridBagConstraints gbcGameLabel = new GridBagConstraints();
        gbcGameLabel.gridx = 0;
        gbcGameLabel.gridy = 0;
        gbcGameLabel.gridwidth = 2;
        gbcGameLabel.anchor = GridBagConstraints.CENTER;
        gbcGameLabel.fill = GridBagConstraints.NONE;
        gbcGameLabel.insets.top = 20;
        gbcGameLabel.insets.left = 20;
        gbcGameLabel.insets.right = 20;
        panel.add(gameLabel, gbcGameLabel);

        JButton loginButton = new JButton("login");
        loginButton.setFont(new Font("JetBrains Mono", Font.PLAIN, 28));
        GridBagConstraints gbcLoginButton = new GridBagConstraints();
        gbcLoginButton.gridx = 0;
        gbcLoginButton.gridy = 1;
        gbcLoginButton.gridwidth = 1;
        gbcLoginButton.anchor = GridBagConstraints.CENTER;
        gbcLoginButton.fill = GridBagConstraints.HORIZONTAL;
        gbcLoginButton.insets.top = 20;
        gbcLoginButton.insets.left = 20;
        gbcLoginButton.insets.right = 10;
        panel.add(loginButton, gbcLoginButton);

        JButton guestButton = new JButton("guest");
        guestButton.setFont(new Font("JetBrains Mono", Font.PLAIN, 28));
        GridBagConstraints gbcGuestButton = new GridBagConstraints();
        gbcGuestButton.gridx = 1;
        gbcGuestButton.gridy = 1;
        gbcGuestButton.gridwidth = 1;
        gbcGuestButton.anchor = GridBagConstraints.CENTER;
        gbcGuestButton.fill = GridBagConstraints.HORIZONTAL;
        gbcGuestButton.insets.top = 20;
        gbcGuestButton.insets.left = 10;
        gbcGuestButton.insets.right = 20;
        panel.add(guestButton, gbcGuestButton);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.pack();
        this.setVisible(true);

        setTitle("ahleeen");
        setContentPane(panel);
        setSize(700, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open Login Page !!");
                login_page log = new login_page();
                dispose(); //close it to open login page
            }
        });
        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open New Game Page!!");
                guest = true; //to know that he is a guest
                new_game gam = new new_game(guest);      //guest
                dispose();                  //close it to open guest page
            }
        });
    }



    public static void main(String args[])
    {
        SwingUtilities.invokeLater(() -> new firstPage());
    }
}

