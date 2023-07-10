/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import game.Board;
import game.Cell;
import pieces.P_TYPE.Types;

public abstract class PIECE {
    private final int row;
    private final int col;
    private final String color;
    
    public PIECE(int row, int col, String color){
        this.row = row;
        this.col = col;
        this.color = color;
    }
    
    public abstract boolean movePiece(int sx, int sy, int ex, int ey, String color, Board board);
    
    public abstract boolean[][] possibleMoves(Board board, int sX, int sY, String playerColor);
    
    public abstract Types getPieceType();

    public abstract void takePiece(int sx, int sy, int ex, int ey);
    
    public abstract String iconLocation();
    
    public String getPieceColor(){
        return this.color;
    }

    public abstract int getPiecePoints();
}
