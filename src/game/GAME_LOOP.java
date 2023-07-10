package game;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import javax.swing.*;

import pieces.PIECE;
import pieces.P_TYPE;
import player.PLAYER;

public class GAME_LOOP {

    private final int white_color = 0x794839;
    private final int black_color = 0x5d3231;
    private final JLabel[] menuLabels;
    private JTextArea historyTextArea;
    private final Board board;
    private final JPanel boardPanel;
    private JButton[][] buttons;
    int cellSize;

    private PLAYER whitePlayer;
    private PLAYER blackPlayer;
    private PLAYER currentPlayer;
    private int giveTurn = 0;
    private int btnClicks = 0;
    private int[] movesBuffer;
    private boolean[][] possibleMoves;
    private LinkedList<Cell> lastMoves;

    public GAME_LOOP(Board b, JPanel p, JLabel[] mL, JTextArea h) {
        this.board = b;
        this.boardPanel = p;
        this.menuLabels = mL;
        this.historyTextArea = h;
        this.cellSize = this.boardPanel.getWidth() / this.board.getSize();
        this.lastMoves = new LinkedList<>();
        initButtons();
        initMovesBuffer();
        initPlayers();
        initPossibleMovesSet();
    }

    private void initMovesBuffer() {
        btnClicks = 0;      //reset buffer's button clicks
        this.movesBuffer = new int[4];  //store starting and ending dst of a move.
        Arrays.fill(this.movesBuffer, -1);
    }

    private void initButtons() {
        this.buttons = new JButton[this.board.getSize()][this.board.getSize()];
        for (int i = 0; i < this.board.getSize(); i++) {
            for (int j = 0; j < this.board.getSize(); j++) {
                this.buttons[i][j] = new JButton();
                this.buttons[i][j].setLocation(j * cellSize, i * cellSize);
                this.buttons[i][j].setSize(cellSize, cellSize);
                this.buttons[i][j].setFocusPainted(false);
                this.buttons[i][j].setBorder(null);

                String c = this.board.getColor(i, j);
                this.buttons[i][j].setBackground(c.equals("white") ? new Color(white_color) : new Color(black_color));

//                if(j==0){
//                    this.buttons[i][j].setVerticalTextPosition(SwingConstants.TOP);
//                    this.buttons[i][j].setHorizontalTextPosition(SwingConstants.LEFT);
//                    this.buttons[i][j].setText(""+(i+1));
//                }

                this.buttons[i][j].addActionListener(this::clicked);

                //light 794839
                //dark 5d3231
            }
        }
    }

    private void initPlayers() {
        this.whitePlayer = new PLAYER("white");
        this.blackPlayer = new PLAYER("black");

        this.currentPlayer = this.whitePlayer;
    }

    public void update() {
        firstDraw();
    }

    private void giveTurn() {
        giveTurn++;
        this.currentPlayer = giveTurn % 2 == 0 ? this.whitePlayer : this.blackPlayer;
        //element 1 is the who's turn label
        this.menuLabels[0].setText(this.currentPlayer == this.whitePlayer ? "White's Turn." : "Black's Turn." );
    }

    private void initPossibleMovesSet(){
        this.possibleMoves = new boolean[this.board.getSize()][this.board.getSize()];
        for(int i = 0; i < this.board.getSize(); i++){
            for(int j = 0; j < this.board.getSize(); j++){
                this.possibleMoves[i][j] = false;
            }
        }
    }

    private void move() {
        int sx = movesBuffer[0];
        int sy = movesBuffer[1];
        int ex = movesBuffer[2];
        int ey = movesBuffer[3];

        PIECE p1 = this.board.getPiece(sx, sy);
        PIECE p2 = this.board.getPiece(ex, ey);

        String color = p1.getPieceColor();

        if (p1.movePiece(sx, sy, ex, ey, this.currentPlayer.getPlayerColor(), this.board) && color.equals(this.currentPlayer.getPlayerColor())) {
            this.board.setPiece(ex, ey, p1);         //set piece to destination
            this.board.setPiece(sx, sy, null);    //remove piece from current loc

            this.lastMoves.add(new Cell(sx, sy, p1.getPieceColor(), p1));

            //take piece
            if(p2 != null && !p2.getPieceColor().equals(p1.getPieceColor())){
                this.currentPlayer.addSCORE(p2.getPiecePoints() );

                if (currentPlayer.getPlayerColor().equals("white")) {
                    menuLabels[1].setText("" + currentPlayer.getSCORE());
                } else {
                    menuLabels[2].setText("" + currentPlayer.getSCORE());
                }
            }

            //check for checks :-p
            for(int i=0; i<board.getSize(); i++){
                for(int j=0; j<board.getSize(); j++){
                    PIECE p = board.getPiece(i, j);

                    //only check current player's possible check moves.
                    if(p != null && p.getPieceColor().equals(this.currentPlayer.getPlayerColor())){
                        boolean[][] tr_moves;
                        tr_moves = p.possibleMoves(board, i, j, this.currentPlayer.getPlayerColor());

                        for(int k=0; k< tr_moves.length; k++) {
                            for (int l = 0; l < tr_moves.length; l++) {
                                PIECE _kng = board.getPiece(k, l);

                                if(tr_moves[k][l] && _kng != null ){
                                    if(_kng.getPieceType() == P_TYPE.Types.KING)
                                        menuLabels[3].setText(("" + currentPlayer.getPlayerColor()).equals("white") ?"Black's in Check.":"White's in Check.");
//                                    else
//                                        menuLabels[3].setText("...");
                                }
                            }
                        }
                    }
                }
            }


            giveTurn();                  //give turn to play to other player
            updateDraw(sx, sy, ex, ey);  //refresh draw board
            updateLastMovesList();
        } else {
            System.out.println("ILLEGAL MOVE!");
        }

        initMovesBuffer();
    }

