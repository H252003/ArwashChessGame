import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import javax.swing.JOptionPane;
public class game extends JFrame {
    private static final int boardSize = 400;
    private static final int squareSize = boardSize / 8;
    public static JPanel board;
    public static JPanel whiteOut;
    public static JPanel blackOut;
    public static square[][] squares;
    public static TimerLabel Timer1 = new TimerLabel();
    public static TimerLabel Timer2 = new TimerLabel();
    public static NameLabel WhiteName;
    public static NameLabel BlackName;

//    public static chckScan CheckScan = new chckScan(this);


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

    public static chckScan checkKing = new chckScan();
    game(String whiteName, String blackName, String time) throws IOException {

        this.setTitle("Arwash Chess Game");
        this.setVisible(true);
        this.setResizable(true);
        this.setSize(900, 900);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getMinimumSize();
        this.getContentPane().setBackground(new Color(255, 255, 255));
        board = new JPanel(new GridLayout(8, 8));
        whiteOut = new JPanel(new GridLayout(8 , 2));
        blackOut = new JPanel(new GridLayout(8 , 2));
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
                squares[i][j] = new square(i, j);
                squares[i][j].setPreferredSize(new Dimension(squareSize, squareSize));

//                if(i==7 && j==7)
//                    squares[i][j] = new square(new King("black", i, j));
//                if(i==5 && j==5)
//                    squares[i][j] = new square(new Queen("white", i, j));
//                if(i==3 && j==6)
//                    squares[i][j] = new square(new Rock("white", i, j));
//                if(i==0 && j==0)
//                    squares[i][j] = new square(new King("white", i, j));

                if (i == 6) {
                    squares[i][j] = new square(new Pawn("black", i, j));
                } else if (i == 7) {
                    if (j == 3)
                        squares[i][j] = new square(new Queen("black", i, j));
                    if (j == 4)
                        squares[i][j] = new square(new King("black", i, j));
                    if (j == 1)
                        squares[i][j] = new square(new Knight("black", i, j));
                    if (j == 6)
                        squares[i][j] = new square(new Knight("black", i, j));
                    if (j == 2)
                        squares[i][j] = new square(new Bishop("black", i, j));
                    if (j == 5)
                        squares[i][j] = new square(new Bishop("black", i, j));
                    if (j == 0)
                        squares[i][j] = new square(new Rock("black", i, j));
                    if (j == 7)
                        squares[i][j] = new square(new Rock("black", i, j));
                } else if (i == 1) {
                    squares[i][j] = new square(new Pawn("white", i, j));
                } else if (i == 0) {
                    if (j == 3)
                        squares[i][j] = new square(new Queen("white", i, j));
                    if (j == 4)
                        squares[i][j] = new square(new King("white", i, j));
                    if (j == 1)
                        squares[i][j] = new square(new Knight("white", i, j));
                    if (j == 0)
                        squares[i][j] = new square(new Rock("white", i, j));
                    if (j == 6)
                        squares[i][j] = new square(new Knight("white", i, j));
                    if (j == 2)
                        squares[i][j] = new square(new Bishop("white", i, j));
                    if (j == 5)
                        squares[i][j] = new square(new Bishop("white", i, j));
                    if (j == 7)
                        squares[i][j] = new square(new Rock("white", i, j));
                }
                int row = i;
                int col = j;
                if ((i + j) % 2 == 0) {
                    squares[i][j].setBackground(new Color(0, 0, 0));
                } else {
                    squares[i][j].setBackground(new Color(219, 107, 107));
                }
                board.add(squares[i][j]);


            }
        }
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


        Timer1 = new TimerLabel(timer1, time);
        Timer2 = new TimerLabel(timer2, time);
        WhiteName = new NameLabel(name2, whiteName, "white");
        BlackName = new NameLabel(name1, blackName, "black");

        // Check if it's the white player's turn
        if (TimerLabel.whiteTurn) {
            Timer2.startTimer();
            Timer1.stopTimer();
            // Check if the time has run out for the black player

        }

        // Check if it's the black player's turn
        else {
            Timer1.startTimer();
            Timer2.stopTimer();
            // Check if the time has run out for the white player
        }
    }



    public static void endGame(boolean turn, String sayIt){
        String winner = NameLabel.setWinner(WhiteName, BlackName, turn);
        String time = TimerLabel.elapsedTime(Timer1,Timer2);

        JOptionPane.showMessageDialog(null, sayIt);
        new_game.endData(winner, time);
    }





    //    Piece getKing(Icon icon)
//    {
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//
//                if(squares[i][j].piece.icon == icon)
//                    return squares[i][j].piece;
//            }
//            }
//        return null;
//    }
    public static square findKing(boolean isWhite  ) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (game.squares[i][j].piece != null && (isWhite == Piece.isWhite(game.squares[i][j].piece.color)) && (game.squares[i][j].piece.getClass() == King.class))
                {
                    return game.squares[i][j];
                }
            }
        }
        return null;
    }

    public static void paintComp(square[][] squares) {
        if(square.oldPiece != null ) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {

                    if (square.oldPiece.isValidMove(squares, i, j)) {
//                        if(square.oldPiece.isSameTeam(square.oldPiece , squares[i][j].piece))
//                            squares[i][j].setBackground(new Color(255, 0, 0));
                        if(squares[i][j].piece == null)
                            squares[i][j].setBackground(new Color(12, 253, 1));
                        else
                            squares[i][j].setBackground(new Color(253, 240, 1));
                    }
                    // color same team pieces with red
                    else if(square.oldPiece.pieceCanMove(i,j) && square.oldPiece.isSameTeam(squares[i][j].piece))
                        squares[i][j].setBackground(new Color(253, 0, 2));
                }

            }

        }
    }




    public static void getBack(square[][] squares) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    squares[i][j].setBackground(new Color(0, 0, 0));
                } else {
                    squares[i][j].setBackground(new Color(219, 107, 107));
                }

            }
        }

    }
//    public static boolean isValidMove(Piece oldPiece ,square[][] squares, int x, int y){
//        if(oldPiece.isSameTeam(oldPiece,squares[x][y].piece)){
//
//            return false ;
//        }
//        if(!oldPiece.pieceCanMove(x,y)) {
//            return false;
//        }
//        if(oldPiece.moveHitPiece(x,y))
//            return false;
//        if(checkKing.isKingChecked(squares[x][y],x,y, oldPiece.getClass() == King.class))
//        return false;
//
//        return true ;
//    }

//    public static boolean validity(square[][] squares, int x, int y, String color) {
//
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//
//                if (squares[x][y].piece.isValidMove(squares, i, j)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }


    public static void main(String args[]) throws IOException {
        game g = new game("A", "B", "4");

    }


}



