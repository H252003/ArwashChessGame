import javax.swing.*;

public class chckScan {


    public chckScan() {
    }

    public boolean isKingChecked(square squares, int newX, int newY) {
        square king = game.findKing(TimerLabel.whiteTurn);
        assert king != null;

        int kingCol = king.col;
        int kingRow = king.row;
        System.out.println("checking for check");

        if (square.oldPiece != null && square.oldPiece.getClass() == King.class) {
            kingCol = newY;
            kingRow = newX;
            System.out.println("king is checked");
        }
        System.out.println("checking if king checked");
        return hitByRook(newY, newX, king, kingCol, kingRow, 0, 1) || //up
                hitByRook(newY, newX, king, kingCol, kingRow, 1, 0) ||  //right
                hitByRook(newY, newX, king, kingCol, kingRow, 0, -1) ||  //down
                hitByRook(newY, newX, king, kingCol, kingRow, -1, 0) || //left

                hitByBishop(newY, newX, king, kingCol, kingRow,-1,-1) || // up left
                hitByBishop(newY,newX, king, kingCol, kingRow,1,-1) || // up right
                hitByBishop(newY, newX, king, kingCol, kingRow,1,1) || // down right
                hitByBishop(newY, newX, king, kingCol, kingRow,-1,1) ; // down left
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
                    if (!Piece.isSameTeam(piece, king.piece) && (piece.getClass() == Bishop.class || piece.getClass() == Queen.class))
                        return true;
                    break;
                }
            }
        }
        return false;
    }

    private boolean hitByKnight(int col, int row, Piece king) {

        return false;
    }
}


