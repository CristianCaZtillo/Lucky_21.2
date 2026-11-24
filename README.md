# Lucky21 — Full-Stack Blackjack Web App

**Lucky21** is a full-stack Blackjack web application built with a **Java Spring Boot** REST API backend and a **vanilla HTML/CSS/JavaScript** frontend. Rather than relying solely on standard collections, the game engine manages the 52-card deck and player/dealer hands using custom-built generic data structures ('Array<T>' and 'DynamicArray<T>').

## Features
* **Full Blackjack Ruleset:** Supports initial two-card deals, Hit/Stand actions, natural Blackjack detection, automatic 21 stand, and dealer hit-until-17 logic.
* **Smart Ace Valuation:** Automatically adjusts Ace values between '11' and '1' to prevent busts.
* **Custom Generic Data Structures:** Implements fixed-capacity 'Array<T>' for the 52-card deck and self-resizing 'DynamicArray<T>' for active hands.
* **RESTful Architecture:** Clean separation of concerns across 'BlackJackController', 'GameService', and immutable/DTO 'GameState' snapshots
* **Responsive Card Table UI:** Interactive felt-table interface with animated card dealing, hidden dealer hole cards, and real-time score tracking

## Tech Stack
* **Backend:** Java, Spring Boot ('spring-boot-starter-web')
* **Frontend:** HTML5, CSS3, Vanilla JavaScript ('Fetch API')
* **Build Tool:** Maven

## Project Structure
text
├── src/main/java/com/example/demo/
│   ├── DemoApplication.java       # Spring Boot entry point
│   ├── BlackJackController.java   # REST endpoints (/new, /hit, /stand)
│   ├── GameService.java           # Core Blackjack rules, scoring & dealer AI
│   ├── GameState.java             # JSON response DTO for frontend state
│   ├── CardDeck.java              # 52-card deck initialization & hand tracking
│   ├── Array.java                 # Custom generic fixed-size array
│   └── DynamicArray.java          # Custom generic resizable array
└── src/main/resources/static/
    └── index.html                 # Frontend UI & API client (lucky_21_index.html)
