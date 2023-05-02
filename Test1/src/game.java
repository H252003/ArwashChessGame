import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class game extends JFrame {
    private static final int boardSize=400;
    private static final int squareSize = boardSize/8 ;
    private JPanel board ;
    public static square[][] squares ;

    //    method to set margins
    private int calculateMarginSize() {
        int marginSize = 0;
        int width = getWidth();
        int height = getHeight();

        if (width > height) {
            marginSize = (width - height) / 2;
        }

        return marginSize;
    }


    game() {

        this.setTitle("Arwash Chess Game");
        this.setVisible(true);
        this.setResizable(true);
        this.setSize(600, 600);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getMinimumSize();
        this.getContentPane().setBackground(new Color(255, 255, 255));
        board = new JPanel(new GridLayout(8, 8));
        JPanel whiteOut = new JPanel();
        JPanel blackOut = new JPanel();
        JPanel player1 = new JPanel();
        JPanel player2 = new JPanel();
        JLabel name1 = new JLabel("player1 name");
        JLabel name2 = new JLabel("player2 name");
        JLabel timer1 = new JLabel("timer 1");
        JLabel timer2 = new JLabel("timer 2");
        player1.add(name1);
        player1.add(timer1);
        player2.add(timer2);
        player2.add(name2);
        board.setBackground(new Color(255, 255, 255));
        whiteOut.setBackground(Color.black);
        blackOut.setBackground(new Color(255, 255, 255));
        player1.setBackground(new Color(128, 128, 128));
        player2.setBackground(new Color(128, 128, 128));
        board.setPreferredSize(new Dimension(400, 400));
        whiteOut.setPreferredSize(new Dimension(50, 50));
        blackOut.setPreferredSize(new Dimension(50, 50));
        player1.setPreferredSize(new Dimension(50, 50));
        player2.setPreferredSize(new Dimension(50, 50));
        this.add(board, BorderLayout.CENTER);
        this.add(whiteOut, BorderLayout.WEST);
        this.add(blackOut, BorderLayout.EAST);
        this.add(player1, BorderLayout.SOUTH);
        this.add(player2, BorderLayout.NORTH);
        squares = new square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new square();
                squares[i][j].setPreferredSize(new Dimension(squareSize, squareSize));
                if (i == 6) {
                    squares[i][j] = new square(new Pawn("black",j,i));
                }
                else if (i==7) {

                    squares[i][3]= new square(new Queen("black",3,i));
                    squares[i][4]= new square(new King("black",4,i));
                    squares[i][1]= new square(new Knight("black",1,i));
                    squares[i][6]= new square(new Knight("black",6,i));
                    squares[i][2]= new square(new Bishop("black",2,i));
                    squares[i][5]= new square(new Bishop("black",5,i));
                    squares[i][0]= new square(new Rock("black",0,i));
                    squares[i][7]=new square(new Rock("black",7,i));
                } else if (i==1) {
                   squares[i][j] = new square(new Pawn("white",j,i));}
                else if (i==0) {
                    squares[i][3]= new square(new Queen("white",3,i));
                    squares[i][4]= new square(new King("white",4,i));
                    squares[i][1]= new square(new Knight("white",1,i));
                    squares[i][6]= new square(new Knight("white",6,i));
                    squares[i][2]= new square(new Bishop("white",2,i));
                    squares[i][5]= new square(new Bishop("white",5,i));
                    squares[i][0]= new square(new Rock("white",0,i));
                    squares[i][7]=new square(new Rock("white",7,i));
                }
                int row = i;
                int col = j;
                if ((i + j) % 2 == 0) {
                    squares[i][j].setBackground(new Color(0, 0, 0));
                } else {
                    squares[i][j].setBackground(new Color(219, 107, 107));
                }
                board.add(squares[i][j]);
            }}
        // add component listener to maintain square ratio of board
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int marginSize = calculateMarginSize();
                whiteOut.setPreferredSize(new Dimension(marginSize, 0));
                blackOut.setPreferredSize(new Dimension(marginSize, 0));
                revalidate();
            }
        });

    }
    public static void main(String args[])
    {
        new game();
    }

}


