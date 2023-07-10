/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package player;

public class PLAYER {
    
    private final String playerColor;
    private int SCORE = 0;
    
    public PLAYER(String p){
        this.playerColor = p;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public int getSCORE() {
        return SCORE;
    }

    public void addSCORE(int SCORE) {
        this.SCORE += SCORE;
    }
}
