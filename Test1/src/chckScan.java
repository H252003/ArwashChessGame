import javax.swing.*;

public class chckScan {


    public chckScan() {
    }

    public boolean isKingChecked(square squares, int newX, int newY, boolean virtualKingIsMoving) {
        //find the king to start from its square
        square king = game.findKing(TimerLabel.whiteTurn);
        assert king != null;
        //assign the coordinates of the king
        int kingCol = king.col;
        int kingRow = king.row;

            //make the king move to the gven coordinates
        if (square.oldPiece != null && square.oldPiece instanceof King) {
            kingCol = newY;
            kingRow = newX;

        }
        if(virtualKingIsMoving)
        {
            kingCol = newY;
            kingRow = newX;
        }

        return hitByRook(newY, newX, king, kingCol, kingRow, 0, 1, virtualKingIsMoving) || //down of king
                hitByRook(newY, newX, king, kingCol, kingRow, 1, 0, virtualKingIsMoving) ||  //right of king
                hitByRook(newY, newX, king, kingCol, kingRow, 0, -1, virtualKingIsMoving) ||  //up of king
                hitByRook(newY, newX, king, kingCol, kingRow, -1, 0, virtualKingIsMoving) || //left of king

                hitByBishop(newY, newX, king, kingCol, kingRow,-1,-1, virtualKingIsMoving) || // up left
                hitByBishop(newY,newX, king, kingCol, kingRow,1,-1, virtualKingIsMoving) || // up right
                hitByBishop(newY, newX, king, kingCol, kingRow,1,1, virtualKingIsMoving) || // down right
                hitByBishop(newY, newX, king, kingCol, kingRow,-1,1, virtualKingIsMoving) || //down left

                eatByBishop(newY, newX, king, kingCol, kingRow) ||


                hitByKnight(newY, newX, king, kingCol, kingRow) ||
                hitByPawn(newY, newX, king, kingCol, kingRow) ||
                hitByKing(king,kingCol,kingRow);

    }

    //check if the king is checked by a rook or a rook is in the way
    private boolean hitByRook(int col, int row, square king, int kingCol, int kingRow, int colVal, int rowVal,boolean virtualKing) {
        for (int i = 1; i < 8; i++) {
            //condition to stop when arrive at the needed square
            if (kingCol + (i * colVal) == col && kingRow + (i * rowVal) == row && !virtualKing)
                break;
            //check if in boundaries
            if (kingCol + (i * colVal) > -1 && kingCol + (i * colVal) < 8 && kingRow + (i * rowVal) > -1 && kingRow + (i * rowVal) < 8) {
                //get the piece on this square
                Piece piece = game.squares[kingRow + (i * rowVal)][kingCol + (i * colVal)].piece;
                //check if the square have a piece & pinned pieces to protect the king
                if (piece != null && piece != square.oldPiece) {
                    if (!piece.isSameTeam(king.piece) && (piece.getClass() == Rock.class || piece.getClass() == Queen.class))
                        return true;
                    //
                    else if(virtualKing && piece instanceof King && piece.isSameTeam(king.piece))
                        continue;
                    break;
                }
            }
        }
        return false;
    }


    private boolean hitByBishop(int col, int row, square king, int kingCol, int kingRow, int colVal, int rowVal, boolean virtualKing) {
        for (int i = 1; i < 8; i++) {
            if (kingCol - (i * colVal) == col && kingRow - (i * rowVal) == row && !virtualKing)
                break;
            if (kingCol - (i * colVal) > -1 && kingCol - (i * colVal) < 8 && kingRow - (i * rowVal) > -1 && kingRow - (i * rowVal) < 8) {
                Piece piece = game.squares[kingRow - (i * rowVal)][kingCol - (i * colVal)].piece;
                if (piece != null && piece != square.oldPiece) {//
                    if (!piece.isSameTeam(king.piece) && (piece.getClass() == Queen.class)) //piece.getClass() == Bishop.class ||
                        return true;
                    else if(virtualKing && piece instanceof King && piece.isSameTeam(king.piece))
                        continue;
                    break;
                }
            }
        }
        return false;
    }

