import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class new_game extends JFrame {

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
    }
    private JLabel white_lab = new JLabel();
    private JTextField white_name = new JTextField();
    private JLabel black_lab =new JLabel();
    private JTextField black_name =new JTextField();
    private JLabel timer_lab=new JLabel();
    private JTextField timer_value=new JTextField();
    private JButton start_btn=new JButton("Start");
    private JButton back_btn=new JButton("Back");

    public new_game(boolean if_guest)
    {

        // Set up the JFrame
        setTitle("New Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        // Create a new JPanel with a GridLayout
        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        // Create the components
        white_lab = new JLabel("white");
        white_lab.setFont(new Font("Algerian", Font.PLAIN, 20));
        white_name = new JTextField(20);
        black_lab = new JLabel("black");
        black_lab.setFont(new Font("Algerian", Font.PLAIN, 20));
        black_name = new JTextField(20);
        timer_lab = new JLabel("time");
        timer_lab.setFont(new Font("Algerian", Font.PLAIN, 20));
        timer_value = new JTextField(20);
        start_btn = new JButton("start");
        back_btn = new JButton("back");

        // Add the components to the main panel using GridBagLayout
        GridBagConstraints constraints = new GridBagConstraints();

        // Add white label and text field
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
        mainPanel.add(white_lab, constraints);

        constraints.gridx = 1;
        mainPanel.add(white_name, constraints);

        // Add black label and text field
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(black_lab, constraints);

        constraints.gridx = 1;
        mainPanel.add(black_name, constraints);

        // Add timer label and text field
        constraints.gridx = 0;
        constraints.gridy = 2;
        mainPanel.add(timer_lab, constraints);

        constraints.gridx = 1;
        mainPanel.add(timer_value, constraints);

        // Add start and back buttons
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        mainPanel.add(back_btn, constraints);

        constraints.gridx = 1;
        mainPanel.add(start_btn, constraints);

        // Add the main panel to the content pane
        getContentPane().add(mainPanel);
        // Add the JPanel to the JFrame
        add(mainPanel);

        // Set the JFrame visible
        setVisible(true);


        this.start_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String timer = new_game.this.timer_value.getText();

                if(isNotNumeric(timer))         // check if timer is numbers only
                {
                    System.out.println("input correct timer");
                    JOptionPane.showMessageDialog(null, "input correct timer");

                }
                else {

                    System.out.println("White player is: " + new_game.this.white_name.getText());
                    System.out.println("Black player is: " + new_game.this.black_name.getText());
                    System.out.println("Time allowed is: " + timer);
                    dispose();
                    game Game = new game(timer);
                }
            }
        });
        this.back_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if(if_guest)        //GOOO back to login or guest page if its a guest
                {
                    System.out.println("this is a guest!!");
                    firstPage guest_ = new firstPage();
                    guest_.show();   //show the first page
                    dispose();   //close new game page
                }
                else{        //this is a user
                    System.out.println("this is a user !!!!!");

                    Main_jr user = new Main_jr();
                    user.show();   //show the first page
                    dispose();   //close new game page

                }
            }
        });
    }


    public static void main(String args[])
    {
        new new_game(true);
    }
}



