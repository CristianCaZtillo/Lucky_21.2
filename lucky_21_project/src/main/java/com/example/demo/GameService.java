package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class GameService {
    private CardDeck deck;
    private int playerCount;
    private int dealerCount;
    private int dealerAces;
    private int playerAces;
    private boolean gameOver;
    private boolean hideDealerHole;
    private String message;
    private String resultClass;

    public GameService() {
        startNewGame();
    }

    public GameState startNewGame() {
        deck = new CardDeck();
        playerCount = 0;
        dealerCount = 0;
        dealerAces = 0;
        playerAces = 0;
        gameOver = false;
        hideDealerHole = true;
        resultClass = "";

        // Initial deal: 2 dealer, 2 player
        dealerDraw();
        dealerDraw();
        dealToPlayer();
        dealToPlayer();

        if (playerCount == 21) {
            hideDealerHole = false;
            gameOver = true;
            message = "Natural Blackjack! You WIN! 🎉";
            resultClass = "win";
        } else {
            message = "Hit or Stand?";
        }

        return getCurrentState();
    }

    public GameState hit() {
        if (gameOver) return getCurrentState();

        dealToPlayer();

        if (playerCount > 21) {
            hideDealerHole = false;
            gameOver = true;
            message = "Bust! Dealer wins.";
            resultClass = "lose";
        } else if (playerCount == 21) {
            return stand(); // Auto-stand on 21
        } else {
            message = "Hit or Stand?";
        }

        return getCurrentState();
    }

    public GameState stand() {
        if (gameOver) return getCurrentState();

        while (dealerCount < 17) {
            dealerDraw();
        }

        hideDealerHole = false;
        gameOver = true;

        if (dealerCount > 21 || playerCount > dealerCount) {
            message = "You win! 🎉";
            resultClass = "win";
        } else if (dealerCount == playerCount) {
            message = "It's a tie.";
            resultClass = "tie";
        } else {
            message = "Dealer wins.";
            resultClass = "lose";
        }

        return getCurrentState();
    }

    private void dealToPlayer() {
        Random rand = new Random();
        int idx = rand.nextInt(deck.deckSize());
        String card = deck.getCard(idx);
        deck.removeCard(idx);

        if (card.startsWith("Ace")) {
            playerCount += 11;
            playerAces++;
        } else {
            playerCount += rankValue(card);
        }

        while (playerCount > 21 && playerAces > 0) {
            playerCount -= 10;
            playerAces--;
        }

        deck.playerAppend(card);
    }

    private void dealerDraw() {
        Random rand = new Random();
        int idx = rand.nextInt(deck.deckSize());
        String card = deck.getCard(idx);
        deck.removeCard(idx);

        if (card.startsWith("Ace")) {
            dealerCount += 11;
            dealerAces++;
        } else {
            dealerCount += rankValue(card);
        }

        while (dealerCount > 21 && dealerAces > 0) {
            dealerCount -= 10;
            dealerAces--;
        }

        deck.dealerAppend(card);
    }

    private int rankValue(String card) {
        String rank = card.split(" ")[0];
        return switch (rank) {
            case "2" -> 2;
            case "3" -> 3;
            case "4" -> 4;
            case "5" -> 5;
            case "6" -> 6;
            case "7" -> 7;
            case "8" -> 8;
            case "9" -> 9;
            case "10", "Jack", "Queen", "King" -> 10;
            default -> 0;
        };
    }

    public GameState getCurrentState() {
        return new GameState(
            ((DynamicArray<String>) deck.playersCards).toList(),
            ((DynamicArray<String>) deck.dealersCards).toList(),
            playerCount,
            dealerCount,
            hideDealerHole,
            gameOver,
            message,
            resultClass
        );
    }
}
