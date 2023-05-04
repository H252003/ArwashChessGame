import javax.swing.*;
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
            game.paintComp(game.squares) ;
            return;

        }
        if (begin_move) {

            if(oldPiece.isSameTeam(piece, oldPiece)) {
                oldPiece = this.piece;
                game.getBack(game.squares);
                System.out.println("change");
                game.paintComp(game.squares) ;
                return;
            }
            this.piece = oldPiece;
            game.squares[oldPiece.x][oldPiece.y].piece = null;


            this.piece.y = row;
            this.piece.x = col;


            oldPiece =null;
            System.out.println("moove!");
            game.getBack(game.squares);
            System.out.println(this.col);
            System.out.println(this.row);
            begin_move = false;
            game.board.repaint();
            game.board.revalidate();


        }

    }

    public static void main (String args[])
    {
        new game();
    }

}
