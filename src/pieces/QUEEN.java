/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import game.Board;
import game.Cell;
import pieces.P_TYPE.Types;

public class QUEEN extends PIECE {
    private final int points = 9;
    public QUEEN(int row, int col, String color) {
        super(row, col, color);
    }

    //returns true if destination cell is valid, when moving a piece
    @Override
    public boolean movePiece(int sy, int sx, int ey, int ex, String playerColor, Board board) {

        if(sy != ey && sx != ex && Math.abs(ey - sy) != Math.abs(ex - sx)) return false;

        //ROOK's logic/////////////////////////////////////////////////////////

        //obstacles check
        int rowDiff = ey - sy;
        int colDiff = ex - sx;

        int rowDir = Integer.signum(rowDiff);
        int colDir = Integer.signum(colDiff);

        for(int i = 1; i < Math.max(Math.abs(rowDiff), Math.abs(colDiff)); i++){
            int row = sy + i * rowDir;
            int col = sx + i * colDir;

            if(board.getPiece(row, col) != null ) return false;
        }

        //BISHOP's logic////////////////////////////////////////////////////////////////

        //obstacles check
        int rowIncrement = (ey > sy) ? 1 : -1;
        int colIncrement = (ex > sx) ? 1 : -1;

        int row = sy;
        int col = sx;

        while( row != ey && col != ex){
            row += rowIncrement;
            col += colIncrement;
            if(board.getPiece(row, col) != null) return false;
        }

        return true;
    }

    @Override
    public boolean[][] possibleMoves(Board board, int sX, int sY, String playerColor) {
        boolean[][] b = new boolean[board.getSize()][board.getSize()];
        for(int i = 0; i < b.length; i++){
            for(int j = 0; j < b.length; j++){
                if(movePiece(sX, sY, i, j, playerColor, board))
                    b[i][j] = true;
            }
        }
        return b;
    }

    @Override
    public Types getPieceType() {
        return P_TYPE.Types.QUEEN;
    }

    @Override
    public void takePiece(int sx, int sy, int ex, int ey) {

    }

    @Override
    public int getPiecePoints() {
        return this.points;
    }

    @Override
    public String iconLocation() {
        if (this.getPieceColor().equals("black")) {
            return "C:\\Users\\prais\\Downloads\\JohnPablok Cburnett Chess set\\PNGs\\With Shadow\\512px\\b_queen_png_shadow_512px.png";
        }

        return "C:\\Users\\prais\\Downloads\\JohnPablok Cburnett Chess set\\PNGs\\With Shadow\\512px\\w_queen_png_shadow_512px.png";

    }
}
