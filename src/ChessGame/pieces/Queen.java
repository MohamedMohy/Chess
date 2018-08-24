package ChessGame.pieces;

import ChessGame.Board;
import ChessGame.Tile;

public class Queen extends Piece {
    public Queen(boolean color) {
        super(color, "queen");
    }

    @Override
    public boolean isValidMove(Tile tile, Board board) {
        String initialLocation = this.getLocationOnBoard();
        String targetLocation = tile.getLocationOnBoard();
        int initialRow = Integer.parseInt(String.valueOf(initialLocation.charAt(0)));
        int initialCol = charToint(initialLocation.charAt(1));
        int finalRow = Integer.parseInt(String.valueOf(targetLocation.charAt(0)));
        int finalCol = charToint(targetLocation.charAt(1));
        if ((Math.abs(Integer.parseInt(String.valueOf(initialLocation.charAt(0))) - Integer.parseInt(String.valueOf(targetLocation.charAt(0)))) == Math.abs(charToint(initialLocation.charAt(1)) - charToint(targetLocation.charAt(1))))) {
            //diagonal move
            if (initialRow < finalRow && initialCol < finalCol) {
                for (int i = initialRow + 1, j = initialCol + 1; i < finalRow && j < finalCol; i++, j++) {
                    if (board.tiles[i][j].getPiece() != null)
                        return false;
                }
                return true;
            }
            if (initialRow > finalRow && initialCol > finalCol) {
                for (int i = initialRow - 1, j = initialCol - 1; i > finalRow && j > finalCol; i--, j--) {
                    if (board.tiles[i][j].getPiece() != null)
                        return false;
                }
                return true;
            }
            if (initialRow > finalRow && initialCol < finalCol) {
                for (int i = initialRow - 1, j = initialCol + 1; i > finalRow && j < finalCol; i--, j++) {
                    if (board.tiles[i][j].getPiece() != null)
                        return false;
                }
                return true;
            }
            if (initialRow < finalRow && initialCol > finalCol) {
                for (int i = initialRow + 1, j = initialCol - 1; i < finalRow && j > finalCol; i++, j--) {
                    if (board.tiles[i][j].getPiece() != null)
                        return false;
                }
                return true;
            }

        }
        if ((initialLocation.charAt(0) == targetLocation.charAt(0))) {
            //horizontal move
            for (int i = charToint(initialLocation.charAt(1)) + 1; i < charToint(targetLocation.charAt(1)); i++)
                if (board.tiles[Integer.parseInt(String.valueOf(initialLocation.charAt(0)))][i].getPiece() != null)
                    return false;

            for (int i = charToint(initialLocation.charAt(1)) - 1; i > charToint(targetLocation.charAt(1)); i--)
                if (board.tiles[Integer.parseInt(String.valueOf(initialLocation.charAt(0)))][i].getPiece() != null)
                    return false;

            return true;
        }
        if ((initialLocation.charAt(1) == targetLocation.charAt(1))) {
            //moves vertically
            for (int i = Integer.parseInt(String.valueOf(initialLocation.charAt(0))) + 1; i < Integer.parseInt(String.valueOf(targetLocation.charAt(0))); i++)
                if (board.tiles[i][charToint(targetLocation.charAt(1))].getPiece() != null)
                    return false;
            for (int i = Integer.parseInt(String.valueOf(initialLocation.charAt(0))) - 1; i > Integer.parseInt(String.valueOf(targetLocation.charAt(0))); i--)
                if (board.tiles[i][charToint(targetLocation.charAt(1))].getPiece() != null)
                    return false;

            return true;
        }
        return false;

    }

}
