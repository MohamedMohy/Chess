package ChessGame.pieces;

import ChessGame.Board;
import ChessGame.Tile;

public class Bishop extends Piece {
    public Bishop(boolean color) {
        super(color, "bishop");
    }


    @Override
    public boolean isValidMove(Tile tile, Board board) {
        String initialLocation = this.getLocationOnBoard();
        String targetLocation = tile.getLocationOnBoard();
        int initialRow = Integer.parseInt(String.valueOf(initialLocation.charAt(0)));
        int initialCol = charToint(initialLocation.charAt(1));
        int finalRow = Integer.parseInt(String.valueOf(targetLocation.charAt(0)));
        int finalCol = charToint(targetLocation.charAt(1));
        if (Math.abs(Integer.parseInt(String.valueOf(initialLocation.charAt(0))) - Integer.parseInt(String.valueOf(targetLocation.charAt(0)))) == Math.abs(charToint(initialLocation.charAt(1)) - charToint(targetLocation.charAt(1)))) {
            //diagonal
            System.out.print("diagonal move");
            if (initialRow < finalRow && initialCol < finalCol) {
                for (int i = initialRow + 1, j = initialCol + 1; i < finalRow && j < finalCol; i++, j++)
                    if (board.tiles[i][j].getPiece() != null)
                        return false;
                return true;
            }
            if (initialRow > finalRow && initialCol > finalCol) {
                for (int i = initialRow - 1, j = initialCol - 1; i > finalRow && j > finalCol; i--, j--)
                    if (board.tiles[i][j].getPiece() != null)
                        return false;
                return true;
            }
            if (initialRow > finalRow && initialCol < finalCol) {
                for (int i = initialRow - 1, j = initialCol + 1; i > finalRow && j < finalCol; i--, j++)
                    if (board.tiles[i][j].getPiece() != null)
                        return false;
                return true;
            }
            if (initialRow < finalRow && initialCol > finalCol) {
                for (int i = initialRow + 1, j = initialCol - 1; i < finalRow && j > finalCol; i++, j--)
                    if (board.tiles[i][j].getPiece() != null)
                        return false;
                return true;
            }

        }
        return false;
    }

}
