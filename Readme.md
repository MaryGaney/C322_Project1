# Emoji Matching Game (MVC)

Site: https://c322-project1.onrender.com/

## Overview
This project is a standalone Java implementation of a two-player emoji
matching gamee to demonstrate a clean mvc architecture.

Two players take turns flipping cards on a 5×6 board. If two flipped cards
contain the same emoji, the player scores a point and continues their turn (make-it-take-it rules).
Otherwise, the cards flip back and the turn switches.

## Architecture

### Model
The model contains all game state and logic:
- Card values and board layout
- Player turns and scores
- Matching rules

The model is completely independent of any user interface, contains any and all domain logic.

### View
Web View Exists:
- A web-based HTML view used only as a rendering layer
The view never modifies game state.

### Controller
The controller receives user actions (card selections) and updates the
model accordingly. It contains no rendering logic.

### Flow
User action -> Controller -> Model update -> View re-render

## Web Deployment
The game is wrapped with a minimal Java web server.
This wrapper adapts HTTP requests into controller actions without
modifying the core MVC design.

The application is deployed on Render.com as a Java web service.

## Technologies
- Java
- Maven
- Spark Java (web adapter)
- HTML/CSS (view only)

## Running Locally
mvn clean package
java -jar target/emoji-matching-game-1.0.jar
