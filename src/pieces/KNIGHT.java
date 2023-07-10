package pieces;

import game.Board;
import pieces.P_TYPE.Types;

public class KNIGHT extends PIECE {
    private final int points = 3;
    public KNIGHT(int row, int col, String color) {
        super(row, col, color);
    }

    //returns true if destination cell is valid, when moving a piece
    @Override
    public boolean movePiece(int sy, int sx, int ey, int ex, String playerColor, Board board) {

        PIECE p;

        if(sx - 2 == ex && sy - 1 == ey){
            //up left
            p = board.getPiece(sy - 1, sx - 2);
            if( p != null ) return !p.getPieceColor().equals(playerColor);
            return true;
        }

        if(sx - 2 == ex && sy + 1 == ey){
            //up right
            p = board.getPiece(sy + 1, sx - 2);
            if( p != null ) return !p.getPieceColor().equals(playerColor);
            return true;
        }

        if(sx + 2 == ex && sy - 1 == ey){
            //down left
            p = board.getPiece(sy - 1, sx + 2);
            if( p != null ) return !p.getPieceColor().equals(playerColor);
            return true;
        }

        if(sx + 2 == ex && sy + 1 == ey){
            //down right
            p = board.getPiece(sy + 1, sx + 2);
            if( p != null ) return !p.getPieceColor().equals(playerColor);
            return true;
        }

        //////lost track of this here, but it works
        if(sy - 2 == ey && sx - 1 == ex){
            //left, up ?
            p = board.getPiece(sy - 2, sx - 1);
            if( p != null ) return !p.getPieceColor().equals(playerColor);
            return true;
        }

        if(sy - 2 == ey && sx + 1 == ex){
            //left, down ?
            p = board.getPiece(sy - 2, sx + 1);
            if( p != null ) return !p.getPieceColor().equals(playerColor);
            return true;
        }

        if(sy + 2 == ey && sx + 1 == ex){
            //right up ?
            p = board.getPiece(sy + 2, sx + 1);
            if( p != null ) return !p.getPieceColor().equals(playerColor);
            return true;
        }

        if(sy + 2 == ey && sx - 1 == ex){
            //right down ?
            p = board.getPiece(sy + 2, sx - 1);
            if( p != null ) return !p.getPieceColor().equals(playerColor);
            return true;
        }

        //ELSE WE EATING. DO SOMETHING HERE LATER
        return false;
    }

    @Override
    public boolean[][] possibleMoves(Board board, int sX, int sY, String playerColor) {
        boolean[][] b = new boolean[board.getSize()][board.getSize()];
        for(int i = 0; i < b.length; i++){
            for(int j = 0; j < b.length; j++){
                b[i][j] = movePiece(sX, sY, i, j, playerColor, board);
            }
        }
        return b;
    }

    @Override
    public Types getPieceType() {
        return P_TYPE.Types.KNIGHT;
    }

    @Override
    public void takePiece(int sx, int sy, int ex, int ey) {

    }

    @Override
    public String iconLocation() {
        if (this.getPieceColor().equals("black")) {
            return "C:\\Users\\prais\\Downloads\\JohnPablok Cburnett Chess set\\PNGs\\With Shadow\\256px\\b_knight_png_shadow_256px.png";
        }

        return "C:\\Users\\prais\\Downloads\\JohnPablok Cburnett Chess set\\PNGs\\With Shadow\\512px\\w_knight_png_shadow_512px.png";

    }

    @Override
    public int getPiecePoints() {
        return this.points;
    }
}
