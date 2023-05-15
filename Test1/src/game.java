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

    public static game gameDispose;
    public static JLayeredPane whiteOut;
    public static JLayeredPane blackOut;
    //     JLayeredPane player1;
//     JLayeredPane player2;
//    JLayeredPane time1;
//     JLayeredPane time2;
    JLayeredPane base;
    public static square[][] squares;
    public static TimerLabel TimerB = new TimerLabel();
    public static TimerLabel TimerW = new TimerLabel();
    public static NameLabel WhiteName;
    public static NameLabel BlackName;
    public static boolean gameEnded;

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

        this.setResizable(false);
        this.setSize(712, 632);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getMinimumSize();
        this.setLayout(null);


        this.getContentPane().setBackground(new Color(255, 255, 255));
        board=new JPanel();
        whiteOut=new JLayeredPane();
        blackOut=new JLayeredPane();
        base = new JLayeredPane();
//        player1 = new JLayeredPane();
//        player2 = new JLayeredPane();
//        time2=new JLayeredPane();
//        time1=new JLayeredPane();



        ImageIcon image = new ImageIcon("Test1/src/resources/Rectangle 1.png");
        JLabel imgLabel = new JLabel(image);
        imgLabel.setBounds(0,0,700,600);
        ImageIcon image1 = new ImageIcon("Test1/src/resources/galaxy.jpg");
        JLabel boardImg = new JLabel(image1);
        boardImg.setBounds(150,100,400,400);
        ImageIcon image2 = new ImageIcon("Test1/src/resources/tiles.png");
        JLabel tilesPic = new JLabel(image2);
        tilesPic.setBounds(150,100,400,400);
        this.setVisible(true);
        board.setLayout(new GridLayout(8,8));
        board.setOpaque(false);
        whiteOut.setLayout(new GridLayout(8,2));
        blackOut.setLayout(new GridLayout(8,2));
        board.setBounds(150,100,400,400);
        whiteOut.setBounds(21,100,100,400);
        blackOut.setBounds(579,100,100,400);
        base.setBounds(0,0,700,600);


        JLabel white_name = new JLabel(whiteName);
        JLabel black_name = new JLabel(blackName);
        JLabel black_timer = new JLabel("timer 1");
        JLabel white_timer = new JLabel("timer 2");

        white_name.setBounds(350,16,200,30);
        black_name.setBounds(350,552,200,30);
        white_timer.setBounds(335,60,87,26);
        black_timer.setBounds(335,513,87,26);
////        player1.add(name1);
//        player1.add(timer1);
//        player2.add(timer2);
////        player2.add(name2);
        gameDispose = this;
        gameDispose.setDefaultCloseOperation(EXIT_ON_CLOSE);
        board.setBackground(new Color(0, 0, 0,0));
        whiteOut.setBackground(new Color(0,0,0,65));
        blackOut.setBackground(new Color(0, 0, 0,65));
//        player1.setBackground(new Color(0, 0, 0, 65));
//        player2.setBackground(new Color(0, 0, 0, 65));
//        board.setPreferredSize(new Dimension(400, 400));
//        whiteOut.setPreferredSize(new Dimension(100, 400));
//        blackOut.setPreferredSize(new Dimension(100, 400));
//        player1.setPreferredSize(new Dimension(200, 30));
//        player2.setPreferredSize(new Dimension(200, 30));
//       this.add(board,BorderLayout.CENTER);
//        this.add(whiteOut, BorderLayout.WEST);
//        this.add(blackOut, BorderLayout.EAST);
//        this.add(player1, BorderLayout.SOUTH);
//        this.add(player2, BorderLayout.NORTH);
//        board.setLocation(100,155);
//        whiteOut.setLocation(100,37);
//        blackOut.setLocation(100,573);

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



                // stoleMate check
