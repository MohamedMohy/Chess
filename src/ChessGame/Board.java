package ChessGame;

import ChessGame.pieces.*;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.List;

public class Board extends GridPane {
    public static Tile[][] tiles;
    private static Board ourInstance = new Board();
    private Image emptyImage;
    public static boolean initialCheckmate =false;
    //todo create list of all possible moves
    public List<List<Tile>> blackMoves = new ArrayList<List<Tile>>();
    public List<List<Tile>> whiteMoves = new ArrayList<List<Tile>>();

    public static boolean isInitialCheckmate() {
        return initialCheckmate;
    }

    public static void setInitialCheckmate(boolean initialCheckmate) {
        Board.initialCheckmate = initialCheckmate;
    }

    private Board() {
        emptyImage = new Image("File:" + System.getProperty("user.dir") + "/src/ChessGame/assets/" +"empty.png");
        int size = 8;
        tiles = new Tile[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                String currentLocation = Integer.toString(row) + getCharForNumber(col);
                Tile square = new Tile(currentLocation);
                if ((row + col) % 2 == 0) square.setStyle("-fx-background-color: white;");
                else square.setStyle("-fx-background-color: saddlebrown;");
                tiles[row][col] = square;
                initStartPosition(row, col);
                this.add(square, col, row);

            }
        }
        for (int i = 0; i < 8; i++) {
            this.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            this.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }

    }

    public static Board getInstance() {
        return ourInstance;
    }

    public static void freeTile(Tile tile) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tiles[i][j].equals(tile))
                    tiles[i][j].setPiece(null);

            }
        }
    }

    private static boolean stalemate(Tile king) {
        //todo populate moves list and check if any move makes checkmate return false from that color

        return false;
    }

    public static boolean checkmate(Tile king) {
        String kingColor = king.getPiece().getColor();
        boolean flag = false;
        int row = Integer.parseInt(String.valueOf(king.locationOnBoard.charAt(0)));
        int col = Piece.charToint(king.locationOnBoard.charAt(1));
        boolean firstDiagonalMove = true;
        //the 8 knight positions
        if (row + 2 < 8 && col + 1 < 8)
            if (tiles[row + 2][col + 1].getPiece() != null && tiles[row + 2][col + 1].getPiece().getColor() != kingColor && tiles[row + 2][col + 1].getPiece().getName().equals("knight"))
                flag = true;
        if (row + 1 < 8 && col + 2 < 8)
            if (tiles[row + 1][col + 2].getPiece() != null && tiles[row + 1][col + 2].getPiece().getColor() != kingColor && tiles[row + 1][col + 2].getPiece().getName().equals("knight"))
                flag = true;
        if (row - 1 >= 0 && col + 2 < 8)
            if (tiles[row - 1][col + 2].getPiece() != null && tiles[row - 1][col + 2].getPiece().getColor() != kingColor && tiles[row - 1][col + 2].getPiece().getName().equals("knight"))
                flag = true;
        if (row - 2 >= 0 && col + 1 < 8)
            if (tiles[row - 2][col + 1].getPiece() != null && tiles[row - 2][col + 1].getPiece().getColor() != kingColor && tiles[row - 2][col + 1].getPiece().getName().equals("knight"))
                flag = true;
        if (row - 1 >= 0 && col - 2 >= 0)
            if (tiles[row - 1][col - 2].getPiece() != null && tiles[row - 1][col - 2].getPiece().getColor() != kingColor && tiles[row - 1][col - 2].getPiece().getName().equals("knight"))
                flag = true;
        if (row - 2 >= 0 && col - 1 >= 0)
            if (tiles[row - 2][col - 1].getPiece() != null && tiles[row - 2][col - 1].getPiece().getColor() != kingColor && tiles[row - 2][col - 1].getPiece().getName().equals("knight"))
                flag = true;
        if (row + 2 < 8 && col - 1 >= 0)
            if (tiles[row + 2][col - 1].getPiece() != null && tiles[row + 2][col - 1].getPiece().getColor() != kingColor && tiles[row + 2][col - 1].getPiece().getName().equals("knight"))
                flag = true;
        if (row + 1 < 8 && col - 2 >= 0)
            if (tiles[row + 1][col - 2].getPiece() != null && tiles[row + 1][col - 2].getPiece().getColor() != kingColor && tiles[row + 1][col - 2].getPiece().getName().equals("knight"))
                flag = true;
        for (int i = row + 1, j = col + 1; i < 8 && j < 8; i++, j++) {
            Piece piece = tiles[i][j].getPiece();
            if (piece != null) {
                if (piece.getColor().equals(kingColor))
                    break;
                if ((piece.getName().equals("queen") || piece.getName().equals("bishop"))) {
                    flag = true;
                    break;
                }
                if (piece.getName().equals("pawn") && firstDiagonalMove) {
                    flag = true;
                    break;
                }
            }
            firstDiagonalMove = false;
        }
        firstDiagonalMove = true;
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            Piece piece = tiles[i][j].getPiece();
            if (piece != null) {
                if (piece.getColor().equals(kingColor))
                    break;
                if (piece.getName().equals("queen") || piece.getName().equals("bishop")) {
                    flag = true;
                    break;
                }
                if ((piece.getName().equals("pawn") && firstDiagonalMove)) {
                    flag = true;
                    break;
                }
            }
            firstDiagonalMove = false;
        }
        firstDiagonalMove = true;
        for (int i = row - 1, j = col + 1; i >= 0 && j < 8; i--, j++) {
            Piece piece = tiles[i][j].getPiece();
            if (piece != null) {
                if ((piece.getColor().equals(kingColor)))
                    break;
                if (piece.getName().equals("queen") || piece.getName().equals("bishop")) {
                    flag = true;
                    break;
                }
                if (piece.getName().equals("pawn") && firstDiagonalMove) {
                    flag = true;
                    break;
                }
            }
            firstDiagonalMove = false;
        }
        firstDiagonalMove = true;
        for (int i = row + 1, j = col - 1; i < 8 && j >= 0; i++, j--) {
            Piece piece = tiles[i][j].getPiece();
            if (piece != null) {
                if (piece.getColor().equals(kingColor))
                    break;
                if (piece.getName().equals("queen") || piece.getName().equals("bishop")) {
                    flag = true;
                    break;
                }
                if (piece.getName().equals("pawn") && firstDiagonalMove) {
                    flag = true;
                    break;
                }
            }
            firstDiagonalMove = false;
        }
        for (int i = col + 1; i < 8; i++) {
            Piece piece = tiles[row][i].getPiece();
            if (piece != null) {
                if (piece.getColor() == kingColor)
                    break;
                if (piece.getName().equals("rook") || piece.getName().equals("queen")) {
                    flag = true;
                    break;
                } else break;
            }
        }
        for (int i = col - 1; i >= 0; i--) {
            Piece piece = tiles[row][i].getPiece();
            if (piece != null) {
                if (piece.getColor().equals(kingColor))
                    break;
                if ((piece.getName().equals("rook") || piece.getName().equals("queen"))) {
                    flag = true;
                    break;
                } else break;
            }
        }
        // check up and down
        for (int i = row + 1; i < 8; i++) {
            Piece piece = tiles[i][col].getPiece();
            if (piece != null) {

                if (piece.getColor().equals(kingColor))
                    break;
                if (piece.getName().equals("rook") || piece.getName().equals("queen")) {
                    flag = true;
                    break;
                } else break;

            }
        }
        for (int i = row - 1; i >= 0; i--) {
            Piece piece = tiles[i][col].getPiece();
            if (piece != null) {
                if (piece.getColor().equals(kingColor))
                    break;
                if (piece.getName().equals("rook") || piece.getName().equals("queen")) {
                    flag = true;
                    break;
                } else break;
            }
        }

        return flag;
    }

    private void initStartPosition(int row, int col) {

        if (row == 1) {
            tiles[row][col].setPiece(new Pawn(false));
            tiles[row][col].getPiece().setLocationOnBoard(row + getCharForNumber(col));
        } else if (row == 6) {
            tiles[row][col].setPiece(new Pawn(true));
            tiles[row][col].getPiece().setLocationOnBoard(row + getCharForNumber(col));
        } else if (row == 0) {
            switch (col) {
                case 0:
                    tiles[row][col].setPiece(new Rook(false));
                    tiles[row][col].getPiece().setLocationOnBoard(row + getCharForNumber(col));
                    break;
                case 1:
                    tiles[row][col].setPiece(new Knight(false));
                    tiles[row][col].getPiece().setLocationOnBoard(row + getCharForNumber(col));
                    break;
                case 2:
                    tiles[row][col].setPiece(new Bishop(false));
                    tiles[row][col].getPiece().setLocationOnBoard(row + getCharForNumber(col));
                    break;
                case 3:
                    tiles[row][col].setPiece(new Queen(false));
                    tiles[row][col].getPiece().setLocationOnBoard(row + getCharForNumber(col));
                    break;
                case 4:
                    tiles[row][col].setPiece(new King(false));
                    tiles[row][col].getPiece().setLocationOnBoard(row + getCharForNumber(col));
                    break;
                case 5:
                    tiles[row][col].setPiece(new Bishop(false));
                    tiles[row][col].getPiece().setLocationOnBoard(row + getCharForNumber(col));
                    break;
                case 6:
                    tiles[row][col].setPiece(new Knight(false));
                    tiles[row][col].getPiece().setLocationOnBoard(row + getCharForNumber(col));
                    break;
                case 7:
                    tiles[row][col].setPiece(new Rook(false));
                    tiles[row][col].getPiece().setLocationOnBoard(row + getCharForNumber(col));
                    break;
            }
        } else if (row == 7) {
            switch (col) {
                case 0:
                    tiles[row][col].setPiece(new Rook(true));
                    tiles[row][col].getPiece().setLocationOnBoard(row + getCharForNumber(col));
                    break;
                case 1:
                    tiles[row][col].setPiece(new Knight(true));
                    tiles[row][col].getPiece().setLocationOnBoard(row + getCharForNumber(col));
                    break;
                case 2:
                    tiles[row][col].setPiece(new Bishop(true));
                    tiles[row][col].getPiece().setLocationOnBoard(row + getCharForNumber(col));
                    break;
                case 3:
                    tiles[row][col].setPiece(new Queen(true));
                    tiles[row][col].getPiece().setLocationOnBoard(row + getCharForNumber(col));
                    break;
                case 4:
                    tiles[row][col].setPiece(new King(true));
                    tiles[row][col].getPiece().setLocationOnBoard(row + getCharForNumber(col));
                    break;
                case 5:
                    tiles[row][col].setPiece(new Bishop(true));
                    tiles[row][col].getPiece().setLocationOnBoard(row + getCharForNumber(col));
                    break;
                case 6:
                    tiles[row][col].setPiece(new Knight(true));
                    tiles[row][col].getPiece().setLocationOnBoard(row + getCharForNumber(col));
                    break;
                case 7:
                    tiles[row][col].setPiece(new Rook(true));
                    tiles[row][col].getPiece().setLocationOnBoard(row + getCharForNumber(col));
                    break;
            }
        } else tiles[row][col].setGraphic(new ImageView(emptyImage));

    }

    private String getCharForNumber(int i) {
        return i >= 0 && i < 27 ? String.valueOf((char) (i + 65)) : null;
    }
}
