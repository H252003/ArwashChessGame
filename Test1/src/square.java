import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class square extends JButton implements ActionListener {
    public square(Piece p) {
        piece = p;
        addActionListener(this);
        col = p.y;
        row = p.x;
        // this.setBorderPainted(false);

    }

    public square(int row, int col) {
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
    chckScan check = new chckScan();

    public void colorCanMove(String color) {
//        boolean noMovesAvalKing = true;
        boolean noMovesAvalAll = true;
//        boolean inStale = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if ((game.squares[i][j].piece != null && game.squares[i][j].piece.color.equals(color))) { //
                    boolean kingIsMoving = false;
                    if (game.squares[i][j].piece instanceof King) {
                        kingIsMoving = true;
                    }

                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            ///////////////////////////
                            Piece tmp = oldPiece;
                            oldPiece = null;
                            ///////////////////////
                            if (game.squares[i][j].piece.isValidMove(game.squares, x,y)) {

                                if (kingIsMoving) {
                                    if (game.checkKing.isKingChecked(game.squares[0][0], x, y, true)) {
                                        oldPiece = tmp;
                                        continue;
                                    }
//                                    noMovesAvalKing = false;

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
//            if (noMovesAvalKing){
            game.endGame(TimerLabel.whiteTurn,"checkMate");
//            } else {
//                game.endGame("staleMate");
        }
        //game.endGame("checkMate");//checkmate

    }
//        }

    public boolean canCastle(){
        return this.piece.getClass() == King.class && piece.isFirst_move && game.squares[this.row][6].piece == null&&
                game.squares[this.row][5].piece == null && game.squares[this.row][7].piece.getClass() == Rock.class;
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

            if (this.piece != null && this.piece.isSameTeam(oldPiece)) {
                oldPiece = this.piece;
                game.getBack(game.squares);
                System.out.println("change");
                game.paintComp(game.squares);
                return;
            }


            //make it move on only green



            if (oldPiece.isValidMove( game.squares, this.row, this.col)) {

                //get pieces out
                if (!oldPiece.isSameTeam(this.piece)&&this.piece!=null) {
                    if (game.squares[this.piece.x][this.piece.y].piece.color.equals("white"))
                        game.whiteOut.add(new JLabel(game.squares[this.piece.x][this.piece.y].piece.icon));
                    else {
                        game.blackOut.add(new JLabel(game.squares[this.piece.x][this.piece.y].piece.icon));
                    }


                }
                this.piece = oldPiece;


                // add to panel but check whether white or black
//


                game.squares[oldPiece.x][oldPiece.y].piece = null;


                //  this.piece = .piece;


                this.piece.y = col;
                this.piece.x = row;

                //check king castle && not its original moves
                if(this.piece.getClass() == King.class && this.piece.isFirst_move)
                {
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


                //oldPiece = null;
                System.out.println("moove!");
                game.getBack(game.squares);
                System.out.println(this.row);
                System.out.println(this.col);
                begin_move = false;

                //check if pawn in last row to promotes
                if (piece.inLastRow) {
                  this.piece=  piece.promotedPawn(game.squares, this.row, this.col);
                }



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


    public static void main (String args[])
    {
        new firstPage();
    }

}
