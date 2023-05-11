import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class Piece {
    public String color;
    public int x;
    public int y;
    public Icon icon;

    public boolean checked=false;

    public boolean isFirst_move = true;
    public boolean isPromoted = false;
    public boolean inLastRow = false;

    public boolean isValidMove(square[][] squares, int x, int y){
        if(this.isSameTeam(squares[x][y].piece)){

            return false ;
        }
        if(!this.pieceCanMove(x,y)) {
            return false;
        }
        if(this.moveHitPiece(x,y))
            return false;
        if(game.checkKing.isKingChecked(squares[x][y],x,y, this.getClass() == King.class))
            return false;

        return true ;
    }

//public boolean isFirstMove=true;

    public Piece(String color, int x, int y) {
        this.color = color;
        this.x = y;
        this.y = x;
        //this.isFirst_move = true;


    }

    public static boolean isWhite(String color){
        if(color == "white"){return true ;
        }
        return false; }

    public boolean isSameTeam(Piece p2)
    {
        if( p2 == null)
            return false;
        return this.color.equals(p2.color);
    }
    public boolean moveHitPiece(int newX, int newY){

        return false;
    }


    public String getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public abstract boolean nextMoveEat(int newX, int newY,int kingX, int kingY );
    public abstract boolean pieceCanMove(int newX, int newY);
    //import javax.swing.JOptionPane;
    //
    //public class OptionDialogExample {
    //    public static void main(String[] args) {
    //        String[] options = {"Option 1", "Option 2", "Option 3", "Option 4"};
    //        String selectedOption = (String) JOptionPane.showInputDialog(null, "Choose an option:", "Options",
    //                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    //
    //        if (selectedOption != null) {
    //            JOptionPane.showMessageDialog(null, "You selected: " + selectedOption, "Selected Option",
    //                    JOptionPane.INFORMATION_MESSAGE);
    //        } else {
    //            JOptionPane.showMessageDialog(null, "No option selected", "Selected Option",
    //                    JOptionPane.WARNING_MESSAGE);
    //        }
    //    }
    //}
    //JOptionPane.showOptionDialog(null, "Choose an option:", "Options",
    //                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

    public  Piece promotedPawn(square[][] squares, int newX, int newY){
        //String promotedPiece= null;
        Object[] options = {"queen", "knight", "bishop","rook"};
        int piece =  JOptionPane.showOptionDialog(null, "Choose a piece:", "mabrooooook",JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
        if (piece == 3) {
            return squares[newX][newY].piece = new Rock(color,newY,newX);

        }

        else if (piece == 2) {

          return    squares[newX][newY].piece = new Bishop(color, newY, newX);

        } else if (piece==0) {
          return    squares[newX][newY].piece = new Queen(color, newY, newX);
        }
        else if (piece== 1 ){
           return   squares[newX][newY].piece = new Knight(color, newY, newX);
        }
        game.board.revalidate();
        game.board.repaint();
        return  squares[newX][newY].piece = new Queen(color, newY, newX);

    }
}



class Pawn extends Piece {
    private boolean hasMoved = false;
    public boolean isPromoted = false;
    public static int colorIndex;
    public static int colorIndex2;



    public  String color =this.getColor();

    public Pawn(String color, int x, int y) throws IOException {
        super(color, x, y);
        isPromoted = false;

        try {
            if (color == "white") {
                icon = new ImageIcon(getClass().getClassLoader().getResource("resources/chess_white_pawn-removebg-preview (1).png"));

//Objects.requireNonNull
            } else {
                icon = new ImageIcon(getClass().getClassLoader().getResource("resources/black_pawn-removebg-preview (1).png"));
            }
        } catch (Exception Ignored) {
            icon = null;
        }
    }


    public boolean pieceCanMove(int newX, int newY) {
        if (this.color.equals("white")){
            colorIndex = -1;
            colorIndex2=6;
        }

        else {
            colorIndex = 1;
            colorIndex2=1;
        }
        //this.icon== new ImageIcon(getClass().getClassLoader().getResource("resources/chess_white_pawn-removebg-preview (1).png"))
        //                ||this.icon==new ImageIcon(getClass().getClassLoader().getResource("resources/black_pawn-removebg-preview (1).png")))
        // promotion
        //&& game.squares[this.x][this.y].piece.icon==new ImageIcon(getClass().getClassLoader().getResource("resources/chess_white_pawn-removebg-preview (1).png"))
        //               || game.squares[this.x][this.y].piece.icon==new ImageIcon(getClass().getClassLoader().getResource("resources/black_pawn-removebg-preview (1).png"))
        if (this.x==colorIndex2 && !isPromoted) {
            // promotedPawn(game.squares, this.x, this.y); //square.oldPiece =
            this.isPromoted = true;
            this.inLastRow = true;
        }


        //push pawn 1
        if(this.y == newY && newX == this.x - colorIndex ) //&& game.squares[newX][newY].piece == null
            return true;

        //push pawn 2
        if (isFirst_move && this.y == newY && newX == this.x - colorIndex * 2 && game.squares[newX + colorIndex][newY].piece == null) //&& game.squares[newX][newY].piece == null
            return true;

        //capture left
        if(this.y == newY - 1 && newX == this.x - colorIndex && game.squares[newX][newY].piece != null)
            return true;

        //capture right
        if(this.y == newY + 1 && newX == this.x - colorIndex && game.squares[newX][newY].piece != null)
            return true;

        return false;
    }
   /* if(color=="white")
    colorIndex=7;
        else colorIndex=0;*/






//

    public boolean moveHitPiece(int newX, int newY)
    {

        return false;
    } @Override
    public boolean nextMoveEat(int newX, int newY,int kingX, int kingY) {
        if (this.color.equals("white"))
            colorIndex = -1;
        else colorIndex = 1;

        //push pawn 1
        if(newY == kingY && kingX == newX - colorIndex ) {
            checked=true;
            System.out.println(checked);

            //game.squares[kingX][kingY].piece.
            return true;

        }

        //push pawn 2
        if (isFirst_move && newY == kingY && kingX == newX - colorIndex * 2 && game.squares[kingX + colorIndex][kingY].piece == null){
            checked=true;
            System.out.println(checked);
            return true;//&& game.squares[newX][newY].piece == null
        }


        //capture left
        if(newY == kingY - 1 && kingX == newX - colorIndex && game.squares[kingX][kingY].piece != null){
            checked=true;
            System.out.println(checked);
            return true;

        }

        //capture right
        if(newY == kingY + 1 && kingX == newX - colorIndex && game.squares[kingX][kingY].piece != null){
            checked=true;
            System.out.println(checked);
            return true;

        }


        return false;
    }

}


class Knight extends Piece {
    private boolean hasMoved;

    public Knight(String color, int x, int y) {
        super(color, x, y);
        hasMoved = false;
        try {
            if (color == "white") {

                icon = new ImageIcon(getClass().getClassLoader().getResource("resources/chess-white-knight-removebg-preview (2).png"));

            } else {
                icon= new ImageIcon(getClass().getClassLoader().getResource("resources/black_knight-removebg-preview (1) (1).png"));
            }
        } catch (Exception Ignored) {
            icon = null;
        }
    }

    @Override
    public boolean nextMoveEat(int newX, int newY, int kingX, int kingY) {
        return false;}

    public boolean pieceCanMove(int newX, int newY) {
        if(Math.abs(newX-this.x) == 1 || Math.abs(newY-this.y) ==1)
            return false;

        return Math.abs(newX-this.x)*Math.abs(newY-this.y)==6;
    }
}




class Bishop extends Piece {
    private boolean hasMoved;

    public Bishop(String color, int x, int y) {
        super(color, x, y);
        hasMoved = false;
        try {
            if (color == "white") {
                icon = new ImageIcon(getClass().getClassLoader().getResource("resources/wwhite_bishop-removebg-preview (1).png"));


            } else {
                icon = new ImageIcon(getClass().getClassLoader().getResource("resources/bblack_bishop-removebg-preview (1).png"));
            }
        } catch (Exception Ignored) {
            icon = null;
        }
    }

    @Override
    public boolean nextMoveEat(int newX, int newY, int kingX, int kingY) {
        return false;
    }

    public boolean pieceCanMove(int newX, int newY) {
        // Check if move is valid for bishop
        if(Math.abs(this.x - newX) <= 3)
        {
            //make it not eat its opposite when shifting colors
            if(this.x == newX)
                if(game.squares[newX][newY].piece == null || isSameTeam(game.squares[newX][newY].piece))

                    return Math.abs(this.y - newY) == 1;

            return Math.abs(this.x - newX) ==  Math.abs(this.y - newY);
        }

        return false;
    }
}
class Rock extends Piece {
    private boolean hasMoved;

    public Rock(String color, int x, int y) {
        super(color, x, y);
        hasMoved = false;
        try {
            if (color == "white") {
                icon= new ImageIcon(getClass().getClassLoader().getResource("resources/rock_white-removebg-preview (2).png"));

            } else {
                icon= new ImageIcon(getClass().getClassLoader().getResource("resources/rock_black-removebg-preview (1).png"));
            }
        } catch (Exception Ignored) {
            icon = null;
        }
    }

    public boolean pieceCanMove(int newX, int newY) {
        // Check if move is valid for rook
        return (this.x == newX && this.y != newY) || (this.x != newX && this.y == newY);



    }

    public boolean moveHitPiece(int newX, int newY)
    {
        //left
        if(this.y > newY)
        {
            for (int ya = this.y - 1; ya > newY;ya--)
            {
                if(game.squares[this.x][ya].piece != null)
                    return true;
            }
        }
        //right
        if(this.y < newY)
        {
            for (int ya = this.y + 1; ya < newY;ya++)
            {
                if(game.squares[this.x][ya].piece != null)
                    return true;
            }
        }
        //up
        if(this.x > newX)
        {
            for (int xa = this.x - 1; xa > newX;xa--)
            {
                if(game.squares[xa][this.y].piece != null)
                    return true;
            }
        }
        //down
        if(this.x < newX)
        {
            for (int xa = this.x + 1; xa < newX;xa++)
            {
                if(game.squares[xa][this.y].piece != null)
                    return true;
            }
        }

        return false;
    }

    @Override
    public boolean nextMoveEat(int newX, int newY, int kingX, int kingY) {
        return false;
    }

}
class King extends Piece {
    @Override
    public String toString(){
        return "King";
    }
    public static boolean canCastleRight = false;
    public static boolean canCastleLeft = false;
    public int kingX= this.x;
    public int kingY= this.y;

    public King(String color, int x, int y) {
        super(color, x, y);

        try {
            if (color == "white") {
                icon=new ImageIcon(getClass().getClassLoader().getResource("resources/white_king2-removebg-preview (2).png"));


            } else {
                icon=new ImageIcon(getClass().getClassLoader().getResource("resources/kingo-removebg-preview (1).png"));
            }
        } catch (Exception Ignored) {
            icon = null;
        }
    }

    @Override
    public boolean nextMoveEat(int newX, int newY, int kingX, int kingY) {
        return false;
    }
//public boolean checked(){
    //if(nextMoveEat()==true)


    public boolean pieceCanMove(int newX, int newY) {
        // Check if move is valid for king
        if (Math.abs((this.x - newX)*(this.y-newY))==1 ||Math.abs(newY-this.y)+ Math.abs( newX-this.x)==1){
            kingX= this.x;
            kingY= this.y;
            return true;}

        if(newX == this.x && isFirst_move) {
            //check right castle
            if (game.squares[this.x][6].piece == null && game.squares[this.x][5].piece == null &&
                    isSameTeam( game.squares[this.x][7].piece) && game.squares[this.x][7].piece.getClass() == Rock.class) {
                canCastleRight = true;
                return true;
            }
            //check left castle
            if(game.squares[this.x][3].piece == null && game.squares[this.x][2].piece == null &&
                    isSameTeam( game.squares[this.x][0].piece) && game.squares[this.x][0].piece.getClass() == Rock.class) {
                if(game.squares[this.x][1].piece != null || newY == 1) {
                    return false;
                }
                canCastleLeft = true;
                return true;
            }
        }




        return false;
    }
}
class Queen extends Piece {
    private boolean hasMoved;

    public Queen(String color, int x, int y) {
        super(color, x, y);
        hasMoved = false;
        try {
            if (color == "white") {
                icon = new ImageIcon(getClass().getClassLoader().getResource("resources/chess-white-queen-removebg-preview (2).png"));


            } else {
                icon = new ImageIcon(getClass().getClassLoader().getResource("resources/queen_black-removebg-preview (3).png"));
            }
        } catch (Exception Ignored) {
            icon = null;
        }
    }

    public boolean pieceCanMove(int newX, int newY) {
        // Check if move is valid for pawn
        if(this.x == newX)
            return Math.abs(this.y - newY) >0;
        return ((this.x == newX && this.y != newY) || (this.x != newX && this.y == newY)) || (Math.abs(this.x - newX) ==  Math.abs(this.y - newY));

    }
    public boolean moveHitPiece(int newX, int newY)
    {
        if(this.x == newX || this.y == newY) {
            //left like rook
            if (this.y > newY) {
                for (int ya = this.y - 1; ya > newY; ya--) {
                    if (game.squares[this.x][ya].piece != null)
                        return true;
                }
            }
            //right like rook
            if (this.y < newY) {
                for (int ya = this.y + 1; ya < newY; ya++) {
                    if (game.squares[this.x][ya].piece != null)
                        return true;
                }
            }
            //up like rook
            if (this.x > newX) {
                for (int xa = this.x - 1; xa > newX; xa--) {
                    if (game.squares[xa][this.y].piece != null)
                        return true;
                }
            }
            //down like rook
            if (this.x < newX) {
                for (int xa = this.x + 1; xa < newX; xa++) {
                    if (game.squares[xa][this.y].piece != null)
                        return true;
                }
            }
        }
        else {
            //up left like bishop
            if (this.y > newY && this.x > newX)
                for (int i = 1; i < Math.abs(this.y - newY); i++)
                    if (game.squares[this.x - i][this.y - i].piece != null)
                        return true;

            //up right like bishop
            if (this.y < newY && this.x > newX)
                for (int i = 1; i < Math.abs(this.y - newY); i++)
                    if (game.squares[this.x - i][this.y + i].piece != null)
                        return true;

            //down left like bishop
            if (this.y > newY && this.x < newX)
                for (int i = 1; i < Math.abs(this.y - newY); i++)
                    if (game.squares[this.x + i][this.y - i].piece != null)
                        return true;

            //down right like bishop
            if (this.y < newY && this.x < newX)
                for (int i = 1; i < Math.abs(this.y - newY); i++)
                    if (game.squares[this.x + i][this.y + i].piece != null)
                        return true;
        }

        return false;
    }

    @Override
    public boolean nextMoveEat(int newX, int newY, int kingX, int kingY) {
        return false;
    }
}


class ChessGame {
    private Piece[][] board;
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;

    public ChessGame() {
        board = new Piece[8][8];
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        initializeBoard();
    }

    private void initializeBoard() {
        // Place pieces on the board
        // ...
    }

//    public void movePiece(Piece piece, int newX, int newY) {
//        if (piece.isValidMove(newX, newY)) {
//            board[piece.getX()][piece.getY()] = null;
//            piece.setX(newX);
//            piece.setY(newY);
//            board[newX][newY] = piece;
//        }


    public boolean isCheckmate() {
        // Check if a player is in checkmate
        // ...

        return false;
    }

}

