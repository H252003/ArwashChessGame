import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Status extends JFrame implements ActionListener {
    JFrame frame=new JFrame();
    JLabel label = new JLabel();
    JButton back_btn = new JButton("BACK");
    JPanel but = new JPanel(new BorderLayout(1,1));
    Status(){
        frame.setSize(800,800);
        frame.setTitle("Chess Game");
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(0x494949));
        label.setText("Status");
        label.setBounds(300, 200, 400, 100);
        label.setForeground(Color.white);
        label.setFont(new Font("B",Font.BOLD,48));
        Object[][] data = {
                {"Player1", 0, "00:00", "Playing..."},
                {"Player2", 0, "00:00", "Playing..."},
        };
        String[] columnNames = {"Player", "Score", "Time", "Status"};
        JTable table = new JTable(data, columnNames);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        JScrollPane scrollPane = new JScrollPane(table);

        this.add(but);
        but.add(back_btn,BorderLayout.SOUTH);
        back_btn.setForeground(Color.black);
        back_btn.setFont(new Font("B",Font.BOLD,20));

        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.add(scrollPane);
        frame.setVisible(true);
        back_btn.addActionListener(this);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== back_btn){
            frame.dispose();
            Main_jr jr = new Main_jr();
        }
    }

}

