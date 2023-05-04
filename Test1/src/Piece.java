import javax.swing.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public abstract class Piece {
    public String color;
    public int x;
    public int y;
    public Icon icon;
//public boolean isFirstMove=true;

    public Piece(String color, int x, int y) {
        this.color = color;
        this.x = y;
        this.y = x;


    }
    public boolean isSameTeam(Piece p1 , Piece p2)
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

    public abstract boolean isValidMovement(int newX, int newY);
}

class Pawn extends Piece {
    private boolean hasMoved =true;
int colorIndex;
    public Pawn(String color, int x, int y) {
        super(color, x, y);
        hasMoved = false;
        try {
            if (color == "white") {
                icon  = new ImageIcon(getClass().getClassLoader().getResource("resources/chess_white_pawn-removebg-preview (1).png"));


            } else {
                icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/black_pawn-removebg-preview (1).png")));
            }
        } catch (Exception Ignored) {
            icon = null;
        }
    }

    public boolean isValidMovement(int newX, int newY) {
       /* if(hasMoved==false){
            if ((this.x-newX==2)&&(this.y-newY==0))
                return true;
            else return false;
        }else {*/
            if ((this.x - newX == 1) && (this.y - newY == 0))
                return true;
            else return false;
        }

    public boolean moveCanEat(int newX, int newY)
    {

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

    public boolean isValidMovement(int newX, int newY) {
        // Check if move is valid for pawn
        if(Math.abs(this.x - newX) <= 3)
            return Math.abs(this.x - newX) ==  Math.abs(this.y - newY);
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
        // Check if move is valid for pawn
        return this.x == newX || this.y == newY;


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
}
class King extends Piece {
    private boolean hasMoved=true;

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

   /* public boolean isValidMovement(int newX, int newY) {
        // Check if move is valid for pawn
        // ...

        return true;
    }*/
    public boolean isValidMovement(int newX, int newY) {
        // Check if move is valid for pawn
        // if(Math.abs(this.x - newX) <= 3)
        if (Math.abs((this.x - newX)*(this.y-newY))==1 ||Math.abs(newY-this.y)+ Math.abs( newX-this.x)==1)
            return true;
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
        return (this.x == newX || this.y == newY) || (Math.abs(this.x - newX) ==  Math.abs(this.y - newY));

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


