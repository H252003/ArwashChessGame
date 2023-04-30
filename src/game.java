import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;


public class game extends JFrame {
    private static final int boardSize=400;
    private static final int squareSize = boardSize/8 ;
    private JPanel board ;
    private JButton[][] squares ;

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
        squares = new JButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new JButton();
                squares[i][j].setPreferredSize(new Dimension(squareSize, squareSize));
                if (i == 6) {
                    Icon chess_black_pawn = new ImageIcon(getClass().getClassLoader().getResource("resources/black_pawn-removebg-preview (1).png"));
                    squares[i][j] = new JButton(chess_black_pawn);
                }
                else if (i==7) {
                    Icon chess_black_queen= new ImageIcon(getClass().getClassLoader().getResource("resources/queen_black-removebg-preview (3).png"));
                    squares[i][3]= new JButton(chess_black_queen);
                    Icon chess_black_king=new ImageIcon(getClass().getClassLoader().getResource("resources/kingo-removebg-preview (1).png"));
                    squares[i][4]= new JButton(chess_black_king);
                    Icon chess_black_knight= new ImageIcon(getClass().getClassLoader().getResource("resources/black_knight-removebg-preview (1) (1).png"));
                    squares[i][1]= new JButton(chess_black_knight);
                    squares[i][6]= new JButton(chess_black_knight);
                    Icon chess_black_bishop= new ImageIcon(getClass().getClassLoader().getResource("resources/bblack_bishop-removebg-preview (1).png"));
                    squares[i][2]= new JButton(chess_black_bishop);
                    squares[i][5]= new JButton(chess_black_bishop);
                    Icon chess_black_rock= new ImageIcon(getClass().getClassLoader().getResource("resources/rock_black-removebg-preview (1).png"));
                    squares[i][0]= new JButton(chess_black_rock);
                    squares[i][7]=new JButton(chess_black_rock);
                } else if (i==1) {
                    Icon chess_white_pawn = new ImageIcon(getClass().getClassLoader().getResource("resources/chess_white_pawn-removebg-preview (1).png"));
                    squares[i][j] = new JButton(chess_white_pawn);}
                else if (i==0) {
                    Icon chess_white_queen= new ImageIcon(getClass().getClassLoader().getResource("resources/chess-white-queen-removebg-preview (2).png"));
                    squares[i][3]= new JButton(chess_white_queen);
                    Icon chess_white_king=new ImageIcon(getClass().getClassLoader().getResource("resources/white_king2-removebg-preview (2).png"));
                    squares[i][4]= new JButton(chess_white_king);
                    Icon chess_white_knight= new ImageIcon(getClass().getClassLoader().getResource("resources/chess-white-knight-removebg-preview (2).png"));
                    squares[i][1]= new JButton(chess_white_knight);
                    squares[i][6]= new JButton(chess_white_knight);
                    Icon chess_white_bishop= new ImageIcon(getClass().getClassLoader().getResource("resources/wwhite_bishop-removebg-preview (1).png"));
                    squares[i][2]= new JButton(chess_white_bishop);
                    squares[i][5]= new JButton(chess_white_bishop);
                    Icon chess_white_rock= new ImageIcon(getClass().getClassLoader().getResource("resources/rock_white-removebg-preview (2).png"));
                    squares[i][0]= new JButton(chess_white_rock);
                    squares[i][7]=new JButton(chess_white_rock);
                }
                int row = i;
                int col = j;
                if ((i + j) % 2 == 0) {
                    squares[i][j].setBackground(new Color(0, 0, 0));
                } else {
                    squares[i][j].setBackground(new Color(219, 107, 107));
                }
                board.add(squares[i][j]);
            }}}}


