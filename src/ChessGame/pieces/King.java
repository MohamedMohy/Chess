package ChessGame.pieces;

import ChessGame.Board;
import ChessGame.Tile;

public class King extends Piece {
    public static boolean checkMatePosition = false;

    public King(boolean color) {
        super(color, "king");
    }

    public static boolean isCheckMatePosition() {
        return checkMatePosition;
    }

    public static void setCheckMatePosition(boolean checkMatePosition) {
        King.checkMatePosition = checkMatePosition;
    }



    @Override
    public boolean isValidMove(Tile tile, Board board) {
        String initialLocation = this.getLocationOnBoard();
        String targetLocation = tile.getLocationOnBoard();
        int initialRow = Integer.parseInt(String.valueOf(initialLocation.charAt(0)));
        int initialCol = charToint(initialLocation.charAt(1));
        int finalRow = Integer.parseInt(String.valueOf(targetLocation.charAt(0)));
        int finalCol = charToint(targetLocation.charAt(1));
        if (Math.abs(initialRow - finalRow) <= 1 && Math.abs(initialCol - finalCol) <= 1)
            return true;
        return false;
    }

}