//                if(i==7 && j==7)
//                    squares[i][j] = new square(new King("black", i, j));
//                if(i==5 && j==5)
//                    squares[i][j] = new square(new Queen("white", i, j));
//                if(i==3 && j==6)
//                    squares[i][j] = new square(new Rock("white", i, j));
//                if(i==0 && j==0)
//                    squares[i][j] = new square(new King("white", i, j));
//                if(i==5 && j==3)
//                    squares[i][j] = new square(new Pawn("white", i, j));


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
                    squares[i][j].setBackground(new Color(0, 0, 0,30));
                    squares[i][j].setOpaque(false);
                } else {
                    squares[i][j].setBackground(new Color(0,0,0,150));
                    squares[i][j].setOpaque(false);

                }
                board.add(squares[i][j]);


            }
        }

//        this.add(board);
        this.add(blackOut);
        this.add(whiteOut);
        base.add(imgLabel,Integer.valueOf(0));
        base.add(boardImg, Integer.valueOf(1));
        base.add(tilesPic, Integer.valueOf(2));
        base.add(board, Integer.valueOf(3));
        base.add(white_name,Integer.valueOf(3));
        base.add(black_name,Integer.valueOf(3));
        base.add(black_timer,Integer.valueOf(3));
        base.add(white_timer,Integer.valueOf(3));
        this.add(base);





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


        TimerB = new TimerLabel(black_timer, time);
        TimerW = new TimerLabel(white_timer, time);
        WhiteName = new NameLabel(white_name, whiteName, "white");
        BlackName = new NameLabel(black_name, blackName, "black");
        TimerLabel.whiteTurn =true;
        // Check if it's the white player's turn
        if (TimerLabel.whiteTurn) {
            TimerW.startTimer();
            TimerB.stopTimer();
            // Check if the time has run out for the black player

        }

        // Check if it's the black player's turn
        else {
            TimerB.startTimer();
            TimerW.stopTimer();
            // Check if the time has run out for the white player
        }
    }



    public static void endGame(boolean turn, String sayIt){
        String winner = NameLabel.setWinner(WhiteName, BlackName, turn);
        String time = TimerLabel.elapsedTime(TimerB, TimerW);
        String status2=sayIt;
        new_game.endData(winner, time,status2);
        new_game.gameData = new_game.whitePlayer + "%" + new_game.blackPlayer + "%" + new_game.Winner +"%"+ new_game.Time+"%" +new_game.status2;
        User.WriteToFile(new_game.gameData);

        TimerB.stopTimer();
        TimerW.stopTimer();
        TimerB.setEnabled(false);
        TimerW.setEnabled(false);

        JOptionPane.showMessageDialog(null, sayIt);
        gameDispose.dispose();

        TimerW.minute = 0;
        TimerB.minute = 0;
        TimerW.second = 0;
        TimerB.second = 0;

        gameEnded = true;
        new new_game(true);

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

                        squares[i][j].setOpaque(true);
//                        if(square.oldPiece.isSameTeam(square.oldPiece , squares[i][j].piece))
//                            squares[i][j].setBackground(new Color(255, 0, 0));
                        if(squares[i][j].piece == null)
                            squares[i][j].setBackground(new Color(12, 253, 1));
                        else
                            squares[i][j].setBackground(new Color(253, 240, 1));
                    }
                    // color same team pieces with red
                    else if(square.oldPiece.pieceCanMove(i,j) && square.oldPiece.isSameTeam(squares[i][j].piece) ) { //&& squares[i][j].piece.moveHitPiece(i,j)
                        squares[i][j].setBackground(new Color(253, 0, 2));
                        squares[i][j].setOpaque(true);
                    }
                }

            }

        }
    }




    public static void getBack(square[][] squares) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j].setOpaque(false);

                if ((i + j) % 2 == 0) {
                    squares[i][j].setBackground(new Color(0, 0, 0,30));
                } else {
                    squares[i][j].setBackground(new Color(0,0,0,150));
                }
                if(square.oldPiece.isValidMove(squares, i, j) && squares[i][j].piece instanceof King)
                    squares[i][j].setBackground(new Color(255, 0, 167));

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
        System.setProperty("sun.java2d.uiScale","1.0");
        game g = new game("A", "B", "1");
        g.setVisible(true);


    }


}