    //check if the piece is bishop
    private boolean checkBishop(Piece p, square king, int col , int row){

        return p!= null && !p.isSameTeam(king.piece) && p instanceof Bishop && !(p.y== col && p.x==row); //not to consider its square
    }
    private boolean eatByBishop(int col, int row, square king, int kingCol, int kingRow) {
        boolean res1 = false,res2 = false,res3= false,res4 = false,res5 = false,res6 = false,res7 = false, res8 = false,
                res9 = false, res10 = false, res11 = false, res12 = false;

        if(kingCol - 1 > -1 && kingRow - 1 > -1)
            res1 = checkBishop(game.squares[kingRow-1][kingCol-1].piece, king, col, row) ;
        if(kingCol - 2 > -1 && kingRow - 2 > -1)
            res2 = checkBishop(game.squares[kingRow-2][kingCol-2].piece, king, col, row) ;
        if(kingCol - 3 > -1 && kingRow - 3 > -1)
            res3 =   checkBishop(game.squares[kingRow-3][kingCol-3].piece, king, col, row) ;
        if(kingCol - 1 > -1 && kingRow + 1 < 8)
            res4 =  checkBishop(game.squares[kingRow+1][kingCol-1].piece, king, col, row) ;
        if(kingCol - 2 > -1 && kingRow + 2 < 8)
            res5 =    checkBishop(game.squares[kingRow+2][kingCol-2].piece, king, col, row);
        if(kingCol - 3 > -1 && kingRow + 3 < 8)
            res6 =     checkBishop(game.squares[kingRow+3][kingCol-3].piece, king, col, row);
        if(kingCol + 1 < 8 && kingRow + 1 < 8)
            res7 =     checkBishop(game.squares[kingRow+1][kingCol+1].piece, king, col, row) ;
        if(kingCol + 2 < 8 && kingRow + 2 < 8)
            res8 =    checkBishop(game.squares[kingRow+2][kingCol+2].piece, king, col, row) ;
        if(kingCol + 3 < 8 && kingRow + 3 < 8)
            res9 =    checkBishop(game.squares[kingRow+3][kingCol+3].piece, king, col, row) ;
        if(kingCol + 1 < 8 && kingRow - 1 > -1)
            res10 =    checkBishop(game.squares[kingRow-1][kingCol+1].piece, king, col, row) ;
        if(kingCol + 2 < 8 && kingRow - 2 > -1)
            res11 =    checkBishop(game.squares[kingRow-2][kingCol+2].piece, king, col, row) ;
        if(kingCol + 3 < 8 && kingRow - 3 > -1)
            res12 =    checkBishop(game.squares[kingRow-3][kingCol+3].piece, king, col, row) ;
        return res1 || res2 || res3 || res4 || res5 || res6 || res7 || res8 || res9 || res10 || res11 || res12;

    }

    private boolean checkknight(Piece p, square king, int col , int row){

        return p!= null && !p.isSameTeam(king.piece) && p instanceof Knight && !(p.y== col && p.x==row);
    }

