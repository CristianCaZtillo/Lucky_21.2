package com.example.demo;

import java.util.List;

public class GameState {
    private List<String> playerCards;
    private List<String> dealerCards;
    private int playerCount;
    private int dealerCount;
    private boolean hideDealerHole;
    private boolean gameOver;
    private String message;
    private String resultClass; // "win", "lose", "tie", or ""

    public GameState() {}

    public GameState(List<String> playerCards, List<String> dealerCards, 
                     int playerCount, int dealerCount, boolean hideDealerHole, 
                     boolean gameOver, String message, String resultClass) {
        this.playerCards = playerCards;
        this.dealerCards = dealerCards;
        this.playerCount = playerCount;
        this.dealerCount = dealerCount;
        this.hideDealerHole = hideDealerHole;
        this.gameOver = gameOver;
        this.message = message;
        this.resultClass = resultClass;
    }

    // Getters and Setters
    public List<String> getPlayerCards() { return playerCards; }
    public List<String> getDealerCards() { return dealerCards; }
    public int getPlayerCount() { return playerCount; }
    public int getDealerCount() { return dealerCount; }
    public boolean isHideDealerHole() { return hideDealerHole; }
    public boolean isGameOver() { return gameOver; }
    public String getMessage() { return message; }
    public String getResultClass() { return resultClass; }
}
