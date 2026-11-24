package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*") // Allows index.html to communicate with Spring Boot
public class BlackJackController {

    private final GameService gameService;

    public BlackJackController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/new")
    public GameState startNewGame() {
        return gameService.startNewGame();
    }

    @PostMapping("/hit")
    public GameState hit() {
        return gameService.hit();
    }

    @PostMapping("/stand")
    public GameState stand() {
        return gameService.stand();
    }
}
