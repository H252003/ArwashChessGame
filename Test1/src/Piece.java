import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public abstract class Piece {
    public String color;
    public int x;
    public int y;
    public Icon icon;

    public boolean checked=false;

    public boolean isFirst_move = true;
//public boolean isFirstMove=true;

    public Piece(String color, int x, int y) {
        this.color = color;
        this.x = y;
        this.y = x;
        //this.isFirst_move = true;


    }
    public static boolean isSameTeam(Piece p1, Piece p2)
    {
        if(p1 == null || p2 == null)
            return false;
        return p1.color.equals(p2.color);
    }
    public boolean moveCanEat(int newX, int newY){

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
    public abstract boolean isValidMovement(int newX, int newY);
}


class Pawn extends Piece {
    private boolean hasMoved = true;
    public static int colorIndex;
    public static int colorIndex2;

    public  String color =this.getColor();

    public Pawn(String color, int x, int y) {
        super(color, x, y);
        hasMoved = false;
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


    public boolean isValidMovement(int newX, int newY) {
        if (this.color.equals("white")){
            colorIndex = -1;
            colorIndex2=7;
        }

        else {
            colorIndex = 1;
            colorIndex2=0;
        }
        //this.icon== new ImageIcon(getClass().getClassLoader().getResource("resources/chess_white_pawn-removebg-preview (1).png"))
        //                ||this.icon==new ImageIcon(getClass().getClassLoader().getResource("resources/black_pawn-removebg-preview (1).png")))
        // promotion
        if (this.x==colorIndex2&& game.squares[this.x][this.y].piece.icon==new ImageIcon(getClass().getClassLoader().getResource("resources/chess_white_pawn-removebg-preview (1).png"))
               || game.squares[this.x][this.y].piece.icon==new ImageIcon(getClass().getClassLoader().getResource("resources/black_pawn-removebg-preview (1).png")))
            promotedPawn(game.squares,this.x,this.y);

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
        private void promotedPawn(square[][] squares, int newX, int newY){
            //String promotedPiece= null;
            String[] options = {"queen", "knight", "bishop","rook"};
            String piece = (String) JOptionPane.showInputDialog(null, "Choose a piece:", "mabrooooook", JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
            if (piece == "rook") {
                squares[newX][newY] = new square(new Rock(color,newY,newX));
            }

            else if (piece == "bishop") {
                squares[newX][newY] = new square(new Bishop(color, newY, newX));

            } else if (piece=="queen") {
                squares[newX][newY] = new square(new Queen(color, newY, newX));
            }
            else if (piece== "knight" ){
                squares[newX][newY] = new square(new Knight(color, newY, newX));
            }

        }



  /*  promotedPawn(newX,newY);
           if(newX==colorIndex){

           int promoted piece = JOptionPane.showConfirmDialog(null, "Choose a piece:", "mabrooooook", JOptionPane.ROOK_BISHOP_QUEEN_KNIGHT_OPTION);
            if (promoted piece == JOptionPane.ROOK_OPTION) {
                squares[newX][newY] = new square(new Rock(color, 5, i));
                }

            else if (promoted piece == JOptionPane.BISHOP_OPTION) {
                JOptionPane.showMessageDialog(null, "byeee");


            } else {
                JOptionPane.showMessageDialog(null, "aywa kdaa et3dl");
            }

            }*/

//

        public boolean moveCanEat ( int newX, int newY)
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

    public boolean isValidMovement(int newX, int newY) {
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

    public boolean isValidMovement(int newX, int newY) {
        // Check if move is valid for bishop
        if(Math.abs(this.x - newX) <= 3)
        {
            //make it not eat its opposite when shifting colors
            if(this.x == newX)
                if(game.squares[newX][newY].piece == null || isSameTeam(this,game.squares[newX][newY].piece))

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

    public boolean isValidMovement(int newX, int newY) {
        // Check if move is valid for rook
        return (this.x == newX && this.y != newY) || (this.x != newX && this.y == newY);


    }

    public boolean moveCanEat(int newX, int newY)
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
    private boolean hasMoved=true;
     public int kingX= this.x;
    public int kingY= this.y;

    public King(String color, int x, int y) {
        super(color, x, y);
        hasMoved = false;
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


    public boolean isValidMovement(int newX, int newY) {
        // Check if move is valid for pawn
        // if(Math.abs(this.x - newX) <= 3)
        if (Math.abs((this.x - newX)*(this.y-newY))==1 ||Math.abs(newY-this.y)+ Math.abs( newX-this.x)==1){
            kingX= this.x;
             kingY= this.y;

            return true;

        }
        else return false;
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

    public boolean isValidMovement(int newX, int newY) {
        // Check if move is valid for pawn
        if(this.x == newX)
            return Math.abs(this.y - newY) >0;
        return ((this.x == newX && this.y != newY) || (this.x != newX && this.y == newY)) || (Math.abs(this.x - newX) ==  Math.abs(this.y - newY));

    }
    public boolean moveCanEat(int newX, int newY)
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