    private boolean hitByKnight(int col, int row, square king, int kingCol, int kingRow) {
        boolean res1 = false,res2 = false,res3= false,res4 = false,res5 = false,res6 = false,res7 = false, res8 = false;
        if(kingCol - 3 > -1 && kingRow - 2 > -1)
            res1 = checkknight(game.squares[kingRow-2][kingCol-3].piece, king, col, row) ;
        if(kingCol + 3 < 8 && kingRow - 2 > -1)
            res2 = checkknight(game.squares[kingRow-2][kingCol+3].piece, king, col, row) ;
        if(kingCol + 2 < 8 && kingRow - 3 > -1)
            res3 =   checkknight(game.squares[kingRow-3][kingCol+2].piece, king, col, row) ;
        if(kingCol + 2 < 8 && kingRow + 3 < 8)
            res4 =  checkknight(game.squares[kingRow+3][kingCol+2].piece, king, col, row) ;
        if(kingCol + 3 < 8 && kingRow + 2 < 8)
            res5 =    checkknight(game.squares[kingRow+2][kingCol+3].piece, king, col, row);
        if(kingCol - 3 > -1 && kingRow + 2 < 8)
            res6 =     checkknight(game.squares[kingRow+2][kingCol-3].piece, king, col, row);
        if(kingCol - 2 > -1 && kingRow + 3 < 8)
            res7 =     checkknight(game.squares[kingRow+3][kingCol-2].piece, king, col, row) ;
        if(kingCol - 2 > -1 && kingRow - 3 > -1)
            res8 =    checkknight(game.squares[kingRow-3][kingCol-2].piece, king, col, row) ;

        return res1 || res2 || res3 || res4 || res5 || res6 || res7 || res8;

    }
    private boolean checkKing(Piece p, square king){

        return p!= null && !p.isSameTeam(king.piece) && p instanceof King ;
    }

    private boolean hitByKing( square king, int kingCol, int kingRow) {
        boolean res1 = false,res2 = false,res3= false,res4 = false,res5 = false,res6 = false,res7 = false, res8 = false;
        if(kingCol - 1 > -1 && kingRow - 1 > -1)
            res1 = checkKing(game.squares[kingRow-1][kingCol-1].piece, king) ;
        if(kingCol +1 < 8 && kingRow - 1 > -1)
            res2 = checkKing(game.squares[kingRow-1][kingCol+1].piece, king) ;
        if(kingRow - 1 > -1)
            res3 =   checkKing(game.squares[kingRow-1][kingCol].piece, king) ;
        if(kingCol -1 > -1)
            res4 =  checkKing(game.squares[kingRow][kingCol-1].piece, king) ;
        if(kingCol + 1 < 8)
            res5 =    checkKing(game.squares[kingRow][kingCol+1].piece, king);
        if(kingCol - 1 > -1 && kingRow + 1 < 8)
            res6 =     checkKing(game.squares[kingRow+1][kingCol-1].piece, king);
        if(kingCol +1 < 8 && kingRow + 1< 8)
            res7 =     checkKing(game.squares[kingRow+1][kingCol+1].piece, king) ;
        if(kingRow +1 <8 )
            res8 =    checkKing(game.squares[kingRow+1][kingCol].piece, king) ;

        return res1 || res2 || res3 || res4 || res5 || res6 || res7 || res8;

    }
    private  boolean hitByPawn(int col, int row, square king, int kingCol, int kingRow){
        int colorVal ; //choose to move in one direction depend on color
        boolean res1 = false ;
        boolean res2 = false ;
        boolean res3 = false ;
        if(king.piece.color.equals("white")){
            colorVal = 1 ;
        }
        else {
            colorVal = -1 ;}

        if(kingCol +1 <8 ){
            //check in boundaries
            if(kingRow+colorVal>-1 && kingRow+colorVal<8){
                res1 = checkPawn(game.squares[kingRow+colorVal][kingCol+1].piece, king, col, row) ;
            }


        }
        if(kingCol -1 > -1 ){
            if(kingRow+colorVal>-1 && kingRow+colorVal<8){
                res2 = checkPawn(game.squares[kingRow+colorVal][kingCol-1].piece, king, col, row) ;
            }


        }
        if(kingRow+colorVal>-1 && kingRow+colorVal<8 ){  //&& kingCol==col
            res3 = checkPawn(game.squares[kingRow+colorVal][kingCol].piece, king, col, row) ;
        }

        return res1||res2||res3;

    }
    private boolean checkPawn(Piece p, square king,int col , int row){

        return p!= null && !p.isSameTeam(king.piece) && p instanceof Pawn && !(p.y== col && p.x==row) ; //
    }
}
