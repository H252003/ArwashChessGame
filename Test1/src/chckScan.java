import javax.swing.*;

public class chckScan {


    public chckScan() {
    }

    public boolean isKingChecked(square squares, int newX, int newY) {
        square king = game.findKing(TimerLabel.whiteTurn);
        assert king != null;

        int kingCol = king.col;
        int kingRow = king.row;


        if (square.oldPiece != null && square.oldPiece.getClass() == King.class) {
            kingCol = newY;
            kingRow = newX;

        }

        return hitByRook(newY, newX, king, kingCol, kingRow, 0, 1) || //up
                hitByRook(newY, newX, king, kingCol, kingRow, 1, 0) ||  //right
                hitByRook(newY, newX, king, kingCol, kingRow, 0, -1) ||  //down
                hitByRook(newY, newX, king, kingCol, kingRow, -1, 0) || //left

                hitByBishop(newY, newX, king, kingCol, kingRow,-1,-1) || // up left
                hitByBishop(newY,newX, king, kingCol, kingRow,1,-1) || // up right
                hitByBishop(newY, newX, king, kingCol, kingRow,1,1) || // down right
                hitByBishop(newY, newX, king, kingCol, kingRow,-1,1) || //down left

              //  eatByBishop(newY, newX, king, kingCol, kingRow) ||


                hitByKnight(newY, newX, king, kingCol, kingRow); // down left
    }

    private boolean hitByRook(int col, int row, square king, int kingCol, int kingRow, int colVal, int rowVal) {
        for (int i = 1; i < 8; i++) {
            if (kingCol + (i * colVal) == col && kingRow + (i * rowVal) == row)
                break;
            if (kingCol + (i * colVal) > -1 && kingCol + (i * colVal) < 8 && kingRow + (i * rowVal) > -1 && kingRow + (i * rowVal) < 8) {
                Piece piece = game.squares[kingRow + (i * rowVal)][kingCol + (i * colVal)].piece;
                if (piece != null && piece != square.oldPiece) {
                    if (!Piece.isSameTeam(piece, king.piece) && (piece.getClass() == Rock.class || piece.getClass() == Queen.class))
                        return true;
                    break;
                }
            }
        }
        return false;
    }


    private boolean hitByBishop(int col, int row, square king, int kingCol, int kingRow, int colVal, int rowVal) {
        for (int i = 1; i < 8; i++) {
            if (kingCol - (i * colVal) == col && kingRow - (i * rowVal) == row)
                break;
            if (kingCol - (i * colVal) > -1 && kingCol - (i * colVal) < 8 && kingRow - (i * rowVal) > -1 && kingRow - (i * rowVal) < 8) {
                Piece piece = game.squares[kingRow - (i * rowVal)][kingCol - (i * colVal)].piece;
                if (piece != null && piece != square.oldPiece) {
                    if (!Piece.isSameTeam(piece, king.piece) && (piece.getClass() == Bishop.class || piece.getClass() == Queen.class)) //
                        return true;
                    break;
                }
            }
        }
        return false;
    }

//    private boolean checkBishop(Piece p, square king, int col , int row){
//
//        return p!= null && !Piece.isSameTeam(p,king.piece) && p.getClass() == Bishop.class && !(p.y== col && p.x==row);
//    }
//    private boolean eatByBishop(int col, int row, square king, int kingCol, int kingRow) {
//        boolean res1 = false,res2 = false,res3= false,res4 = false,res5 = false,res6 = false,res7 = false, res8 = false,
//                res9 = false, res10 = false, res11 = false, res12 = false;
//
//        if(kingCol - 1 > -1 && kingRow - 1 > -1)
//            res1 = checkBishop(game.squares[kingRow-1][kingCol-1].piece, king, col, row) ;
//        if(kingCol - 2 > -1 && kingRow - 2 > -1)
//            res2 = checkBishop(game.squares[kingRow-2][kingCol-2].piece, king, col, row) ;
//        if(kingCol - 3 > -1 && kingRow - 3 > -1)
//            res3 =   checkBishop(game.squares[kingRow-3][kingCol-3].piece, king, col, row) ;
//        if(kingCol - 1 > -1 && kingRow + 1 < 8)
//            res4 =  checkBishop(game.squares[kingRow-1][kingCol+1].piece, king, col, row) ;
//        if(kingCol - 2 > -1 && kingRow + 2 < 8)
//            res5 =    checkBishop(game.squares[kingRow-2][kingCol+2].piece, king, col, row);
//        if(kingCol - 3 > -1 && kingRow + 3 < 8)
//            res6 =     checkBishop(game.squares[kingRow-3][kingCol+3].piece, king, col, row);
//        if(kingCol + 1 < 8 && kingRow + 1 < 8)
//            res7 =     checkBishop(game.squares[kingRow+1][kingCol+1].piece, king, col, row) ;
//        if(kingCol + 2 < 8 && kingRow + 2 < 8)
//            res8 =    checkBishop(game.squares[kingRow+2][kingCol+2].piece, king, col, row) ;
//        if(kingCol + 3 < 8 && kingRow + 3 < 8)
//            res9 =    checkBishop(game.squares[kingRow+3][kingCol+3].piece, king, col, row) ;
//        if(kingCol + 1 < 8 && kingRow - 1 > -1)
//            res10 =    checkBishop(game.squares[kingRow+1][kingCol-1].piece, king, col, row) ;
//        if(kingCol + 2 < 8 && kingRow - 2 > -1)
//            res11 =    checkBishop(game.squares[kingRow+2][kingCol-2].piece, king, col, row) ;
//        if(kingCol + 3 < 8 && kingRow - 3 > -1)
//            res12 =    checkBishop(game.squares[kingRow+3][kingCol-3].piece, king, col, row) ;
//        return res1 || res2 || res3 || res4 || res5 || res6 || res7 || res8 || res9 || res10 || res11 || res12;
//
//    }

    private boolean checkknight(Piece p, square king, int col , int row){

            return p!= null && !Piece.isSameTeam(p,king.piece) && p.getClass() == Knight.class && !(p.y== col && p.x==row);
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

}



