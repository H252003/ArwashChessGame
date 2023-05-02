import javax.swing.*;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login_page extends JFrame {

    public JButton BACKButton, LOGINButton;

    public login_page() {
        super("Swing App");

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel loginLabel = new JLabel("LOGIN");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 24));
        GridBagConstraints gbcLoginLabel = new GridBagConstraints();
        gbcLoginLabel.gridx = 0;
        gbcLoginLabel.gridy = 0;
        gbcLoginLabel.gridwidth = 2;
        gbcLoginLabel.anchor = GridBagConstraints.CENTER;
        gbcLoginLabel.fill = GridBagConstraints.NONE;
        gbcLoginLabel.insets.top = 20;
        gbcLoginLabel.insets.left = 20;
        gbcLoginLabel.insets.right = 20;
        panel.add(loginLabel, gbcLoginLabel);

        JLabel usernameLabel = new JLabel("username");
        GridBagConstraints gbcUsernameLabel = new GridBagConstraints();
        gbcUsernameLabel.gridx = 0;
        gbcUsernameLabel.gridy = 1;
        gbcUsernameLabel.anchor = GridBagConstraints.WEST;
        gbcUsernameLabel.fill = GridBagConstraints.NONE;
        gbcUsernameLabel.insets.top = 10;
        gbcUsernameLabel.insets.left = 20;
        gbcUsernameLabel.insets.bottom = 10;
        panel.add(usernameLabel, gbcUsernameLabel);

        JTextField usernameField = new JTextField(20);
        GridBagConstraints gbcUsernameField = new GridBagConstraints();
        gbcUsernameField.gridx = 1;
        gbcUsernameField.gridy = 1;
        gbcUsernameField.anchor = GridBagConstraints.WEST;
        gbcUsernameField.fill = GridBagConstraints.HORIZONTAL;
        gbcUsernameField.insets.top = 10;
        gbcUsernameField.insets.left = 0;
        gbcUsernameField.insets.bottom = 10;
        panel.add(usernameField, gbcUsernameField);

        JLabel passwordLabel = new JLabel("password");
        GridBagConstraints gbcPasswordLabel = new GridBagConstraints();
        gbcPasswordLabel.gridx = 0;
        gbcPasswordLabel.gridy = 2;
        gbcPasswordLabel.anchor = GridBagConstraints.WEST;
        gbcPasswordLabel.fill = GridBagConstraints.NONE;
        gbcPasswordLabel.insets.top = 10;
        gbcPasswordLabel.insets.left = 20;
        gbcPasswordLabel.insets.bottom = 10;
        panel.add(passwordLabel, gbcPasswordLabel);

        JPasswordField passwordField = new JPasswordField(20);
        GridBagConstraints gbcPasswordField = new GridBagConstraints();
        gbcPasswordField.gridx = 1;
        gbcPasswordField.gridy = 2;
        gbcPasswordField.anchor = GridBagConstraints.WEST;
        gbcPasswordField.fill = GridBagConstraints.HORIZONTAL;
        gbcPasswordField.insets.top = 10;
        gbcPasswordField.insets.left = 0;
        gbcPasswordField.insets.bottom = 10;
        panel.add(passwordField, gbcPasswordField);

        BACKButton = new JButton("BACK");
        GridBagConstraints gbcBackButton = new GridBagConstraints();
        gbcBackButton.gridx = 0;
        gbcBackButton.gridy = 3;
        gbcBackButton.anchor = GridBagConstraints.CENTER;
        gbcBackButton.fill = GridBagConstraints.HORIZONTAL;
        gbcBackButton.insets.top = 10;
        gbcBackButton.insets.left = 20;
        gbcBackButton.insets.right = 10;
        panel.add(BACKButton, gbcBackButton);

        LOGINButton = new JButton("LOGIN");
        GridBagConstraints gbcLoginButton = new GridBagConstraints();
        gbcLoginButton.gridx = 1;
        gbcLoginButton.gridy = 3;
        gbcLoginButton.anchor = GridBagConstraints.CENTER;
        gbcLoginButton.fill = GridBagConstraints.HORIZONTAL;
        gbcLoginButton.insets.top = 10;
        gbcLoginButton.insets.left = 10;
        gbcLoginButton.insets.right = 20;
        panel.add(LOGINButton, gbcLoginButton);

        getContentPane().add(panel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        setTitle("looogggg");
        setContentPane(panel);
        setSize(700, 400);
        setVisible(true);


        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstPage f = new firstPage();
                dispose();

            }
        });
        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = usernameField.getText();
                String pass = passwordField.getText();
                if (user.equals("admin")) {
                    JOptionPane.showMessageDialog(null, "helloooo!");
                    System.out.println("logged in page!!");
                    dispose();
                    new Main_jr();
                }
            }
        });
    }
}