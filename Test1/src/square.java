import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class square extends JButton implements ActionListener {
    public square(Piece p) {
        piece = p;
        addActionListener(this);
        col = p.getX();
        row = p.getY();
       // this.setBorderPainted(false);

    }

    public square(int col, int row) {
        addActionListener(this);
        this.col = col;
        this.row = row;
//        this.setBorderPainted(false);
    }

    public square() {
        addActionListener(this);
//        this.setBorderPainted(false);
    }

    public int col;
    public int row;
    public Piece piece = null;
    public static boolean begin_move = false;


    public static Piece oldPiece = null;

    @Override
    public Icon getIcon() {
        if (piece != null)
            return piece.icon;
        return null;
    }

    public void hasValidMoves(String is_white) {
        boolean checkMate = true;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (game.squares[i][j].piece != null && game.squares[i][j].piece.color.equals(is_white)) {

                    if (game.validity(game.squares, i, j, is_white)) {
                        checkMate = false;

                    }

                }

            }
        }
        if(checkMate)
        {
            game.endGame();
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if (this.piece != null && !begin_move && ((TimerLabel.whiteTurn && this.piece.color.equals("white")) || (!TimerLabel.whiteTurn && this.piece.color.equals("black")))) {
            begin_move = true;
            oldPiece = this.piece;
            System.out.println("first presseed!");
            game.paintComp(game.squares);
            return;

        }
        if (begin_move) {

            if (oldPiece.isSameTeam(piece, oldPiece)) {
                oldPiece = this.piece;
                game.getBack(game.squares);
                System.out.println("change");
                game.paintComp(game.squares);
                return;
            }


            //make it move on only green
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {


                    if (game.isValidMove(square.oldPiece, game.squares, i, j)) {

                        if (this.col == i && this.row == j) {
                            //get pieces out
                            if (!oldPiece.isSameTeam(this.piece, oldPiece)&&this.piece!=null) {
                                if (game.squares[this.piece.x][this.piece.y].piece.color.equals("white"))
                                    game.whiteOut.add(new JLabel(game.squares[this.piece.x][this.piece.y].piece.icon));
                                else {
                                    game.blackOut.add(new JLabel(game.squares[this.piece.x][this.piece.y].piece.icon));
                                }


                            }
                            this.piece = oldPiece;


                            // add to panel but check whether white or black
//


                            System.out.println("here");
                            game.squares[oldPiece.x][oldPiece.y].piece = null;


                            //  this.piece = .piece;


                            this.piece.y = row;
                            this.piece.x = col;
                            this.piece.isFirst_move = false;


                            //oldPiece = null;
                            System.out.println("moove!");
                            game.getBack(game.squares);
                            System.out.println(this.col);
                            System.out.println(this.row);
                            begin_move = false;

                            //check if pawn in last row to promotes
                            if (piece.inLastRow) {
                                this.piece = this.piece.promotedPawn(game.squares, this.col, this.row);
                            }








                               /* if (piece.isPromoted){
                                    game.squares[oldPiece.x][oldPiece.y].piece = null;
                                    System.out.println("ppp");
                                }*/
                            game.board.repaint();
                            game.board.revalidate();

                            //change player turn
                            TimerLabel.whiteTurn = !TimerLabel.whiteTurn;

                            //if no moves available

                            if(TimerLabel.whiteTurn)
                                hasValidMoves("white");
                            else
                                hasValidMoves("black");
                            //endGame



                            //start timer
                            if (TimerLabel.whiteTurn) {
                                game.Timer2.startTimer();
                                game.Timer1.stopTimer();
                            } else {
                                game.Timer1.startTimer();
                                game.Timer2.stopTimer();
                            }
                        }


                    }

                }
            }


        }
        }


    public static void main (String args[])
    {
        new firstPage();
    }

}
