package ChessGame.pieces;

import ChessGame.Board;
import ChessGame.Tile;


public class Pawn extends Piece {
    public Pawn(boolean color) {
        super(color, "pawn");
    }


    @Override
    public boolean isValidMove(Tile tile, Board board) {
        String initialLocation = this.getLocationOnBoard();
        String targetLocation = tile.getLocationOnBoard();
        int initialRow = Integer.parseInt(String.valueOf(initialLocation.charAt(0)));
        int initialCol = charToint(initialLocation.charAt(1));
        int finalRow = Integer.parseInt(String.valueOf(targetLocation.charAt(0)));
        int finalCol = charToint(targetLocation.charAt(1));
        if (this.isWhite()) {
            if (initialLocation.charAt(1) == targetLocation.charAt(1)) {
                if (initialLocation.charAt(0) == targetLocation.charAt(0) + 1 || (initialLocation.charAt(0) == targetLocation.charAt(0) + 2) && !this.isTouched()) {
                    for (int i = initialRow - 1; i >= finalRow; i--) {
                        if (board.tiles[i][finalCol].getPiece() != null)
                            return false;
                    }
                    return true;
                }
            }
            if ((initialLocation.charAt(1) == targetLocation.charAt(1) + 1 || initialLocation.charAt(1) == targetLocation.charAt(1) - 1) && initialLocation.charAt(0) == targetLocation.charAt(0) + 1) {
                if (tile.getPiece() != null) {
                    return true;
                }

            }
        }
        if (this.isBlack()) {
            if (initialLocation.charAt(1) == targetLocation.charAt(1) && this.isBlack()) {
                if (initialLocation.charAt(0) == targetLocation.charAt(0) - 1 || (initialLocation.charAt(0) == targetLocation.charAt(0) - 2) && !this.isTouched()) {
                    for (int i = initialRow + 1; i <= finalRow; i++) {
                        if (board.tiles[i][finalCol].getPiece() != null)
                            return false;
                    }
                    return true;
                }
            }
            if ((initialLocation.charAt(1) == targetLocation.charAt(1) + 1 || initialLocation.charAt(1) == targetLocation.charAt(1) - 1) && initialLocation.charAt(0) == targetLocation.charAt(0) - 1) {
                if (tile.getPiece() != null) {
                    return true;
                }

            }
        }
        return false;
    }



}
