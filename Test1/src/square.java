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

    }

    public square(int col, int row) {
        addActionListener(this);
        this.col = col;
        this.row = row;
    }

    public square() {
        addActionListener(this);

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


    @Override
    public void actionPerformed(ActionEvent e) {

        if (this.piece != null && !begin_move) {
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

                    if (game.isValidMove(square.oldPiece, game.squares, i, j))
                        if(this.col == i && this.row == j){

                        this.piece = oldPiece;

                        // add to panel but check whether white or black


                        game.squares[oldPiece.x][oldPiece.y].piece = null;


                        this.piece.y = row;
                        this.piece.x = col;
                        this.piece.isFirst_move = false;


                        //oldPiece = null;

                        System.out.println("moove!");
                        game.getBack(game.squares);
                        System.out.println(this.col);
                        System.out.println(this.row);
                        begin_move = false;
                        game.board.repaint();
                        game.board.revalidate();
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
