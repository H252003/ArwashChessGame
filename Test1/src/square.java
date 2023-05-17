import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class square extends JButton implements ActionListener {
    public square(Piece p) {//assign piece
        piece = p;
        addActionListener(this);
        col = p.y;
        row = p.x;

    }

    public square(int row, int col) {
        addActionListener(this);
        this.col = col;
        this.row = row;
    }//assign place

    public square() {
        addActionListener(this);
    }

    public int col;
    public int row;
    public Piece piece = null;
    public static boolean begin_move = false; // check first click


    public static Piece oldPiece = null;

    @Override
    public Icon getIcon() { // show piece image
        if (piece != null)
            return piece.icon;
        return null;
    }

    public void colorCanMove(String color) {// check if no moves of this color for check mate
        boolean kingIsInCheck = false;
        boolean noMovesAvalAll = true;


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if ((game.squares[i][j].piece != null && game.squares[i][j].piece.color.equals(color))) { //
                    boolean kingIsMoving = false;
                    if (game.squares[i][j].piece instanceof King) {
                        kingIsMoving = true;
                    }

                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {

                                    if(game.checkKing.isKingChecked(game.squares[0][0], x, y, false)){
                                        kingIsInCheck = true;
                                       // continue;
                                    }
                            Piece tmp = oldPiece;
                            oldPiece = null;
                            if (game.squares[i][j].piece.isValidMove(game.squares, x,y)) {
                                if (kingIsMoving) {
                                    if (game.checkKing.isKingChecked(game.squares[0][0], x, y, true)) {
                                        oldPiece = tmp;
                                        continue;
                                    }

                                }

                                oldPiece = tmp;
                                noMovesAvalAll = false;
                                break;

                            }
                            }
                        if(!noMovesAvalAll) break;
                    }

                }

            }
        }
        if (noMovesAvalAll) {
            if (kingIsInCheck){
                game.endGame(TimerLabel.whiteTurn,"checkMate");
            } else {
                game.endGame(TimerLabel.whiteTurn,"staleMate");
        }

    }
        }

    @Override
    public void actionPerformed(ActionEvent e) {
// first pressed
        if (this.piece != null && !begin_move && ((TimerLabel.whiteTurn && this.piece.color.equals("white")) || (!TimerLabel.whiteTurn && this.piece.color.equals("black")))) {
            begin_move = true;
            oldPiece = this.piece;
            game.paintComp(game.squares);
            return;

        }
        if (begin_move) {
            // change selected piece
            if (this.piece != null && this.piece.isSameTeam(oldPiece)) {
                oldPiece = this.piece;
                game.getBack(game.squares);
                game.paintComp(game.squares);
                return;
            }


            if (oldPiece.isValidMove( game.squares, this.row, this.col)) {
                //get pieces out
                if (!oldPiece.isSameTeam(this.piece)&&this.piece!=null) {
                    if (game.squares[this.piece.x][this.piece.y].piece.color.equals("white"))
                        game.whiteOut.add(new JLabel(game.squares[this.piece.x][this.piece.y].piece.icon));
                    else {
                        game.blackOut.add(new JLabel(game.squares[this.piece.x][this.piece.y].piece.icon));
                    }


                }
                //assign new piece in new square
                this.piece = oldPiece;
                game.squares[oldPiece.x][oldPiece.y].piece = null;
                this.piece.y = col;
                this.piece.x = row;

                //check king castle && not its original moves
                if(this.piece instanceof King && this.piece.isFirst_move)
                {
                    //want to castle right
                    if(King.canCastleRight && this.piece.y == 6 )
                    {
                        game.squares[this.piece.x][5].piece = game.squares[this.piece.x][7].piece;
                        game.squares[this.piece.x][5].piece.y = 5;
                        game.squares[this.piece.x][7].piece = null;
                    }
                    if(King.canCastleLeft && this.piece.y == 2)
                    {
                        game.squares[this.piece.x][3].piece = game.squares[this.piece.x][0].piece;
                        game.squares[this.piece.x][3].piece.y = 3;
                        game.squares[this.piece.x][0].piece = null;
                    }
                }

                this.piece.isFirst_move = false;
                //remove square colors for moves
                game.getBack(game.squares);

                //to start a new press
                begin_move = false;

                //check if pawn in last row to promotes
                if (piece.inLastRow) {
                  this.piece=  piece.promotedPawn(game.squares, this.row, this.col);
                }


                //update the board
                game.board.repaint();
                game.board.revalidate();

                //change player turn
                TimerLabel.whiteTurn = !TimerLabel.whiteTurn;

                //if no moves available
                if(TimerLabel.whiteTurn)
                    colorCanMove("white");
                else
                    colorCanMove("black");
                //endGame



                //change timer
                if (TimerLabel.whiteTurn) {
                    game.TimerW.startTimer();
                    game.TimerB.stopTimer();
                } else {
                    game.TimerB.startTimer();
                    game.TimerW.stopTimer();
                }
            }


        }
    }

}
