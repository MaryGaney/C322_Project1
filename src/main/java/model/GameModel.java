package model;

import java.util.*;

public class GameModel {
    public static final int ROWS = 5;
    public static final int COLS = 6;

    private Card[][] board;
    private Player[] players;
    private int currentPlayer;

    private Card firstSelection;
    private Card secondSelection;

    private boolean mismatchPending;

    public GameModel() {
        resetGame();
    }

    public void resetGame() {
        players = new Player[] {
            new Player("Player 1"),
            new Player("Player 2")
        };
        currentPlayer = 0;
        mismatchPending = false;
        firstSelection = null;
        secondSelection = null;
        initializeBoard();
    }

    //lookat all the lil guys i found, god i love utf-8
    private void initializeBoard() {
        List<String> emojis = List.of(
            "🌲","🍃","🌸","🌻","🍄",
            "🐝","🦋","🐞","🌼","🌊",
            "🌙","⭐","🔥","🍁","🌵"
        );

        List<String> deck = new ArrayList<>();
        for (String e : emojis) {
            deck.add(e);
            deck.add(e);
        }
        Collections.shuffle(deck);

        board = new Card[ROWS][COLS];
        Iterator<String> it = deck.iterator();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                board[r][c] = new Card(it.next());
            }
        }
    }

    public Card getCard(int r, int c) {
        return board[r][c];
    }

    public Player getCurrentPlayer() {
        return players[currentPlayer];
    }

    public Player[] getPlayers() {
        return players;
    }

    public boolean isMismatchPending() {
        return mismatchPending;
    }

    public boolean isGameOver() {
        for (Card[] row : board) {
            for (Card c : row) {
                if (!c.isMatched()) return false;
            }
        }
        return true;
    }

    public void selectCard(int r, int c) {
        if (mismatchPending) return;

        Card card = board[r][c];
        if (card.isFaceUp() || card.isMatched()) return;

        card.flip();

        if (firstSelection == null) {
            firstSelection = card;
        } else {
            secondSelection = card;
            evaluateTurn();
        }
    }

    private void evaluateTurn() {
        if (firstSelection.getEmoji().equals(secondSelection.getEmoji())) {
            firstSelection.setMatched();
            secondSelection.setMatched();
            players[currentPlayer].incrementScore();
            clearSelections();
        } else {
            mismatchPending = true;
        }
    }

    public void resolveMismatch() {
        if (!mismatchPending) return;

        firstSelection.flip();
        secondSelection.flip();
        currentPlayer = 1 - currentPlayer;
        mismatchPending = false;
        clearSelections();
    }

    private void clearSelections() {
        firstSelection = null;
        secondSelection = null;
    }
}
