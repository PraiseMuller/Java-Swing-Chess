/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import game.Board;
import pieces.P_TYPE.Types;

public class Pawn extends PIECE {
    private boolean isFirstMove;
    private final int points = 1;
    public Pawn(int row, int col, String color) {
        super(row, col, color);
        this.isFirstMove = true;
    }

    //returns true if destination cell is valid, when moving a piece
    @Override
    public boolean movePiece(int sy, int sx, int ey, int ex, String playerColor, Board board) {

        if (playerColor.equals("black")) {
            if (this.isFirstMove && ey == sy + 2 && ex == sx && (board.getPiece(sy + 1, ex) == null)) {
                this.isFirstMove = false;
                return true;
            } else if (ey == sy + 1 && ex == sx && (board.getPiece(sy + 1, ex) == null)) {
                this.isFirstMove = false;
                return true;
            }
            //taking a piece -> left
            else if (ey == sy + 1 && ex == sx + 1) {
                PIECE temp = board.getPiece(sy + 1, sx + 1);
                if(temp==null) return false;
                else return !temp.getPieceColor().equals(playerColor);
            }
            //taking a piece -> right
            else if (ey == sy + 1 && ex == sx - 1) {
                PIECE temp = board.getPiece(sy + 1, sx - 1);
                if(temp==null) return false;
                else return !temp.getPieceColor().equals(playerColor);
            }

            //PAWN PROMOTION.
        } 
        
        else {
            if (this.isFirstMove && ey == sy - 2 && ex == sx && (board.getPiece(sy - 1, ex) == null)) {
                this.isFirstMove = false;
                return true;
            } else if (ey == sy - 1 && ex == sx && (board.getPiece(sy - 1, ex) == null)) {
                this.isFirstMove = false;
                return true;
            }
            //taking a piece -> right
            else if (ey == sy - 1 && ex == sx - 1) {
                 PIECE temp = board.getPiece(sy - 1, sx - 1);
                 if(temp==null) return false;
                 else return !temp.getPieceColor().equals(playerColor);
            }
            //taking a piece -> left
            else if (ey == sy - 1 && ex == sx + 1) {
                PIECE temp = board.getPiece(sy - 1, sx + 1);
                if(temp==null) return false;
                else return !temp.getPieceColor().equals(playerColor);
            }

            //PAWN PROMOTION.
        }

        //ELSE WE EATING. DO SOMETHING HERE LATER
        return false;
    }

    @Override
    public boolean[][] possibleMoves(Board board, int sX, int sY, String playerColor) {

        boolean temp = this.isFirstMove;
        boolean[][] b = new boolean[board.getSize()][board.getSize()];

        for(int i = 0; i < b.length; i++){
            for(int j = 0; j < b.length; j++){
                b[i][j] = movePiece(sX, sY, i, j, playerColor, board);
            }
        }

        if(temp) this.isFirstMove = true;   //reset to true.. this fn() changes first move.  it shouldn't.

        return b;
    }

    @Override
    public Types getPieceType() {
        return P_TYPE.Types.PAWN;
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
            return "C:\\Users\\prais\\Downloads\\JohnPablok Cburnett Chess set\\PNGs\\With Shadow\\512px\\b_pawn_png_shadow_512px.png";
        }

        return "C:\\Users\\prais\\Downloads\\JohnPablok Cburnett Chess set\\PNGs\\With Shadow\\512px\\w_pawn_png_shadow_512px.png";

    }
}
