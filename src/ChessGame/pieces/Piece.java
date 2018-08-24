package ChessGame.pieces;

import ChessGame.Board;
import ChessGame.Tile;
import javafx.scene.image.Image;

public abstract class Piece {
    // true for white color
    boolean color;
    boolean Touched;
    Image image;
    String name;
    String locationOnBoard;

    public Piece(boolean color, String name) {
        this.color = color;
        this.name = name;
        this.Touched = false;
        this.image = new Image("File:" + System.getProperty("user.dir") + "/src/ChessGame/assets/" + this.getColor() + "_" + this.getName() + ".png");
    }

    public static int charToint(char c) {
        return (int) c - 65;
    }

    public String getLocationOnBoard() {
        return locationOnBoard;
    }

    public void setLocationOnBoard(String locationOnBoard) {
        this.locationOnBoard = locationOnBoard;
    }

    public String getColor() {
        return (this.color) ? "white" : "black";
    }

    public boolean isWhite() {
        return color;
    }

    public boolean isBlack() {
        return !color;
    }

    public boolean isTouched() {
        return Touched;
    }

    public void setTouched(boolean Touched) {
        this.Touched = Touched;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract boolean isValidMove(Tile tile, Board board);


}
