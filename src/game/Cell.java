/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import pieces.PIECE;

public class Cell {
    private int cols;
    private int rows;
    private String color;
    private PIECE piece;
    
    public Cell(int r, int c, String col, PIECE p){
        this.rows = r;
        this.cols = c;
        this.color = col;
        this.piece = p;
    }
    
    public void setPiece(PIECE p){
        this.piece = p;
    }
    
    public PIECE getPiece(){
        return this.piece;
    }
    
    public String getColor(){
        return this.color;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }
}
