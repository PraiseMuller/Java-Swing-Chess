package game;

import pieces.BISHOP;
import pieces.KING;
import pieces.KNIGHT;
import pieces.PIECE;
import pieces.Pawn;
import pieces.QUEEN;
import pieces.ROOK;

public class Board {

    private final int size;
    private Cell[][] locations;

    public Board(int s) {
        this.size = s;
        initBoard();
    }

    private void initBoard() {
        this.locations = new Cell[this.size][this.size];
        int start = 0;
        for (int i = 0; i < this.size; i++) {
            int tmp = start;
            for (int j = 0; j < this.size; j++) {
                this.locations[i][j] = new Cell(i, j, tmp % 2 == 0 ? "white" : "black",null);
                tmp++;
            }
            start++;
        }
        
        //initPiecePositions();
        //PAWNS - INITIALIZATION
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {                        
                if(i==6){
                    Pawn p = new Pawn(i, j, "white");
                    this.locations[i][j].setPiece(p);
                }
                if(i==1){
                    Pawn p = new Pawn(i, j, "black");
                    this.locations[i][j].setPiece(p);
                }
            }
        }
        
        //ROOKS - INITIALIZATION//
        //---white----
        ROOK r = new ROOK(0, 0, "black");
        this.locations[0][0].setPiece(r);
        r = new ROOK(0, 7, "black");
        this.locations[0][7].setPiece(r);
        //---black---
        r = new ROOK(7, 0, "white");
        this.locations[7][0].setPiece(r);
        r = new ROOK(7, 7, "white");
        this.locations[7][7].setPiece(r);
        
        
        //KNIGHTS - INITIALIZATION//
        //---white----
        KNIGHT k = new KNIGHT(0, 1, "black");
        this.locations[0][1].setPiece(k);
        k = new KNIGHT(0, 6, "black");
        this.locations[0][6].setPiece(k);
        //---black---
        k = new KNIGHT(7, 1, "white");
        this.locations[7][1].setPiece(k);
        k = new KNIGHT(7, 6, "white");
        this.locations[7][6].setPiece(k);
        
        
        //BISHOPS - INITIALIZATION//
        //---white----
        BISHOP b = new BISHOP(0, 2, "black");
        this.locations[0][2].setPiece(b);
        b = new BISHOP(0, 5, "black");
        this.locations[0][5].setPiece(b);
        //---black---
        b = new BISHOP(7, 2, "white");
        this.locations[7][2].setPiece(b);
        b = new BISHOP(7, 5, "white");
        this.locations[7][5].setPiece(b);
        
        
        //QUEENS - INITIALIZATION//
        //---white----
        QUEEN q = new QUEEN(0, 3, "black");
        this.locations[0][3].setPiece(q);
        //---black---
        q = new QUEEN(7, 3, "white");
        this.locations[7][3].setPiece(q);

        
        //QUEENS - INITIALIZATION//
        //---white----
        KING king = new KING(0, 4, "black");
        this.locations[0][4].setPiece(king);
        //---black---
        king = new KING(7, 4, "white");
        this.locations[7][4].setPiece(king);
    }

    public Cell getCell(int i, int j) {
        return this.locations[i][j];
    }
    
    public PIECE getPiece(int i, int j) {
        return this.locations[i][j].getPiece();
    }
    
    public void setPiece(int i, int j, PIECE p) {
        this.locations[i][j].setPiece(p);
    }

    public int getSize() {
        return this.size;
    }
    
    public String getColor(int i, int j) {
        return this.locations[i][j].getColor();
    }
}
