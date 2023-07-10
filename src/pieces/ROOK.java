package pieces;

import game.Board;
import pieces.P_TYPE.Types;

public class ROOK extends PIECE {
    private final int points = 5;
    public ROOK(int row, int col, String color) {
        super(row, col, color);
    }

    //returns true if destination cell is valid, when moving a piece
    @Override
    public boolean movePiece(int sy, int sx, int ey, int ex, String playerColor, Board board) {

        if(sy != ey && sx != ex) return false;

        //obstacles check
        int rowDiff = ey - sy;
        int colDiff = ex - sx;

        int rowDir = Integer.signum(rowDiff);
        int colDir = Integer.signum(colDiff);

        for(int i = 1; i < Math.max(Math.abs(rowDiff), Math.abs(colDiff)); i++){
            int row = sy + i * rowDir;
            int col = sx + i * colDir;

            if(board.getPiece(row, col) != null) return false;
        }

//        for (int i = 0; i < 8; i++) {
//            //left  7 -> 0 cell numbering
//            if (sx - i == ex && sy == ey) {
////                for(int t = sx - 1; t >= ex; t--){   //  sx > ex
//                for(int t = ex; t < sx; t++){
//                    if(board.getPiece(sy, t) != null && board.getPiece(sy, t).getPieceColor().equals(playerColor))
//                        return false;
//                }
//                return true;
//            }
//
//            //right  0 -> 7 cell numbering
//            if (sx + i == ex && sy == ey) {
////                for(int t = sx + 1; t <= ex; t++){   //ex > sx
//                for(int t = ex; t > sx; t--){
//                    if(board.getPiece(sy, t) != null && board.getPiece(sy, t).getPieceColor().equals(playerColor))
//                        return false;
//                }
//                return true;
//            }
//
//            //down  0 -> 7 cell numbering
//            if (sy + i == ey && sx == ex) {
//                for(int t = sy+1; t <= ey; t++){
//                    if(board.getPiece(t, sx) != null && board.getPiece(t, sx).getPieceColor().equals(playerColor))
//                        return false;
//                }
//                return true;
//            }
//
//            //up  7 -> 0 cell numbering
//            if (sy - i == ey && sx == ex) {
//                for(int t = sy-1; t >= ey; t--){
//                    if(board.getPiece(t, sx) != null && board.getPiece(t, sx).getPieceColor().equals(playerColor))
//                        return false;
//                }
//                return true;
//            }
//        }

        //ELSE WE EATING. DO SOMETHING HERE LATER
        return true;
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
        return P_TYPE.Types.ROOK;
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
            return "C:\\Users\\prais\\Downloads\\JohnPablok Cburnett Chess set\\PNGs\\With Shadow\\512px\\b_rook_png_shadow_512px.png";
        }
        return "C:\\Users\\prais\\Downloads\\JohnPablok Cburnett Chess set\\PNGs\\With Shadow\\512px\\w_rook_png_shadow_512px.png";
    }
}
