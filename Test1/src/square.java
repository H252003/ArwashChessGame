import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class square extends JButton implements ActionListener {
    public square(Piece p) {
        piece = p;
        addActionListener(this);
        xPos = p.getX();
        yPos = p.getY();


    }

    public square(int xPos, int yPos) {
        addActionListener(this);
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public square() {
        addActionListener(this);


    }

    public int xPos;
    public int yPos;
    public Piece piece;
    public static boolean begin_move = false;
    public static boolean end_move = false;
    public static Piece piece_hold;

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
            piece_hold = this.piece;
            System.out.println("first presseed!");
            return;
        }
        if (begin_move) {


            this.piece = piece_hold;
          game.squares[piece_hold.getY()][piece_hold.getX()].piece = null;
          // game.squares[piece_hold.getY()][piece_hold.getX()] = null;



//           game.squares[this.xPos][this.yPos].piece = piece_hold;
            this.piece.setY(xPos);
            this.piece.setX(yPos);
           piece_hold=null;
            System.out.println("moove!");
            System.out.println(this.xPos);
            System.out.println(this.yPos);
            begin_move = false;
            game.board.repaint();
            game.board.revalidate();


        }

    }


//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (this.piece != null && !begin_move) {
//            // First click: select the piece to move
//            begin_move = true;
//            piece_hold = this.piece;
//            System.out.println("First piece selected: " + piece_hold);
//        } else if (begin_move) {
//            // Second click: move the selected piece to this square
//            int oldX = piece_hold.getX();
//            int oldY = piece_hold.getY();
//
//            // Check if the move is valid
////            if (piece_hold.canMoveTo(this.xPos, this.yPos)) {
////                // Check if there is a piece to capture at the destination square
////                if (this.piece != null && !this.piece.getColor().equals(piece_hold.getColor())) {
////                    // Capture the opponent's piece
////                    System.out.println("Captured " + this.piece);
////                }
//
//                // Move the piece to the new square
//                game.squares[oldY][oldX].piece = null;
//                piece_hold.setX(this.xPos);
//                piece_hold.setY(this.yPos);
//                this.piece = piece_hold;
//                game.board.repaint();
//                game.board.revalidate();
//                System.out.println("Moved " + piece_hold);
//            } else {
//                System.out.println("Invalid move");
//            }
//
//            // Reset the selection
//            begin_move = false;
//            piece_hold = null;
//        }


        public static void main (String args[])
        {
            new game();
        }

}
