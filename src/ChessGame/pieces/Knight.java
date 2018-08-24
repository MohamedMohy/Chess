package ChessGame.pieces;

import ChessGame.Board;
import ChessGame.Tile;

public class Knight extends Piece {
    public Knight(boolean color) {
        super(color, "knight");
    }


    @Override
    public boolean isValidMove(Tile tile, Board board) {
        //can only move 2 horizontal and 1 vertical or 1 horizontal and 2 vertical
        String initialLocation = this.getLocationOnBoard();
        String targetLocation = tile.getLocationOnBoard();
        int initialRow = Integer.parseInt(String.valueOf(initialLocation.charAt(0)));
        int initialCol = charToint(initialLocation.charAt(1));
        int finalRow = Integer.parseInt(String.valueOf(targetLocation.charAt(0)));
        int finalCol = charToint(targetLocation.charAt(1));
        if ((Math.abs(initialCol - finalCol) == 1 && Math.abs(initialRow - finalRow) == 2) || (Math.abs(initialCol - finalCol) == 2 && Math.abs(initialRow - finalRow) == 1))
            return true;
        return false;
    }

}
