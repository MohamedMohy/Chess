package ChessGame.pieces;

import ChessGame.Board;
import ChessGame.Tile;

public class Rook extends Piece {
    public Rook(boolean color) {
        super(color, "rook");
    }


    @Override
    public boolean isValidMove(Tile tile, Board board) {
        String initialLocation = this.getLocationOnBoard();
        String targetLocation = tile.getLocationOnBoard();

        if (initialLocation.charAt(0) == targetLocation.charAt(0)) {
            //horizontal move
            for (int i = charToint(initialLocation.charAt(1)) + 1; i < charToint(targetLocation.charAt(1)); i++)
                if (board.tiles[Integer.parseInt(String.valueOf(initialLocation.charAt(0)))][i].getPiece() != null)
                    return false;

            for (int i = charToint(initialLocation.charAt(1)) - 1; i > charToint(targetLocation.charAt(1)); i--)
                if (board.tiles[Integer.parseInt(String.valueOf(initialLocation.charAt(0)))][i].getPiece() != null)
                    return false;

            return true;
        }
        if (initialLocation.charAt(1) == targetLocation.charAt(1)) {
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
