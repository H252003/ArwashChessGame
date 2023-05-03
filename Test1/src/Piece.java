import javax.swing.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public abstract class Piece {
    public String color;
    private int x;
    private int y;
    public Icon icon;


    public Piece(String color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;


    }
public boolean isSameTeam(Piece p1 , Piece p2)
{
    if(p1 == null || p2 == null)
        return false;
    return p1.color.equals(p2.color);
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

    public abstract boolean isValidMove(int newX, int newY);
}

class Pawn extends Piece {
    private boolean hasMoved;

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

    public boolean isValidMove(int newX, int newY) {


        return true;
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

    public boolean isValidMove(int newX, int newY) {


        return true;
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

        public boolean isValidMove(int newX, int newY) {
            // Check if move is valid for pawn
            // ...

            return true;
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

            public boolean isValidMove(int newX, int newY) {
                // Check if move is valid for pawn
                // ...

                return true;
            }
        }
            class King extends Piece {
                private boolean hasMoved;

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

                public boolean isValidMove(int newX, int newY) {
                    // Check if move is valid for pawn
                    // ...

                    return true;
                }
            }
                class Queen extends Piece {
                    private boolean hasMoved;

                    public Queen(String color, int x, int y) {
                        super(color, x, y);
                        hasMoved = false;
                        try {
                            if (color == "white") {
                                icon= new ImageIcon(getClass().getClassLoader().getResource("resources/chess-white-queen-removebg-preview (2).png"));


                            } else {
                                icon = new ImageIcon(getClass().getClassLoader().getResource("resources/queen_black-removebg-preview (3).png"));}
                        } catch (Exception Ignored) {
                            icon = null;
                        }
                    }

                    public boolean isValidMove(int newX, int newY) {
                        // Check if move is valid for pawn
                        // ...

                        return true;
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
    }}


