package controller;

import model.GameModel;

public class GameController {
    private GameModel model;

    public GameController(GameModel model) {
        this.model = model;
    }

    public void handleCardClick(int row, int col) {
        model.selectCard(row, col);
    }

    public void resolveMismatch() {
        model.resolveMismatch();
    }

    public void resetGame() {
        model.resetGame();
    }
}