    //A CELL HAS BEEN CLICKED
    private void clicked(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        int y = btn.getX() / this.cellSize;
        int x = btn.getY() / this.cellSize;

        switch (btnClicks) {
            case 0 -> {
                movesBuffer[0] = x;
                movesBuffer[1] = y;
                
                PIECE p1 = this.board.getPiece(x, y);

                if(p1==null){
                    initMovesBuffer();
                    return;
                }
                else if(p1.getPieceColor().equals(this.currentPlayer.getPlayerColor())){
                    //re-initialize possible moves arr and get moves
                    DAW(false);
                    initPossibleMovesSet();
                    this.possibleMoves = p1.possibleMoves(this.board, x, y, currentPlayer.getPlayerColor());
                    DAW(true);
                }
            }

            case 1 -> {
                movesBuffer[2] = x;
                movesBuffer[3] = y;

                //clear DAW, redraw & remove possible moves from board
                DAW(false);
                
                //Player clicked on a piece then decides not to move that one
                PIECE p1 = this.board.getPiece(x, y);
                if(p1 != null && p1.getPieceColor().equals(this.currentPlayer.getPlayerColor()) ){
                    initMovesBuffer();
                    movesBuffer[0] = x;
                    movesBuffer[1] = y;

                    DAW(false);
                    initPossibleMovesSet();
                    this.possibleMoves = p1.possibleMoves(this.board, x, y, this.currentPlayer.getPlayerColor());
                    DAW(true);
                }
            }
        }
        btnClicks++;

        //Moves stored, now move the piece
        if (movesBuffer[3] != -1) {
            move();
        }
    }

    //draw board
    public void firstDraw() {
        for (int i = 0; i < this.board.getSize(); i++) {
            for (int j = 0; j < this.board.getSize(); j++) {

                PIECE p = this.board.getPiece(i, j);

                if (p != null) {
                    ImageIcon icon = new ImageIcon(p.iconLocation());
                    this.buttons[i][j].setIcon(resizeIcon(icon, cellSize - 20, cellSize - 20));
                } else {
                    this.buttons[i][j].setIcon(null);
                }
                this.boardPanel.add(this.buttons[i][j]);
            }
        }
    }
    
    private void updateDraw(int sx, int sy, int ex, int ey){
        PIECE p = this.board.getPiece(ex, ey);
        if (p != null) {
            ImageIcon icon = new ImageIcon(p.iconLocation());
            this.buttons[ex][ey].setIcon(resizeIcon(icon, cellSize - 25, cellSize - 25));
            this.buttons[sx][sy].setIcon(null);
        }
        Cell _lastMove = this.lastMoves.peekLast();
        this.buttons[_lastMove.getRows()][_lastMove.getCols()].setBorder(BorderFactory.createLineBorder(new Color(0x2677D233, true), 50, false));
    }

    private void DAW(boolean flag){
        //flag -> param to redraw & remove possible moves from board. set board to init colors
        //draw all possible moves
        for(int i = 0; i < possibleMoves.length; i++){
            for(int j = 0; j < possibleMoves.length; j++){
                if(this.possibleMoves[i][j] && flag){
                    this.buttons[i][j].setBorder(BorderFactory.createLineBorder(new Color(0xDC9FCBD, true), 10, true));
                }
                else if(!flag){
                    //default color ?
                    this.buttons[i][j].setBackground(this.board.getCell(i, j).getColor().equals("white") ? new Color(white_color) : new Color(black_color) );
                    this.buttons[i][j].setBorder(null);
                }
            }
        }
    }

    private void updateLastMovesList(){

        Cell _lastMove = this.lastMoves.peekLast();

        PIECE p = _lastMove.getPiece();

        this.historyTextArea.append(" "+p.getPieceType()+" -> "+(_lastMove.getRows()+1) +", "+(_lastMove.getRows()+1) + "\n");
    }

    private Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
