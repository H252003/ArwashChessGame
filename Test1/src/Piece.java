import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class Piece {
    public String color;
    public int x;
    public int y;
    public Icon icon;

    public boolean checked=false; //king

    public boolean isFirst_move = true; // because castle and pawn

    public boolean inLastRow = false; // promotion

    public boolean isValidMove(square[][] squares, int x, int y){ // method to show general valid moves
        if(this.isSameTeam(squares[x][y].piece)){

            return false ;
        }
        if(!this.pieceCanMove(x,y)) {
            return false;
        }
        if(this.moveHitPiece(x,y))
            return false;
        if(game.checkKing.isKingChecked(squares[x][y],x,y, this instanceof King))
            return false;

        return true ;
    }


    public Piece(String color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;

    }

    public static boolean isWhite(String color){
        if(color.equals("white")){return true ;
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




    public abstract boolean pieceCanMove(int newX, int newY);


    public  Piece promotedPawn(square[][] squares, int newX, int newY){

        Object[] options = {"queen", "knight", "bishop","rook"};
        int piece =  JOptionPane.showOptionDialog(null, "Choose a piece:", "mabrooooook",JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
        if (piece == 3) {
            return squares[newX][newY].piece = new Rock(color,newX,newY);
        }
        else if (piece == 2) {
          return    squares[newX][newY].piece = new Bishop(color, newX, newY);

        } else if (piece==0) {
          return    squares[newX][newY].piece = new Queen(color, newX, newY);
        }
        else if (piece== 1 ){
           return   squares[newX][newY].piece = new Knight(color, newX, newY);
        }
        game.board.revalidate();
        game.board.repaint();
        return  squares[newX][newY].piece = new Queen(color, newX, newY);

    }
}



class Pawn extends Piece {

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
            colorIndex2=7;
        }

        else {
            colorIndex = 1;
            colorIndex2=0;
        }

        if (this.x==colorIndex2 && !isPromoted) {

            this.isPromoted = true;
            this.inLastRow = true;
        }


        //push pawn 1
        if(this.y == newY && newX == this.x - colorIndex )
            return true;

        //push pawn 2
        if (isFirst_move && this.y == newY && newX == this.x - colorIndex * 2 && game.squares[newX + colorIndex][newY].piece == null)
            return true;

        //capture left
        if(this.y == newY - 1 && newX == this.x - colorIndex && game.squares[newX][newY].piece != null)
            return true;

        //capture right
        if(this.y == newY + 1 && newX == this.x - colorIndex && game.squares[newX][newY].piece != null)
            return true;

        return false;
    }


    public boolean moveHitPiece(int newX, int newY)
    {

        return false;
    }

}


class Knight extends Piece {


    public Knight(String color, int x, int y) { // set piece with image
        super(color, x, y);

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



    public boolean pieceCanMove(int newX, int newY) {
        if(Math.abs(newX-this.x) == 1 || Math.abs(newY-this.y) ==1)
            return false;

        return Math.abs(newX-this.x) * Math.abs(newY-this.y) == 6;
    }
}




class Bishop extends Piece {

    public Bishop(String color, int x, int y) {
        super(color, x, y);
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

    public Rock(String color, int x, int y) {
        super(color, x, y);
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



    public boolean pieceCanMove(int newX, int newY) {
        // Check if move is valid for king
        if (Math.abs((this.x - newX)*(this.y-newY))==1 ||Math.abs(newY-this.y)+ Math.abs( newX-this.x)==1){
            kingX= this.x;
            kingY= this.y;
            return true;}

        if(newX == this.x && isFirst_move && !game.checkKing.isKingChecked(game.squares[0][0], this.x, 4, false)) {
            //check right castle
            if (game.squares[this.x][6].piece == null && game.squares[this.x][5].piece == null && (newY == 5 || newY == 6) &&
                    isSameTeam( game.squares[this.x][7].piece) && game.squares[this.x][7].piece.getClass() == Rock.class) {
                canCastleRight = true;
                return true;
            }
            //check left castle
            if(game.squares[this.x][3].piece == null && game.squares[this.x][2].piece == null && game.squares[this.x][1].piece == null &&
                    (newY == 3 || newY == 2) && isSameTeam( game.squares[this.x][0].piece) && game.squares[this.x][0].piece.getClass() == Rock.class) {
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
        if(this.x == newX) //not have red square on itself
            return Math.abs(this.y - newY) >0;
        return ((this.x == newX && this.y != newY)  || (this.x != newX && this.y == newY)) /* move like rook*/ || (Math.abs(this.x - newX) ==  Math.abs(this.y - newY));/*move like bishop*/

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
}

