//import javax.swing.*;
//
//public class chckScan {
//
//    game board;
//
//
////    public chckScan(game board)
////    {
////        this.board = board;
////    }
////
////    public boolean isKingChecked(Piece squares, int newX, int newY) {
////        Piece king = board.getKing(squares.icon);
////        assert king != null;
////
////        int kingCol = king.y;
////        int kingRow = king.x;
////        System.out.println("checking for check");
////
////        if (square.oldPiece != null && (square.oldPiece.icon == new ImageIcon(getClass().getClassLoader().getResource("resources/white_king2-removebg-preview (2).png")) || square.oldPiece.icon == new ImageIcon(getClass().getClassLoader().getResource("resources/kingo-removebg-preview (1).png")))) {
////            kingCol = newY;
////            kingRow = newX;
////            System.out.println("king is checked");
////        }
////        System.out.println("checking if king checked");
////        return hitByRook(newY, newX, king, kingCol, kingRow, 0, 1) || //up
////                hitByRook(newY, newX, king, kingCol, kingRow, 1, 0) || //right
////                hitByRook(newY, newX, king, kingCol, kingRow, 0, -1) || //down
////                hitByRook(newY, newX, king, kingCol, kingRow, -1, 0) || //left
//                hitByBishop(newY, newX, king, kingCol, kingRow, 1, 1) || //up-right
//                hitByBishop(newY, newX, king, kingCol, kingRow, -1, 1) || //up-left
//                hitByBishop(newY, newX, king, kingCol, kingRow, 1, -1) || //down-right
//                hitByBishop(newY, newX, king, kingCol, kingRow, -1, -1) || //down-left
//                hitByKnight(newY, newX, king); //knights
//    }
//
//    private boolean hitByRook(int col, int row, Piece king, int kingCol, int kingRow, int colVal, int rowVal)
//    {
//        for (int i = 1; i< 8;i++)
//        {
//            if(kingCol + (i * colVal) ==col && kingRow + (i * rowVal) == row)
//                break;
//
//            Piece piece = game.squares[kingRow + (i * rowVal)][kingCol + (i * colVal)].piece;
//            if(piece != null && piece != square.oldPiece){
//
//                    if (!Piece.isSameTeam(piece, king) && (piece.icon == new ImageIcon(getClass().getClassLoader().getResource("resources/rock_white-removebg-preview (2).png"))
//                            || piece.icon == new ImageIcon(getClass().getClassLoader().getResource("resources/rock_black-removebg-preview (1).png")) ||
//                            piece.icon == new ImageIcon(getClass().getClassLoader().getResource("resources/chess-white-queen-removebg-preview (2).png"))
//                            || piece.icon == new ImageIcon(getClass().getClassLoader().getResource("resources/queen_black-removebg-preview (3).png"))))
//                        return true;
//                    break;
//                }
//            }
//            return false;
//        }
//    private boolean hitByBishop(int col, int row, Piece king, int kingCol, int kingRow, int colVal, int rowVal) {
//        for (int i = 1; i < 8; i++) {
//            if (kingCol - (i * colVal) == col && kingRow - (i * rowVal) == row)
//                break;
//
//            Piece piece = game.squares[kingRow - (i * rowVal)][kingCol - (i * colVal)].piece;
//            if (piece != null && piece != square.oldPiece) {
//                if (!Piece.isSameTeam(piece, king) && (piece.icon == new ImageIcon(getClass().getClassLoader().getResource("resources/bishop_white-removebg-preview (2).png"))
//                        || piece.icon == new ImageIcon(getClass().getClassLoader().getResource("resources/bishop_black-removebg-preview (1).png"))
//                        || piece.icon == new ImageIcon(getClass().getClassLoader().getResource("resources/chess-white-queen-removebg-preview (2).png"))
//                        || piece.icon == new ImageIcon(getClass().getClassLoader().getResource("resources/queen_black-removebg-preview (3).png"))))
//                    return true;
//                break;
//            }
//        }
//        return false;
//    }
//
//    private boolean hitByKnight(int col, int row, Piece king) {
//        int[] knightRow = {-3, -2, 2, 3, 3, 2, -2, -3};
//        int[] knightCol = {2, 3, 3, 2, -2, -3, -3, -2};
//
//        for (int i = 0; i < 8; i++) {
//            int newRow = king.x + knightRow[i];
//            int newCol = king.y + knightCol[i];
//
//            if (newRow < 0 || newRow > 7 || newCol < 0 || newCol > 7)
//                continue;
//
//            Piece piece = game.squares[newRow][newCol].piece;
//            if (piece != null && piece != square.oldPiece) {
//                if (piece.icon == new ImageIcon(getClass().getClassLoader().getResource("resources/knight_white-removebg-preview (2).png"))
//                        || piece.icon == new ImageIcon(getClass().getClassLoader().getResource("resources/knight_black-removebg-preview (1).png"))) {
//                    if (!Piece.isSameTeam(piece, king))
//                        return true;
//                }
//            }
//        }
//        return false;
//    }
//    }
//
//
