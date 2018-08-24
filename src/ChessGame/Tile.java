package ChessGame;

import ChessGame.pieces.Piece;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile extends Button {
    private static boolean firstClick = true;
    private static boolean whiteTurn = true;
    private static Piece temporaryHoldedPiece;
    private static Tile temporaryHoldedTile;
    private static Image oldImage;
    static EventHandler eventHandler = new EventHandler() {
        @Override
        public void handle(Event event) {

            if (firstClick && ((Tile) (event.getSource())).hasPiece()) {
                if (!whiteTurn && ((Tile) (event.getSource())).getPiece().isWhite() || whiteTurn && ((Tile) (event.getSource())).getPiece().isBlack()) {
                    System.err.println("it's not your turn!");
                    return;
                }
                temporaryHoldedTile = (Tile) event.getSource();
                temporaryHoldedPiece = (temporaryHoldedTile).getPiece();
                firstClick = false;
                //todo implement changing style when a piece is clicked
                return;
            }
            if (!firstClick) {
                oldImage = null;
                Object obj = event.getSource();
                if ((Tile) obj == (temporaryHoldedTile)) {
                    System.err.println("you clicked the same tile! nothing happened");
                    temporaryHoldedPiece = null;
                    temporaryHoldedTile = null;
                    return;
                }
                if (((Tile) obj).getPiece() != null && temporaryHoldedPiece != null && (((Tile) obj).getPiece().getColor()).equals(temporaryHoldedPiece.getColor())) {
                    System.err.println("Tile already occupied");
                    return;
                }


                if (temporaryHoldedPiece != null && temporaryHoldedPiece.isValidMove(((Tile) obj), Board.getInstance())) {
                    if (((Tile) obj).getPiece() != null)
                        oldImage = ((Tile) obj).getPiece().getImage();
                    ((Tile) obj).setPiece(temporaryHoldedPiece);
                    ((Tile) obj).getPiece().setLocationOnBoard(((Tile) obj).getLocationOnBoard());
                    ((Tile) obj).getPiece().setTouched(true);
                    ((Tile) obj).setGraphic(new ImageView(temporaryHoldedPiece.getImage()));
                    temporaryHoldedTile.setGraphic(new ImageView(new Image("File:" + System.getProperty("user.dir") + "/src/ChessGame/assets/" + "empty.png")));
                    temporaryHoldedTile.setPiece(null);
                    Board.freeTile(temporaryHoldedTile);
                    Boolean checkMate;
                    for (Tile[] tile : Board.tiles) {
                        for (Tile T : tile) {
                            if (T.getPiece() != null && T.getPiece().getName().equals("king")) {
                                checkMate = Board.checkmate(T);
                                if (checkMate) {
                                    System.out.println("Checkmate!");
                                    if (Board.isInitialCheckmate()) {
                                        System.err.println("You cannot perform that move! save the king");
                                        temporaryHoldedTile.setPiece(((Tile) obj).getPiece());
                                        temporaryHoldedTile.getPiece().setLocationOnBoard(temporaryHoldedTile.getLocationOnBoard());
                                        temporaryHoldedTile.setGraphic(new ImageView(((Tile) obj).getPiece().getImage()));
                                        if (oldImage == null)
                                            ((Tile) obj).setGraphic(new ImageView(new Image("File:" + System.getProperty("user.dir") + "/src/ChessGame/assets/" + "empty.png")));
                                        else ((Tile) obj).setGraphic(new ImageView(oldImage));
                                        ((Tile) obj).setPiece(null);
                                        return;
                                    }
                                    if (((Tile) obj).getPiece().getColor().equals(T.getPiece().getColor())) {
                                        System.err.println("you cannot perform that move!");
                                        temporaryHoldedTile.setPiece(((Tile) obj).getPiece());
                                        temporaryHoldedTile.getPiece().setLocationOnBoard(temporaryHoldedTile.getLocationOnBoard());
                                        temporaryHoldedTile.setGraphic(new ImageView(((Tile) obj).getPiece().getImage()));
                                        ((Tile) obj).setGraphic(new ImageView(new Image("File:" + System.getProperty("user.dir") + "/src/ChessGame/assets/" + "empty.png")));
                                        ((Tile) obj).setPiece(null);
                                        return;
                                    }

                                    Board.setInitialCheckmate(true);
                                    temporaryHoldedTile = null;
                                    temporaryHoldedPiece = null;
                                    whiteTurn = !whiteTurn;
                                    return;

                                } else {
                                    Board.setInitialCheckmate(false);
                                }
                            }
                        }
                    }
                    temporaryHoldedTile = null;
                    temporaryHoldedPiece = null;
                    whiteTurn = !whiteTurn;
                }

                firstClick = true;

            }
        }
    };
    final String locationOnBoard;
    Piece piece;

    public Tile(String locationOnBoard) {
        super();
        this.locationOnBoard = locationOnBoard;
        this.setOnMouseClicked(eventHandler);
    }

    private boolean hasPiece() {
        return this.piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        if (piece != null)
            this.setGraphic(new ImageView(piece.getImage()));

    }

    public String getLocationOnBoard() {
        return locationOnBoard;
    }


}


