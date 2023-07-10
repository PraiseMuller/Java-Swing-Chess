package pieces;

import game.Board;
import pieces.P_TYPE.Types;

public class BISHOP extends PIECE {
    private final int points = 3;
    public BISHOP(int row, int col, String color) {
        super(row, col, color);
    }

    //returns true if destination cell is valid, when moving a piece
    @Override
    public boolean movePiece(int sy, int sx, int ey, int ex, String playerColor, Board board) {

        if(Math.abs(ey - sy) != Math.abs(ex - sx)) return false;

        //obstacles check
//        int rowIncrement = (ey > sy) ? 1 : -1;
//        int colIncrement = (ex > sx) ? 1 : -1;
//
//        int i = 0;
//
//        while( row != ey && col != ex){
//            int row = sy + i * rowIncrement;
//            int col = sx + i * colIncrement;
//            if(board.getPiece(row, col) != null) return false;
//            i++;
//        }
        int rowDiff = ey - sy;
        int colDiff = ex - sx;

        int rowDir = Integer.signum(rowDiff);
        int colDir = Integer.signum(colDiff);

        for(int i = 1; i < Math.abs(rowDiff); i++){
            for(int j = 1; j < Math.abs(colDiff); j++) {
                int row = sy + i * rowDir;
                int col = sx + j * colDir;
                if (board.getPiece(row, col) != null) return false;
            }
        }

        //if(sy != ey && sx != ex) return false;

        //obstacles check
//        int rowDiff = ey - sy;
//        int colDiff = ex - sx;
//
//        int rowDir = Integer.signum(rowDiff);
//        int colDir = Integer.signum(colDiff);
//
//        for(int i = 1; i < Math.max(Math.abs(rowDiff), Math.abs(colDiff)); i++){
//            int row = sy + i * rowDir;
//            int col = sx + i * colDir;
//
//            if(board.getPiece(row, col) != null ) return false;
//        }

//        for(int i = 1; i <= 8; i++){
//            // down left
//            if(sx - i == ex && sy + i == ey){
//                for(int t = sy+1, k = sx - 1; t <= ey; t++){   //down
//                    if (board.getPiece(t, k) != null && board.getPiece(t, k).getPieceColor().equals(playerColor))
//                        return false;
//                    k--;
//                }
//                return true;
//            }
//
//            //down right
//            if(sx + i == ex && sy + i == ey){
//                for(int t = sy+1, k = sx + 1; t <= ey; t++){ //down
//                    if (board.getPiece(t, k) != null && board.getPiece(t, k).getPieceColor().equals(playerColor))
//                        return false;
//                    k++;
//                }
//                return true;
//            }
//
//
//            //up left
//            if(sx - i == ex && sy - i == ey){
//                for(int t = sy-1, k = sx - 1; t >= ey; t--){ //up
//                    if (board.getPiece(t, k) != null && board.getPiece(t, k).getPieceColor().equals(playerColor))
//                        return false;
//                    k--;
//                }
//                return true;
//            }
//
//            //up right
//            if(sx + i == ex && sy - i == ey){
//                for(int t = sy-1, k = sx + 1; t >= ey; t--){ //up
//                    if (board.getPiece(t, k) != null && board.getPiece(t, k).getPieceColor().equals(playerColor))
//                        return false;
//                    k++;
//                }
//                return true;
//            }
//        }

        return true;
    }

    @Override
    public boolean[][] possibleMoves(Board board,int sX, int sY, String playerColor) {
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
        return P_TYPE.Types.BISHOP;
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
            return "C:\\Users\\prais\\Downloads\\JohnPablok Cburnett Chess set\\PNGs\\With Shadow\\512px\\b_bishop_png_shadow_512px.png";
        }

        return "C:\\Users\\prais\\Downloads\\JohnPablok Cburnett Chess set\\PNGs\\With Shadow\\512px\\w_bishop_png_shadow_512px.png";

    }
}
