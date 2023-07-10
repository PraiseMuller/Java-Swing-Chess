/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import game.Board;
import game.Cell;
import pieces.P_TYPE.Types;

public class KING extends PIECE {

    public KING(int row, int col, String color) {
        super(row, col, color);
    }

    //returns true if destination cell is valid, when moving a piece
    @Override
    public boolean movePiece(int sy, int sx, int ey, int ex, String playerColor, Board board) {

        PIECE p;

        if (sx - 1 == ex && sy == ey ){
            p = board.getPiece(ey, sx-1);
            if( p != null ) return !p.getPieceColor().equals(playerColor);
            return true;   //up
        }

        if (sx + 1 == ex && sy == ey){
            p = board.getPiece(ey, sx+1);
            if( p != null ) return !p.getPieceColor().equals(playerColor);
            return true;   //down
        }

        if (sy + 1 == ey && sx == ex){
            p = board.getPiece(sy+1, sx);
            if( p != null )
            return !p.getPieceColor().equals(playerColor);
            return true;   //right
        }

        if (sy - 1 == ey && sx == ex){
            p = board.getPiece(sy-1, sx);
            if( p != null ) return !p.getPieceColor().equals(playerColor);
            return true;   //left
        }

        if (sx - 1 == ex && sy + 1 == ey){
            p = board.getPiece(sy+1,sx-1);
            if( p != null ) return !p.getPieceColor().equals(playerColor);
            return true;   //up left
        }

        if (sx - 1 == ex && sy - 1 == ey){
            p = board.getPiece(sy-1, sx-1);
            if( p != null ) return !p.getPieceColor().equals(playerColor);
            return true;   //up right
        }

        if (sx + 1 == ex && sy + 1 == ey){
            p = board.getPiece(sy+1,sx+1);
            if( p != null ) return !p.getPieceColor().equals(playerColor);
            return true;   //down left
        }

        if (sx + 1 == ex && sy - 1 == ey){
            p = board.getPiece(sy-1, sx+1);
            if( p != null ) return !p.getPieceColor().equals(playerColor);
            return true;   //down right
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
        return P_TYPE.Types.KING;
    }

    @Override
    public void takePiece(int sx, int sy, int ex, int ey) {

    }

    @Override
    public int getPiecePoints() {
        return -1;
    }

    @Override
    public String iconLocation() {
        if (this.getPieceColor().equals("black")) {
            return "C:\\Users\\prais\\Downloads\\JohnPablok Cburnett Chess set\\PNGs\\With Shadow\\512px\\b_king_png_shadow_512px.png";
        }

        return "C:\\Users\\prais\\Downloads\\JohnPablok Cburnett Chess set\\PNGs\\With Shadow\\512px\\w_king_png_shadow_512px.png";

    }
}
